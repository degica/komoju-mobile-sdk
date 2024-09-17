package com.degica.komoju.mobile.sdk.entities

import com.degica.komoju.mobile.sdk.entities.PaymentMethod.Konbini.KonbiniBrand

sealed interface Payment {
    val amount: Double
    val currency: String
    val status: PaymentStatus

    data class Konbini(
        override val status: PaymentStatus,
        override val amount: Double,
        override val currency: String,
        val redirectURL: String,
        val konbiniStoreKey: String,
        val email: String,
        val instructionURL: String,
        val receiptNumber: String?,
        val confirmationCode: String?,
    ) : Payment

    data class Error(val code: String, val message: String, override val amount: Double, override val currency: String) : Payment {
        override val status: PaymentStatus = PaymentStatus.EXPIRED
    }
}

enum class PaymentStatus {
    PENDING,
    COMPLETED,
    AUTHORIZED,
    CAPTURED,
    CANCELLED,
    EXPIRED,
    FAILED,
    REFUNDED,
    ;

    companion object {
        fun fromString(status: String): PaymentStatus = entries.find { it.name == status.uppercase() } ?: throw IllegalArgumentException("Invalid payment status: $status")
    }
}
