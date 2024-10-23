package com.komoju.android.sdk

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Parcelable
import androidx.activity.result.contract.ActivityResultContract
import com.komoju.android.sdk.types.Currency
import com.komoju.android.sdk.types.Language
import com.komoju.android.sdk.ui.theme.ConfigurableTheme
import com.komoju.android.sdk.utils.empty
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlinx.parcelize.Parcelize

/**
 * Main entry point for Komoju SDK integration.
 * Provides configuration setup and payment processing contract.
 */
object KomojuSDK {

    /**
     * Configuration class to hold parameters required for payment processing.
     * Implements [Parcelable] to allow passing data between activities.
     */
    @Parcelize
    data class Configuration(
        internal val language: Language, // Language setting for the payment UI.
        internal val currency: Currency, // Currency used in the transaction.
        internal val publishableKey: String?, // Public API key for Komoju integration.
        internal val isDebugMode: Boolean, // Debug mode flag for logging and testing.
        internal val sessionId: String?, // Unique session ID for payment transaction.
        internal val redirectURL: String = String.empty, // URL to redirect after payment completion.
        internal val configurableTheme: ConfigurableTheme, // Custom theme for UI elements.
        internal val inlinedProcessing: Boolean, // Flag to enable inlined processing.
    ) : Parcelable {

        /**
         * Builder class for creating a [Configuration] instance.
         * Offers a flexible way to set optional parameters.
         */
        class Builder(private val publishableKey: String, private val sessionId: String) {
            private var language: Language = Language.ENGLISH // Default language is English.
            private var currency: Currency = Currency.JPY // Default currency is Japanese Yen.
            private var isDebugMode: Boolean = false // Debug mode is off by default.
            private var configurableTheme: ConfigurableTheme = ConfigurableTheme.default // Custom theme for UI elements.
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
            )
        }
    }

    /**
     * Data class to hold the result of a payment transaction.
     * @param isSuccessFul Whether the payment was successful or not.
     */
    @Parcelize
    data class PaymentResult(val isSuccessFul: Boolean) : Parcelable

    /**
     * Property that provides the contract to start the payment process
     * and receive the result (success or failure).
     */
    @JvmStatic
    val KomojuPaymentResultContract: ActivityResultContract<Configuration, PaymentResult> get() = KomojuStartPaymentForResultContract()
}

/**
 * Extension function to check if the current configuration is valid for processing a payment.
 * @return True if the configuration is non-null and contains both publishableKey and sessionId.
 */
@OptIn(ExperimentalContracts::class)
fun KomojuSDK.Configuration?.canProcessPayment(): Boolean {
    contract {
        returns(true) implies (this@canProcessPayment != null)
    }
    return this?.publishableKey != null && this.sessionId != null
}

/**
 * Internal contract that handles starting the payment activity and returning the result.
 */
internal class KomojuStartPaymentForResultContract : ActivityResultContract<KomojuSDK.Configuration, KomojuSDK.PaymentResult>() {

    companion object {
        const val CONFIGURATION_KEY: String = "KomojuSDK.Configuration" // Key for passing configuration data via Intent.
        const val RESULT_KEY: String = "KomojuSDK.PaymentResult" // Key for passing result data via Intent.
    }

    /**
     * Creates an [Intent] to start the payment activity using the provided configuration.
     * Performs pre-checks before starting the activity.
     * @param context Context in which the payment activity will be started.
     * @param input Configuration used for payment setup.
     * @return The [Intent] with necessary data to start the payment activity.
     */
    override fun createIntent(context: Context, input: KomojuSDK.Configuration): Intent {
        context.preChecks() // Ensure app scheme is correctly set in resources.
        val intent = Intent(context, KomojuPaymentActivity::class.java)
        intent.putExtra(
            CONFIGURATION_KEY,
            input.copy(
                redirectURL = "${context.resources.getString(R.string.komoju_consumer_app_scheme)}://",
            ),
        )
        return intent
    }

    /**
     * Performs pre-checks to ensure that the app scheme is correctly set in strings.xml.
     * Throws an error if the scheme is not properly configured.
     */
    private fun Context.preChecks() {
        if (resources.getString(R.string.komoju_consumer_app_scheme) == "this-should-not-be-the-case") {
            error("Please set komoju_consumer_app_scheme in strings.xml with your app scheme")
        }
    }

    /**
     * Parses the result returned by the payment activity.
     * This is a placeholder for actual result parsing logic.
     * @param resultCode Result code from the activity.
     * @param intent The returned [Intent] containing the result data.
     * @return [KomojuSDK.PaymentResult] indicating whether the payment was successful or not.
     */
    override fun parseResult(resultCode: Int, intent: Intent?): KomojuSDK.PaymentResult = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> intent?.getParcelableExtra(RESULT_KEY, KomojuSDK.PaymentResult::class.java)
        else ->
            @Suppress("DEPRECATION")
            intent?.getParcelableExtra(RESULT_KEY)
    } ?: KomojuSDK.PaymentResult(isSuccessFul = false)
}
