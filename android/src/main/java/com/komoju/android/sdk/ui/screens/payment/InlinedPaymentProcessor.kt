package com.komoju.android.sdk.ui.screens.payment

import com.komoju.android.sdk.ui.screens.failed.Reason
import com.komoju.mobile.sdk.entities.PaymentStatus
import com.komoju.mobile.sdk.remote.apis.KomojuRemoteApi

internal suspend fun KomojuRemoteApi.verifyTokenAndProcessPayment(
    sessionId: String,
    token: String,
    amount: String,
    currency: String,
    onError: (Reason) -> Unit,
    onSuccess: suspend (PaymentStatus) -> Unit,
) {
    tokens.verifySecureToken(token).onSuccess { isVerifiedByToken ->
        if (isVerifiedByToken) {
            payByToken(sessionId, token, amount, currency, onError, onSuccess)
        } else {
            onError(Reason.CREDIT_CARD_ERROR)
        }
    }.onFailure {
        onError(Reason.CREDIT_CARD_ERROR)
    }
}

private suspend fun KomojuRemoteApi.payByToken(
    sessionId: String,
    token: String,
    amount: String,
    currency: String,
    onError: (Reason) -> Unit,
    onSuccess: suspend (PaymentStatus) -> Unit,
) {
    sessions.pay(sessionId, token, amount, currency).onSuccess { response ->
        if (response.status == PaymentStatus.CAPTURED) {
            processBySession(sessionId, onSuccess, onError)
        } else {
            onError(Reason.CREDIT_CARD_ERROR)
        }
    }.onFailure {
        onError(Reason.CREDIT_CARD_ERROR)
    }
}

private suspend fun KomojuRemoteApi.processBySession(sessionId: String, onSuccess: suspend (PaymentStatus) -> Unit, onError: (Reason) -> Unit) {
    sessions.verifyPaymentBySessionID(sessionId).onSuccess { paymentDetails ->
        onSuccess(paymentDetails.status)
    }.onFailure {
        onError(Reason.OTHER)
    }
}
