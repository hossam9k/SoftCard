package com.surepay.cards_presentation.pin_verification

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Resources
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import com.surepay.core.R


fun Context.getActivity(): FragmentActivity? {
    return when (this) {
        is FragmentActivity -> this
        else -> {
            var context = this
            while (context is ContextWrapper) {
                context = context.baseContext
                if (context is FragmentActivity) return context
            }
            null
        }
    }
}

private val biometricsIgnoredErrors = listOf(
    BiometricPrompt.ERROR_NEGATIVE_BUTTON,
    BiometricPrompt.ERROR_CANCELED,
    BiometricPrompt.ERROR_USER_CANCELED,
    BiometricPrompt.ERROR_NO_BIOMETRICS
)

 fun showBiometricPrompt(context: FragmentActivity,pinViewModel: PinViewModel) {
    val promptInfo = BiometricPrompt.PromptInfo.Builder()
      //  .setTitle(getString("Pin"))
        .setSubtitle(Resources.getSystem().getString(R.string.pin_biometric_prompt_description))
        .setNegativeButtonText(Resources.getSystem().getString(android.R.string.cancel))
        .build()

    val biometricPrompt = BiometricPrompt(
        context,
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(
                errorCode: Int,
                errString: CharSequence
            ) {
                if (errorCode !in biometricsIgnoredErrors) {
                    Toast.makeText(
                        context,
                        Resources.getSystem().getString(R.string.pin_biometric_error, errString),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onAuthenticationSucceeded(
                result: BiometricPrompt.AuthenticationResult
            ) {
               // pinViewModel.onBiometricUnlock()
            }

            override fun onAuthenticationFailed() {
                Toast.makeText(
                    context,
                    R.string.pin_biometric_authentication_error,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    )
    biometricPrompt.authenticate(promptInfo)
}