package com.komoju.mobile.sdk.entities

internal data class SecureTokenResponse(val id: String, val status: Status, val authURL: String) {
    enum class Status {
        OK,
        SKIPPED,
        NEEDS_VERIFY,
        ERRORED,
        UNKNOWN,
    }
}
