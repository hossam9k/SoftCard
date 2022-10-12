package com.surepay.cards_presentation.pincode

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.surepay.core.util.UiEvent
import com.surepay.core_ui.Dimensions
import com.surepay.core_ui.LocalSpacing
import com.surepay.core_ui.theme.SoftCardTheme
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun PinScreen(
    scaffoldState: ScaffoldState,
    onNavigateUp: () -> Unit,
    pinViewModel: PinViewModel = hiltViewModel(),


    ){
    val spacing: Dimensions = LocalSpacing.current
    val state = pinViewModel.state
    val context = LocalContext.current


    LaunchedEffect(key1 = true) {
        pinViewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.showErrorMessagge -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                is UiEvent.Success -> {

                }
                else -> Unit
            }
        }
    }




    Spacer(modifier = Modifier.height(spacing.spaceExtraLarge))


PinBody(spacing = spacing, pinViewModel = pinViewModel, pinState = state)
}


@Composable
fun PinBody(
    pinViewModel: PinViewModel,
    pinState: PinState,
    spacing : Dimensions,
){


    Spacer(modifier = Modifier.height(spacing.spaceExtraLarge))



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {

        Spacer(modifier = Modifier.height(spacing.spaceExtraLarge))

        GreetingText(greetingText = "Hello, There")

        Spacer(modifier = Modifier.height(spacing.spaceMedium))

        EnterPinCodeText(text = "Enter here")

        Spacer(modifier = Modifier.weight(1f))

        PinView(state = pinState, pinViewModel = pinViewModel)

        Spacer(modifier = Modifier.height(spacing.spaceMedium))

        MyRow(
            listOf("7", "8", "9"),
            listOf(0.25f, 0.25f, 0.25f),
            pinViewModel
        )
        MyRow(
            listOf("4", "5", "6"),
            listOf(0.25f, 0.25f, 0.25f),
            pinViewModel
        )
        MyRow(
            listOf("1", "2", "3"),
            listOf(0.25f, 0.25f, 0.25f),
            pinViewModel
        )
        MyRow(
            listOf("Face", "0", "Delete"),
            listOf(0.25f, 0.25f, 0.25f),
            pinViewModel
        )
    }
}

@Composable
fun MyRow(
    texts: List<String>,
    weights: List<Float>,
    pinViewModel: PinViewModel
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        for (i in texts.indices) {
            MyButton(
                text = texts[i],
                modifier = Modifier.weight(weights[i]),

                onClick ={
                    if (pinViewModel.pin.length<4){
                        pinViewModel.onPinEnter(it)
                    }

                }
            )
        }
    }
}


@Composable
fun MyButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: (text:String)-> Unit
) {
    Button(
        modifier = modifier
            .padding(4.dp),
        onClick = {
            onClick(text)

        },
        elevation = null,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)

    ) {

        Text(text)
    }
}

@Composable
fun GreetingText(greetingText: String){
    Text(
        text = greetingText,
        style = MaterialTheme.typography.h6,
        color = Color.Black,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun EnterPinCodeText(text: String){
    Text(
        text = text,
        style = MaterialTheme.typography.h6,
        color = Color.Black,
        fontWeight = FontWeight.Normal
    )
}




@Composable
fun PinView(modifier: Modifier = Modifier,
            state:  PinState,
            pinViewModel: PinViewModel){

    val spacing: Dimensions = LocalSpacing.current
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.width(spacing.MaxTabletWidth),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

//            Spacer(modifier = Modifier.height(spacing.MarginStandard))
//                PasswordTextField(
//                    value = pinViewModel.pin,
//                   // onValueChange = pinViewModel::onPinEnter,
//                    label = { Text(text = stringResource(id = com.surepay.core.R.string.pin)) },
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
//                    isError = state.pinError,
//                )

            PinDotsTextField(pinViewModel)

            Spacer(modifier = Modifier.height(spacing.MarginStandard))

        }
    }
}


@Preview
@Composable
fun PreviewPinCode(){
    SoftCardTheme {
        Scaffold {
//            PinBody(
//                hiltViewModel(),
//
//            )

        }
    }

}