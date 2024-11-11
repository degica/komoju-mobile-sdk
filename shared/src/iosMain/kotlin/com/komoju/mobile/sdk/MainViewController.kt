package com.komoju.mobile.sdk

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.uikit.ComposeUIViewControllerDelegate
import androidx.compose.ui.uikit.OnFocusBehavior
import androidx.compose.ui.window.ComposeUIViewController
import com.komoju.mobile.sdk.navigation.PaymentResultScreenModel
import com.komoju.mobile.sdk.navigation.paymentResultScreenModel
import com.komoju.mobile.sdk.ui.screens.KomojuPaymentEntryPoint
import platform.UIKit.UIViewController

internal val LocalViewController = compositionLocalOf<() -> UIViewController> {
    error("LocalViewController not provided")
}

fun MainViewController(
    configuration: KomojuMobileSDKConfiguration,
    onDismiss: (KomojuMobileSDKPaymentResult) -> Unit
) = extendedComposeViewController(configuration, onDismiss)

@OptIn(ExperimentalComposeApi::class)
private fun extendedComposeViewController(
    configuration: KomojuMobileSDKConfiguration,
    onDismiss: (KomojuMobileSDKPaymentResult) -> Unit,
): UIViewController {
    var platformResultScreenModel : PaymentResultScreenModel? = null
    var localViewController: UIViewController? = null
    return ComposeUIViewController(
        configure = {
            onFocusBehavior = OnFocusBehavior.FocusableAboveKeyboard
            opaque = false
            delegate = object : ComposeUIViewControllerDelegate {
                override fun viewWillDisappear(animated: Boolean){
                    super.viewWillDisappear(animated)
                    onDismiss(platformResultScreenModel?.result ?: KomojuMobileSDKPaymentResult(isSuccessFul = false))
                }
            }
        },
        content = {
            CompositionLocalProvider(LocalViewController provides { localViewController!! }) {
                KomojuPaymentEntryPoint(
                    configuration = configuration,
                    onCreated = { navigator ->
                        platformResultScreenModel = navigator.paymentResultScreenModel()
                    },
                )

            }
        },
    ).also {
        localViewController = it
    }
}
