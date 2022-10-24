package com.surepay.cards_presentation.pin_verification

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.surepay.cards_presentation.R
import com.surepay.core.util.UiEvent
import com.surepay.core_ui.Dimensions
import com.surepay.core_ui.LocalSpacing
import com.surepay.core_ui.theme.SoftCardTheme

@Composable
fun PinScreen(
    scaffoldState: ScaffoldState,
    onNavigateUp: () -> Unit,
    onNavigatePinVerification: () -> Unit,
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
                    onNavigatePinVerification()


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

        HeaderText(headerText = "Hello, There")

        Spacer(modifier = Modifier.height(spacing.spaceMedium))

        SubHeaderText(text = "Enter here")

        Spacer(modifier = Modifier.weight(1f))

        PinView(state = pinState, pinViewModel = pinViewModel)

        Spacer(modifier = Modifier.height(spacing.spaceMedium))

        PinRow(
            arrayOf(PinPad.SEVEN, PinPad.EIGHT, PinPad.NINE),
            listOf(0.25f, 0.25f, 0.25f),
            pinViewModel
        )
        PinRow(
            arrayOf(PinPad.FOUR, PinPad.FIVE, PinPad.SIX),
            listOf(0.25f, 0.25f, 0.25f),
            pinViewModel
        )
        PinRow(
            arrayOf(PinPad.ONE, PinPad.TOW, PinPad.THREE),
            listOf(0.25f, 0.25f, 0.25f),
            pinViewModel
        )
        PinRow(
            arrayOf(PinPad.BIOMETRIC, PinPad.ZERO, PinPad.DELETE),
            listOf(0.25f, 0.25f, 0.25f),
            pinViewModel
        )
    }
}


@Composable
fun PinRow(
    buttons: Array<PinPad>,
    weights: List<Float>,
    pinViewModel: PinViewModel
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        for (i in buttons.indices) {
            when(buttons[i]){
                PinPad.BIOMETRIC ->{
                    PinPadImageButton(
                        pin =buttons[i] ,
                        painter = painterResource(R.drawable.ic_remove),
                        modifier = Modifier.weight(weights[i]).height(50.dp),
                        onClick ={
                            Log.d("buttonIndex", "$it $i ${pinViewModel.pin}")
                            print("buttonIndex $it $i ${pinViewModel.pin}")
                            if (pinViewModel.pin.isNotEmpty()){
                                pinViewModel.onPinDelete()
                            }
                        } )
                }
                PinPad.DELETE ->{
                    PinPadImageButton(
                        pin =buttons[i],
                        painter = painterResource(R.drawable.ic_fingerprint),
                        modifier = Modifier.weight(weights[i]).height(50.dp),
                        onClick ={
                            Log.d("buttonIndex", "$it $i ${pinViewModel.pin}")
                            print("buttonIndex $it $i ${pinViewModel.pin}")
                            if (pinViewModel.pin.isNotEmpty()){
                                pinViewModel.onPinDelete()
                            }
                        } )
                }
                else->{
                    PinPadNumberButton(
                        pin = buttons[i] ,
                        modifier = Modifier.weight(weights[i]),
                        onClick ={
                            if (pinViewModel.pin.length<4){
                                pinViewModel.onPinEnter(it.toString())
                            }

                        }
                    )
                }
            }
        }
    }
}

@Composable
fun PinPadNumberButton(
    pin: PinPad,
    modifier: Modifier = Modifier,
    onClick: (text:Int)-> Unit
) {
    Button(
        modifier = modifier
            .padding(4.dp),
        onClick = {
            onClick(pin.value)

        },
        elevation = null,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)

    ) {

        Text(pin.value.toString())
    }
}

@Composable
fun PinPadImageButton(
    pin: PinPad,
    modifier: Modifier = Modifier,
    painter: Painter,
    onClick: (text:Int)-> Unit
) {

    Image(
        modifier = modifier
            .padding(4.dp)
            .clickable {
                onClick(pin.value)
            },
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Fit,
    )
}

@Composable
fun HeaderText(headerText: String){
    Text(
        text = headerText,
        style = MaterialTheme.typography.h6,
        color = Color.Black,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun SubHeaderText(text: String){
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