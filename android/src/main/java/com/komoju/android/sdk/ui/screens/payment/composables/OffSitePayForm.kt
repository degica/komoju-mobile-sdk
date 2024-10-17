package com.komoju.android.sdk.ui.screens.payment.composables

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
import com.komoju.android.sdk.R
import com.komoju.android.sdk.ui.composables.PrimaryButton
import com.komoju.android.sdk.ui.theme.KomojuMobileSdkTheme
import com.komoju.android.sdk.ui.theme.LocalI18nTexts
import com.komoju.mobile.sdk.entities.PaymentMethod
import com.komoju.mobile.sdk.types.OffSitePaymentType

@Composable
internal fun OffSitePayForm(paymentMethod: PaymentMethod.OffSitePayment, onPayButtonClicked: () -> Unit) {
    val titleKey = remember(paymentMethod) {
        when (paymentMethod.type) {
            OffSitePaymentType.ALI_PAY -> "PAYMENT_VIA_ALI_PAY"
            OffSitePaymentType.AU_PAY -> "PAYMENT_VIA_AU_PAY"
            OffSitePaymentType.MER_PAY -> "PAYMENT_VIA_MER_PAY"
            OffSitePaymentType.PAY_PAY -> "PAYMENT_VIA_PAY_PAY"
            OffSitePaymentType.RAKUTEN_PAY -> "PAYMENT_VIA_RAKUTEN"
            OffSitePaymentType.LINE_PAY -> "PAYMENT_VIA_LINE_PAY"
            else -> null
        }
    }

    val messageKey = remember(paymentMethod) {
        when (paymentMethod.type) {
            OffSitePaymentType.ALI_PAY -> "ALI_PAY_REDIRECT_MESSAGE"
            OffSitePaymentType.AU_PAY -> "AU_PAY_REDIRECT_MESSAGE"
            OffSitePaymentType.MER_PAY -> "MER_PAY_REDIRECT_MESSAGE"
            OffSitePaymentType.PAY_PAY -> "PAY_PAY_REDIRECT_MESSAGE"
            OffSitePaymentType.RAKUTEN_PAY -> "RAKUTEN_REDIRECT_MESSAGE"
            OffSitePaymentType.LINE_PAY -> "LINE_PAY_REDIRECT_MESSAGE"
            else -> null
        }
    }

    val paymentButtonKey = remember(paymentMethod) {
        when (paymentMethod.type) {
            OffSitePaymentType.ALI_PAY -> "CONTINUE_TO_ALI_PAY"
            OffSitePaymentType.AU_PAY -> "CONTINUE_TO_AU_PAY"
            OffSitePaymentType.MER_PAY -> "CONTINUE_TO_MER_PAY"
            OffSitePaymentType.PAY_PAY -> "CONTINUE_TO_PAY_PAY"
            OffSitePaymentType.RAKUTEN_PAY -> "CONTINUE_TO_RAKUTEN"
            OffSitePaymentType.LINE_PAY -> "CONTINUE_TO_LINE_PAY"
            else -> null
        }
    }

    if (titleKey != null && messageKey != null && paymentButtonKey != null) {
        Column(modifier = Modifier.padding(all = 16.dp)) {
            Text(text = LocalI18nTexts.current[titleKey], style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp))
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = LocalI18nTexts.current[messageKey])
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
                Text(text = LocalI18nTexts.current["LIGHT_BOX_CONTENT"])
            }
            Spacer(modifier = Modifier.height(32.dp))
            PrimaryButton(LocalI18nTexts.current[paymentButtonKey], modifier = Modifier.fillMaxWidth(), onPayButtonClicked)
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun AppPayFormPreview() {
    KomojuMobileSdkTheme {
        OffSitePayForm(
            PaymentMethod.OffSitePayment(
                displayName = "PayPay",
                hashedGateway = "paypay",
                exchangeRate = 1.0,
                currency = "JPY",
                amount = "100",
                additionalFields = listOf(),
                type = OffSitePaymentType.PAY_PAY,
            ),
        ) { }
    }
}
