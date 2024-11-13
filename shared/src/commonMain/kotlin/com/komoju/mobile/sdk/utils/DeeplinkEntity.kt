package com.komoju.mobile.sdk.utils

import com.eygraber.uri.Uri

sealed interface DeeplinkEntity {
    sealed interface Verify : DeeplinkEntity {
        data class BySecureToken(val secureTokenId: String, val amount: String, val currency: String) : Verify

        data object BySessionId : Verify
    }

    companion object {
        fun from(rawDeeplink: String): DeeplinkEntity {
            val uri = Uri.parse(rawDeeplink)
            val token = uri.getQueryParameter("secure_token_id")
            return when {
                token != null -> Verify.BySecureToken(
                    secureTokenId = token,
                    amount = uri.getQueryParameter("amount").orEmpty(),
                    currency = uri.getQueryParameter("currency").orEmpty(),
                )
                else -> Verify.BySessionId
            }
        }
    }
}
