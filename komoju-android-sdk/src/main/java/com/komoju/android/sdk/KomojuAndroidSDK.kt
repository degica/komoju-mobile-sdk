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
import com.komoju.mobile.sdk.ui.theme.ConfigurableTheme as CoreConfigurableTheme
import com.komoju.mobile.sdk.ui.theme.DefaultConfigurableTheme
import com.komoju.mobile.sdk.ui.theme.toColor
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlinx.parcelize.Parcelize

/**
 * Singleton object providing the main SDK functionalities for Komoju Android integration.
 */
object KomojuAndroidSDK {

    /**
     * An [ActivityResultContract] for starting a payment flow and returning a result asynchronously.
     */
    val activityResultContract: ActivityResultContract<Configuration, PaymentResult>
        get() = KomojuStartPaymentForResultContract()

    /**
     * Configuration class to hold parameters required for payment processing.
     *
     * @param language The language setting for the payment UI.
     * @param currency The currency to be used for the transaction.
     * @param publishableKey The public API key for the Komoju integration.
     * @param isDebugMode A flag to indicate whether the SDK is in debug mode.
     * @param sessionId A unique session ID for the payment transaction.
     * @param redirectURL The URL to redirect the user to after payment completion.
     * @param appScheme The app scheme for deep linking.
     * @param configurableTheme The custom theme for the UI elements of the payment flow.
     * @param inlinedProcessing A flag to indicate if inlined processing is enabled.
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
             * If this is enabled, the SDK will attempt to process payments with fewer UI screens.
             *
             * Example:
             * - For PayPay, the SDK will close as soon as the payment is verified.
             * - For Credit Card payments, if second-step verification isn't required, the SDK will skip the WebView and handle the callback directly.
             *
             * @param inlinedProcessing Boolean flag to enable inlined processing.
             */
            @ExperimentalKomojuPaymentApi
            fun setInlinedProcessing(inlinedProcessing: Boolean) = apply {
                this.inlinedProcessing = inlinedProcessing
            }

            /**
             * Builds the [Configuration] instance with the provided settings.
             *
             * @return The configured [Configuration] object.
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

    /**
     * Class to define configurable theme options for the payment UI.
     *
     * @param primaryColorInt The primary color of the UI as an integer.
     * @param primaryContentColorInt The color for primary content in the UI.
     * @param loaderColorInt The color of the loading indicator.
     * @param primaryShapeCornerRadiusInDp The corner radius (in dp) for primary UI elements.
     */
    @Parcelize
    data class ConfigurableTheme(
        val primaryColorInt: Int,
        val primaryContentColorInt: Int,
        val loaderColorInt: Int,
        val primaryShapeCornerRadiusInDp: Int,
    ) : Parcelable {
        companion object {
            // Default theme used by the SDK.
            val default: ConfigurableTheme = com.komoju.mobile.sdk.ui.theme.ConfigurableTheme.default.toAndroidSDKConfigurableTheme()
        }
    }

    /**
     * A result of the payment, indicating whether the payment was successful or not.
     *
     * @param isSuccessFul Boolean flag indicating the payment result.
     */
    @Parcelize
    data class PaymentResult(val isSuccessFul: Boolean) : Parcelable
}

internal fun KomojuAndroidSDK.ConfigurableTheme.toKomojuConfigurableTheme(): CoreConfigurableTheme = DefaultConfigurableTheme(
    primaryColor = primaryColorInt.toLong(),
    primaryContentColor = primaryContentColorInt.toLong(),
    loaderColor = loaderColorInt.toLong(),
    primaryShapeCornerRadiusInDp = this.primaryShapeCornerRadiusInDp,
)

internal fun CoreConfigurableTheme.toAndroidSDKConfigurableTheme(): KomojuAndroidSDK.ConfigurableTheme = KomojuAndroidSDK.ConfigurableTheme(
    primaryColorInt = primaryColor.toColor().toArgb(),
    primaryContentColorInt = primaryContentColor.toColor().toArgb(),
    loaderColorInt = loaderColor.toColor().toArgb(),
    primaryShapeCornerRadiusInDp = primaryShapeCornerRadiusInDp,
)

/**
 * Extension function to check if the current configuration is valid for processing a payment.
 * A configuration is valid if it is non-null and contains both a valid publishable key and session ID.
 *
 * @return True if the configuration is valid for payment processing, false otherwise.
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

internal fun KomojuAndroidSDK.PaymentResult.toPaymentResult(): KomojuMobileSDKPaymentResult =
    KomojuMobileSDKPaymentResult(this.isSuccessFul)
