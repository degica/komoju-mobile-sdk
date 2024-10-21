package com.komoju.mobile.sdk.remote.apis

import com.komoju.mobile.sdk.i18n.I18nTexts
import com.komoju.mobile.sdk.remote.createNetworkClient

interface KomojuRemoteApi : AutoCloseable {
    val sessions: SessionApi
    val tokens: TokensApi

    companion object {
        fun create(publishableKey: String?, language: String): KomojuRemoteApi = KomojuRemoteApiImpl(publishableKey, language)
    }
}

internal class KomojuRemoteApiImpl(publishableKey: String?, language: String) : KomojuRemoteApi {
    private val networkClient by lazy { createNetworkClient(publishableKey) }

    private val i18nTexts by lazy { I18nTexts(language) }

    override val sessions: SessionApi by lazy { SessionApiImpl(i18nTexts, networkClient) }

    override val tokens: TokensApi by lazy { TokenApiImpl(networkClient) }

    override fun close() {
        networkClient.close()
    }
}
