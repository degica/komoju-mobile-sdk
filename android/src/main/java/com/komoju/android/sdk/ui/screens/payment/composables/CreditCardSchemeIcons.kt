package com.komoju.android.sdk.ui.screens.payment.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.komoju.android.sdk.R
import com.komoju.android.sdk.utils.CardScheme

@Composable
internal fun CreditCardSchemeIcons(cardScheme: CardScheme) {
    Row {
        AnimatedVisibility(visible = cardScheme == CardScheme.UNKNOWN) {
            Row {
                Image(painter = painterResource(id = R.drawable.komoju_ic_visa), contentDescription = "visa_icon")
                Spacer(Modifier.width(8.dp))
                Image(painter = painterResource(id = R.drawable.komoju_ic_master), contentDescription = "mastercard_icon")
                Spacer(Modifier.width(8.dp))
                Image(painter = painterResource(id = R.drawable.komoju_ic_amex), contentDescription = "amex_icon")
            }
        }
        AnimatedVisibility(visible = cardScheme == CardScheme.VISA) {
            Image(painter = painterResource(id = R.drawable.komoju_ic_visa), contentDescription = "visa_icon")
        }
        AnimatedVisibility(visible = cardScheme == CardScheme.MASTERCARD) {
            Image(painter = painterResource(id = R.drawable.komoju_ic_master), contentDescription = "mastercard_icon")
        }
        AnimatedVisibility(visible = cardScheme == CardScheme.AMEX) {
            Image(painter = painterResource(id = R.drawable.komoju_ic_amex), contentDescription = "amex_icon")
        }
        AnimatedVisibility(visible = cardScheme == CardScheme.DINERS_CLUB) {
            Image(painter = painterResource(id = R.drawable.komoju_ic_diners), contentDescription = "diners_icon")
        }
        AnimatedVisibility(visible = cardScheme == CardScheme.JCB) {
            Image(painter = painterResource(id = R.drawable.komoju_ic_jcb), contentDescription = "jcb_icon")
        }
    }
}

@Composable
@Preview
private fun CreditCardSchemeIconsPreview() {
    CreditCardSchemeIcons(CardScheme.UNKNOWN)
}
