package com.komoju.mobile.sdk.ui.screens.payment.composables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.unit.dp
import com.komoju.mobile.sdk.ui.theme.Gray200
import com.komoju.mobile.sdk.ui.theme.LocalConfigurableTheme
import com.komoju.mobile.sdk.ui.theme.Red600
import com.komoju.mobile.sdk.ui.theme.toColor

@Composable
internal fun CompatTextField(
    modifier: Modifier = Modifier,
    value: String,
    title: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    error: String? = null,
    keyboardType: KeyboardType = KeyboardType.Unspecified,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.Unspecified,
    keyBoardLocale: Locale = Locale.current,
    imeActions: ImeAction = ImeAction.Unspecified,
    singleLine: Boolean = false,
) {
    val configurableTheme = LocalConfigurableTheme.current
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(8.dp),
        label = {
            Text(text = title)
        },
        placeholder = {
            Text(text = placeholder)
        },
        isError = error != null,
        singleLine = singleLine,
        supportingText = {
            if (error != null) {
                Text(text = error, color = Red600)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            capitalization = capitalization,
            hintLocales = LocaleList(keyBoardLocale),
            imeAction = imeActions,
        ),
        colors = OutlinedTextFieldDefaults.colors()
            .copy(
                focusedLabelColor = configurableTheme.primaryColor.toColor(),
                focusedIndicatorColor = configurableTheme.primaryColor.toColor(),
                unfocusedIndicatorColor = Gray200,
            ),
    )
}
