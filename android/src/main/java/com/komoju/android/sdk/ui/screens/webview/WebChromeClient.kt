package com.komoju.android.sdk.ui.screens.webview

import android.webkit.ConsoleMessage
import com.kevinnzou.web.AccompanistWebChromeClient

internal class WebChromeClient : AccompanistWebChromeClient() {
    override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean = true
}
