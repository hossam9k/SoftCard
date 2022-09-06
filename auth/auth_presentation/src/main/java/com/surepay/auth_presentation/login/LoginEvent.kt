package com.surepay.auth_presentation.login

sealed class LoginEvent{
    object OnLoginClick : LoginEvent()
    object Error : LoginEvent()

}
