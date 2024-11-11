package com.komoju.mobile.sdk.entities

import com.komoju.mobile.sdk.types.OffSitePaymentType

internal sealed interface PaymentMethod {
    val hashedGateway: String
    val exchangeRate: Double
    val currency: String
    val amount: String
    val additionalFields: List<String>

    data class CreditCard(
        override val hashedGateway: String,
        override val exchangeRate: Double,
        override val currency: String,
        override val amount: String,
        override val additionalFields: List<String>,
        val brands: List<String>,
    ) : PaymentMethod

    data class OffSitePayment(
        override val hashedGateway: String,
        override val exchangeRate: Double,
        override val currency: String,
        override val amount: String,
        override val additionalFields: List<String>,
        val type: OffSitePaymentType,
    ) : PaymentMethod

    data class BankTransfer(
        override val hashedGateway: String,
        override val exchangeRate: Double,
        override val currency: String,
        override val amount: String,
        override val additionalFields: List<String>,
        val customerFee: Int,
    ) : PaymentMethod

    data class PayEasy(
        override val hashedGateway: String,
        override val exchangeRate: Double,
        override val currency: String,
        override val amount: String,
        override val additionalFields: List<String>,
        val customerFee: Int,
    ) : PaymentMethod

    data class WebMoney(
        override val hashedGateway: String,
        override val exchangeRate: Double,
        override val currency: String,
        override val amount: String,
        override val additionalFields: List<String>,
    ) : PaymentMethod

    data class BitCash(
        override val hashedGateway: String,
        override val exchangeRate: Double,
        override val currency: String,
        override val amount: String,
        override val additionalFields: List<String>,
    ) : PaymentMethod

    data class NetCash(
        override val hashedGateway: String,
        override val exchangeRate: Double,
        override val currency: String,
        override val amount: String,
        override val additionalFields: List<String>,
    ) : PaymentMethod

    data class Paidy(
        override val hashedGateway: String,
        override val exchangeRate: Double,
        override val currency: String,
        override val amount: String,
        override val additionalFields: List<String>,
        val isOffsite: Boolean,
    ) : PaymentMethod

    data class Konbini(
        override val hashedGateway: String,
        override val exchangeRate: Double,
        override val currency: String,
        override val amount: String,
        override val additionalFields: List<String>,
        val customerFee: Int,
        val brands: List<KonbiniBrand>,
    ) : PaymentMethod {
        sealed interface KonbiniBrand {
            val key: String

            data class SevenEleven(override val key: String, val merchantNumber: String) : KonbiniBrand

            data class Lawson(override val key: String) : KonbiniBrand

            data class FamilyMart(override val key: String) : KonbiniBrand

            data class MiniStop(override val key: String) : KonbiniBrand

            data class DailyYamazaki(override val key: String) : KonbiniBrand

            data class SeicoMart(override val key: String) : KonbiniBrand
        }
    }

    data class Other(
        override val hashedGateway: String,
        override val exchangeRate: Double,
        override val currency: String,
        override val amount: String,
        override val additionalFields: List<String>,
    ) : PaymentMethod
}
