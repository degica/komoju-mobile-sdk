package com.komoju.mobile.sdk.remote.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PaymentErrorResponseDto(@SerialName("error") val error: Error? = null) {
    @Serializable
    data class Error(@SerialName("code") val code: String? = null, @SerialName("message") val message: String? = null)
}
