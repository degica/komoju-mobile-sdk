package com.degica.komoju.android.ui.remote.dtos

import com.google.gson.annotations.SerializedName

data class PublishableKeyResponse(
    @SerializedName("publishableKey")
    val publishableKey: String,
)
