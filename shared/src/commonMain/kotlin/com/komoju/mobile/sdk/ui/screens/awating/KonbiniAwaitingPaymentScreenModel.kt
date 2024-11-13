package com.komoju.mobile.sdk.ui.screens.awating

import com.komoju.mobile.sdk.KomojuMobileSDKConfiguration
import com.komoju.mobile.sdk.entities.Payment
import com.komoju.mobile.sdk.navigation.RouterStateScreenModel
import com.komoju.mobile.sdk.ui.screens.KomojuPaymentRoute
import com.komoju.mobile.sdk.ui.screens.Router

internal class KonbiniAwaitingPaymentScreenModel(payment: Payment? = null) :
    RouterStateScreenModel<KonbiniAwaitingPaymentUiState>(
        KonbiniAwaitingPaymentUiState(payment),
    ) {

    fun onPrimaryButtonClicked(configuration: KomojuMobileSDKConfiguration) {
        when (val payment = state.value.payment) {
            is Payment.Konbini ->
                mutableRouter.value =
                    Router.Push(KomojuPaymentRoute.WebView(configuration, payment.instructionURL, canComeBack = true))
            else -> Unit
        }
    }

    fun onSecondaryButtonClicked() {
        mutableRouter.value = Router.Pop
    }
}
