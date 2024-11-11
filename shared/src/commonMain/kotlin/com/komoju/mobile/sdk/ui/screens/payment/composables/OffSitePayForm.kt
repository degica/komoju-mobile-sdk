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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.komoju.mobile.sdk.entities.PaymentMethod
import komoju_mobile_sdk.shared.generated.resources.Res
import com.komoju.mobile.sdk.types.OffSitePaymentType
import com.komoju.mobile.sdk.ui.composables.PrimaryButton
import com.komoju.mobile.sdk.ui.theme.KomojuMobileSdkTheme
import komoju_mobile_sdk.shared.generated.resources.komoju_continue_to_alipay
import komoju_mobile_sdk.shared.generated.resources.komoju_continue_to_aupay
import komoju_mobile_sdk.shared.generated.resources.komoju_continue_to_linepay
import komoju_mobile_sdk.shared.generated.resources.komoju_continue_to_merpay
import komoju_mobile_sdk.shared.generated.resources.komoju_continue_to_paypay
import komoju_mobile_sdk.shared.generated.resources.komoju_continue_to_rakuten
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_app_opens_info
import komoju_mobile_sdk.shared.generated.resources.komoju_payment_via_alipay
import komoju_mobile_sdk.shared.generated.resources.komoju_payment_via_au_pay
import komoju_mobile_sdk.shared.generated.resources.komoju_payment_via_line_pay
import komoju_mobile_sdk.shared.generated.resources.komoju_payment_via_mer_pay
import komoju_mobile_sdk.shared.generated.resources.komoju_payment_via_paypay
import komoju_mobile_sdk.shared.generated.resources.komoju_payment_via_rakuten_pay
import komoju_mobile_sdk.shared.generated.resources.komoju_webview_open_info
import komoju_mobile_sdk.shared.generated.resources.komoju_you_will_be_redirected_to_alipay
import komoju_mobile_sdk.shared.generated.resources.komoju_you_will_be_redirected_to_au_pay
import komoju_mobile_sdk.shared.generated.resources.komoju_you_will_be_redirected_to_line_pay
import komoju_mobile_sdk.shared.generated.resources.komoju_you_will_be_redirected_to_mer_pay
import komoju_mobile_sdk.shared.generated.resources.komoju_you_will_be_redirected_to_paypay
import komoju_mobile_sdk.shared.generated.resources.komoju_you_will_be_redirected_to_rakuten
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun OffSitePayForm(paymentMethod: PaymentMethod.OffSitePayment, onPayButtonClicked: () -> Unit) {
    val titleKey = remember(paymentMethod) {
        when (paymentMethod.type) {
            OffSitePaymentType.ALI_PAY -> Res.string.komoju_payment_via_alipay
            OffSitePaymentType.AU_PAY -> Res.string.komoju_payment_via_au_pay
            OffSitePaymentType.MER_PAY -> Res.string.komoju_payment_via_mer_pay
            OffSitePaymentType.PAY_PAY -> Res.string.komoju_payment_via_paypay
            OffSitePaymentType.RAKUTEN_PAY -> Res.string.komoju_payment_via_rakuten_pay
            OffSitePaymentType.LINE_PAY -> Res.string.komoju_payment_via_line_pay
            else -> null
        }
    }

    val messageKey = remember(paymentMethod) {
        when (paymentMethod.type) {
            OffSitePaymentType.ALI_PAY -> Res.string.komoju_you_will_be_redirected_to_alipay
            OffSitePaymentType.AU_PAY -> Res.string.komoju_you_will_be_redirected_to_au_pay
            OffSitePaymentType.MER_PAY -> Res.string.komoju_you_will_be_redirected_to_mer_pay
            OffSitePaymentType.PAY_PAY -> Res.string.komoju_you_will_be_redirected_to_paypay
            OffSitePaymentType.RAKUTEN_PAY -> Res.string.komoju_you_will_be_redirected_to_rakuten
            OffSitePaymentType.LINE_PAY -> Res.string.komoju_you_will_be_redirected_to_line_pay
            else -> null
        }
    }

    val paymentButtonKey = remember(paymentMethod) {
        when (paymentMethod.type) {
            OffSitePaymentType.ALI_PAY -> Res.string.komoju_continue_to_alipay
            OffSitePaymentType.AU_PAY -> Res.string.komoju_continue_to_aupay
            OffSitePaymentType.MER_PAY -> Res.string.komoju_continue_to_merpay
            OffSitePaymentType.PAY_PAY -> Res.string.komoju_continue_to_paypay
            OffSitePaymentType.RAKUTEN_PAY -> Res.string.komoju_continue_to_rakuten
            OffSitePaymentType.LINE_PAY -> Res.string.komoju_continue_to_linepay
            else -> null
        }
    }

    if (titleKey != null && messageKey != null && paymentButtonKey != null) {
        Column(modifier = Modifier.padding(all = 16.dp)) {
            Text(text = stringResource(titleKey), style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp))
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = stringResource(messageKey))
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .background(Color(0xFFF3F7F9), shape = RoundedCornerShape(8.dp))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(Res.drawable.komoju_ic_app_opens_info),
                    contentDescription = "app_opens_info",
                    modifier = Modifier.size(32.dp),
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = stringResource(Res.string.komoju_webview_open_info))
            }
            Spacer(modifier = Modifier.height(32.dp))
            PrimaryButton(stringResource(paymentButtonKey), modifier = Modifier.fillMaxWidth(), onPayButtonClicked)
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
