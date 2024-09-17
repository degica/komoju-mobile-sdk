package com.degica.komoju.mobile.sdk.remote.dtos

import com.degica.komoju.mobile.sdk.entities.PaymentRequest
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PaymentRequestDto(@SerialName("payment_details") val paymentDetails: PaymentDetails? = null) {
    companion object {
        fun from(paymentRequest: PaymentRequest): PaymentRequestDto = when (paymentRequest) {
            is PaymentRequest.CreditCard -> TODO()
            is PaymentRequest.Konbini -> PaymentRequestDto(
                paymentDetails = PaymentDetails(
                    store = paymentRequest.konbiniBrand.key,
                    type = "konbini",
                    email = paymentRequest.email,
                ),
            )
            is PaymentRequest.PayPay -> TODO()
        }
    }

    @Serializable
    data class PaymentDetails(@SerialName("store") val store: String? = null, @SerialName("type") val type: String? = null, @SerialName("email") val email: String? = null)
}
