package com.surepay.cards_presentation.pin_verification


data class PinState(
    val pin: String? = "",
    val pinEnabled: Boolean = false,
    val pinError: Boolean = false,
    val pinLoading: Boolean = false,
)



