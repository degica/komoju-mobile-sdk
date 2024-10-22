package com.komoju.mobile.sdk.remote.mappers

import com.komoju.mobile.sdk.entities.PaymentMethod
import com.komoju.mobile.sdk.entities.PaymentMethod.Konbini.KonbiniBrand
import com.komoju.mobile.sdk.entities.Session
import com.komoju.mobile.sdk.remote.dtos.SessionResponse
import com.komoju.mobile.sdk.types.OffSitePaymentType
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject

internal object SessionMapper {
    fun map(response: SessionResponse): Session = Session(
        paymentMethods = response.paymentMethods?.mapNotNull { paymentMethod ->
            when (val paymentMethodType = paymentMethod?.type) {
                "credit_card" -> PaymentMethod.CreditCard(
                    hashedGateway = paymentMethod.hashedGateway.orEmpty(),
                    exchangeRate = paymentMethod.exchangeRate ?: 1.0,
                    currency = paymentMethod.currency.orEmpty(),
                    amount = (response.amount ?: 0).toString(),
                    additionalFields = paymentMethod.additionalFields?.filterNotNull().orEmpty(),
                    brands = (paymentMethod.brands as? JsonArray)?.map { it.toString() }.orEmpty(),
                )

                "konbini" -> PaymentMethod.Konbini(
                    hashedGateway = paymentMethod.hashedGateway.orEmpty(),
                    exchangeRate = paymentMethod.exchangeRate ?: 1.0,
                    currency = paymentMethod.currency.orEmpty(),
                    amount = (response.amount ?: 0).toString(),
                    additionalFields = paymentMethod.additionalFields?.filterNotNull().orEmpty(),
                    customerFee = paymentMethod.customerFee ?: 0,
                    brands = (paymentMethod.brands as? JsonObject)?.mapNotNull { (key, _) ->
                        when (key) {
                            "seven-eleven" -> KonbiniBrand.SevenEleven(
                                key = key,
                                merchantNumber = paymentMethod.sevenElevenMerchantNumber.orEmpty(),
                            )

                            "lawson" -> KonbiniBrand.Lawson(key)
                            "family-mart" -> KonbiniBrand.FamilyMart(key)
                            "ministop" -> KonbiniBrand.MiniStop(key)
                            "daily-yamazaki" -> KonbiniBrand.DailyYamazaki(key)
                            "seicomart" -> KonbiniBrand.SeicoMart(key)
                            else -> null
                        }
                    }.orEmpty(),
                )

                in OffSitePaymentType.supportedTypes -> PaymentMethod.OffSitePayment(
                    hashedGateway = paymentMethod?.hashedGateway.orEmpty(),
                    exchangeRate = paymentMethod?.exchangeRate ?: 1.0,
                    currency = paymentMethod?.currency.orEmpty(),
                    amount = (response.amount ?: 0).toString(),
                    additionalFields = paymentMethod?.additionalFields?.filterNotNull().orEmpty(),
                    type = OffSitePaymentType.fromId(paymentMethodType.orEmpty()),
                )

                "bank_transfer" -> PaymentMethod.BankTransfer(
                    hashedGateway = paymentMethod.hashedGateway.orEmpty(),
                    exchangeRate = paymentMethod.exchangeRate ?: 1.0,
                    currency = paymentMethod.currency.orEmpty(),
                    amount = (response.amount ?: 0).toString(),
                    additionalFields = paymentMethod.additionalFields?.filterNotNull().orEmpty(),
                    customerFee = paymentMethod.customerFee ?: 0,
                )

                "pay_easy" -> PaymentMethod.PayEasy(
                    hashedGateway = paymentMethod.hashedGateway.orEmpty(),
                    exchangeRate = paymentMethod.exchangeRate ?: 1.0,
                    currency = paymentMethod.currency.orEmpty(),
                    amount = (response.amount ?: 0).toString(),
                    additionalFields = paymentMethod.additionalFields?.filterNotNull().orEmpty(),
                    customerFee = paymentMethod.customerFee ?: 0,
                )

                "web_money" -> PaymentMethod.WebMoney(
                    hashedGateway = paymentMethod.hashedGateway.orEmpty(),
                    exchangeRate = paymentMethod.exchangeRate ?: 1.0,
                    currency = paymentMethod.currency.orEmpty(),
                    amount = (response.amount ?: 0).toString(),
                    additionalFields = paymentMethod.additionalFields?.filterNotNull().orEmpty(),
                )

                "bit_cash" -> PaymentMethod.BitCash(
                    hashedGateway = paymentMethod.hashedGateway.orEmpty(),
                    exchangeRate = paymentMethod.exchangeRate ?: 1.0,
                    currency = paymentMethod.currency.orEmpty(),
                    amount = (response.amount ?: 0).toString(),
                    additionalFields = paymentMethod.additionalFields?.filterNotNull().orEmpty(),
                )

                "net_cash" -> PaymentMethod.NetCash(
                    hashedGateway = paymentMethod.hashedGateway.orEmpty(),
                    exchangeRate = paymentMethod.exchangeRate ?: 1.0,
                    currency = paymentMethod.currency.orEmpty(),
                    amount = (response.amount ?: 0).toString(),
                    additionalFields = paymentMethod.additionalFields?.filterNotNull().orEmpty(),
                )

                "paidy" -> PaymentMethod.Paidy(
                    hashedGateway = paymentMethod.hashedGateway.orEmpty(),
                    exchangeRate = paymentMethod.exchangeRate ?: 1.0,
                    currency = paymentMethod.currency.orEmpty(),
                    amount = (response.amount ?: 0).toString(),
                    additionalFields = paymentMethod.additionalFields?.filterNotNull().orEmpty(),
                    isOffsite = paymentMethod.offsite ?: false,
                )

                null -> null
                else -> PaymentMethod.Other(
                    hashedGateway = paymentMethod.hashedGateway.orEmpty(),
                    exchangeRate = paymentMethod.exchangeRate ?: 1.0,
                    currency = paymentMethod.currency.orEmpty(),
                    amount = (response.amount ?: 0).toString(),
                    additionalFields = paymentMethod.additionalFields?.filterNotNull().orEmpty(),
                )
            }
        }.orEmpty(),
    )
}
