package com.degica.komoju.mobile.sdk.remote.apis

import com.degica.komoju.mobile.sdk.entities.Session
import com.degica.komoju.mobile.sdk.i18n.I18nTexts
import com.degica.komoju.mobile.sdk.remote.dtos.SessionResponse
import com.degica.komoju.mobile.sdk.remote.mappers.SessionMapper
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface SessionApi {
    suspend fun show(id: String): Result<Session>
}

internal class SessionApiImpl(private val language: I18nTexts, private val networkClient: HttpClient) : SessionApi {
    override suspend fun show(id: String): Result<Session> = runCatching {
        networkClient.get("v1/sessions/$id").body<SessionResponse>()
    }.map {
        SessionMapper.map(it, language)
    }
}
