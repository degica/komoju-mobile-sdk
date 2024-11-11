package com.komoju.android.ui.screens.store

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.komoju.android.BuildConfig
import com.komoju.android.sdk.KomojuAndroidSDK
import com.komoju.android.sdk.annotations.ExperimentalKomojuPaymentApi
import com.komoju.android.sdk.types.Currency
import com.komoju.android.sdk.types.Language
import com.komoju.android.remote.RemoteApiService
import com.komoju.android.remote.dtos.CreateSessionRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FakeStoreScreenModel : ScreenModel {

    private val remoteApiService = RemoteApiService.create()
    private var publishableKey: String? = null
    private val _komojuSDKConfiguration = MutableStateFlow<KomojuAndroidSDK.Configuration?>(null)
    val komojuSDKConfiguration = _komojuSDKConfiguration.asStateFlow()
    private val _uiState = MutableStateFlow(FakeStoreUiState())
    val uiState = _uiState.onStart {
        init()
    }.stateIn(screenModelScope, SharingStarted.WhileSubscribed(5000), _uiState.value)

    private val currency = Currency.JPY
    private val language = Language.default

    private suspend fun init() {
        publishableKey = fetchPublishableKey()
        loadItems()
    }

    private fun loadItems() {
        _uiState.update {
            it.copy(items = FakeStoreDisplayDataRepository.items)
        }
    }

    private suspend fun fetchPublishableKey(): String? = runCatching {
        remoteApiService.getPublishableKey().body()?.publishableKey
    }.onSuccess { publishableKey ->
        if (publishableKey == null) {
            _uiState.update { it.copy(error = "Failed to fetch publishable key from Server ${BuildConfig.SERVER_URL}") }
        }
    }.onFailure { error ->
        error.printStackTrace()
        _uiState.update { it.copy(error = "Failed to fetch publishable key.\n${error.message}") }
    }.getOrNull()

    fun onItemChanged(item: Item) {
        _uiState.value = _uiState.value.copy(
            items = _uiState.value.items.toMutableList().apply {
                set(indexOfFirst { it.imageID == item.imageID }, item)
            }.toList(),
        )
    }

    @OptIn(ExperimentalKomojuPaymentApi::class)
    fun onBuyClicked(item: Item) {
        screenModelScope.launch {
            createSession(item)?.let { sessionId ->
                KomojuAndroidSDK.Configuration.Builder(
                    requireNotNull(publishableKey),
                    sessionId,
                ).setLanguage(language)
                    .setCurrency(currency)
                    .setConfigurableTheme(komojuConfigurableTheme)
                    .setInlinedProcessing(true)
                    .build().let { komojuConfig ->
                        _komojuSDKConfiguration.value = komojuConfig
                    }
            }
        }
    }

    private suspend fun createSession(item: Item): String? = runCatching {
        _uiState.update { it.copy(isCreatingSession = true) }
        remoteApiService.createSession(
            request = CreateSessionRequest(
                amount = item.price.toInt(),
                currency = currency.currencyCode,
                language = language.languageCode,
            ),
        ).body()?.sessionId
    }.onSuccess { sessionId ->
        if (sessionId == null) {
            _uiState.update { it.copy(isCreatingSession = false, error = "Failed to fetch session id.") }
        } else {
            _uiState.update { it.copy(isCreatingSession = false) }
        }
    }.onFailure { error ->
        _uiState.update {
            it.copy(
                isCreatingSession = false,
                error = "Failed to fetch session id.\n" + "${error.message}",
            )
        }
    }.getOrNull()

    fun onErrorShown() {
        _uiState.update {
            it.copy(error = null)
        }
    }

    fun onKomojuPaymentCompleted() {
        _komojuSDKConfiguration.value = null
    }
}
