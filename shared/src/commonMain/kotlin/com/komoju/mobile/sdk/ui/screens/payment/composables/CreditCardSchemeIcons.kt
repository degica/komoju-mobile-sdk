package com.komoju.mobile.sdk.ui.screens.payment.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import komoju_mobile_sdk.shared.generated.resources.Res
import com.komoju.mobile.sdk.utils.CardScheme
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_amex
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_diners
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_jcb
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_master
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_visa
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun CreditCardSchemeIcons(cardScheme: CardScheme) {
    Row {
        AnimatedVisibility(visible = cardScheme == CardScheme.UNKNOWN) {
            Row {
                Image(painter = painterResource(Res.drawable.komoju_ic_visa), contentDescription = "visa_icon")
                Spacer(Modifier.width(8.dp))
                Image(painter = painterResource(Res.drawable.komoju_ic_master), contentDescription = "mastercard_icon")
                Spacer(Modifier.width(8.dp))
                Image(painter = painterResource(Res.drawable.komoju_ic_amex), contentDescription = "amex_icon")
            }
        }
        AnimatedVisibility(visible = cardScheme == CardScheme.VISA) {
            Image(painter = painterResource(Res.drawable.komoju_ic_visa), contentDescription = "visa_icon")
        }
        AnimatedVisibility(visible = cardScheme == CardScheme.MASTERCARD) {
            Image(painter = painterResource(Res.drawable.komoju_ic_master), contentDescription = "mastercard_icon")
        }
        AnimatedVisibility(visible = cardScheme == CardScheme.AMEX) {
            Image(painter = painterResource(Res.drawable.komoju_ic_amex), contentDescription = "amex_icon")
        }
        AnimatedVisibility(visible = cardScheme == CardScheme.DINERS_CLUB) {
            Image(painter = painterResource(Res.drawable.komoju_ic_diners), contentDescription = "diners_icon")
        }
        AnimatedVisibility(visible = cardScheme == CardScheme.JCB) {
            Image(painter = painterResource(Res.drawable.komoju_ic_jcb), contentDescription = "jcb_icon")
        }
    }
}

@Composable
@Preview
private fun CreditCardSchemeIconsPreview() {
    CreditCardSchemeIcons(CardScheme.UNKNOWN)
}
