package com.komoju.mobile.sdk.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.rememberNavigatorScreenModel
import cafe.adriel.voyager.navigator.Navigator
import com.komoju.mobile.sdk.KomojuMobileSDKPaymentResult

class PaymentResultScreenModel internal constructor() : ScreenModel {
    var result: KomojuMobileSDKPaymentResult? = null
        private set

    fun setResult(result: KomojuMobileSDKPaymentResult) {
        this.result = result
    }
}

@Composable
fun Navigator.paymentResultScreenModel() = rememberNavigatorScreenModel(
    tag = PaymentResultScreenModel::class.simpleName,
    factory = ::PaymentResultScreenModel,
)
