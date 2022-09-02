package com.surepay.auth_data.remote.entity

import com.squareup.moshi.Json

data class LoginData(
    @field:Json(name = "completed")
    val completed: Boolean,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "userId")
    val userId: Int
)