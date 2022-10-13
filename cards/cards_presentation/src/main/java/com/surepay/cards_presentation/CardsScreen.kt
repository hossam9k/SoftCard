package com.surepay.cards_presentation

import android.util.Log
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.google.accompanist.pager.*
import com.surepay.core_ui.Dimensions
import com.surepay.core_ui.LocalSpacing
import com.surepay.core_ui.theme.SoftCardTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@Composable
fun CardsScreen(
    scaffoldState: ScaffoldState,
    onNavigateCardDetails: (String, String, String, Int) -> Unit,

    ) {

    val spacing: Dimensions = LocalSpacing.current

    val pagerState = rememberPagerState(
        //pageCount = pagerList.size,
        initialPage = 0
    )
//        ReplaceWith("animateScrollToPage(page = page, pageOffset = pageOffset)")
    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(2000)
            tween<Float>(
                600,
                easing = LinearOutSlowInEasing
            )
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount)
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.height(spacing.space100))


        VerticalPager(pagerState = pagerState, onClick = { cardModel ->
            onNavigateCardDetails(
                cardModel.cardHolderName,
                cardModel.cardExpirationDate,
                cardModel.cardNumber,
                cardModel.cardLogo
            )
        })

        //Horizontal dot indicator
        HorizontalPagerIndicator(
            pagerState = pagerState, modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )

    }

}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun VerticalPager(pagerState: PagerState, onClick: (model: CardModel) -> Unit) {
    VerticalPager(
        count = pagerList.size,
        state = pagerState,
        contentPadding = PaddingValues(top = 40.dp, bottom = 40.dp),
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .fillMaxWidth()
            .height(210.dp),
    ) { page ->
        val cardModel = pagerList[page]

        Card(
            modifier = Modifier
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale

                    }
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )

                }
                .aspectRatio(2.3f),
            backgroundColor = Color.Transparent,
            shape = RoundedCornerShape(15.dp),
            elevation = 10.dp,
            onClick = {
                onClick(cardModel)
            },
            // onNavigateCardDetails(cardModel)

        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(cardModel.cardBackgroundColor)
                //   .aspectRatio(1f)
                // .align(Alignment.Center)
            ) {


                //  CardBackgroundImage(pagerModel)

                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(15.dp, top = 50.dp)
                ) {

                    CardNumberText(cardModel)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        CardExpirationDate(cardModel, Modifier.weight(1f))
                        CardHolderName(cardModel, Modifier.weight(1f))
                    }


                }

            }
        }

    }
}

@Composable
fun CardBackgroundImage(pagerModel: CardModel) {
    Image(
        painter = painterResource(
            id = pagerModel.cardLogo,
        ),
        contentDescription = "Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun CardNumberText(pagerModel: CardModel) {
    Text(
        text = pagerModel.cardNumber,
        style = MaterialTheme.typography.h6,
        color = Color.White,
        fontWeight = FontWeight.Bold
    )
}


@Composable
fun CardExpirationDate(pagerModel: CardModel, modifier: Modifier = Modifier) {
    Text(
        text = pagerModel.cardExpirationDate,
        style = MaterialTheme.typography.body1,
        color = Color.White,
        fontWeight = FontWeight.Normal,
        modifier = modifier
            .padding(0.dp, 8.dp, 0.dp, 0.dp)


    )

}

@Composable
fun CardHolderName(pagerModel: CardModel, modifier: Modifier = Modifier) {
    Text(
        text = pagerModel.cardHolderName,
        style = MaterialTheme.typography.body1,
        color = Color.White,
        fontWeight = FontWeight.Normal,
        modifier = modifier
            .padding(0.dp, 8.dp, 0.dp, 0.dp)
    )
}

@ExperimentalComposeUiApi
@ExperimentalPagerApi
@Preview
@Composable
fun PreviewCardsScreen() {

    SoftCardTheme(darkTheme = false) {
        Scaffold {
            CardsScreen(
                rememberScaffoldState(),
                onNavigateCardDetails = { _, _, _, _ ->

                }
            )
        }


    }
}