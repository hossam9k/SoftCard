package com.surepay.auth_data.remote

import com.surepay.auth_data.remote.entity.LoginData
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

   // @FormUrlEncoded
    @GET("todos/1")
    suspend fun login(
    ): LoginData

    companion object {
        const val BASE_URL = "https://FakeUrl.typicode.com/"
    }
}