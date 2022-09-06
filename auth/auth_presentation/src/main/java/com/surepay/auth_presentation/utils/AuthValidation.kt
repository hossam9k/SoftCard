package com.surepay.auth_presentation.utils

fun isEmailNotBlank(email: String): Boolean{
    return email.isNotBlank()
}

fun isPasswordEmpty(password: String): Boolean{
    return password.isNotBlank()
}