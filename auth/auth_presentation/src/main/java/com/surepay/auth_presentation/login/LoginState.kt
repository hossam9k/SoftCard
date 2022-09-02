package com.surepay.auth_presentation.login

import com.surepay.auth_domain.model.Login

data class LoginState(
    val isLoading: Boolean  =  false,
    val error: String? = null,
    val login: Login? = null
)
