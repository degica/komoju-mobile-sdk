package com.komoju.mobile.sdk.remote.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SecureTokenResponseDto(
    @SerialName("authentication_url") val authenticationUrl: String? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("id") val id: String? = null,
    @SerialName("verification_status") val verificationStatus: String? = null,
)
