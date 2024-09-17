package com.degica.komoju.android.sdk.ui.screens.payment.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.degica.komoju.android.sdk.R
import com.degica.komoju.android.sdk.types.Language
import com.degica.komoju.android.sdk.ui.composables.PrimaryButton
import com.degica.komoju.android.sdk.ui.theme.KomojuMobileSdkTheme
import com.degica.komoju.android.sdk.ui.theme.LocalI18nTextsProvider
import com.degica.komoju.mobile.sdk.entities.PaymentMethod

@Composable
internal fun AppPayForm(paymentMethod: PaymentMethod, onPayButtonClicked: () -> Unit) {
    val titleKey = remember(paymentMethod) {
        when (paymentMethod) {
            is PaymentMethod.AliPay -> "PAYMENT_VIA_ALI_PAY"
            is PaymentMethod.AuPay -> "PAYMENT_VIA_AU_PAY"
            is PaymentMethod.LinePay -> "PAYMENT_VIA_LINE_PAY"
            is PaymentMethod.MerPay -> "PAYMENT_VIA_MER_PAY"
            is PaymentMethod.PayPay -> "PAYMENT_VIA_PAY_PAY"
            is PaymentMethod.RakutenPay -> "PAYMENT_VIA_RAKUTEN"
            else -> null
        }
    }

    val messageKey = remember(paymentMethod) {
        when (paymentMethod) {
            is PaymentMethod.AliPay -> "ALI_PAY_REDIRECT_MESSAGE"
            is PaymentMethod.AuPay -> "AU_PAY_REDIRECT_MESSAGE"
            is PaymentMethod.LinePay -> "LINE_PAY_REDIRECT_MESSAGE"
            is PaymentMethod.MerPay -> "MER_PAY_REDIRECT_MESSAGE"
            is PaymentMethod.PayPay -> "PAY_PAY_REDIRECT_MESSAGE"
            is PaymentMethod.RakutenPay -> "RAKUTEN_REDIRECT_MESSAGE"
            else -> null
        }
    }

    val paymentButtonKey = remember(paymentMethod) {
        when (paymentMethod) {
            is PaymentMethod.AliPay -> "CONTINUE_TO_ALI_PAY"
            is PaymentMethod.AuPay -> "CONTINUE_TO_AU_PAY"
            is PaymentMethod.LinePay -> "CONTINUE_TO_LINE_PAY"
            is PaymentMethod.MerPay -> "CONTINUE_TO_MER_PAY"
            is PaymentMethod.PayPay -> "CONTINUE_TO_PAY_PAY"
            is PaymentMethod.RakutenPay -> "CONTINUE_TO_RAKUTEN"
            else -> null
        }
    }

    if (titleKey != null && messageKey != null && paymentButtonKey != null) {
        Column(modifier = Modifier.padding(all = 16.dp)) {
            Text(text = LocalI18nTextsProvider.current[titleKey], style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp))
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = LocalI18nTextsProvider.current[messageKey])
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .background(Color(0xFFF3F7F9), shape = RoundedCornerShape(8.dp))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(R.drawable.komoju_ic_app_opens_info),
                    contentDescription = "app_opens_info",
                    modifier = Modifier.size(32.dp),
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = LocalI18nTextsProvider.current["LIGHT_BOX_CONTENT"])
            }
            Spacer(modifier = Modifier.height(32.dp))
            PrimaryButton(LocalI18nTextsProvider.current[paymentButtonKey], modifier = Modifier.fillMaxWidth(), onPayButtonClicked)
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun AppPayFormPreview() {
    KomojuMobileSdkTheme(Language.ENGLISH) {
        AppPayForm(
            PaymentMethod.LinePay(
                displayName = "LINE Pay",
                hashedGateway = "LINE",
                exchangeRate = 1.0,
                currency = "JPY",
                amount = 100.0,
                additionalFields = listOf(),
                isOffsite = false,
            ),
        ) { }
    }
}
