package com.surepay.core_ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.surepay.core_ui.LocalSpacing


@Composable
fun UnitTextField(
    value: String,
    onValueChange: (String) -> Unit,
//    unit: String,
    label: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(
        color = MaterialTheme.colors.primaryVariant,
        fontSize = 14.sp
    ),
){
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = textStyle,
            label = label,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = modifier.width(spacing.spaceSmall))

    }
}