package com.komoju.android.sdk.ui.screens.payment.composables

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.komoju.android.sdk.R
import com.komoju.android.sdk.ui.theme.Gray200
import com.komoju.android.sdk.ui.theme.KomojuDarkGreen
import com.komoju.mobile.sdk.entities.PaymentMethod
import com.komoju.mobile.sdk.types.OffSitePaymentType

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
        is PaymentMethod.BankTransfer -> stringResource(R.string.komoju_bank_transfer)
        is PaymentMethod.BitCash -> stringResource(R.string.komoju_bitcash)
        is PaymentMethod.CreditCard -> stringResource(R.string.komoju_credit_card)
        is PaymentMethod.Konbini -> stringResource(R.string.komoju_konbini)
        is PaymentMethod.NetCash -> stringResource(R.string.komoju_netcash)
        is PaymentMethod.OffSitePayment -> when (type) {
            OffSitePaymentType.AU_PAY -> stringResource(R.string.komoju_aupay)
            OffSitePaymentType.ALI_PAY -> stringResource(R.string.komoju_alipay)
            OffSitePaymentType.MER_PAY -> stringResource(R.string.komoju_merpay)
            OffSitePaymentType.PAY_PAY -> stringResource(R.string.komoju_paypay)
            OffSitePaymentType.RAKUTEN_PAY -> stringResource(R.string.komoju_rakuten_pay)
            OffSitePaymentType.LINE_PAY -> stringResource(R.string.komoju_line_pay)
            OffSitePaymentType.UNKNOWN -> stringResource(R.string.komoju_unknown)
        }
        is PaymentMethod.Other -> stringResource(R.string.komoju_other)
        is PaymentMethod.Paidy -> stringResource(R.string.komoju_paidy)
        is PaymentMethod.PayEasy -> stringResource(R.string.komoju_payeasy)
        is PaymentMethod.WebMoney -> stringResource(R.string.komoju_webmoney)
    }

private val PaymentMethod.displayIcon
    get() = when (this) {
        is PaymentMethod.OffSitePayment -> when (type) {
            OffSitePaymentType.ALI_PAY -> R.drawable.komoju_ic_alipay
            OffSitePaymentType.AU_PAY -> R.drawable.komoju_ic_au_pay
            OffSitePaymentType.MER_PAY -> R.drawable.komoju_ic_merpay
            OffSitePaymentType.PAY_PAY -> R.drawable.komoju_ic_paypay
            OffSitePaymentType.RAKUTEN_PAY -> R.drawable.komoju_ic_rakuten_pay
            OffSitePaymentType.LINE_PAY -> R.drawable.komoju_ic_linepay
            OffSitePaymentType.UNKNOWN -> R.drawable.komoju_ic_credit_card
        }

        is PaymentMethod.BankTransfer -> R.drawable.komoju_ic_bank_transfer
        is PaymentMethod.BitCash -> R.drawable.komoju_ic_bitcash
        is PaymentMethod.CreditCard -> R.drawable.komoju_ic_credit_card
        is PaymentMethod.Konbini -> R.drawable.komoju_ic_konbini
        is PaymentMethod.NetCash -> R.drawable.komoju_ic_net_cash
        is PaymentMethod.Paidy -> R.drawable.komoju_ic_paidy
        is PaymentMethod.PayEasy -> R.drawable.komoju_ic_pay_easy
        is PaymentMethod.WebMoney -> R.drawable.komoju_ic_web_money
        is PaymentMethod.Other -> R.drawable.komoju_ic_credit_card
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
