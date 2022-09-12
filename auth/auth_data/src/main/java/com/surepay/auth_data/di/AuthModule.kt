package com.surepay.auth_data.di

import com.surepay.auth_data.BuildConfig
import com.surepay.auth_data.remote.AuthApi
import com.surepay.auth_data.repository.AuthRepositoryImpl
import com.surepay.auth_data.repository.TestAuthRepositoryImpl
import com.surepay.auth_domain.repositpry.AuthRepository
import com.surepay.core.domain.qualifier.TestQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level =  if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(client: OkHttpClient): AuthApi {
        return Retrofit.Builder()
            .baseUrl(AuthApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()

    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        authApi: AuthApi
    ): AuthRepository {
        return AuthRepositoryImpl(
            authApi = authApi
        )
    }

    @TestQualifier
    @Provides
    @Singleton
    fun provideTestAuthRepository(
        authApi: AuthApi
    ): AuthRepository {
        return TestAuthRepositoryImpl(
            authApi = authApi
        )
    }
}