package com.komoju.mobile.sdk.ui.screens.webview

import com.komoju.mobile.sdk.KomojuMobileSDKConfiguration
import com.komoju.mobile.sdk.navigation.RouterStateScreenModel
import com.komoju.mobile.sdk.ui.screens.KomojuPaymentRoute
import com.komoju.mobile.sdk.ui.screens.Router
import com.komoju.mobile.sdk.utils.DeeplinkEntity

internal class WebViewScreenModel : RouterStateScreenModel<Unit>(Unit) {

    fun onBackPressed() {
        mutableRouter.pop()
    }

    fun onNewDeeplink(configuration: KomojuMobileSDKConfiguration, deeplink: String) {
        val deeplinkEntity = DeeplinkEntity.from(deeplink)
        mutableRouter.value = Router.ReplaceAll(
            KomojuPaymentRoute.ProcessPayment(
                configuration = configuration,
                processType = when (deeplinkEntity) {
                    is DeeplinkEntity.Verify.BySecureToken -> KomojuPaymentRoute.ProcessPayment.ProcessType.VerifyTokenAndPay(
                        deeplinkEntity.secureTokenId,
                        amount = deeplinkEntity.amount,
                        currency = deeplinkEntity.currency,
                    )

                    DeeplinkEntity.Verify.BySessionId -> KomojuPaymentRoute.ProcessPayment.ProcessType.Session
                },
            ),
        )
    }
}
