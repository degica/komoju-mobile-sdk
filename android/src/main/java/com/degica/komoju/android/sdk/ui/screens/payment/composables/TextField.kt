package com.degica.komoju.android.sdk.ui.screens.payment.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.degica.komoju.android.sdk.ui.theme.Gray200
import com.degica.komoju.android.sdk.ui.theme.Gray500
import com.degica.komoju.android.sdk.ui.theme.LocalI18nTextsProvider

@Composable
internal fun TextField(value: String, titleKey: String, placeholderKey: String, onValueChange: (String) -> Unit, keyboardType: KeyboardType = KeyboardType.Unspecified) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))
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
                .border(1.dp, Gray200, shape = RoundedCornerShape(8.dp))
                .padding(16.dp),
        ) {
            BasicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            )
            if (value.isEmpty()) {
                Text(
                    text = LocalI18nTextsProvider.current[placeholderKey],
                    style = TextStyle(fontSize = 16.sp, color = Gray500),
                )
            }
        }
    }
}
