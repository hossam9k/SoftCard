package com.surepay.core.util

sealed class UiEvent {
    object Success: UiEvent()
    object NavigateUp: UiEvent()
    data class showErrorMessagge(val message: UiText): UiEvent()
}
