package com.surepay.auth_presentation.login

sealed class LoginEvent{
    data class OnLoginClick(val email: String, val password: String): LoginEvent()
}
