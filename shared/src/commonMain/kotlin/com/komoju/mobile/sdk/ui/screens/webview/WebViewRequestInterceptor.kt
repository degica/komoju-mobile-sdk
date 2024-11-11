package com.komoju.mobile.sdk.ui.screens.webview

import com.eygraber.uri.Uri
import com.multiplatform.webview.request.RequestInterceptor
import com.multiplatform.webview.request.WebRequest
import com.multiplatform.webview.request.WebRequestInterceptResult
import com.multiplatform.webview.web.WebViewNavigator

internal class WebViewRequestInterceptor(private val appScheme: String, private val onDeeplinkCaptured: (String) -> Unit) :
    RequestInterceptor {
    override fun onInterceptUrlRequest(request: WebRequest, navigator: WebViewNavigator): WebRequestInterceptResult =
        request.checkAndOpen(appScheme, onDeeplinkCaptured)

    private fun WebRequest.checkAndOpen(appScheme: String, onDeeplinkCaptured: (String) -> Unit): WebRequestInterceptResult {
        try {
            val uri = Uri.parse(url)
            if (uri.scheme == appScheme) {
                onDeeplinkCaptured(url)
                return WebRequestInterceptResult.Reject
            } else {
                error("Unsupported scheme for deeplink, load in webView Instead.")
            }
        } catch (_: Exception) {
            return WebRequestInterceptResult.Allow
        }
    }
}
