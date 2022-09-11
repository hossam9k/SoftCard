package com.surepay.auth_domain.use_case.utils

fun isEmailNotBlank(email: String): Boolean{
    return email.isNotBlank()
}

fun isPasswordNotBlank(password: String): Boolean{
    return password.isNotBlank()
}