package com.surepay.cards_presentation.pincode

import com.surepay.core_ui.mvi.Event
import com.surepay.core_ui.mvi.MviIntent
import com.surepay.core_ui.mvi.ReduceAction
import com.surepay.core_ui.mvi.State

sealed class PinEvent{
    data class PinUpdated(val pin: String) : PinEvent()
    object PinUnlockRequested : PinEvent()
    object BiometricUnlock : PinEvent()
}

