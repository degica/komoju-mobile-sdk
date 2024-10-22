package com.komoju.mobile.sdk.remote.apis

import com.komoju.mobile.sdk.remote.createNetworkClient

interface KomojuRemoteApi : AutoCloseable {
    val sessions: SessionApi
    val tokens: TokensApi

    companion object {
        fun create(publishableKey: String?): KomojuRemoteApi = KomojuRemoteApiImpl(publishableKey)
    }
}

internal class KomojuRemoteApiImpl(publishableKey: String?) : KomojuRemoteApi {
    private val networkClient by lazy { createNetworkClient(publishableKey) }

    override val sessions: SessionApi by lazy { SessionApiImpl(networkClient) }

    override val tokens: TokensApi by lazy { TokenApiImpl(networkClient) }

    override fun close() {
        networkClient.close()
    }
}
