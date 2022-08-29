package com.surepay.auth_data.remote.entity

import com.squareup.moshi.Json

data class LoginData(
    @field:Json(name = "token")
    val token:String
    )