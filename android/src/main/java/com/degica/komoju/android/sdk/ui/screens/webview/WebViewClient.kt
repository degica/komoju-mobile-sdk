package com.degica.komoju.android.sdk.ui.screens.webview

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.content.Intent.CATEGORY_BROWSABLE
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.Intent.FLAG_ACTIVITY_REQUIRE_DEFAULT
import android.content.Intent.FLAG_ACTIVITY_REQUIRE_NON_BROWSER
import android.net.Uri
import android.os.Build
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat.startActivity
import com.kevinnzou.web.AccompanistWebViewClient

internal class WebViewClient : AccompanistWebViewClient() {

    @Deprecated("Deprecated in Java")
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean = view.checkAndOpen(url)
    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean = view.checkAndOpen(request.url.toString())

    private fun WebView.checkAndOpen(url: String): Boolean {
        try {
            val intent = Intent(ACTION_VIEW, Uri.parse(url)).apply {
                // The URL should either launch directly in a non-browser app (if it's
                // the default), or in the disambiguation dialog.
                addCategory(CATEGORY_BROWSABLE)
                flags = when {
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_REQUIRE_NON_BROWSER or FLAG_ACTIVITY_REQUIRE_DEFAULT
                    else -> FLAG_ACTIVITY_NEW_TASK
                }
            }
            startActivity(context, intent, ActivityOptionsCompat.makeBasic().toBundle())
        } catch (e: ActivityNotFoundException) {
            Log.d("Aman", "Cannot open any activity for url: $url")
            loadUrl(url)
        }

        return true
    }
}
