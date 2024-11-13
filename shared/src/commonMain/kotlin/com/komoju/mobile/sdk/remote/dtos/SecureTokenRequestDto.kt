package com.komoju.mobile.sdk.remote.dtos

import com.komoju.mobile.sdk.entities.SecureTokenRequest
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SecureTokenRequestDto(
    @SerialName("amount") val amount: String? = null,
    @SerialName("currency") val currency: String? = null,
    @SerialName("return_url") val returnUrl: String? = null,
    @SerialName("tax") val tax: String? = null,
    @SerialName("payment_details") val paymentDetails: PaymentDetails? = null,
) {

    @Serializable
    data class PaymentDetails(
        @SerialName("month") val month: String? = null,
        @SerialName("name") val name: String? = null,
        @SerialName("number") val number: String? = null,
        @SerialName("type") val type: String? = null,
        @SerialName("verification_value") val verificationValue: String? = null,
        @SerialName("year") val year: String? = null,
    )

    companion object {
        fun fromEntity(request: SecureTokenRequest): SecureTokenRequestDto = SecureTokenRequestDto(
            amount = request.amount,
            currency = request.currency,
            returnUrl = request.returnUrl,
            tax = "0",
            paymentDetails = PaymentDetails(
                month = request.expiryMonth,
                name = request.cardHolderName,
                number = request.cardNumber,
                type = "credit_card",
                verificationValue = request.cvv,
                year = request.expiryYear,
            ),
        )
    }
}
