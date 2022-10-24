package com.surepay.cards_presentation.pin_verification

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.surepay.cards_presentation.R
import com.surepay.core_ui.Dimensions
import com.surepay.core_ui.LocalSpacing

@Composable
fun PinVerificationScreen(
    scaffoldState: ScaffoldState,
    onNavigateUp: () -> Unit,
) {

    val spacing: Dimensions = LocalSpacing.current


    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .blur(90.dp)
           // .background(Color.Black.copy(alpha = 0.6f),)
    )
    {
        Spacer(modifier = Modifier.height(spacing.spaceExtraLarge))

        Image(image = R.drawable.ic_check_circle_blue)

    }

}


@Composable
fun Image(image: Int) {
    Image(
        painter = painterResource(
            id = image,
        ),
        contentDescription = "Image",
        contentScale = ContentScale.Fit,

        modifier = Modifier.height(100.dp).width(100.dp)
    )
}