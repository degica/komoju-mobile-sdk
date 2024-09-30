package com.komoju.android.sdk.ui.screens.payment.composables

import androidx.compose.runtime.Composable
import com.komoju.android.sdk.ui.screens.payment.BitCashDisplayData
import com.komoju.android.sdk.ui.screens.payment.CommonDisplayData
import com.komoju.android.sdk.ui.screens.payment.CreditCardDisplayData
import com.komoju.android.sdk.ui.screens.payment.KonbiniDisplayData
import com.komoju.android.sdk.ui.screens.payment.NetCashDisplayData
import com.komoju.android.sdk.ui.screens.payment.WebMoneyDisplayData
import com.komoju.mobile.sdk.entities.PaymentMethod

@Composable
internal fun PaymentMethodForm(
    paymentMethod: PaymentMethod,
    commonDisplayData: CommonDisplayData,
    onCommonDisplayDataChange: (CommonDisplayData) -> Unit,
    creditCardDisplayData: CreditCardDisplayData,
    onCreditCardDisplayDataChange: (CreditCardDisplayData) -> Unit,
    konbiniDisplayData: KonbiniDisplayData,
    onKonbiniDisplayDataChange: (KonbiniDisplayData) -> Unit,
    bitCashDisplayData: BitCashDisplayData,
    onBitCashDisplayDataChange: (BitCashDisplayData) -> Unit,
    netCashDisplayData: NetCashDisplayData,
    onNetCashDisplayDataChange: (NetCashDisplayData) -> Unit,
    webMoneyDisplayData: WebMoneyDisplayData,
    onWebMoneyDisplayDataChange: (WebMoneyDisplayData) -> Unit,
    onPaymentRequested: (PaymentMethod) -> Unit,
) {
    when (paymentMethod) {
        is PaymentMethod.CreditCard -> CreditCardForm(
            creditCard = paymentMethod,
            creditCardDisplayData = creditCardDisplayData,
            onCreditCardDisplayDataChange = onCreditCardDisplayDataChange,
            onPayButtonClicked = {
                onPaymentRequested(paymentMethod)
            },
        )

        is PaymentMethod.Konbini -> KonbiniForm(
            konbini = paymentMethod,
            commonDisplayData = commonDisplayData,
            konbiniDisplayData = konbiniDisplayData,
            onCommonDisplayDataChange = onCommonDisplayDataChange,
            onKonbiniDisplayDataChange = onKonbiniDisplayDataChange,
            onPayButtonClicked = {
                onPaymentRequested(paymentMethod)
            },
        )

        is PaymentMethod.AliPay,
        is PaymentMethod.AuPay,
        is PaymentMethod.MerPay,
        is PaymentMethod.PayPay,
        is PaymentMethod.RakutenPay,
        -> AppPayForm(paymentMethod, onPayButtonClicked = {
            onPaymentRequested(paymentMethod)
        })

        is PaymentMethod.BankTransfer -> BankForm(
            bankTransfer = paymentMethod,
            commonDisplayData = commonDisplayData,
            onCommonDisplayDataChange = onCommonDisplayDataChange,
            onPayButtonClicked = {
                onPaymentRequested(paymentMethod)
            },
        )

        is PaymentMethod.BitCash -> BitCashForm(
            bitCash = paymentMethod,
            bitCashDisplayData = bitCashDisplayData,
            onBitCashDisplayDataChange = onBitCashDisplayDataChange,
            onPayButtonClicked = {
                onPaymentRequested(paymentMethod)
            },
        )

        is PaymentMethod.NetCash -> NetCashForm(
            netCash = paymentMethod,
            netCashDisplayData = netCashDisplayData,
            onNetCashDisplayDataChange = onNetCashDisplayDataChange,
            onPayButtonClicked = {
                onPaymentRequested(paymentMethod)
            },
        )

        is PaymentMethod.WebMoney -> WebMoneyForm(
            webMoney = paymentMethod,
            webMoneyDisplayData = webMoneyDisplayData,
            onWebMoneyDisplayDataChange = onWebMoneyDisplayDataChange,
            onPayButtonClicked = {
                onPaymentRequested(paymentMethod)
            },
        )

        is PaymentMethod.Paidy -> Unit
        is PaymentMethod.PayEasy -> Unit

        is PaymentMethod.Other -> Unit
    }
}
