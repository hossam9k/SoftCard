package com.surepay.auth_domain.exeptions

sealed class EmailValidationException : Throwable() {
    object EmptyEmail: EmailValidationException()
    object InvalidEmail: EmailValidationException()
}