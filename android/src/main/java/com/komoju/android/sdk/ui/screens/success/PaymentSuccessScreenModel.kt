package com.komoju.android.sdk.ui.screens.success

import com.komoju.android.sdk.navigation.RouterStateScreenModel

internal class PaymentSuccessScreenModel : RouterStateScreenModel<Unit>(Unit) {
    fun onCloseButtonClicked() {
        mutableRouter.pop()
    }

    fun onBackToStoreButtonClicked() {
        mutableRouter.pop()
    }
}
