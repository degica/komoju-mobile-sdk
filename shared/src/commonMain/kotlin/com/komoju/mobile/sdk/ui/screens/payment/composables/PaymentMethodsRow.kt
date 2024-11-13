package com.komoju.mobile.sdk.ui.screens.payment.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.komoju.mobile.sdk.entities.PaymentMethod
import com.komoju.mobile.sdk.i18n.I18nStringKey
import com.komoju.mobile.sdk.i18n.i18nStringResource
import com.komoju.mobile.sdk.types.OffSitePaymentType
import com.komoju.mobile.sdk.ui.icon.Alipay
import com.komoju.mobile.sdk.ui.icon.AuPay
import com.komoju.mobile.sdk.ui.icon.BankTransfer
import com.komoju.mobile.sdk.ui.icon.Bitcash
import com.komoju.mobile.sdk.ui.icon.CreditCard
import com.komoju.mobile.sdk.ui.icon.KomojuIcon
import com.komoju.mobile.sdk.ui.icon.Konbini
import com.komoju.mobile.sdk.ui.icon.Linepay
import com.komoju.mobile.sdk.ui.icon.Merpay
import com.komoju.mobile.sdk.ui.icon.NetCash
import com.komoju.mobile.sdk.ui.icon.Paidy
import com.komoju.mobile.sdk.ui.icon.PayEasy
import com.komoju.mobile.sdk.ui.icon.Paypay
import com.komoju.mobile.sdk.ui.icon.RakutenPay
import com.komoju.mobile.sdk.ui.icon.WebMoney
import com.komoju.mobile.sdk.ui.theme.Gray200
import com.komoju.mobile.sdk.ui.theme.KomojuDarkGreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun PaymentMethodsRow(
    paymentMethods: List<PaymentMethod>,
    selectedPaymentMethod: PaymentMethod?,
    onSelected: (PaymentMethod) -> Unit,
) {
    LazyRow(contentPadding = PaddingValues(horizontal = 8.dp)) {
        items(paymentMethods) { paymentMethod ->
            PaymentMethodComposable(
                paymentMethod,
                paymentMethod == selectedPaymentMethod,
                onSelected = {
                    onSelected(paymentMethod)
                },
            )
        }
    }
}

@Composable
private fun PaymentMethodComposable(paymentMethod: PaymentMethod, isSelected: Boolean, onSelected: () -> Unit) {
    Column(
        modifier = Modifier
            .defaultMinSize(minWidth = 120.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, if (isSelected) KomojuDarkGreen else Gray200, RoundedCornerShape(8.dp))
            .clickable(onClick = onSelected)
            .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 8.dp),
    ) {
        Image(
            painter = rememberVectorPainter(paymentMethod.displayIcon),
            contentDescription = "${paymentMethod.displayName} icon",
            modifier = Modifier.height(32.dp),
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(paymentMethod.displayName, color = Color.Black, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
    }
}

private val PaymentMethod.displayName
    @Composable
    get() = when (this) {
        is PaymentMethod.BankTransfer -> i18nStringResource(I18nStringKey.bank_transfer)
        is PaymentMethod.BitCash -> i18nStringResource(I18nStringKey.bit_cash)
        is PaymentMethod.CreditCard -> i18nStringResource(I18nStringKey.credit_card)
        is PaymentMethod.Konbini -> i18nStringResource(I18nStringKey.konbini)
        is PaymentMethod.NetCash -> i18nStringResource(I18nStringKey.net_cash)
        is PaymentMethod.OffSitePayment -> when (type) {
            OffSitePaymentType.AU_PAY -> i18nStringResource(I18nStringKey.au_pay)
            OffSitePaymentType.ALI_PAY -> i18nStringResource(I18nStringKey.alipay)
            OffSitePaymentType.MER_PAY -> i18nStringResource(I18nStringKey.mer_pay)
            OffSitePaymentType.PAY_PAY -> i18nStringResource(I18nStringKey.paypay)
            OffSitePaymentType.RAKUTEN_PAY -> i18nStringResource(I18nStringKey.rakuten_pay)
            OffSitePaymentType.LINE_PAY -> i18nStringResource(I18nStringKey.line_pay)
            OffSitePaymentType.UNKNOWN -> i18nStringResource(I18nStringKey.unknown)
        }
        is PaymentMethod.Other -> i18nStringResource(I18nStringKey.other)
        is PaymentMethod.Paidy -> i18nStringResource(I18nStringKey.paidy)
        is PaymentMethod.PayEasy -> i18nStringResource(I18nStringKey.pay_easy)
        is PaymentMethod.WebMoney -> i18nStringResource(I18nStringKey.web_money)
    }

private val PaymentMethod.displayIcon
    get() = when (this) {
        is PaymentMethod.OffSitePayment -> when (type) {
            OffSitePaymentType.ALI_PAY -> KomojuIcon.Alipay
            OffSitePaymentType.AU_PAY -> KomojuIcon.AuPay
            OffSitePaymentType.MER_PAY -> KomojuIcon.Merpay
            OffSitePaymentType.PAY_PAY -> KomojuIcon.Paypay
            OffSitePaymentType.RAKUTEN_PAY -> KomojuIcon.RakutenPay
            OffSitePaymentType.LINE_PAY -> KomojuIcon.Linepay
            OffSitePaymentType.UNKNOWN -> KomojuIcon.CreditCard
        }

        is PaymentMethod.BankTransfer -> KomojuIcon.BankTransfer
        is PaymentMethod.BitCash -> KomojuIcon.Bitcash
        is PaymentMethod.CreditCard -> KomojuIcon.CreditCard
        is PaymentMethod.Konbini -> KomojuIcon.Konbini
        is PaymentMethod.NetCash -> KomojuIcon.NetCash
        is PaymentMethod.Paidy -> KomojuIcon.Paidy
        is PaymentMethod.PayEasy -> KomojuIcon.PayEasy
        is PaymentMethod.WebMoney -> KomojuIcon.WebMoney
        is PaymentMethod.Other -> KomojuIcon.CreditCard
    }

@Composable
@Preview
private fun PaymentMethodComposablePreview() {
    val paymentMethods = listOf(
        PaymentMethod.CreditCard(
            hashedGateway = "",
            exchangeRate = 0.0,
            currency = "",
            amount = "0",
            additionalFields = listOf(),
            brands = listOf(),
        ),
        PaymentMethod.Konbini(
            hashedGateway = "",
            exchangeRate = 0.0,
            currency = "",
            amount = "0",
            additionalFields = listOf(),
            customerFee = 0,
            brands = listOf(),
        ),
        PaymentMethod.OffSitePayment(
            hashedGateway = "",
            exchangeRate = 0.0,
            currency = "",
            amount = "0",
            additionalFields = listOf(),
            type = OffSitePaymentType.PAY_PAY,
        ),
    )
    PaymentMethodsRow(paymentMethods, paymentMethods.first()) {}
}
