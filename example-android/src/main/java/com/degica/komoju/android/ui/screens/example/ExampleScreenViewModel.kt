package com.degica.komoju.android.ui.screens.example

import android.util.Log
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.degica.komoju.android.sdk.KomojuSDK
import com.degica.komoju.android.sdk.types.Currency
import com.degica.komoju.android.sdk.types.Language
import com.degica.komoju.android.ui.remote.RemoteApiService
import com.degica.komoju.android.ui.remote.dtos.CreateSessionRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExampleScreenViewModel : ViewModel() {

    private val remoteApiService = RemoteApiService.create()

    private val _uiState = MutableStateFlow(ExampleScreenState())
    val uiState = _uiState.asStateFlow()

    private val _komojuSDKConfiguration = MutableStateFlow<KomojuSDK.Configuration?>(null)
    val komojuSDKConfiguration = _komojuSDKConfiguration.asStateFlow()

    private val uiStateValue get() = _uiState.value

    fun onLanguageChanged(language: Language) = _uiState.update {
        it.copy(selectedLanguage = language)
    }

    fun onCurrencyChanged(currency: Currency) = _uiState.update {
        it.copy(selectedCurrency = currency)
    }

    fun onAmountChanged(amount: String) {
        if (amount.isDigitsOnly()) _uiState.update { it.copy(amount = amount) }
    }

    fun processPayment() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            fetchPublishableKey()?.let { publishableKey ->
                createSession()?.let { sessionId ->
                    Log.d("Aman", "publishableKey: $publishableKey, sessionId: $sessionId")
                    KomojuSDK.Configuration.Builder(publishableKey, sessionId)
                        .setLanguage(uiStateValue.selectedLanguage)
                        .setCurrency(uiStateValue.selectedCurrency)
                        .build().let { komojuConfig ->
                            _komojuSDKConfiguration.update { komojuConfig }
                        }
                    _uiState.update { it.copy(isLoading = false) }
                }
            }
        }
    }

    private suspend fun fetchPublishableKey(): String? = runCatching {
        remoteApiService.getPublishableKey().body()?.publishableKey
    }.onSuccess { publishableKey ->
        if (publishableKey == null) {
            _uiState.update { it.copy(isLoading = false, error = "Failed to fetch publishable key.") }
        }
    }.onFailure { error ->
        _uiState.update { it.copy(isLoading = false, error = "Failed to fetch publishable key.\n${error.message}") }
    }.getOrNull()

    private suspend fun createSession(): String? = runCatching {
        remoteApiService.createSession(
            request = CreateSessionRequest(
                amount = uiStateValue.amount.toInt(),
                currency = uiStateValue.selectedCurrency.currencyCode,
                language = uiStateValue.selectedLanguage.languageCode,
            ),
        ).body()?.sessionId
    }.onSuccess { sessionId ->
        if (sessionId == null) {
            _uiState.update { it.copy(isLoading = false, error = "Failed to fetch session id.") }
        }
    }.onFailure { error ->
        _uiState.update {
            it.copy(
                isLoading = false,
                error = "Failed to fetch session id.\n" + "${error.message}",
            )
        }
    }.getOrNull()

    fun onErrorShown() = _uiState.update {
        it.copy(error = null)
    }

    fun onKomojuPaymentCompleted() {
        _komojuSDKConfiguration.update {
            null
        }
    }
}
