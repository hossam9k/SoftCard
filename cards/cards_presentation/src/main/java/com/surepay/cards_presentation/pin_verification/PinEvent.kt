package com.surepay.cards_presentation.pin_verification

sealed class PinEvent{
    data class PinUpdated(val pin: String) : PinEvent()
    object PinUnlockRequested : PinEvent()
    object BiometricUnlock : PinEvent()
}

