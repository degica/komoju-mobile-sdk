package com.degica.komoju.android.sdk.utils

import androidx.core.net.toUri

internal sealed interface DeeplinkEntity {
    sealed interface Verify : DeeplinkEntity {
        data class BySecureToken(val secureTokenId: String, val amount: String, val currency: String) : Verify

        data object BySessionId : Verify
    }

    companion object {
        fun from(rawDeeplink: String): DeeplinkEntity {
            val uri = rawDeeplink.toUri()
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
