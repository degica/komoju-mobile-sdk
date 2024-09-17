package com.degica.komoju.android.sdk.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.degica.komoju.android.sdk.KomojuSDK
import com.degica.komoju.android.sdk.ui.screens.status.PaymentStatusScreen
import com.degica.komoju.android.sdk.ui.screens.webview.WebViewScreen
import com.degica.komoju.mobile.sdk.entities.Payment

internal sealed class Router {
    data object Pop : Router()
    data object PopToRoot : Router()
    data class Push(val route: KomojuPaymentRoute) : Router()
    data class Replace(val route: KomojuPaymentRoute) : Router()
    data class ReplaceAll(val route: KomojuPaymentRoute) : Router()
}

internal sealed interface KomojuPaymentRoute {
    data class Status(val configuration: KomojuSDK.Configuration, val payment: Payment) : KomojuPaymentRoute
    data class WebView(val url: String, val canComeBack: Boolean = false) : KomojuPaymentRoute

    val screen
        get() = when (this) {
            is WebView -> WebViewScreen(this)
            is Status -> PaymentStatusScreen(this)
        }
}

@Composable
internal fun RouterEffect(router: Router?, onHandled: () -> Unit) {
    val navigator = LocalNavigator.currentOrThrow
    LaunchedEffect(router) {
        when (router) {
            is Router.Pop -> navigator.pop()
            is Router.PopToRoot -> navigator.popUntilRoot()
            is Router.Push -> navigator.push(router.route.screen)
            is Router.Replace -> navigator.replace(router.route.screen)
            is Router.ReplaceAll -> navigator.replaceAll(router.route.screen)
            null -> Unit
        }
        onHandled()
    }
}
