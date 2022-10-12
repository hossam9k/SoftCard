package com.surepay.cards_presentation.pincode

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.surepay.core_ui.theme.SoftCardTheme

@Composable
fun PinDotsTextField(pinViewModel:  PinViewModel) {

    val charLimit = 4


    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .width(
                    200.dp,
                ),
        ) {

                BasicTextField(
                    value = pinViewModel.pin,
                    enabled = pinViewModel.state.pinEnabled,
                    onValueChange = {
//                        pinViewModel.onPinEnter(it)
//                        if (pinViewModel.pin.length==4) return@BasicTextField
//
                    },
                    singleLine = true,
                    textStyle = TextStyle(
                        color = Transparent,
                    ),
                    cursorBrush = SolidColor(Transparent),
                    modifier = Modifier
                        .clip(CircleShape)
                        .fillMaxWidth()
                        .background(Transparent)
                        .padding(16.dp),
                )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .clip(CircleShape)
                    .fillMaxWidth()
                    .background(Transparent)
                    .padding(16.dp),
            ) {
                for (i in 0 until charLimit) {
                    if (i < pinViewModel.pin.length) {
                        Dot(Blue)
                    } else {
                        Dot(Gray)
                    }
                }
            }
        }

    }
}

// This can be replaced with any composable as per requirement.
@Composable
fun Dot(
    color: Color,
) {
    Box(
        modifier = Modifier
            .requiredSize(
                size = 16.dp,
            )
            .background(
                color = color,
                shape = CircleShape,
            ),
    )
}


@Preview
@Composable
fun PreviewDots(){
    SoftCardTheme {
        Scaffold {
            //PinDotsTextField()

        }
    }

}
