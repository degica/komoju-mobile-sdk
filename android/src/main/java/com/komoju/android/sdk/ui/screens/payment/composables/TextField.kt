package com.komoju.android.sdk.ui.screens.payment.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.komoju.android.sdk.ui.theme.Gray200
import com.komoju.android.sdk.ui.theme.Gray500
import com.komoju.android.sdk.ui.theme.LocalI18nTextsProvider
import com.komoju.android.sdk.ui.theme.Red600

@Composable
internal fun TextField(
    value: String,
    titleKey: String,
    placeholderKey: String,
    onValueChange: (String) -> Unit,
    error: String? = null,
    keyboardType: KeyboardType = KeyboardType.Unspecified,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.Unspecified,
) {
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = LocalI18nTextsProvider.current[titleKey],
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .border(1.dp, if (error != null) Red600 else Gray200, shape = RoundedCornerShape(8.dp))
                .padding(16.dp),
        ) {
            BasicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType, capitalization = capitalization),
            )
            if (value.isEmpty()) {
                Text(
                    text = LocalI18nTextsProvider.current[placeholderKey],
                    style = TextStyle(fontSize = 16.sp, color = Gray500),
                )
            }
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = LocalI18nTextsProvider.current[error.orEmpty()],
            style = TextStyle(fontSize = 16.sp, color = Red600),
        )
    }
}
