package com.komoju.android.remote.dtos

import com.google.gson.annotations.SerializedName

data class CreateSessionResponse(
    @SerializedName("sessionId")
    val sessionId: String,
)
