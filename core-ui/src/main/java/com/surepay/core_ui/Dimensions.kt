package com.surepay.core_ui

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val default: Dp = 0.dp,
    val spaceExtraSmall: Dp = 4.dp,
    val spaceSmall: Dp = 8.dp,
    val spaceMedium: Dp = 16.dp,
    val spaceLarge: Dp = 32.dp,
    val spaceExtraLarge: Dp = 64.dp,
    val space100: Dp = 100.dp,

    val MarginStandard: Dp = 8.dp,
    val MarginDouble: Dp = 16.dp,
    val MarginTreble: Dp = 24.dp,
    val MarginQuad: Dp = 32.dp,

    val borderWidth: Dp = 1.dp,
    val cornerRadius: Dp = 4.dp,
    val MaxTabletWidth: Dp = 512.dp,
    val MaxDialogWidth: Dp = 448.dp,
    val PasswordBoxHeight: Dp = 136.dp,
    val ButtonWidth: Dp = 128.dp,
    val DialogButtonWidth: Dp = 160.dp,
)

val LocalSpacing = compositionLocalOf { Dimensions() }
