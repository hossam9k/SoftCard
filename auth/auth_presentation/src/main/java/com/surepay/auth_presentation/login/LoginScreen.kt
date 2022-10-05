package com.surepay.auth_presentation.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.surepay.core.R
import com.surepay.core.util.UiEvent
import com.surepay.core_ui.Dimensions
import com.surepay.core_ui.LocalSpacing
import com.surepay.core_ui.components.ActionButton
import com.surepay.core_ui.components.UnitTextField
import com.surepay.core_ui.theme.SoftCardTheme
import dagger.hilt.android.lifecycle.HiltViewModel

@ExperimentalComposeUiApi
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
    navigateToCardsScreen: () -> Unit
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
                    navigateToCardsScreen()
                }
                else -> Unit
            }
        }
    }

    LoginBody(loginViewModel,state)
}


@Composable
fun WelcomeText(){
    Text(text = "Soft Card",
        color = Color.Red,
        fontSize = 25.sp,
        style = MaterialTheme.typography.h5,
        fontWeight= FontWeight.Bold,
        //modifier = Modifier.padding(top = 200.dp),

    )
}

@Composable
fun LoginBGImage(){
    Image(painter = painterResource(id = com.surepay.auth_presentation.R.drawable.citi), contentDescription = "logo",
        modifier = Modifier.fillMaxSize())
}

@Composable
fun LoginHeader(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.Blue)
    ) {
        LoginBGImage()

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(15.dp)
        ) {

            WelcomeText()

        }

    }
}

@Composable
fun LoginBody(
    loginViewModel: LoginViewModel,
    state : LoginState,
    spacing: Dimensions = LocalSpacing.current,
){

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
       // modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Top
    ) {
        LoginHeader()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(spacing.spaceMedium),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            // modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Top
        ) {



            EmailTextField(loginViewModel = loginViewModel)
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            PasswordTextField(loginViewModel)
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            LoginButton{
                loginViewModel.onEvent(LoginEvent.OnLoginClick)
            }
        }
        }


    ProgressIndicator(state = state)
}

@Composable
fun ProgressIndicator(state: LoginState){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> CircularProgressIndicator()

        }
    }
}

@Composable
fun EmailTextField(loginViewModel: LoginViewModel){
    UnitTextField(
        label = { Text(text =stringResource(id = R.string.email_hint)) },
        value = loginViewModel.email,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
//                keyboardActions = KeyboardActions(
//                    onNext = {
//
//                    }
//                ),
        onValueChange =
        loginViewModel::onEmailEnter,

        )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PasswordTextField(loginViewModel: LoginViewModel){
    val keyboard = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    UnitTextField(
        label = { Text(text = stringResource(id = R.string.password_hint)) },
        value = loginViewModel.password,
        visualTransformation = if (loginViewModel.passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
                keyboard?.hide()
            }
        ),
        trailingIcon = {
            IconButton(onClick = {
                if (loginViewModel.passwordVisibility)
               loginViewModel.isPasswordVisible(false)
                else
                    loginViewModel.isPasswordVisible(true)
            }) {
                Icon(
                    imageVector = if (loginViewModel.passwordVisibility)
                        Icons.Filled.Visibility
                    else
                        Icons.Filled.VisibilityOff, ""
                )
            }
        },
        onValueChange = loginViewModel::onPasswordEnter,
    )
}

@Composable
fun LoginButton(modifier: Modifier = Modifier,spacing :Dp= LocalSpacing.current.spaceMedium,onLoginClicked :()-> Unit){
    ActionButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(spacing),
            //.align(Alignment.CenterHorizontally),
        text = stringResource(id = R.string.login),
        onClick = onLoginClicked ,
    )
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun PreviewLoginScreen(state : LoginState = LoginState()){
    SoftCardTheme(darkTheme = false){
        Scaffold {
            LoginBody(hiltViewModel(),state)
        }


    }
}