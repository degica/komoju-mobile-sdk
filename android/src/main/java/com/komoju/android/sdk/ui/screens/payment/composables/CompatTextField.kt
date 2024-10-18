package com.komoju.android.sdk.ui.screens.payment.composables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.unit.dp
import com.komoju.android.sdk.ui.theme.Gray200
import com.komoju.android.sdk.ui.theme.LocalConfigurableTheme
import com.komoju.android.sdk.ui.theme.LocalI18nTexts
import com.komoju.android.sdk.ui.theme.Red600

@Composable
internal fun CompatTextField(
    modifier: Modifier = Modifier,
    value: String,
    titleKey: String,
    placeholderKey: String,
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
            Text(text = LocalI18nTexts.current[titleKey])
        },
        placeholder = {
            Text(text = LocalI18nTexts.current[placeholderKey])
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
                focusedLabelColor = Color(configurableTheme.primaryButtonColor),
                focusedIndicatorColor = Color(configurableTheme.primaryButtonColor),
                unfocusedIndicatorColor = Gray200,
            ),
    )
//    Column {
//        Text(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp),
//            text = LocalI18nTexts.current[titleKey],
//        )
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp, vertical = 8.dp)
//                .border(1.dp, if (error != null) Red600 else Gray200, shape = RoundedCornerShape(8.dp))
//                .padding(16.dp),
//        ) {
//            BasicTextField(
//                modifier = Modifier.fillMaxWidth(),
//                value = value,
//                onValueChange = onValueChange,
//                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
//                singleLine = true,
//                keyboardOptions = KeyboardOptions(keyboardType = keyboardType, capitalization = capitalization),
//            )
//            if (value.isEmpty()) {
//                Text(
//                    text = LocalI18nTexts.current[placeholderKey],
//                    style = TextStyle(fontSize = 16.sp, color = Gray500),
//                )
//            }
//        }
//        Text(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp),
//            text = LocalI18nTexts.current[error.orEmpty()],
//            style = TextStyle(fontSize = 16.sp, color = Red600),
//        )
//    }
}
