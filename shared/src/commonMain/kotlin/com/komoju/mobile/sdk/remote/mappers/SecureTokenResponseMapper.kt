package com.komoju.mobile.sdk.remote.mappers

import com.komoju.mobile.sdk.entities.SecureTokenResponse
import com.komoju.mobile.sdk.remote.dtos.SecureTokenResponseDto

internal object SecureTokenResponseMapper {
    fun map(response: SecureTokenResponseDto): SecureTokenResponse = SecureTokenResponse(
        id = response.id.orEmpty(),
        authURL = response.authenticationUrl.orEmpty(),
        status = SecureTokenResponse.Status.entries.firstOrNull {
            it.name == response.verificationStatus
        } ?: SecureTokenResponse.Status.UNKNOWN,
    )
}
