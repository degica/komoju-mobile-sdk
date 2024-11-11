package com.komoju.mobile.sdk.utils

import nl.jacobras.humanreadable.HumanReadable

internal object AmountUtils {
    fun formatToDecimal(currency: String, amount: String): String {
        if (amount.isBlank()) return ""
        return currency.symbol() + HumanReadable.number(amount.toInt(), decimals = 0)
    }
}

private fun String.symbol(): String = when (this.uppercase()) {
    "JPY" -> "¥"
    else -> "$"
}
