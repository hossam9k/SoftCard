package com.surepay.cards_presentation

import android.graphics.PorterDuff
import android.widget.RatingBar
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import com.google.accompanist.pager.HorizontalPager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.google.accompanist.pager.*
import com.surepay.core_ui.theme.SoftCardTheme
import kotlin.math.absoluteValue

@ExperimentalComposeUiApi
@ExperimentalPagerApi
@Composable
fun CardsScreen(){

    val pagerState  = rememberPagerState(
        //pageCount = pagerList.size,
        initialPage =  0
    )

//    LaunchedEffect(Unit){
//        while (true){
//            yield()
//            delay(2000)
//            pagerState.animateScrollToPage(
//                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
//                animationSpec = tween(600)
//            )
//        }
//    }

    Column( modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
//        Column(modifier = Modifier
//            .height(50.dp)
//            .fillMaxWidth()
//            .background(color = Orange),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Text(text = "View Pager Slide",
//                color = Color.White,
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold
//            )
//
//        }

        Spacer(modifier = Modifier.height(100.dp))
        HorizontalPager(
            count = pagerList.size,
            state = pagerState,
            // the more you increae the end padding the more
            // content of your other page will show
            // Add 32.dp horizontal padding to 'center' the pages
            contentPadding = PaddingValues(start = 65.dp,end = 65.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) { page ->
            Card(modifier = Modifier
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
                .aspectRatio(1.5f),
                backgroundColor = Color.Transparent,
                shape = RoundedCornerShape(15.dp),
                elevation = 10.dp
            ) {

                val newModels = pagerList[page]
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(newModels.cardBackgroundColor)
                       // .align(Alignment.Center)
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(newModels.cardBackgroundColor)
                        // .align(Alignment.Center)
                    )
                    Image(
                        painter = painterResource(
                            id = newModels.cardLogo,
                        ),
                                contentDescription = "Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(15.dp, top = 50.dp)
                    ) {

                        Text(
                            text = newModels.cardNumber,
                            style = MaterialTheme.typography.h6,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )


//                        AndroidView(
//                            factory = { ratingBar },
//                            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
//                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = newModels.cardExpirationDate,
                                style = MaterialTheme.typography.body1,
                                color = Color.White,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                                    .weight(1f)

                            )

                            Text(
                                text = newModels.cardHolderName,
                                style = MaterialTheme.typography.body1,
                                color = Color.White,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                                    .weight(1f)
                            )
                        }



                    }

                }
            }

        }

        //Horizontal dot indicator
        HorizontalPagerIndicator(
            pagerState = pagerState,modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )

    }

}

@ExperimentalComposeUiApi
@ExperimentalPagerApi
@Preview
@Composable
fun PreviewCardsScreen(){
    SoftCardTheme(darkTheme = false){
        Scaffold {
            CardsScreen()
        }


    }
}