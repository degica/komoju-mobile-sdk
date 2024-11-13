package com.komoju.android.sdk

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.activity.result.contract.ActivityResultContract
import com.komoju.mobile.sdk.KomojuMobileSDKPaymentResult

/**
 * Internal contract that handles starting the payment activity and returning the result.
 */
internal class KomojuStartPaymentForResultContract :
    ActivityResultContract<KomojuAndroidSDK.Configuration, KomojuAndroidSDK.PaymentResult>() {

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
    override fun createIntent(context: Context, input: KomojuAndroidSDK.Configuration): Intent {
        context.preChecks() // Ensure app scheme is correctly set in resources.
        val intent = Intent(context, KomojuPaymentActivity::class.java)
        intent.putExtra(
            CONFIGURATION_KEY,
            input.copy(
                redirectURL = "${context.resources.getString(R.string.komoju_consumer_app_scheme)}://",
                appScheme = context.resources.getString(R.string.komoju_consumer_app_scheme),
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
     * @return [KomojuMobileSDKPaymentResult] indicating whether the payment was successful or not.
     */
    override fun parseResult(resultCode: Int, intent: Intent?): KomojuAndroidSDK.PaymentResult = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> intent?.getParcelableExtra(
            RESULT_KEY,
            KomojuAndroidSDK.PaymentResult::class.java,
        )
        else ->
            @Suppress("DEPRECATION")
            intent?.getParcelableExtra(RESULT_KEY)
    } ?: KomojuAndroidSDK.PaymentResult(isSuccessFul = false)
}
