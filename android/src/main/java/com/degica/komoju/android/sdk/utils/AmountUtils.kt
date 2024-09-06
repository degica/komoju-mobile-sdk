package com.degica.komoju.android.sdk.utils

import android.icu.text.NumberFormat
import android.icu.util.Currency as PlatformCurrency
import com.degica.komoju.android.sdk.types.Currency

internal object AmountUtils {
    fun formatToDecimal(currency: Currency, amount: Double): String {
        val locale = currency.toLocale()
        return NumberFormat.getCurrencyInstance(locale).apply {
            this.maximumFractionDigits = 2
            this.minimumFractionDigits = 2
            this.currency = PlatformCurrency.getInstance(locale)
        }.format(amount)
    }
}
