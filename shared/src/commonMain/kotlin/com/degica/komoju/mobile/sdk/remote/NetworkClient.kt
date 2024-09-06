package com.degica.komoju.mobile.sdk.remote

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.appendIfNameAbsent
import io.ktor.util.encodeBase64
import kotlinx.serialization.json.Json

private const val BASE_URL = "https://komoju.com/api/"

internal fun createNetworkClient(publishableKey: String) = HttpClient {
    defaultRequest {
        url(BASE_URL)
        headers.append("authorization", "Basic ${publishableKey.encodeBase64()}")
        headers.appendIfNameAbsent("Accept", "application/json")
        headers.appendIfNameAbsent("Content-Type", "application/json")
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
