package com.surepay.cards_presentation

import androidx.compose.ui.graphics.Color


data class CardModel(

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
    CardModel(
        "Hossam Atef",
        "122",
        "093863363632676",
        R.drawable.image_6,
        Color.Red
    ),
    CardModel(
        "Hossam sfsdfcs",
        "122",
        "093863363632676",
        R.drawable.image_6,
        Color.Magenta
    ),
    CardModel(
        "Hossam Atef",
        "122",
        "093863363632676",
        R.drawable.image_6,
        Color.Cyan
    ),
    CardModel(
        "Hossam Atef",
        "122",
        "093863363632676",
        R.drawable.image_6,
        Color.Green
    ),
    CardModel(
        "Hossam Atef",
        "122",
        "093863363632676",
        R.drawable.image_6,
        Color.Black
    ),
    CardModel(
        "Hossam Atef",
        "122",
        "093863363632676",
        R.drawable.image_6,
        Color.Blue
    )

)