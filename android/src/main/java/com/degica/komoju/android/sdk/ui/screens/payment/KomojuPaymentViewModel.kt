package com.degica.komoju.android.sdk.ui.screens.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.degica.komoju.android.sdk.KomojuSDK
import com.degica.komoju.mobile.sdk.entities.PaymentMethod
import com.degica.komoju.mobile.sdk.remote.apis.KomojuRemoteApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class KomojuPaymentViewModel : ViewModel() {

    private var komojuApi: KomojuRemoteApi? = null

    private val _uiState = MutableStateFlow(KomojuPaymentUIState())
    val uiState = _uiState.asStateFlow()

    fun init(config: KomojuSDK.Configuration) {
        if (komojuApi == null) {
            komojuApi = KomojuRemoteApi(config.publishableKey!!, config.language.languageCode)
            viewModelScope.launch {
                komojuApi?.sessions?.show(config.sessionId!!)?.onSuccess { session ->
                    _uiState.update {
                        it.copy(session = session, selectedPaymentMethod = session.paymentMethods.firstOrNull())
                    }
                }
            }
        }
    }

    fun onNewPaymentMethodSelected(paymentMethod: PaymentMethod) {
        _uiState.update { it.copy(selectedPaymentMethod = paymentMethod) }
    }
}
