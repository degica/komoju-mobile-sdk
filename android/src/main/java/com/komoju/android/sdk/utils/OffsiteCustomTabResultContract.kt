package com.komoju.android.sdk.utils

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.graphics.Color
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContract
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsIntent.ACTIVITY_HEIGHT_FIXED
import androidx.browser.customtabs.CustomTabsIntent.COLOR_SCHEME_LIGHT

internal class OffsiteCustomTabResultContract : ActivityResultContract<String, Int>() {
    override fun createIntent(context: Context, input: String): Intent {
        val height = context.resources.displayMetrics.heightPixels
        val builder = CustomTabsIntent.Builder()
            .setColorScheme(COLOR_SCHEME_LIGHT)
            .setDefaultColorSchemeParams(
                CustomTabColorSchemeParams.Builder()
                    .setToolbarColor(Color.WHITE)
                    .setNavigationBarColor(Color.WHITE)
                    .setSecondaryToolbarColor(Color.WHITE)
                    .setNavigationBarDividerColor(Color.BLACK)
                    .build(),
            )
            .setShareState(CustomTabsIntent.SHARE_STATE_OFF)
            .setInitialActivityHeightPx(height, ACTIVITY_HEIGHT_FIXED)
            .setCloseButtonPosition(CustomTabsIntent.CLOSE_BUTTON_POSITION_END)
            .setToolbarCornerRadiusDp(10)
        val customTabsIntent = builder.build().intent
        customTabsIntent.setData(Uri.parse(input))
        customTabsIntent.flags += FLAG_ACTIVITY_NO_HISTORY
        return customTabsIntent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Int = resultCode
}
