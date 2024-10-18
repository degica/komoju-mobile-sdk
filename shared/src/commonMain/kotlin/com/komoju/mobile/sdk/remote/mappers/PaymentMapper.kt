package com.komoju.mobile.sdk.remote.mappers

import com.komoju.mobile.sdk.entities.Payment
import com.komoju.mobile.sdk.entities.PaymentStatus
import com.komoju.mobile.sdk.remote.dtos.PaymentResponseDto
import com.komoju.mobile.sdk.remote.dtos.SessionResponse
import com.komoju.mobile.sdk.types.OffSitePaymentType

internal object PaymentMapper {
    fun map(response: PaymentResponseDto): Payment = map(response.payment)

    fun map(response: SessionResponse): Payment = map(response.payment)

    private fun map(payment: PaymentResponseDto.Payment?): Payment = when (val type = payment?.paymentDetails?.type) {
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

        "credit_card" -> Payment.CreditCard(
            amount = payment.amount.orEmpty(),
            currency = payment.currency.orEmpty(),
            status = PaymentStatus.fromString(payment.status.orEmpty()),
        )

        in OffSitePaymentType.supportedTypes, "paidy" -> Payment.OffSitePayment(
            amount = payment?.amount.orEmpty(),
            currency = payment?.currency.orEmpty(),
            redirectURL = payment?.paymentDetails?.redirectUrl.orEmpty(),
            status = PaymentStatus.fromString(payment?.status.orEmpty()),
            type = type.orEmpty(),
        )

        "net_cash", "bit_cash", "web_money" -> Payment.Completed(
            amount = payment.amount.orEmpty(),
            currency = payment.currency.orEmpty(),
            status = PaymentStatus.fromString(payment.status.orEmpty()),
        )

        "bank_transfer" -> Payment.BankTransfer(
            amount = payment.amount.orEmpty(),
            currency = payment.currency.orEmpty(),
            status = PaymentStatus.fromString(payment.status.orEmpty()),
            instructionURL = payment.paymentDetails.instructionsUrl.orEmpty(),
        )
        "pay_easy" -> Payment.PayEasy(
            amount = payment.amount.orEmpty(),
            currency = payment.currency.orEmpty(),
            status = PaymentStatus.fromString(payment.status.orEmpty()),
            instructionURL = payment.paymentDetails.instructionsUrl.orEmpty(),
        )

        else -> error("Invalid payment type")
    }
}
