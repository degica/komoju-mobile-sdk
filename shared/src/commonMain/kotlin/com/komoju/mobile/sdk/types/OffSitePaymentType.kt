package com.komoju.mobile.sdk.types

enum class OffSitePaymentType(val id: String) {
    AU_PAY("aupay"),
    ALI_PAY("alipay"),
    MER_PAY("merpay"),
    PAY_PAY("paypay"),
    RAKUTEN_PAY("rakutenpay"),
    LINE_PAY("linepay"),
    UNKNOWN(""),
    ;

    companion object {
        val supportedTypes = entries.map { it.id }
        fun fromId(id: String): OffSitePaymentType = entries.find { it.id == id } ?: UNKNOWN
    }
}
