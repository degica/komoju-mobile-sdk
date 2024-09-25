package com.degica.komoju.android.sdk

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.degica.komoju.android.sdk.ui.screens.KomojuPaymentRoute
import com.degica.komoju.android.sdk.ui.screens.Router
import com.degica.komoju.android.sdk.utils.DeeplinkEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class KomojuPaymentViewModel(internal val configuration: KomojuSDK.Configuration) : ViewModel() {

    private val _isVisible = MutableStateFlow(false)
    val isVisible = _isVisible.asStateFlow()

    private val _router = MutableStateFlow<Router?>(null)
    val router = _router.asStateFlow()

    fun toggleVisibility(value: Boolean) {
        _isVisible.value = value
    }

    fun onRouteConsumed() {
        _router.value = null
    }

    fun onNewDeeplink(deeplink: String) {
        val deeplinkEntity = DeeplinkEntity.from(deeplink)
        _router.value = Router.ReplaceAll(
            KomojuPaymentRoute.ProcessPayment(
                configuration = configuration,
                processType = when (deeplinkEntity) {
                    is DeeplinkEntity.Verify.BySecureToken -> KomojuPaymentRoute.ProcessPayment.ProcessType.VerifyTokenAndPay(
                        deeplinkEntity.secureTokenId,
                        amount = deeplinkEntity.amount,
                        currency = deeplinkEntity.currency,
                    )
                    DeeplinkEntity.Verify.BySessionId -> KomojuPaymentRoute.ProcessPayment.ProcessType.Session
                },
            ),
        )
        Log.d("Aman", "handleIntentAction $deeplinkEntity")
    }
}

internal class KomojuPaymentViewModelFactory(private val configuration: KomojuSDK.Configuration) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return KomojuPaymentViewModel(configuration) as T
    }
}
