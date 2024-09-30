package com.komoju.mobile.sdk.remote.apis

import com.komoju.mobile.sdk.entities.SecureTokenRequest
import com.komoju.mobile.sdk.entities.SecureTokenResponse
import com.komoju.mobile.sdk.remote.dtos.SecureTokenRequestDto
import com.komoju.mobile.sdk.remote.dtos.SecureTokenResponseDto
import com.komoju.mobile.sdk.remote.dtos.SecureTokenVerificationResponseDto
import com.komoju.mobile.sdk.remote.mappers.SecureTokenResponseMapper
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

interface TokensApi {
    suspend fun generateSecureToken(request: SecureTokenRequest): Result<SecureTokenResponse>
    suspend fun verifySecureToken(token: String): Result<Boolean>
}

internal class TokenApiImpl(private val networkClient: HttpClient) : TokensApi {
    override suspend fun generateSecureToken(request: SecureTokenRequest) = runCatching {
        networkClient.post("v1/secure_tokens") {
            contentType(ContentType.Application.Json)
            setBody(SecureTokenRequestDto.fromEntity(request))
        }.body<SecureTokenResponseDto>()
    }.mapCatching(SecureTokenResponseMapper::map)

    override suspend fun verifySecureToken(token: String): Result<Boolean> = runCatching {
        networkClient.get("v1/secure_tokens/$token").body<SecureTokenVerificationResponseDto>().verificationStatus == "OK"
    }
}
