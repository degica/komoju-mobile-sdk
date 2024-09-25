package com.degica.komoju.android.sdk.ui.screens.verify

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.degica.komoju.android.sdk.KomojuSDK
import com.degica.komoju.android.sdk.ui.screens.KomojuPaymentRoute
import com.degica.komoju.android.sdk.ui.screens.Router
import com.degica.komoju.android.sdk.ui.screens.failed.Reason
import com.degica.komoju.mobile.sdk.entities.PaymentStatus
import com.degica.komoju.mobile.sdk.remote.apis.KomojuRemoteApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class VerifyPaymentScreenModel(private val config: KomojuSDK.Configuration) : ScreenModel {

    private val komojuApi: KomojuRemoteApi = KomojuRemoteApi(config.publishableKey, config.language.languageCode)

    private val _router = MutableStateFlow<Router?>(null)
    val router = _router.asStateFlow()

    fun process(type: KomojuPaymentRoute.ProcessPayment.ProcessType) {
        screenModelScope.launch {
            when (type) {
                is KomojuPaymentRoute.ProcessPayment.ProcessType.Session -> processBySession()
                is KomojuPaymentRoute.ProcessPayment.ProcessType.VerifyTokenAndPay -> verifyTokenAndProcessPayment(type)
                is KomojuPaymentRoute.ProcessPayment.ProcessType.PayByToken -> payByToken(type.token, type.amount, type.currency)
            }
        }
    }

    private suspend fun processBySession() {
        komojuApi.sessions.verifyPaymentBySessionID(config.sessionId.orEmpty()).onSuccess { paymentDetails ->
            _router.value = when (paymentDetails.status) {
                PaymentStatus.COMPLETED, PaymentStatus.CAPTURED -> Router.ReplaceAll(KomojuPaymentRoute.PaymentSuccess)
                else -> Router.ReplaceAll(KomojuPaymentRoute.PaymentFailed(Reason.OTHER))
            }
        }.onFailure {
            _router.value = Router.ReplaceAll(KomojuPaymentRoute.PaymentFailed(Reason.OTHER))
        }
    }

    private suspend fun payByToken(token: String, amount: String, currency: String) {
        komojuApi.sessions.pay(config.sessionId.orEmpty(), token, amount, currency).onSuccess { response ->
            if (response.status == PaymentStatus.CAPTURED) processBySession() else _router.value = Router.ReplaceAll(KomojuPaymentRoute.PaymentFailed(Reason.CREDIT_CARD_ERROR))
        }.onFailure {
            _router.value = Router.ReplaceAll(KomojuPaymentRoute.PaymentFailed(Reason.CREDIT_CARD_ERROR))
        }
    }

    private suspend fun verifyTokenAndProcessPayment(verifyTokenAndPay: KomojuPaymentRoute.ProcessPayment.ProcessType.VerifyTokenAndPay) {
        komojuApi.tokens.verifySecureToken(verifyTokenAndPay.token).onSuccess { isVerifiedByToken ->
            if (isVerifiedByToken) {
                with(verifyTokenAndPay) { payByToken(token, amount, currency) }
            } else {
                _router.value =
                    Router.ReplaceAll(KomojuPaymentRoute.PaymentFailed(Reason.CREDIT_CARD_ERROR))
            }
        }.onFailure {
            _router.value = Router.ReplaceAll(KomojuPaymentRoute.PaymentFailed(Reason.CREDIT_CARD_ERROR))
        }
    }

    fun onRouteHandled() {
        _router.value = null
    }
}
