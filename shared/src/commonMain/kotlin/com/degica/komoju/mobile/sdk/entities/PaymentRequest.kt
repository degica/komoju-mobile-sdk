package com.degica.komoju.mobile.sdk.entities

import com.degica.komoju.mobile.sdk.entities.PaymentMethod.Konbini.KonbiniBrand

sealed interface PaymentRequest {
    val paymentMethod: PaymentMethod

    data class Konbini(override val paymentMethod: PaymentMethod.Konbini, val konbiniBrand: KonbiniBrand, val email: String) : PaymentRequest

    data class PayPay(override val paymentMethod: PaymentMethod.PayPay) : PaymentRequest
}
