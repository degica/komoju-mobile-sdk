package com.komoju.mobile.sdk.utils

private val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$".toRegex()

internal inline val String.Companion.empty get() = ""

internal inline val String.isValidEmail: Boolean get() = matches(EMAIL_REGEX)

internal inline val String.isDigitsOnly get() = all { it.isDigit() }

// japanese detection Stuff below.
internal inline val Char.isKana get() = isHiragana || isKatakana
internal inline val String.isKanaOnly: Boolean get() = all { it.isKana }

// Source: https://github.com/esnaultdev/wanakana-kt
private const val KATAKANA_START = 0x30a1
private const val KATAKANA_END = 0x30fc
private const val HIRAGANA_START = 0x3041
private const val HIRAGANA_END = 0x3096
private const val PROLONGED_SOUND_MARK = 0x30fc
private val KATAKANA_RANGE = KATAKANA_START..KATAKANA_END
private val HIRAGANA_RANGE = HIRAGANA_START..HIRAGANA_END
private val Char.isHiragana: Boolean get() = code == PROLONGED_SOUND_MARK || code in HIRAGANA_RANGE
private val Char.isKatakana: Boolean get() = code in KATAKANA_RANGE
