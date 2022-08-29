package com.surepay.auth_data.di

import com.surepay.auth_data.remote.LoginApi
import com.surepay.auth_data.repository.LoginRepositoryImpl
import com.surepay.auth_domain.repositpry.LoginRepository
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
object LoginModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideOpenFoodApi(client: OkHttpClient): LoginApi {
        return Retrofit.Builder()
            .baseUrl(LoginApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()

    }

    @Provides
    @Singleton
    fun provideTrackerRepository(
        authApi: LoginApi
    ): LoginRepository {
        return LoginRepositoryImpl(
            authApi = authApi
        )
    }

}