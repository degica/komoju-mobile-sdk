package com.komoju.mobile.sdk.entities

sealed interface Payment {
    val amount: String
    val currency: String
    val status: PaymentStatus

    data class Konbini(
        override val status: PaymentStatus,
        override val amount: String,
        override val currency: String,
        val redirectURL: String,
        val konbiniStoreKey: String,
        val email: String,
        val instructionURL: String,
        val receiptNumber: String?,
        val confirmationCode: String?,
    ) : Payment

    data class PayPay(override val status: PaymentStatus, override val amount: String, override val currency: String, val redirectURL: String) : Payment

    data class CreditCard(override val status: PaymentStatus, override val amount: String, override val currency: String) : Payment

    data class RakutenPay(override val status: PaymentStatus, override val amount: String, override val currency: String, val redirectURL: String) : Payment

    data class Error(val code: String, val message: String, override val amount: String, override val currency: String) : Payment {
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

        fun PaymentStatus.isSuccessful(): Boolean = this in listOf(COMPLETED, CAPTURED)
    }
}
