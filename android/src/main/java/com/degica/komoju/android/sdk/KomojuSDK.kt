package com.degica.komoju.android.sdk

import android.content.Context
import android.os.Parcelable
import com.degica.komoju.android.sdk.types.Currency
import com.degica.komoju.android.sdk.types.Language
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlinx.parcelize.Parcelize

object KomojuSDK {
    @Parcelize
    data class Configuration(
        internal val language: Language,
        internal val currency: Currency,
        internal val publishableKey: String?,
        internal val isDebugMode: Boolean,
        internal val sessionId: String?,
        internal val redirectURL: String = "",
    ) : Parcelable {
        class Builder(private var publishableKey: String, private var sessionId: String) {
            private var language: Language = Language.ENGLISH
            private var currency: Currency = Currency.JPY
            private var isDebugMode: Boolean = false

            fun setLanguage(language: Language) = apply {
                this.language = language
            }

            fun setCurrency(currency: Currency) = apply {
                this.currency = currency
            }

            fun setDebugMode(isDebugMode: Boolean) = apply {
                this.isDebugMode = isDebugMode
            }

            fun build(): Configuration = Configuration(
                language = language,
                currency = currency,
                publishableKey = publishableKey,
                sessionId = sessionId,
                isDebugMode = isDebugMode,
            )
        }
    }

    internal const val CONFIGURATION_KEY: String = "KomojuSDK.Configuration"
    fun show(context: Context, configuration: Configuration) {
        context.preChecks()
        val intent = android.content.Intent(context, KomojuPaymentActivity::class.java)
        intent.putExtra(
            CONFIGURATION_KEY,
            configuration.copy(
                redirectURL = "${context.resources.getString(R.string.komoju_consumer_app_scheme)}://",
            ),
        )
        context.startActivity(intent)
    }

    private fun Context.preChecks() {
        if (resources.getString(R.string.komoju_consumer_app_scheme) == "this-should-not-be-the-case") {
            error("Please set komoju_consumer_app_scheme in strings.xml with your app scheme")
        }
    }
}

@OptIn(ExperimentalContracts::class)
fun KomojuSDK.Configuration?.canProcessPayment(): Boolean {
    contract {
        returns(false) implies (this@canProcessPayment != null)
    }
    return this?.publishableKey != null && this.sessionId != null
}
