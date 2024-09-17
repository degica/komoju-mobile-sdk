package com.degica.komoju.mobile.sdk.remote.mappers

import com.degica.komoju.mobile.sdk.entities.Payment
import com.degica.komoju.mobile.sdk.entities.PaymentRequest
import com.degica.komoju.mobile.sdk.entities.PaymentStatus
import com.degica.komoju.mobile.sdk.remote.dtos.PaymentResponseDto
import com.degica.komoju.mobile.sdk.remote.dtos.SessionResponse

internal object PaymentMapper {
    fun map(response: PaymentResponseDto): Payment {
        return map(response.payment)
    }

    fun map(response: SessionResponse): Payment {
        return map(response.payment)
    }

    private fun map(payment: PaymentResponseDto.Payment?): Payment = when (payment?.paymentDetails?.type) {
        "konbini" -> Payment.Konbini(
            amount = payment.amount?.toDouble() ?: 0.0,
            currency = payment.currency.orEmpty(),
            redirectURL = payment.paymentDetails.redirectUrl.orEmpty(),
            status = PaymentStatus.fromString(payment.status.orEmpty()),
            konbiniStoreKey = payment.paymentDetails.store.orEmpty(),
            email = payment.paymentDetails.email.orEmpty(),
            instructionURL = payment.paymentDetails.instructionsUrl.orEmpty(),
            receiptNumber = payment.paymentDetails.receipt,
            confirmationCode = payment.paymentDetails.confirmationCode,
        )

        else -> error("Invalid payment type")
    }
}
