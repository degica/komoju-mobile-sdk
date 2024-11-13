package com.komoju.mobile.sdk.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.komoju.mobile.sdk.ui.theme.KomojuMobileSdkTheme
import com.komoju.mobile.sdk.ui.theme.LocalConfigurableTheme
import com.komoju.mobile.sdk.ui.theme.toColor
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun PrimaryButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    val configurableTheme = LocalConfigurableTheme.current
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = configurableTheme.primaryColor.toColor(),
            contentColor = configurableTheme.primaryContentColor.toColor(),
        ),
        shape = RoundedCornerShape(configurableTheme.primaryShapeCornerRadiusInDp.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(38.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(modifier = Modifier.padding(8.dp), text = text, style = TextStyle(fontWeight = FontWeight.Bold), maxLines = 1)
        }
    }
}

@Composable
@Preview
private fun PaymentButtonPreview() {
    KomojuMobileSdkTheme {
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
