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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.komoju.mobile.sdk.entities.PaymentMethod
import komoju_mobile_sdk.shared.generated.resources.Res
import com.komoju.mobile.sdk.types.OffSitePaymentType
import com.komoju.mobile.sdk.ui.theme.Gray200
import com.komoju.mobile.sdk.ui.theme.KomojuDarkGreen
import komoju_mobile_sdk.shared.generated.resources.komoju_alipay
import komoju_mobile_sdk.shared.generated.resources.komoju_aupay
import komoju_mobile_sdk.shared.generated.resources.komoju_bank_transfer
import komoju_mobile_sdk.shared.generated.resources.komoju_bitcash
import komoju_mobile_sdk.shared.generated.resources.komoju_credit_card
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_alipay
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_au_pay
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_bank_transfer
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_bitcash
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_credit_card
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_konbini
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_linepay
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_merpay
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_net_cash
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_paidy
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_pay_easy
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_paypay
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_rakuten_pay
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_web_money
import komoju_mobile_sdk.shared.generated.resources.komoju_konbini
import komoju_mobile_sdk.shared.generated.resources.komoju_line_pay
import komoju_mobile_sdk.shared.generated.resources.komoju_merpay
import komoju_mobile_sdk.shared.generated.resources.komoju_netcash
import komoju_mobile_sdk.shared.generated.resources.komoju_other
import komoju_mobile_sdk.shared.generated.resources.komoju_paidy
import komoju_mobile_sdk.shared.generated.resources.komoju_payeasy
import komoju_mobile_sdk.shared.generated.resources.komoju_paypay
import komoju_mobile_sdk.shared.generated.resources.komoju_rakuten_pay
import komoju_mobile_sdk.shared.generated.resources.komoju_unknown
import komoju_mobile_sdk.shared.generated.resources.komoju_webmoney
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
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
            painter = painterResource(paymentMethod.displayIcon),
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
        is PaymentMethod.BankTransfer -> stringResource(Res.string.komoju_bank_transfer)
        is PaymentMethod.BitCash -> stringResource(Res.string.komoju_bitcash)
        is PaymentMethod.CreditCard -> stringResource(Res.string.komoju_credit_card)
        is PaymentMethod.Konbini -> stringResource(Res.string.komoju_konbini)
        is PaymentMethod.NetCash -> stringResource(Res.string.komoju_netcash)
        is PaymentMethod.OffSitePayment -> when (type) {
            OffSitePaymentType.AU_PAY -> stringResource(Res.string.komoju_aupay)
            OffSitePaymentType.ALI_PAY -> stringResource(Res.string.komoju_alipay)
            OffSitePaymentType.MER_PAY -> stringResource(Res.string.komoju_merpay)
            OffSitePaymentType.PAY_PAY -> stringResource(Res.string.komoju_paypay)
            OffSitePaymentType.RAKUTEN_PAY -> stringResource(Res.string.komoju_rakuten_pay)
            OffSitePaymentType.LINE_PAY -> stringResource(Res.string.komoju_line_pay)
            OffSitePaymentType.UNKNOWN -> stringResource(Res.string.komoju_unknown)
        }
        is PaymentMethod.Other -> stringResource(Res.string.komoju_other)
        is PaymentMethod.Paidy -> stringResource(Res.string.komoju_paidy)
        is PaymentMethod.PayEasy -> stringResource(Res.string.komoju_payeasy)
        is PaymentMethod.WebMoney -> stringResource(Res.string.komoju_webmoney)
    }

private val PaymentMethod.displayIcon
    get() = when (this) {
        is PaymentMethod.OffSitePayment -> when (type) {
            OffSitePaymentType.ALI_PAY -> Res.drawable.komoju_ic_alipay
            OffSitePaymentType.AU_PAY -> Res.drawable.komoju_ic_au_pay
            OffSitePaymentType.MER_PAY -> Res.drawable.komoju_ic_merpay
            OffSitePaymentType.PAY_PAY -> Res.drawable.komoju_ic_paypay
            OffSitePaymentType.RAKUTEN_PAY -> Res.drawable.komoju_ic_rakuten_pay
            OffSitePaymentType.LINE_PAY -> Res.drawable.komoju_ic_linepay
            OffSitePaymentType.UNKNOWN -> Res.drawable.komoju_ic_credit_card
        }

        is PaymentMethod.BankTransfer -> Res.drawable.komoju_ic_bank_transfer
        is PaymentMethod.BitCash -> Res.drawable.komoju_ic_bitcash
        is PaymentMethod.CreditCard -> Res.drawable.komoju_ic_credit_card
        is PaymentMethod.Konbini -> Res.drawable.komoju_ic_konbini
        is PaymentMethod.NetCash -> Res.drawable.komoju_ic_net_cash
        is PaymentMethod.Paidy -> Res.drawable.komoju_ic_paidy
        is PaymentMethod.PayEasy -> Res.drawable.komoju_ic_pay_easy
        is PaymentMethod.WebMoney -> Res.drawable.komoju_ic_web_money
        is PaymentMethod.Other -> Res.drawable.komoju_ic_credit_card
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
