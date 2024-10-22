package com.komoju.android.sdk.ui.screens.verify

import cafe.adriel.voyager.core.model.screenModelScope
import com.komoju.android.sdk.KomojuSDK
import com.komoju.android.sdk.navigation.RouterStateScreenModel
import com.komoju.android.sdk.ui.screens.KomojuPaymentRoute
import com.komoju.android.sdk.ui.screens.Router
import com.komoju.android.sdk.ui.screens.failed.Reason
import com.komoju.mobile.sdk.entities.PaymentStatus
import com.komoju.mobile.sdk.entities.PaymentStatus.Companion.isSuccessful
import com.komoju.mobile.sdk.remote.apis.KomojuRemoteApi
import kotlinx.coroutines.launch

internal class VerifyPaymentScreenModel(private val config: KomojuSDK.Configuration) : RouterStateScreenModel<Unit>(Unit) {

    private val komojuApi: KomojuRemoteApi = KomojuRemoteApi.create(config.publishableKey)

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
            mutableRouter.value = when {
                config.inlinedProcessing -> Router.SetPaymentResultAndPop(
                    KomojuSDK.PaymentResult(
                        isSuccessFul = paymentDetails.status.isSuccessful(),
                    ),
                )

                else -> when (paymentDetails.status.isSuccessful()) {
                    true -> Router.ReplaceAll(KomojuPaymentRoute.PaymentSuccess)
                    else -> Router.ReplaceAll(KomojuPaymentRoute.PaymentFailed(Reason.OTHER))
                }
            }
        }.onFailure {
            mutableRouter.value = Router.ReplaceAll(KomojuPaymentRoute.PaymentFailed(Reason.OTHER))
        }
    }

    private suspend fun payByToken(token: String, amount: String, currency: String) {
        komojuApi.sessions.pay(config.sessionId.orEmpty(), token, amount, currency).onSuccess { response ->
            if (response.status ==
                PaymentStatus.CAPTURED
            ) {
                processBySession()
            } else {
                mutableRouter.value = Router.ReplaceAll(KomojuPaymentRoute.PaymentFailed(Reason.CREDIT_CARD_ERROR))
            }
        }.onFailure {
            mutableRouter.value = Router.ReplaceAll(KomojuPaymentRoute.PaymentFailed(Reason.CREDIT_CARD_ERROR))
        }
    }

    private suspend fun verifyTokenAndProcessPayment(verifyTokenAndPay: KomojuPaymentRoute.ProcessPayment.ProcessType.VerifyTokenAndPay) {
        komojuApi.tokens.verifySecureToken(verifyTokenAndPay.token).onSuccess { isVerifiedByToken ->
            if (isVerifiedByToken) {
                with(verifyTokenAndPay) { payByToken(token, amount, currency) }
            } else {
                mutableRouter.value =
                    Router.ReplaceAll(KomojuPaymentRoute.PaymentFailed(Reason.CREDIT_CARD_ERROR))
            }
        }.onFailure {
            mutableRouter.value = Router.ReplaceAll(KomojuPaymentRoute.PaymentFailed(Reason.CREDIT_CARD_ERROR))
        }
    }

    override fun onDispose() {
        komojuApi.close()
        super.onDispose()
    }
}
