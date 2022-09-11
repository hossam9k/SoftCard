package com.surepay.auth_domain.exeptions

sealed class LoginException(): Throwable() {
    object UNAUTHORIZED: LoginException()
}