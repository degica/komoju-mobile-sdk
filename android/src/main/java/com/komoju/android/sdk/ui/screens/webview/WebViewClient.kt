package com.komoju.android.sdk.ui.screens.webview

import android.content.Intent
import android.webkit.WebResourceRequest
import android.webkit.WebView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import com.kevinnzou.web.AccompanistWebViewClient
import com.komoju.android.sdk.KomojuPaymentActivity
import com.komoju.android.sdk.R

internal class WebViewClient : AccompanistWebViewClient() {

    @Deprecated("Deprecated in Java")
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean = view.checkAndOpen(url)
    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean = view.checkAndOpen(request.url.toString())

    private fun WebView.checkAndOpen(url: String): Boolean {
        try {
            val uri = url.toUri()
            if (uri.scheme == resources.getString(R.string.komoju_consumer_app_scheme)) {
                startActivity(
                    context,
                    Intent(context, KomojuPaymentActivity::class.java).apply {
                        data = uri
                    },
                    ActivityOptionsCompat.makeBasic().toBundle(),
                )
                return true
            } else {
                error("Unsupported scheme for deeplink, load in webView Instead.")
            }
        } catch (_: Exception) {
            loadUrl(url)
            return false
        }
    }
}
