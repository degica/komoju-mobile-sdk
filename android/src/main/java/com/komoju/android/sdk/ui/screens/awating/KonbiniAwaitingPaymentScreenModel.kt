package com.komoju.android.sdk.ui.screens.awating

import com.komoju.android.sdk.navigation.RouterStateScreenModel
import com.komoju.android.sdk.ui.screens.KomojuPaymentRoute
import com.komoju.android.sdk.ui.screens.Router
import com.komoju.mobile.sdk.entities.Payment

internal class KonbiniAwaitingPaymentScreenModel(payment: Payment? = null) :
    RouterStateScreenModel<KonbiniAwaitingPaymentUiState>(
        KonbiniAwaitingPaymentUiState(payment),
    ) {

    fun onPrimaryButtonClicked() {
        when (val payment = state.value.payment) {
            is Payment.Konbini -> mutableRouter.value = Router.Push(KomojuPaymentRoute.WebView(payment.instructionURL, canComeBack = true))
            else -> Unit
        }
    }

    fun onSecondaryButtonClicked() {
        mutableRouter.value = Router.Pop
    }
}
