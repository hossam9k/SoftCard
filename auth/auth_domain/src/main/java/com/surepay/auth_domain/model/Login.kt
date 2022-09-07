package com.surepay.auth_domain.model

import com.surepay.core.util.UiText

class Login(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
) {


    sealed class Result {
        data class Success(
            val carbsRatio: Float,
            val proteinRatio: Float,
            val fatRatio: Float
        ): Result()
        data class Error(val message: UiText): Result()
    }
}