package com.komoju.mobile.sdk.entities

import com.komoju.mobile.sdk.entities.PaymentMethod.Konbini.KonbiniBrand

sealed interface PaymentRequest {
    val paymentMethod: PaymentMethod

    data class Konbini(override val paymentMethod: PaymentMethod.Konbini, val konbiniBrand: KonbiniBrand, val email: String) : PaymentRequest
    data class OffSitePaymentRequest(override val paymentMethod: PaymentMethod.OffSitePayment) : PaymentRequest
}
