package com.surepay.cards_presentation.pincode


data class PinState(
    val pin: String? = "",
    val pinEnabled: Boolean = false,
    val pinError: Boolean = false,
    val pinLoading: Boolean = false,
)



