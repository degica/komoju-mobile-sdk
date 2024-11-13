package com.komoju.mobile.sdk.remote.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PayByTokenRequestDto(
    @SerialName("amount") val amount: String? = null,
    @SerialName("currency") val currency: String? = null,
    @SerialName("payment_details") val paymentDetails: String? = null,
    @SerialName("tax") val tax: String = "0",
) {
    companion object {
        fun from(amount: String, currency: String, paymentDetails: String) = PayByTokenRequestDto(
            amount = amount,
            currency = currency,
            paymentDetails = paymentDetails,
        )
    }
}
