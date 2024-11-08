package com.komoju.android.sdk.utils

private val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$".toRegex()

internal inline val String.Companion.empty get() = ""

internal inline val String.isValidEmail: Boolean get() = matches(EMAIL_REGEX)

internal inline val String.isKatakanaOnly: Boolean get() = all { it.isKatakana }

internal inline val Char.isKatakana get() = Character.UnicodeBlock.of(this) == Character.UnicodeBlock.KATAKANA
