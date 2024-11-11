package com.komoju.mobile.sdk.ui.screens.payment.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.komoju.mobile.sdk.ui.icon.Amex
import com.komoju.mobile.sdk.ui.icon.Diners
import com.komoju.mobile.sdk.ui.icon.Jcb
import com.komoju.mobile.sdk.ui.icon.KomojuIcon
import com.komoju.mobile.sdk.ui.icon.Master
import com.komoju.mobile.sdk.ui.icon.Visa
import com.komoju.mobile.sdk.utils.CardScheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun CreditCardSchemeIcons(cardScheme: CardScheme) {
    Row {
        AnimatedVisibility(visible = cardScheme == CardScheme.UNKNOWN) {
            Row {
                Image(painter = rememberVectorPainter(KomojuIcon.Visa), contentDescription = "visa_icon")
                Spacer(Modifier.width(8.dp))
                Image(painter = rememberVectorPainter(KomojuIcon.Master), contentDescription = "mastercard_icon")
                Spacer(Modifier.width(8.dp))
                Image(painter = rememberVectorPainter(KomojuIcon.Amex), contentDescription = "amex_icon")
            }
        }
        AnimatedVisibility(visible = cardScheme == CardScheme.VISA) {
            Image(painter = rememberVectorPainter(KomojuIcon.Visa), contentDescription = "visa_icon")
        }
        AnimatedVisibility(visible = cardScheme == CardScheme.MASTERCARD) {
            Image(painter = rememberVectorPainter(KomojuIcon.Master), contentDescription = "mastercard_icon")
        }
        AnimatedVisibility(visible = cardScheme == CardScheme.AMEX) {
            Image(painter = rememberVectorPainter(KomojuIcon.Amex), contentDescription = "amex_icon")
        }
        AnimatedVisibility(visible = cardScheme == CardScheme.DINERS_CLUB) {
            Image(painter = rememberVectorPainter(KomojuIcon.Diners), contentDescription = "diners_icon")
        }
        AnimatedVisibility(visible = cardScheme == CardScheme.JCB) {
            Image(painter = rememberVectorPainter(KomojuIcon.Jcb), contentDescription = "jcb_icon")
        }
    }
}

@Composable
@Preview
private fun CreditCardSchemeIconsPreview() {
    CreditCardSchemeIcons(CardScheme.UNKNOWN)
}
