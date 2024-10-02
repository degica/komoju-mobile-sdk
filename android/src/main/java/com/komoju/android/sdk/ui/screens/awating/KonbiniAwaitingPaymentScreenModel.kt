package com.komoju.android.sdk.ui.screens.awating

import cafe.adriel.voyager.core.model.screenModelScope
import com.komoju.android.sdk.KomojuSDK
import com.komoju.android.sdk.navigation.RouterStateScreenModel
import com.komoju.android.sdk.ui.screens.KomojuPaymentRoute
import com.komoju.android.sdk.ui.screens.Router
import com.komoju.mobile.sdk.entities.Payment
import com.komoju.mobile.sdk.remote.apis.KomojuRemoteApi
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class KonbiniAwaitingPaymentScreenModel(private val config: KomojuSDK.Configuration, private val payment: Payment? = null) :
    RouterStateScreenModel<KonbiniAwaitingPaymentUiState>(KonbiniAwaitingPaymentUiState(payment)) {
    private val komojuApi = KomojuRemoteApi(config.publishableKey, config.language.languageCode)

    fun onPrimaryButtonClicked() {
        when (val payment = state.value.payment) {
            is Payment.Konbini -> mutableRouter.value = Router.Push(KomojuPaymentRoute.WebView(payment.instructionURL, canComeBack = true))
            else -> Unit
        }
    }

    fun onSecondaryButtonClicked() {
//        when (state.value.payment) {
//            is Payment.Konbini -> refreshPayment()
//            else -> Unit
//        }
        mutableRouter.value = Router.Pop
    }

    fun refreshPayment() {
        screenModelScope.launch {
            val sessionId = config.sessionId ?: return@launch
            mutableState.update {
                it.copy(isLoading = true)
            }
            komojuApi.sessions.verifyPaymentBySessionID(sessionId).onSuccess { payment ->
                mutableState.update {
                    it.copy(payment = payment, isLoading = false)
                }
            }.onFailure {
                mutableState.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }
}
