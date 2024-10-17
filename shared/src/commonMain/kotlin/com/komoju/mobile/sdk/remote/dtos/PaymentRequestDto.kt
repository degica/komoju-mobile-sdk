package com.komoju.mobile.sdk.remote.dtos

import com.komoju.mobile.sdk.entities.PaymentRequest
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PaymentRequestDto(@SerialName("payment_details") val paymentDetails: PaymentDetails? = null) {
    companion object {
        fun from(paymentRequest: PaymentRequest): PaymentRequestDto = when (paymentRequest) {
            is PaymentRequest.Konbini -> PaymentRequestDto(
                paymentDetails = PaymentDetails(
                    store = paymentRequest.konbiniBrand.key,
                    type = "konbini",
                    email = paymentRequest.email,
                ),
            )
            is PaymentRequest.OffSitePaymentRequest -> PaymentRequestDto(
                paymentDetails = PaymentDetails(
                    type = paymentRequest.paymentMethod.type.id,
                ),
            )

            is PaymentRequest.Paidy -> PaymentRequestDto(
                paymentDetails = PaymentDetails(
                    type = "paidy",
                    fullName = paymentRequest.fullName,
                    phoneNumber = paymentRequest.phoneNumber,
                ),
            )

            is PaymentRequest.NetCash -> PaymentRequestDto(
                paymentDetails = PaymentDetails(
                    type = "net_cash",
                    prepaidNumber = paymentRequest.netCashId,
                ),
            )

            is PaymentRequest.BitCash -> PaymentRequestDto(
                paymentDetails = PaymentDetails(
                    type = "bit_cash",
                    prepaidNumber = paymentRequest.bitCashId,
                ),
            )
        }
    }

    @Serializable
    data class PaymentDetails(
        @SerialName("store") val store: String? = null,
        @SerialName("type") val type: String? = null,
        @SerialName("email") val email: String? = null,
        @SerialName("customer_name") val fullName: String? = null,
        @SerialName("phone") val phoneNumber: String? = null,
        @SerialName("prepaid_number") val prepaidNumber: String? = null,
    )
}
