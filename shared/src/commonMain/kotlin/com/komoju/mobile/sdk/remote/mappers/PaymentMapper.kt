package com.komoju.mobile.sdk.remote.mappers

import com.komoju.mobile.sdk.entities.Payment
import com.komoju.mobile.sdk.entities.PaymentStatus
import com.komoju.mobile.sdk.remote.dtos.PaymentResponseDto
import com.komoju.mobile.sdk.remote.dtos.SessionResponse

internal object PaymentMapper {
    fun map(response: PaymentResponseDto): Payment = map(response.payment)

    fun map(response: SessionResponse): Payment = map(response.payment)

    private fun map(payment: PaymentResponseDto.Payment?): Payment = when (payment?.paymentDetails?.type) {
        "konbini" -> Payment.Konbini(
            amount = payment.amount.orEmpty(),
            currency = payment.currency.orEmpty(),
            redirectURL = payment.paymentDetails.redirectUrl.orEmpty(),
            status = PaymentStatus.fromString(payment.status.orEmpty()),
            konbiniStoreKey = payment.paymentDetails.store.orEmpty(),
            email = payment.paymentDetails.email.orEmpty(),
            instructionURL = payment.paymentDetails.instructionsUrl.orEmpty(),
            receiptNumber = payment.paymentDetails.receipt,
            confirmationCode = payment.paymentDetails.confirmationCode,
        )

        "paypay" -> Payment.PayPay(
            amount = payment.amount.orEmpty(),
            currency = payment.currency.orEmpty(),
            redirectURL = payment.paymentDetails.redirectUrl.orEmpty(),
            status = PaymentStatus.fromString(payment.status.orEmpty()),
        )

        "credit_card" -> Payment.CreditCard(
            amount = payment.amount.orEmpty(),
            currency = payment.currency.orEmpty(),
            status = PaymentStatus.fromString(payment.status.orEmpty()),
        )

        "rakutenpay" -> Payment.RakutenPay(
            amount = payment.amount.orEmpty(),
            currency = payment.currency.orEmpty(),
            redirectURL = payment.paymentDetails.redirectUrl.orEmpty(),
            status = PaymentStatus.fromString(payment.status.orEmpty()),
        )

        else -> error("Invalid payment type")
    }
}
