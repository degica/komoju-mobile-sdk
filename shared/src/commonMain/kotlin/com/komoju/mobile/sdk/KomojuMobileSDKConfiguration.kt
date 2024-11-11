package com.komoju.mobile.sdk

import com.komoju.mobile.sdk.ui.theme.ConfigurableTheme
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

data class KomojuMobileSDKConfiguration(
    val language: String, // Language setting for the payment UI. 'en' for English, 'ja' for Japanese
    val currency: String, // Currency used in the transaction. USD for United States Dollar, JPY for Japanese Yen
    val publishableKey: String?, // Public API key for Komoju integration.
    val isDebugMode: Boolean, // Debug mode flag for logging and testing.
    val sessionId: String?, // Unique session ID for payment transaction.
    val redirectURL: String, // URL to redirect after payment completion.
    val appScheme: String, // App schema for deep links.
    val configurableTheme: ConfigurableTheme, // Custom theme for UI elements.
    val inlinedProcessing: Boolean, // Flag to enable inlined processing.
)

/**
 * Extension function to check if the current configuration is valid for processing a payment.
 * @return True if the configuration is non-null and contains both publishableKey and sessionId.
 */
@OptIn(ExperimentalContracts::class)
fun KomojuMobileSDKConfiguration?.canProcessPayment(): Boolean {
    contract {
        returns(true) implies (this@canProcessPayment != null)
    }
    return this?.publishableKey != null && this.sessionId != null
}
