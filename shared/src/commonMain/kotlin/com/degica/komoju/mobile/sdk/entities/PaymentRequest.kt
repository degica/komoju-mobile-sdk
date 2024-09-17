package com.degica.komoju.mobile.sdk.entities

import com.degica.komoju.mobile.sdk.entities.PaymentMethod.Konbini.KonbiniBrand

sealed interface PaymentRequest {
    val paymentMethod: PaymentMethod

    data class CreditCard(
        override val paymentMethod: PaymentMethod.CreditCard,
        val cardHolderName: String,
        val cardNumber: String,
        val expirationDate: String,
        val securityCode: String,
    ) : PaymentRequest

    data class Konbini(override val paymentMethod: PaymentMethod.Konbini, val konbiniBrand: KonbiniBrand, val email: String) : PaymentRequest

    data class PayPay(override val paymentMethod: PaymentMethod.PayPay, val email: String) : PaymentRequest
}
