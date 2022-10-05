package com.surepay.cards_presentation.card_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.surepay.core.R
import com.surepay.core_ui.Dimensions
import com.surepay.core_ui.LocalSpacing
import com.surepay.core_ui.components.ActionButton
import com.surepay.core_ui.theme.SoftCardTheme

@Composable
fun CardDetailsScreen(
    scaffoldState: ScaffoldState,
    cardHolderName: String,
    cardExpirationDate: String,
    cardNumber: String,
    cardLogo: Int,
    onNavigateUp: () -> Unit,
    onNavigatePinCode:  () -> Unit
    ) {

    val spacing: Dimensions = LocalSpacing.current

    Spacer(modifier = Modifier.height(spacing.spaceExtraLarge))

    Column(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .height(200.dp)
                .padding(start = 16.dp, top = 30.dp, end = 16.dp),
            backgroundColor = Color.Transparent,
            shape = RoundedCornerShape(15.dp),
            elevation = 4.dp,
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Blue)
                //   .aspectRatio(1f)
                // .align(Alignment.Center)
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(30.dp, top = 50.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    print("cardNumber $cardNumber")
                    CardNumberText(cardNumber)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        CardExpirationDate(Modifier.weight(1f))
                        CardHolderName(Modifier.weight(1f))
                    }


                }


            }

        }
        Spacer(modifier = Modifier.weight(1f))

        ProceedButton(Modifier) {
            onNavigatePinCode()
        }
    }



}

@Composable
fun CardDetails(modifier: Modifier =  Modifier) {
    Card(
        modifier = modifier
            .padding(start = 16.dp, top = 30.dp, end = 16.dp),
        backgroundColor = Color.Transparent,
        shape = RoundedCornerShape(15.dp),
        elevation = 4.dp,
    ) {


    }
}

@Composable
fun CardNumberText(cardNumber: String) {
    Text(
        text = cardNumber,
        style = MaterialTheme.typography.h6,
        color = Color.White,
        fontWeight = FontWeight.Bold
    )
}


@Composable
fun CardExpirationDate(modifier: Modifier = Modifier) {
    Text(
        text = "20/22",
        style = MaterialTheme.typography.body1,
        color = Color.White,
        fontWeight = FontWeight.Normal,
        modifier = modifier
            .padding(0.dp, 8.dp, 0.dp, 0.dp)


    )

}

@Composable
fun CardHolderName(modifier: Modifier = Modifier) {
    Text(
        text = "Hossam Atef",
        style = MaterialTheme.typography.body1,
        color = Color.White,
        fontWeight = FontWeight.Normal,
        modifier = modifier
            .padding(0.dp, 8.dp, 0.dp, 0.dp)
    )
}

@Composable
fun ProceedButton(modifier: Modifier = Modifier,spacing : Dp = LocalSpacing.current.spaceMedium, onLoginClicked :()-> Unit){
    ActionButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = LocalSpacing.current.spaceLarge, start = spacing, end = spacing),
        //.align(Alignment.CenterHorizontally),
        text = stringResource(id = R.string.proceed),
        onClick = onLoginClicked ,
    )
}

@Preview
@Composable
fun PreviewCardDetails() {
    SoftCardTheme {
        Scaffold {
             CardDetailsScreen(
                 cardHolderName = "Hossam Atef",
                 cardExpirationDate = "2/22",
                 cardNumber = "1129371792487192874",
                 cardLogo = 2,
                 onNavigateUp = {
                 },
                 scaffoldState = rememberScaffoldState(),
                 onNavigatePinCode = {}
             )
        }
    }
}