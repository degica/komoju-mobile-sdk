package com.komoju.mobile.sdk.utils

internal object Logger {
    private var shouldPrintLogs = false

    fun setDebugMode(debugMode: Boolean) {
        shouldPrintLogs = debugMode
    }

    fun d(message: String?) {
        if (shouldPrintLogs) println(message)
    }

    fun e(t: Throwable) {
        if (shouldPrintLogs) println(t)
    }
}
