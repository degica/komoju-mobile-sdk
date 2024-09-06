package com.degica.komoju.android.sdk.types

import java.util.Locale

enum class Language(val languageCode: String) {
    ENGLISH(languageCode = "en"),
    JAPANESE(languageCode = "ja"),
    ;

    internal fun toLocale(): Locale = when (this) {
        ENGLISH -> Locale.ENGLISH
        JAPANESE -> Locale.JAPANESE
    }
}
