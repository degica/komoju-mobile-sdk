package com.degica.komoju.mobile.sdk.remote.apis

import com.degica.komoju.mobile.sdk.entities.Payment
import com.degica.komoju.mobile.sdk.entities.PaymentRequest
import com.degica.komoju.mobile.sdk.entities.Session
import com.degica.komoju.mobile.sdk.i18n.I18nTexts
import com.degica.komoju.mobile.sdk.remote.dtos.PaymentErrorResponseDto
import com.degica.komoju.mobile.sdk.remote.dtos.PaymentRequestDto
import com.degica.komoju.mobile.sdk.remote.dtos.PaymentResponseDto
import com.degica.komoju.mobile.sdk.remote.dtos.SessionResponse
import com.degica.komoju.mobile.sdk.remote.mappers.PaymentErrorMapper
import com.degica.komoju.mobile.sdk.remote.mappers.PaymentMapper
import com.degica.komoju.mobile.sdk.remote.mappers.SessionMapper
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

interface SessionApi {
    suspend fun show(id: String): Result<Session>
    suspend fun pay(id: String, paymentRequest: PaymentRequest): Result<Payment>
    suspend fun refreshPayment(id: String): Result<Payment>
}

internal class SessionApiImpl(private val language: I18nTexts, private val networkClient: HttpClient) : SessionApi {
    override suspend fun show(id: String): Result<Session> = runCatching {
        networkClient.get("v1/sessions/$id").body<SessionResponse>()
    }.mapCatching { response ->
        SessionMapper.map(response, language)
    }

    override suspend fun pay(id: String, paymentRequest: PaymentRequest) = runCatching {
        networkClient.post("v1/sessions/$id/pay") {
            contentType(ContentType.Application.Json)
            setBody(PaymentRequestDto.from(paymentRequest))
        }.run {
            when (status) {
                HttpStatusCode.OK -> body<PaymentResponseDto>()
                else -> body<PaymentErrorResponseDto>()
            }
        }
    }.mapCatching { response ->
        when (response) {
            is PaymentResponseDto -> PaymentMapper.map(response)
            is PaymentErrorResponseDto -> PaymentErrorMapper.map(paymentRequest, response)
            else -> error("Invalid response")
        }
    }

    override suspend fun refreshPayment(id: String): Result<Payment> = runCatching {
        networkClient.get("v1/sessions/$id").body<SessionResponse>()
    }.mapCatching { response ->
        PaymentMapper.map(response)
    }
}
