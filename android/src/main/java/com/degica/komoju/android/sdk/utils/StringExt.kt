package com.degica.komoju.android.sdk.utils

private val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$".toRegex()

internal inline val String.Companion.empty get() = ""

internal inline val String.isValidEmail: Boolean get() = matches(EMAIL_REGEX)

internal inline val String.hostName get() = replace("https://", "")
    .replace("http://", "").run {
        substring(0..<indexOf('/'))
    }
