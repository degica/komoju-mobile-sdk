package com.degica.komoju.android.sdk.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.degica.komoju.android.sdk.KomojuSDK
import com.degica.komoju.android.sdk.ui.screens.awating.KonbiniAwaitingPaymentScreen
import com.degica.komoju.android.sdk.ui.screens.failed.PaymentFailedScreen
import com.degica.komoju.android.sdk.ui.screens.failed.Reason
import com.degica.komoju.android.sdk.ui.screens.success.PaymentSuccessScreen
import com.degica.komoju.android.sdk.ui.screens.webview.WebViewScreen
import com.degica.komoju.mobile.sdk.entities.Payment

internal sealed class Router {
    data object Pop : Router()
    data object PopToRoot : Router()
    data class Push(val route: KomojuPaymentRoute) : Router()
    data class Replace(val route: KomojuPaymentRoute) : Router()
    data class ReplaceAll(val route: KomojuPaymentRoute) : Router()
    data class Handle(val url: String) : Router()
}

internal sealed interface KomojuPaymentRoute {
    data class KonbiniAwaitingPayment(val configuration: KomojuSDK.Configuration, val payment: Payment) : KomojuPaymentRoute
    data class WebView(val url: String, val canComeBack: Boolean = false) : KomojuPaymentRoute
    data object PaymentSuccess : KomojuPaymentRoute
    data class PaymentFailed(val reason: Reason) : KomojuPaymentRoute

    val screen
        get() = when (this) {
            is WebView -> WebViewScreen(this)
            is KonbiniAwaitingPayment -> KonbiniAwaitingPaymentScreen(this)
            is PaymentFailed -> PaymentFailedScreen(reason)
            is PaymentSuccess -> PaymentSuccessScreen()
        }
}

@Composable
internal fun RouterEffect(router: Router?, onHandled: () -> Unit) {
    val navigator = LocalNavigator.currentOrThrow
    val context = LocalContext.current
    LaunchedEffect(router) {
        when (router) {
            is Router.Pop -> navigator.pop()
            is Router.PopToRoot -> navigator.popUntilRoot()
            is Router.Push -> navigator.push(router.route.screen)
            is Router.Replace -> navigator.replace(router.route.screen)
            is Router.ReplaceAll -> navigator.replaceAll(router.route.screen)
            is Router.Handle -> when (router.url.canOpenAnApp(context)) {
                true -> context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(router.url)))
                false -> navigator.push(KomojuPaymentRoute.WebView(router.url).screen)
            }
            else -> Unit
        }
        onHandled()
    }
}

internal fun String.canOpenAnApp(context: Context): Boolean = Intent(Intent.ACTION_VIEW).apply {
    data = Uri.parse(this@canOpenAnApp)
}.resolveActivity(context.packageManager) != null
