package com.komoju.mobile.sdk.entities

import com.komoju.mobile.sdk.entities.PaymentMethod.Konbini.KonbiniBrand

sealed interface PaymentRequest {
    val paymentMethod: PaymentMethod

    data class Konbini(override val paymentMethod: PaymentMethod.Konbini, val konbiniBrand: KonbiniBrand, val email: String) : PaymentRequest
    data class Paidy(override val paymentMethod: PaymentMethod.Paidy, val fullName: String, val phoneNumber: String) : PaymentRequest
    data class OffSitePaymentRequest(override val paymentMethod: PaymentMethod.OffSitePayment) : PaymentRequest
    data class NetCash(override val paymentMethod: PaymentMethod.NetCash, val netCashId: String) : PaymentRequest
    data class BitCash(override val paymentMethod: PaymentMethod.BitCash, val bitCashId: String) : PaymentRequest
    data class WebMoney(override val paymentMethod: PaymentMethod.WebMoney, val prepaidNumber: String) : PaymentRequest
}
