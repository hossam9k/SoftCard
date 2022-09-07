package com.surepay.auth_data.repository

import com.google.common.truth.Truth.assertThat
import com.surepay.auth_data.remote.AuthApi
import com.surepay.auth_data.remote.malformedLoginResponse
import com.surepay.auth_data.remote.validLoginResponse
import com.surepay.auth_domain.model.Login
import com.surepay.core.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalCoroutinesApi::class)
class AuthRepositoryImplTest {
    private lateinit var repository: AuthRepositoryImpl
    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var api: AuthApi


    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        okHttpClient = OkHttpClient.Builder()
            .writeTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.SECONDS)
            .build()
        api = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(AuthApi::class.java)
        repository = AuthRepositoryImpl(
            authApi = api
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Login, valid response, returns results`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(validLoginResponse)
        )
        val result: Resource<Login> = repository.login("jhgf","gfd")
        val isSuccess = result is Resource.Success
        assertThat(isSuccess).isTrue()
    }

    @Test
    fun `Login, invalid response, returns results`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(500)
                .setBody(validLoginResponse)
        )
        val result: Resource<Login> = repository.login("jhgf","gfd")
        val isFailure = result is Resource.Error
        assertThat(isFailure).isTrue()
    }

    @Test
    fun `Login, malformed response, returns failure`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(malformedLoginResponse)
        )
        val result = repository.login("banana", "asaw")
        val isFailure = result is Resource.Error

        assertThat(isFailure).isTrue()
    }
}