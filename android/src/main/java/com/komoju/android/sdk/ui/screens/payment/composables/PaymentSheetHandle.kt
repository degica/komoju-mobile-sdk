package com.komoju.android.sdk.ui.screens.payment.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun PaymentSheetHandle(title: String, onCloseClicked: () -> Unit) {
    Box(modifier = Modifier.padding(top = 16.dp)) {
        Text(
            title,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            style = TextStyle(fontWeight = FontWeight.Bold, color = Color.Black, textAlign = TextAlign.Center, fontSize = 16.sp),
        )
        Image(
            imageVector = Icons.Rounded.Close,
            contentDescription = "Close Payment Sheet",
            modifier = Modifier.align(Alignment.CenterEnd)
                .clickable(
                    indication = ripple(bounded = true, radius = 24.dp),
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = onCloseClicked,
                )
                .padding(16.dp),
        )
    }
}
