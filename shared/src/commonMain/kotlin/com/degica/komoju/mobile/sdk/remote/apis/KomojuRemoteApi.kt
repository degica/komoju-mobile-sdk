package com.degica.komoju.mobile.sdk.remote.apis

import com.degica.komoju.mobile.sdk.i18n.I18nTexts
import com.degica.komoju.mobile.sdk.remote.createNetworkClient

class KomojuRemoteApi(private val publishableKey: String, private val language: String) {
    private val networkClient = createNetworkClient(publishableKey)
    private val i18nTexts = I18nTexts(language)

    val sessions: SessionApi = SessionApiImpl(i18nTexts, networkClient)
}
