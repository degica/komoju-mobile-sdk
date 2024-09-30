package com.komoju.mobile.sdk.remote.apis

import com.komoju.mobile.sdk.i18n.I18nTexts
import com.komoju.mobile.sdk.remote.createNetworkClient

class KomojuRemoteApi(publishableKey: String?, language: String) {
    private val networkClient by lazy { createNetworkClient(publishableKey) }

    private val i18nTexts by lazy { I18nTexts(language) }

    val sessions: SessionApi by lazy { SessionApiImpl(i18nTexts, networkClient) }
    val tokens: TokensApi by lazy { TokenApiImpl(networkClient) }
}
