package com.surepay.cards_presentation.pincode

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surepay.core.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val MinPinLength = 4
private const val MaxPinLength = 4
private const val CorrectPin = "1234"

class PinViewModel @Inject constructor() : ViewModel()  {


    private val unlockPin = CorrectPin

    var state by mutableStateOf(PinState())
        private set

    var pin by mutableStateOf("")
        private set

    fun onPinEnter(pin: String) {
        this.pin += pin
    }

    fun onPinDelete(){
       this.pin = this.pin.dropLast(1)
        print("DELETE $pin")
    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: PinEvent) {
        when (event) {
            is PinEvent.PinUpdated -> {
                executePin(pin)
            }
            is PinEvent.BiometricUnlock -> {

            }
            is PinEvent.PinUnlockRequested -> {

            }
        }

    }

    private fun executePin(pin: String) {

        viewModelScope.launch {
            state = state.copy(
                pinLoading =  true,
                pinError = false
            )

        }

    }

}