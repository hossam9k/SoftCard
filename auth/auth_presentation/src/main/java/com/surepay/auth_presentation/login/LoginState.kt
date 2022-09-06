package com.surepay.auth_presentation.login

import com.surepay.auth_domain.model.Login
import com.surepay.core.util.UiText

data class LoginState(
    val isLoading: Boolean  =  false,
    val error: UiText? = null,
    val login: Login? = null
)
