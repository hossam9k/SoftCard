package com.surepay.cards_presentation


data class PagerModel(

    val title :String,
    val rating : Float,
    val desc :String,
    val imgUri:Int
)

/**
 * create pager List
 * */

val pagerList = listOf(
    PagerModel(
        "Sitting Pretty",
        4.0f,
        "All the Children in the word are cute and innocent for like this...",
        R.drawable.image_6
    ),
    PagerModel(
        "Love her Expression",
        4.0f,
        "All the Children in the word are cute and innocent for like this...",
        R.drawable.image_6
    ),
    PagerModel(
        "Childhood Superman",
        4.0f,
        "All the Children in the word are cute and innocent for like this...",
        R.drawable.image_6
    ),
    PagerModel(
        "Candle Night At Marina",
        4.0f,
        "All the Children in the word are cute and innocent for like this...",
        R.drawable.image_6
    ),
    PagerModel(
        "Girl with Beautiful smile",
        4.0f,
        "All the Children in the word are cute and innocent for like this...",
        R.drawable.image_6
    ),
    PagerModel(
        "Bath Time",
        4.0f,
        "All the Children in the word are cute and innocent for like this...",
        R.drawable.image_6
    )

)