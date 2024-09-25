package com.degica.komoju.mobile.sdk.remote.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SecureTokenVerificationResponseDto(
    @SerialName("secure_token") val secureToken: String? = null,
    @SerialName("verification_status") val verificationStatus: String? = null,
)
