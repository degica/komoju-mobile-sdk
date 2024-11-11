package com.komoju.mobile.sdk.ui.screens.success

import com.komoju.mobile.sdk.navigation.RouterStateScreenModel

internal class PaymentSuccessScreenModel : RouterStateScreenModel<Unit>(Unit) {
    fun onCloseButtonClicked() {
        mutableRouter.pop()
    }

    fun onBackToStoreButtonClicked() {
        mutableRouter.pop()
    }
}
