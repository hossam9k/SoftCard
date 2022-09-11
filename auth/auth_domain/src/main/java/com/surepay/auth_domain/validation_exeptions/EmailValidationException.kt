package com.surepay.auth_domain.validation_exeptions

sealed class EmailValidationException(): Throwable() {
    object EmptyEmail: EmailValidationException()
    object InvalidEmail: EmailValidationException()
}