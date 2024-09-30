package com.komoju.mobile.sdk.entities

data class SecureTokenRequest(
    val amount: String,
    val currency: String,
    val returnUrl: String,
    val expiryMonth: String,
    val expiryYear: String,
    val cvv: String,
    val cardNumber: String,
    val cardHolderName: String,
)
