package com.degica.komoju.android.sdk.utils

import androidx.core.net.toUri
import com.degica.komoju.android.sdk.types.Currency
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal data class DeeplinkEntity(
    val hmac: String,
    val sessionId: String,
    val time: LocalDateTime,
    val amount: Int,
    val currency: Currency,
    val totalAmount: Int,
    val paymentMethod: String,
    val paymentMethodFee: String,
    val status: String,
    val tax: Int,
    val uuid: String,
) {
    companion object {
        fun from(rawDeeplink: String): DeeplinkEntity {
            val uri = rawDeeplink.toUri()
            return DeeplinkEntity(
                hmac = uri.getQueryParameter("hmac").orEmpty(),
                sessionId = uri.getQueryParameter("session_id").orEmpty(),
                time = Instant.fromEpochMilliseconds(uri.getQueryParameter("timestamp")?.toLongOrNull() ?: System.currentTimeMillis())
                    .toLocalDateTime(TimeZone.currentSystemDefault()),
                amount = uri.getQueryParameter("transaction[amount]")?.toIntOrNull() ?: 0,
                currency = Currency.parse(uri.getQueryParameter("transaction[currency]").orEmpty()),
                totalAmount = uri.getQueryParameter("transaction[total_amount]")?.toIntOrNull() ?: 0,
                paymentMethod = uri.getQueryParameter("transaction[payment_method]").orEmpty(),
                paymentMethodFee = uri.getQueryParameter("transaction[payment_method_fee]").orEmpty(),
                status = uri.getQueryParameter("transaction[status]").orEmpty(),
                tax = uri.getQueryParameter("transaction[tax]")?.toIntOrNull() ?: 0,
                uuid = uri.getQueryParameter("transaction[uuid]").orEmpty(),
            )
        }
    }
}
