package com.komoju.mobile.sdk.remote

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.encodeBase64
import kotlinx.serialization.json.Json

private const val BASE_URL = "https://komoju.com/api/"

internal fun createNetworkClient(publishableKey: String?) = HttpClient {
    defaultRequest {
        url(BASE_URL)
        headers.append("Authorization", value = "Basic ${publishableKey?.encodeBase64()}")
        headers.append("KOMOJU-VIA", "mobile_${PLATFORM.lowercase()}")
        headers.append("X-KOMOJU-API-VERSION", "2024-07-15")
        headers.append("Accept", "application/json")
        headers.append("Content-Type", "application/json")
    }
    install(ContentNegotiation) {
        json(
            Json {
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
            },
        )
    }
}

expect val PLATFORM: String
