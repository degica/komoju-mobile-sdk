package com.degica.komoju.android.sdk.ui.screens.status

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.degica.komoju.android.sdk.KomojuSDK
import com.degica.komoju.android.sdk.ui.screens.KomojuPaymentRoute
import com.degica.komoju.android.sdk.ui.screens.Router
import com.degica.komoju.mobile.sdk.entities.Payment
import com.degica.komoju.mobile.sdk.remote.apis.KomojuRemoteApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class PaymentStatusScreenModel(private val config: KomojuSDK.Configuration, private val payment: Payment? = null) :
    StateScreenModel<PaymentStatusUiState>(PaymentStatusUiState(payment)) {
    private val komojuApi = KomojuRemoteApi(config.publishableKey, config.language.languageCode)
    private val _router = MutableStateFlow<Router?>(null)
    val router = _router.asStateFlow()

    fun onRouteConsumed() {
        _router.value = null
    }

    fun onPrimaryButtonClicked() {
        when (val payment = state.value.payment) {
            is Payment.Konbini -> _router.value = Router.Push(KomojuPaymentRoute.WebView(payment.instructionURL, canComeBack = true))
            else -> Unit
        }
    }

    fun onSecondaryButtonClicked() {
        when (val payment = state.value.payment) {
            is Payment.Konbini -> refreshPayment()
            else -> Unit
        }
    }

    fun refreshPayment() {
        screenModelScope.launch {
            val sessionId = config.sessionId ?: return@launch
            mutableState.update {
                it.copy(isLoading = true)
            }
            komojuApi.sessions.refreshPayment(sessionId).onSuccess { payment ->
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
