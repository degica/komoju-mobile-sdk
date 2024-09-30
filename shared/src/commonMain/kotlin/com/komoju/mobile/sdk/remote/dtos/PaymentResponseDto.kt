package com.komoju.mobile.sdk.remote.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PaymentResponseDto(@SerialName("payment") val payment: Payment? = null) {
    @Serializable
    data class Payment(
        @SerialName("amount") val amount: String? = null,
        @SerialName("amount_refunded") val amountRefunded: Int? = null,
        @SerialName("captured_at") val capturedAt: String? = null,
        @SerialName("created_at") val createdAt: String? = null,
        @SerialName("currency") val currency: String? = null,
        @SerialName("customer_family_name") val customerFamilyName: String? = null,
        @SerialName("customer_given_name") val customerGivenName: String? = null,
        @SerialName("description") val description: String? = null,
        @SerialName("external_order_num") val externalOrderNum: String? = null,
        @SerialName("id") val id: String? = null,
        @SerialName("locale") val locale: String? = null,
        @SerialName("mcc") val mcc: String? = null,
        @SerialName("payment_deadline") val paymentDeadline: String? = null,
        @SerialName("payment_details") val paymentDetails: PaymentDetails? = null,
        @SerialName("payment_method_fee") val paymentMethodFee: Int? = null,
        @SerialName("resource") val resource: String? = null,
        @SerialName("session") val session: String? = null,
        @SerialName("status") val status: String? = null,
        @SerialName("tax") val tax: Int? = null,
        @SerialName("total") val total: Int? = null,
    ) {

        @Serializable
        data class PaymentDetails(
            @SerialName("confirmation_code") val confirmationCode: String? = null,
            @SerialName("email") val email: String? = null,
            @SerialName("instructions_url") val instructionsUrl: String? = null,
            @SerialName("redirect_url") val redirectUrl: String? = null,
            @SerialName("receipt") val receipt: String? = null,
            @SerialName("store") val store: String? = null,
            @SerialName("type") val type: String? = null,
            @SerialName("wellnet_wallet_url") val wellnetWalletUrl: String? = null,
        )
    }
}
