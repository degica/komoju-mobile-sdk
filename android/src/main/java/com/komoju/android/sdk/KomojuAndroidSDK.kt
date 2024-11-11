package com.komoju.android.sdk

import android.os.Parcelable
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.ui.graphics.toArgb
import com.komoju.android.sdk.annotations.ExperimentalKomojuPaymentApi
import com.komoju.android.sdk.types.Currency
import com.komoju.android.sdk.types.Language
import com.komoju.mobile.sdk.KomojuMobileSDKConfiguration
import com.komoju.mobile.sdk.KomojuMobileSDKPaymentResult
import com.komoju.mobile.sdk.canProcessPayment
import com.komoju.mobile.sdk.ui.theme.DefaultConfigurableTheme
import com.komoju.mobile.sdk.ui.theme.toColor
import kotlinx.parcelize.Parcelize
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import com.komoju.mobile.sdk.ui.theme.ConfigurableTheme as CoreConfigurableTheme

object KomojuAndroidSDK {
    val activityResultContract: ActivityResultContract<Configuration, PaymentResult>
        get() = KomojuStartPaymentForResultContract()

    /**
     * Configuration class to hold parameters required for payment processing.
     */
    @Parcelize
    data class Configuration(
        val language: Language, // Language setting for the payment UI.
        val currency: Currency, // Currency used in the transaction.
        val publishableKey: String?, // Public API key for Komoju integration.
        val isDebugMode: Boolean, // Debug mode flag for logging and testing.
        val sessionId: String?, // Unique session ID for payment transaction.
        val redirectURL: String, // URL to redirect after payment completion.
        val appScheme: String, // App schema for deep links.
        val configurableTheme: ConfigurableTheme, // Custom theme for UI elements.
        val inlinedProcessing: Boolean, // Flag to enable inlined processing.
    ) : Parcelable {

        /**
         * Builder class for creating a [Configuration] instance.
         * Offers a flexible way to set optional parameters.
         */
        class Builder(private val publishableKey: String, private val sessionId: String) {
            private var language: Language = Language.JAPANESE // Default language is Japanese.
            private var currency: Currency = Currency.JPY // Default currency is Japanese Yen.
            private var isDebugMode: Boolean = false // Debug mode is off by default.
            private var configurableTheme: ConfigurableTheme =
                ConfigurableTheme.default // Custom theme for UI elements.
            private var inlinedProcessing: Boolean = false // Inlined processing is off by default.

            /** Sets the language for the payment. */
            fun setLanguage(language: Language) = apply {
                this.language = language
            }

            /** Sets the currency for the transaction. */
            fun setCurrency(currency: Currency) = apply {
                this.currency = currency
            }

            /** Enables or disables debug mode. */
            fun setDebugMode(isDebugMode: Boolean) = apply {
                this.isDebugMode = isDebugMode
            }

            /** Sets the custom theme for the payment UI. */
            fun setConfigurableTheme(configurableTheme: ConfigurableTheme) = apply {
                this.configurableTheme = configurableTheme
            }

            /**
             * WARNING: Experimental API [Try this only if you are sure] Disabled by Default.
             *
             * This API enables or disables inlined processing.
             * If this is enabled then The SDK will try to do processing with minimum amount of screens.
             *
             * For e.g.
             * * If PayPay Payment id captured, it will close the SDK ASAP it verifies the payment.
             * * When you will try to pay with Credit Card and Second step verification is not required, SDK will never show the WebView and will handle the callback itself.
             *
             */
            @ExperimentalKomojuPaymentApi
            fun setInlinedProcessing(inlinedProcessing: Boolean) = apply {
                this.inlinedProcessing = inlinedProcessing
            }

            /**
             * Builds the [Configuration] instance with the provided settings.
             */
            fun build(): Configuration = Configuration(
                language = language,
                currency = currency,
                publishableKey = publishableKey,
                sessionId = sessionId,
                isDebugMode = isDebugMode,
                configurableTheme = configurableTheme,
                inlinedProcessing = inlinedProcessing,
                appScheme = "",
                redirectURL = "",
            )
        }
    }

    @Parcelize
    data class ConfigurableTheme(
        val primaryColorInt: Int,
        val primaryContentColorInt: Int,
        val loaderColorInt: Int,
        val primaryShapeCornerRadiusInDp: Int,
    ) : Parcelable {
        companion object {
            val default: ConfigurableTheme = com.komoju.mobile.sdk.ui.theme.ConfigurableTheme.default.toAndroidSDKConfigurableTheme()
        }
    }

    @Parcelize
    data class PaymentResult(val isSuccessFul: Boolean) : Parcelable
}

internal fun KomojuAndroidSDK.ConfigurableTheme.toKomojuConfigurableTheme(): CoreConfigurableTheme = DefaultConfigurableTheme(
    primaryColor = primaryColorInt.toLong(),
    primaryContentColor = primaryContentColorInt.toLong(),
    loaderColor = loaderColorInt.toLong(),
    primaryShapeCornerRadiusInDp = this.primaryShapeCornerRadiusInDp,
)

internal fun CoreConfigurableTheme.toAndroidSDKConfigurableTheme(): KomojuAndroidSDK.ConfigurableTheme =
    KomojuAndroidSDK.ConfigurableTheme(
        primaryColorInt = primaryColor.toColor().toArgb(),
        primaryContentColorInt = primaryContentColor.toColor().toArgb(),
        loaderColorInt = loaderColor.toColor().toArgb(),
        primaryShapeCornerRadiusInDp = primaryShapeCornerRadiusInDp,
    )


/**
 * Extension function to check if the current configuration is valid for processing a payment.
 * @return True if the configuration is non-null and contains both publishableKey and sessionId.
 */
@OptIn(ExperimentalContracts::class)
fun KomojuAndroidSDK.Configuration?.canProcessPayment(): Boolean {
    contract {
        returns(true) implies (this@canProcessPayment != null)
    }
    return this?.toMobileConfiguration().canProcessPayment()
}

internal fun KomojuMobileSDKConfiguration.toAndroidConfiguration(): KomojuAndroidSDK.Configuration = KomojuAndroidSDK.Configuration(
    language = Language.parse(this.language),
    currency = Currency.parse(this.currency),
    publishableKey = this.publishableKey,
    isDebugMode = this.isDebugMode,
    sessionId = this.sessionId,
    redirectURL = this.redirectURL,
    configurableTheme = this.configurableTheme.toAndroidSDKConfigurableTheme(),
    inlinedProcessing = this.inlinedProcessing,
    appScheme = this.appScheme,
)

internal fun KomojuAndroidSDK.Configuration.toMobileConfiguration(): KomojuMobileSDKConfiguration = KomojuMobileSDKConfiguration(
    language = this.language.languageCode,
    currency = this.currency.currencyCode,
    publishableKey = this.publishableKey,
    isDebugMode = this.isDebugMode,
    sessionId = this.sessionId,
    redirectURL = this.redirectURL,
    configurableTheme = this.configurableTheme.toKomojuConfigurableTheme(),
    inlinedProcessing = this.inlinedProcessing,
    appScheme = this.appScheme,
)


internal fun KomojuMobileSDKPaymentResult.toParcelable(): KomojuAndroidSDK.PaymentResult = KomojuAndroidSDK.PaymentResult(this.isSuccessFul)

internal fun KomojuAndroidSDK.PaymentResult.toPaymentResult(): KomojuMobileSDKPaymentResult = KomojuMobileSDKPaymentResult(this.isSuccessFul)

