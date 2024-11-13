package com.komoju.mobile.sdk.ui.screens.payment.composables

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
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.komoju.mobile.sdk.entities.PaymentMethod
import com.komoju.mobile.sdk.i18n.I18nStringKey
import com.komoju.mobile.sdk.i18n.i18nStringResource
import com.komoju.mobile.sdk.types.OffSitePaymentType
import com.komoju.mobile.sdk.ui.composables.PrimaryButton
import com.komoju.mobile.sdk.ui.icon.AppOpensInfo
import com.komoju.mobile.sdk.ui.icon.KomojuIcon
import com.komoju.mobile.sdk.ui.theme.KomojuMobileSdkTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun OffSitePayForm(paymentMethod: PaymentMethod.OffSitePayment, onPayButtonClicked: () -> Unit) {
    val titleKey = remember(paymentMethod) {
        when (paymentMethod.type) {
            OffSitePaymentType.ALI_PAY -> I18nStringKey.payment_via_alipay
            OffSitePaymentType.AU_PAY -> I18nStringKey.payment_via_au_pay
            OffSitePaymentType.MER_PAY -> I18nStringKey.payment_via_mer_pay
            OffSitePaymentType.PAY_PAY -> I18nStringKey.payment_via_paypay
            OffSitePaymentType.RAKUTEN_PAY -> I18nStringKey.payment_via_rakuten_pay
            OffSitePaymentType.LINE_PAY -> I18nStringKey.payment_via_line_pay
            else -> null
        }
    }

    val messageKey = remember(paymentMethod) {
        when (paymentMethod.type) {
            OffSitePaymentType.ALI_PAY -> I18nStringKey.you_will_be_redirected_to_alipay
            OffSitePaymentType.AU_PAY -> I18nStringKey.you_will_be_redirected_to_au_pay
            OffSitePaymentType.MER_PAY -> I18nStringKey.you_will_be_redirected_to_mer_pay
            OffSitePaymentType.PAY_PAY -> I18nStringKey.you_will_be_redirected_to_paypay
            OffSitePaymentType.RAKUTEN_PAY -> I18nStringKey.you_will_be_redirected_to_rakuten
            OffSitePaymentType.LINE_PAY -> I18nStringKey.you_will_be_redirected_to_line_pay
            else -> null
        }
    }

    val paymentButtonKey = remember(paymentMethod) {
        when (paymentMethod.type) {
            OffSitePaymentType.ALI_PAY -> I18nStringKey.continue_to_alipay
            OffSitePaymentType.AU_PAY -> I18nStringKey.continue_to_au_pay
            OffSitePaymentType.MER_PAY -> I18nStringKey.continue_to_mer_pay
            OffSitePaymentType.PAY_PAY -> I18nStringKey.continue_to_paypay
            OffSitePaymentType.RAKUTEN_PAY -> I18nStringKey.continue_to_rakuten
            OffSitePaymentType.LINE_PAY -> I18nStringKey.continue_to_line_pay
            else -> null
        }
    }

    if (titleKey != null && messageKey != null && paymentButtonKey != null) {
        Column(modifier = Modifier.padding(all = 16.dp)) {
            Text(text = i18nStringResource(titleKey), style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp))
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = i18nStringResource(messageKey))
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .background(Color(0xFFF3F7F9), shape = RoundedCornerShape(8.dp))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = rememberVectorPainter(KomojuIcon.AppOpensInfo),
                    contentDescription = "app_opens_info",
                    modifier = Modifier.size(32.dp),
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = i18nStringResource(I18nStringKey.webview_open_info))
            }
            Spacer(modifier = Modifier.height(32.dp))
            PrimaryButton(i18nStringResource(paymentButtonKey), modifier = Modifier.fillMaxWidth(), onPayButtonClicked)
        }
    }
}

@Composable
@Preview
private fun AppPayFormPreview() {
    KomojuMobileSdkTheme {
        OffSitePayForm(
            PaymentMethod.OffSitePayment(
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
