package com.degica.komoju.android.ui.screens.example

import com.degica.komoju.android.sdk.types.Currency
import com.degica.komoju.android.sdk.types.Language

data class ExampleScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedLanguage: Language = Language.ENGLISH,
    val selectedCurrency: Currency = Currency.JPY,
    val amount: String = "",
)
