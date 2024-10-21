package com.komoju.android.sdk.types

import java.util.Locale

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

    /**
     * Converts the current language to its corresponding locale.
     *
     * @return [Locale] for the language.
     * - ENGLISH returns [Locale.ENGLISH]
     * - JAPANESE returns [Locale.JAPANESE]
     */
    internal fun toLocale(): Locale = when (this) {
        ENGLISH -> Locale.ENGLISH
        JAPANESE -> Locale.JAPANESE
    }

    companion object {
        /**
         * Provides the default [Language] based on the system's default locale.
         *
         * @return The corresponding [Language] based on the system's default language:
         * - Returns [JAPANESE] if the default language is Japanese.
         * - Returns [ENGLISH] for all other cases.
         */
        val default
            get() = when (Locale.getDefault().language) {
                Locale.JAPANESE.language -> JAPANESE
                else -> ENGLISH
            }
    }
}
