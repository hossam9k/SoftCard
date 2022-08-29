package com.surepay.auth_data.remote

import com.surepay.auth_data.remote.entity.LoginData
import retrofit2.http.Field
import retrofit2.http.POST

interface LoginApi {

    @POST("")
    suspend fun login(
        @Field("email") email: String,
        @Field("page") password: String
    ): LoginData

    companion object {
        const val BASE_URL = "https://..."
    }
}