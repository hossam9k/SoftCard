package com.surepay.cards_presentation.pincode

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.surepay.core_ui.Dimensions
import com.surepay.core_ui.LocalSpacing
import com.surepay.core_ui.theme.SoftCardTheme

@Composable
fun PinCodeScreen(
    scaffoldState: ScaffoldState,
    onNavigateUp: () -> Unit,
){
    val spacing: Dimensions = LocalSpacing.current

    Spacer(modifier = Modifier.height(spacing.spaceExtraLarge))

    PinCodeBody(spacing)

}


@Composable
fun PinCodeBody(
    spacing : Dimensions
){

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

        MyRow(
            listOf("7", "8", "9"),
            listOf(0.25f, 0.25f, 0.25f),
        )
        MyRow(
            listOf("4", "5", "6"),
            listOf(0.25f, 0.25f, 0.25f),
        )
        MyRow(
            listOf("1", "2", "3"),
            listOf(0.25f, 0.25f, 0.25f),
        )
        MyRow(
            listOf("Forgot", "0", "face"),
            listOf(0.25f, 0.25f, 0.25f),
        )
    }
}

@Composable
fun MyRow(
    texts: List<String>,
    weights: List<Float>,
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        for (i in texts.indices) {
            MyButton(
                text = texts[i],
                modifier = Modifier.weight(weights[i]),

                onClick = {
                    print("pinaa$it")
                    Log.d("pina",it)
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

@Preview
@Composable
fun PreviewPinCode(){
    SoftCardTheme {
        Scaffold {
            PinCodeScreen(
                scaffoldState = rememberScaffoldState(),
                onNavigateUp = {

                }
            )
        }
    }

}