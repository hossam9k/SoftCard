package com.surepay.auth_presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.surepay.core.R
import com.surepay.core.util.UiEvent
import com.surepay.core_ui.Dimensions
import com.surepay.core_ui.LocalSpacing
import com.surepay.core_ui.components.ActionButton
import com.surepay.core_ui.components.UnitTextField
import com.surepay.core_ui.theme.SoftCardTheme

@ExperimentalComposeUiApi
@Composable
fun LoginScreen(
    scaffoldState: ScaffoldState,
    navigateToCardsScreen: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
){
    val state = loginViewModel.state
    val context = LocalContext.current




    LaunchedEffect(key1 = true) {
//        state.login?.let {
//            Log.d("LoginTag",it.title)
//        }
//        state.error?.let {
//
//            scaffoldState.snackbarHostState.showSnackbar(
//                message = state.error.asString(context)
//            )
//
//
//        }
        loginViewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.showErrorMessagge -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                is UiEvent.Success -> {
                   // navigateToCardsScreen()
                }
                else -> Unit
            }
        }
    }

    LoginBody(loginViewModel,state)
}

@Composable
fun LoginBody(
    loginViewModel: LoginViewModel,
    state : LoginState,
    spacing: Dimensions = LocalSpacing.current,
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UnitTextField(
            label = { Text(text =stringResource(id = R.string.email_hint)) },
            value = loginViewModel.email,
            onValueChange =
                loginViewModel::onEmailEnter
            ,

        )
        Spacer(modifier = Modifier.height(spacing.spaceExtraLarge))
        UnitTextField(
            label = { Text(text = stringResource(id = R.string.password_hint)) },
            value = loginViewModel.password,
            onValueChange = loginViewModel::onPasswordEnter,
        )
        Spacer(modifier = Modifier.height(spacing.spaceLarge))

        ActionButton(
            text = stringResource(id = R.string.login),
            onClick = { loginViewModel.onEvent(LoginEvent.OnLoginClick) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> CircularProgressIndicator()

        }
    }
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun PreviewLoginScreen(loginViewModel: LoginViewModel = hiltViewModel(),state : LoginState = LoginState()){
    SoftCardTheme(darkTheme = false){
        Scaffold {
            LoginBody(loginViewModel,state)
        }


    }
}