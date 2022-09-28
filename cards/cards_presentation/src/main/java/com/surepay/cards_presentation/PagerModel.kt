package com.surepay.cards_presentation

import androidx.compose.ui.graphics.Color


data class PagerModel(

    val cardHolderName :String,
    val cardExpirationDate : String,
    val cardNumber :String,
    val cardLogo:Int,
    val cardBackgroundColor: Color

)

/**
 * create pager List
 * */

val pagerList = listOf(
    PagerModel(
        "Hossam Atef",
        "1/22",
        "093863363632676",
        R.drawable.image_6,
        Color.Red
    ),
    PagerModel(
        "Hossam Atef",
        "1/22",
        "093863363632676",
        R.drawable.image_6,
        Color.Magenta
    ),
    PagerModel(
        "Hossam Atef",
        "1/22",
        "093863363632676",
        R.drawable.image_6,
        Color.Yellow
    ),
    PagerModel(
        "Hossam Atef",
        "1/22",
        "093863363632676",
        R.drawable.image_6,
        Color.Green
    ),
    PagerModel(
        "Hossam Atef",
        "1/22",
        "093863363632676",
        R.drawable.image_6,
        Color.Black
    ),
    PagerModel(
        "Hossam Atef",
        "1/22",
        "093863363632676",
        R.drawable.image_6,
        Color.Blue
    )

)