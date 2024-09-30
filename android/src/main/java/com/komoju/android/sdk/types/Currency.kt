package com.komoju.android.sdk.types

import java.util.Locale

enum class Currency(val currencyCode: String) {
    JPY(currencyCode = "JPY"),
    USD(currencyCode = "USD"),
    ;

    fun toLocale(): Locale = when (this) {
        JPY -> Locale.JAPAN
        USD -> Locale.US
    }

    companion object {
        fun parse(languageCode: String): Currency = entries.firstOrNull { it.currencyCode == languageCode } ?: USD
    }
}
