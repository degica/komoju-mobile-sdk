package com.degica.komoju.android.sdk.ui.screens.webview

import android.util.Log
import android.webkit.ConsoleMessage
import com.kevinnzou.web.AccompanistWebChromeClient

internal class WebChromeClient : AccompanistWebChromeClient() {
    override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
        Log.d("Aman", "WebView Logs" + consoleMessage.message())
        return true
    }
}
