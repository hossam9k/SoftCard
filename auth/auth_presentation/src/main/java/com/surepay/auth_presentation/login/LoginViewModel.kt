package com.surepay.auth_presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surepay.auth_domain.exeptions.EmailValidationException
import com.surepay.auth_domain.use_case.LoginUseCase
import com.surepay.core.R
import com.surepay.core.util.Resource
import com.surepay.core.util.UiEvent
import com.surepay.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var passwordVisibility by mutableStateOf(false)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onEmailEnter(email: String) {
        this.email = email
    }

    fun isPasswordVisible(passwordVisibility: Boolean) {
        this.passwordVisibility = passwordVisibility
    }

    fun onPasswordEnter(password: String) {
        this.password = password
    }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnLoginClick -> {
                executeLogin(email, password)
            }
            LoginEvent.Error -> {}
        }

    }

    private fun executeLogin(email: String, password: String) {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            when (val result = loginUseCase(email, password)) {
                is Resource.Error<*> -> {
                    if (result.error is EmailValidationException.InvalidEmail) {
                        state = state.copy(
                            isLoading = false,
                            error = UiText.StringResource(R.string.not_valid_email)
                        )
                        _uiEvent.send(

                            UiEvent.showErrorMessagge(
                                UiText.StringResource(R.string.not_valid_email)
                            )
                        )
                    }

                    if (result.error is EmailValidationException.EmptyEmail) {
                        state = state.copy(
                            isLoading = false,
                            error = UiText.StringResource(R.string.email_password_error)
                        )
                        _uiEvent.send(

                            UiEvent.showErrorMessagge(
                                UiText.StringResource(R.string.email_password_error)
                            )
                        )
                    }

                }
                is Resource.Success -> {
                    state = state.copy(
                        login = result.data,
                        isLoading = false,
                        error = null
                    )
                    _uiEvent.send(UiEvent.Success)
                }
            }
        }
    }

}