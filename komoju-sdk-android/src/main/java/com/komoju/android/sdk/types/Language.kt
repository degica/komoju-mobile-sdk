package com.komoju.android.sdk.types

import androidx.compose.ui.text.intl.Locale

/**
 * Enum class representing supported languages with their respective language codes.
 * Each language is associated with a specific locale.
 */
enum class Language(val languageCode: String) {

    /**
     * English language with code "en".
     */
    ENGLISH(languageCode = "en"),

    /**
     * Japanese language with code "ja".
     */
    JAPANESE(languageCode = "ja"),
    ;

    override fun toString(): String = languageCode

    companion object {
        /**
         * Provides the default [Language] based on the system's default locale.
         *
         * @return The corresponding [Language] based on the system's default language:
         * - Returns [JAPANESE] if the default language is Japanese.
         * - Returns [ENGLISH] for all other cases.
         */
        val default
            get() = when (Locale.current.language) {
                "ja" -> JAPANESE
                else -> ENGLISH
            }

        fun parse(languageCode: String?): Language = entries.find { it.languageCode == languageCode } ?: default
    }
}
