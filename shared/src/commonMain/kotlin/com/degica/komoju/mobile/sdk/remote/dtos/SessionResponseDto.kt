package com.degica.komoju.mobile.sdk.remote.dtos

import com.degica.komoju.mobile.sdk.remote.dtos.PaymentResponseDto.Payment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
internal data class SessionResponse(
    @SerialName("amount") val amount: Int? = null,
    @SerialName("cancelled_at") val cancelledAt: String? = null,
    @SerialName("completed_at") val completedAt: String? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("currency") val currency: String? = null,
    @SerialName("default_locale") val defaultLocale: String? = null,
    @SerialName("expired") val expired: Boolean? = null,
    @SerialName("id") val id: String? = null,
    @SerialName("metadata") val metadata: Metadata? = null,
    @SerialName("mode") val mode: String? = null,
    @SerialName("payment_data") val paymentData: PaymentData? = null,
    @SerialName("payment_methods") val paymentMethods: List<PaymentMethod?>? = null,
    @SerialName("resource") val resource: String? = null,
    @SerialName("return_url") val returnUrl: String? = null,
    @SerialName("session_url") val sessionUrl: String? = null,
    @SerialName("status") val status: String? = null,
    @SerialName("payment") val payment: Payment? = null,
) {
    @Serializable
    class Metadata

    @Serializable
    data class PaymentData(@SerialName("capture") val capture: String? = null)

    @Serializable
    data class PaymentMethod(
        @SerialName("additional_fields") val additionalFields: List<String?>? = null,
        @SerialName("amount") val amount: String? = null,
        @SerialName("brands") val brands: JsonElement? = null,
        @SerialName("currency") val currency: String? = null,
        @SerialName("customer_fee") val customerFee: Int? = null,
        @SerialName("exchange_rate") val exchangeRate: Double? = null,
        @SerialName("hashed_gateway") val hashedGateway: String? = null,
        @SerialName("offsite") val offsite: Boolean? = null,
        @SerialName("second_icon") val secondIcon: String? = null,
        @SerialName("seven_eleven_merchant_number") val sevenElevenMerchantNumber: String? = null,
        @SerialName("type") val type: String? = null,
    )
}
