package com.komoju.android.sdk.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.rememberNavigatorScreenModel
import cafe.adriel.voyager.navigator.Navigator
import com.komoju.android.sdk.KomojuSDK

internal class PaymentResultScreenModel : ScreenModel {
    var result: KomojuSDK.PaymentResult? = null
        private set

    fun setResult(result: KomojuSDK.PaymentResult) {
        this.result = result
    }
}

@Composable
internal fun Navigator.paymentResultScreenModel() = rememberNavigatorScreenModel(PaymentResultScreenModel::class.simpleName) { PaymentResultScreenModel() }
