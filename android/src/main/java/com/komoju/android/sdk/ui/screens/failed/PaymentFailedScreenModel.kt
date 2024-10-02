package com.komoju.android.sdk.ui.screens.failed

import com.komoju.android.sdk.navigation.RouterStateScreenModel

internal class PaymentFailedScreenModel : RouterStateScreenModel<Unit>(Unit) {
    fun onCloseButtonClicked() {
        mutableRouter.pop()
    }

    fun onBackToStoreButtonClicked() {
        mutableRouter.pop()
    }
}
