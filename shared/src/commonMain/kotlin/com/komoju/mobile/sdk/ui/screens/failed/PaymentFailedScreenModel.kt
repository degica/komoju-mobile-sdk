package com.komoju.mobile.sdk.ui.screens.failed

import com.komoju.mobile.sdk.navigation.RouterStateScreenModel

internal class PaymentFailedScreenModel : RouterStateScreenModel<Unit>(Unit) {
    fun onCloseButtonClicked() {
        mutableRouter.pop()
    }

    fun onBackToStoreButtonClicked() {
        mutableRouter.pop()
    }
}
