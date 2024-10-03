package com.komoju.android.sdk.ui.screens.webview

import com.komoju.android.sdk.navigation.RouterStateScreenModel

internal class WebViewScreenModel : RouterStateScreenModel<Unit>(Unit) {

    fun onBackPressed() {
        mutableRouter.pop()
    }
}
