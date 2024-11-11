package com.komoju.mobile.sdk.i18n

interface I18nStrings {
    fun get(key: I18nStringKey): String

    companion object {
        operator fun invoke(key: I18nStringKey) = EnglishStrings.get(key)
    }
}
