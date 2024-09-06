package com.degica.komoju.android.sdk

import com.degica.komoju.android.sdk.types.Currency
import com.degica.komoju.android.sdk.types.Language

class KomojuSDK(configuration: Configuration) {
    data class Configuration(
        internal val language: Language,
        internal val currency: Currency,
        internal val publishableKey: String?,
        internal val isDebugMode: Boolean,
        internal val sessionId: String?,
    ) {
        class Builder {
            private var language: Language = Language.ENGLISH
            private var currency: Currency = Currency.JPY
            private var publishableKey: String? = null
            private var sessionId: String? = null
            private var isDebugMode: Boolean = false

            fun setLanguage(language: Language) = apply {
                this.language = language
            }

            fun setCurrency(currency: Currency) = apply {
                this.currency = currency
            }

            fun setPublishableKey(publishableKey: String) = apply {
                this.publishableKey = publishableKey
            }

            fun setDebugMode(isDebugMode: Boolean) = apply {
                this.isDebugMode = isDebugMode
            }

            fun setSessionId(sessionId: String) = apply {
                this.sessionId = sessionId
            }

            fun build(): Configuration = Configuration(
                language = language,
                currency = currency,
                publishableKey = publishableKey,
                sessionId = sessionId,
                isDebugMode = isDebugMode,
            )
        }

        fun canProcessPayment() = publishableKey.isNullOrEmpty().not() && sessionId.isNullOrEmpty().not()

        companion object {
            fun default(): Configuration = Builder().build()
        }
    }
}
