package com.komoju.android.sdk

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.komoju.mobile.sdk.KomojuMobileSDKConfiguration
import com.komoju.mobile.sdk.ui.screens.KomojuPaymentRoute
import com.komoju.mobile.sdk.ui.screens.Router
import com.komoju.mobile.sdk.utils.DeeplinkEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class KomojuPaymentViewModel(internal val configuration: KomojuMobileSDKConfiguration) : ViewModel() {

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
    }
}

internal class KomojuPaymentViewModelFactory(private val configuration: KomojuAndroidSDK.Configuration) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return KomojuPaymentViewModel(configuration.toMobileConfiguration()) as T
    }
}
