package com.komoju.android.sdk.types

/**
 * Enum class representing supported currencies with corresponding ISO 4217 currency codes.
 * Each currency is associated with a specific locale.
 */
enum class Currency(val currencyCode: String) {

    /**
     * Japanese Yen currency with code "JPY".
     */
    JPY(currencyCode = "JPY"),

    /**
     * United States Dollar currency with code "USD".
     */
    USD(currencyCode = "USD"),
    ;

    companion object {
        /**
         * Parses the given language code and returns the corresponding [Currency] enum constant.
         * If the code does not match any currency, USD is returned as the default.
         *
         * @param languageCode A string representing the currency code (e.g., "JPY", "USD").
         * @return The matching [Currency] enum constant or [USD] if no match is found.
         */
        fun parse(languageCode: String): Currency = entries.firstOrNull { it.currencyCode == languageCode } ?: USD
    }
}
