package com.komoju.android.sdk.utils

import android.icu.text.NumberFormat
import android.icu.util.Currency as PlatformCurrency
import com.komoju.android.sdk.types.Currency

internal object AmountUtils {
    fun formatToDecimal(currency: Currency, amount: String): String {
        if (amount.isBlank()) return ""
        val locale = currency.toLocale()
        return NumberFormat.getCurrencyInstance(locale).apply {
            this.maximumFractionDigits = 0
            this.minimumFractionDigits = 0
            this.currency = PlatformCurrency.getInstance(locale)
        }.format(amount.toDouble())
    }
}
