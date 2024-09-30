package com.komoju.android.sdk.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.komoju.android.sdk.types.Language
import com.komoju.android.sdk.ui.theme.Blue600
import com.komoju.android.sdk.ui.theme.KomojuMobileSdkTheme

@Composable
internal fun PrimaryButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Blue600, contentColor = Color.White),
        shape = RoundedCornerShape(8.dp),
    ) {
        Text(modifier = Modifier.padding(8.dp), text = text, style = TextStyle(fontWeight = FontWeight.Bold), maxLines = 1)
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun PaymentButtonPreview() {
    KomojuMobileSdkTheme(language = Language.ENGLISH) {
        PrimaryButton(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            text = "Pay \$100.00",
        ) {
            println("Pay button clicked")
        }
    }
}
