package com.komoju.mobile.sdk.remote.apis

import com.komoju.mobile.sdk.KomojuMobileSDKConfiguration
import com.komoju.mobile.sdk.remote.createNetworkClient

internal interface KomojuRemoteApi : AutoCloseable {
    val sessions: SessionApi
    val tokens: TokensApi

    companion object {
        fun create(configuration: KomojuMobileSDKConfiguration): KomojuRemoteApi = KomojuRemoteApiImpl(configuration)
    }
}

internal class KomojuRemoteApiImpl(configuration: KomojuMobileSDKConfiguration) : KomojuRemoteApi {
    private val networkClient by lazy { createNetworkClient(configuration) }

    override val sessions: SessionApi by lazy { SessionApiImpl(networkClient) }

    override val tokens: TokensApi by lazy { TokenApiImpl(networkClient) }

    override fun close() {
        networkClient.close()
    }
}
