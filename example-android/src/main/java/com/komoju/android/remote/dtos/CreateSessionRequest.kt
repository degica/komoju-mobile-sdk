package com.komoju.android.remote.dtos

import com.google.gson.annotations.SerializedName

data class CreateSessionRequest(
    @SerializedName("amount") val amount: Int,
    @SerializedName("currency") val currency: String,
    @SerializedName("language") val language: String,
)
