package com.komoju.android.sdk.ui.screens

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.komoju.android.sdk.KomojuSDK
import com.komoju.android.sdk.navigation.paymentResultScreenModel
import com.komoju.android.sdk.ui.screens.awating.KonbiniAwaitingPaymentScreen
import com.komoju.android.sdk.ui.screens.failed.PaymentFailedScreen
import com.komoju.android.sdk.ui.screens.failed.Reason
import com.komoju.android.sdk.ui.screens.success.PaymentSuccessScreen
import com.komoju.android.sdk.ui.screens.verify.ProcessPaymentScreen
import com.komoju.android.sdk.ui.screens.webview.WebViewScreen
import com.komoju.mobile.sdk.entities.Payment

internal sealed class Router {
    data object Pop : Router()
    data object PopAll : Router()
    data object PopToRoot : Router()
    data class Push(val route: KomojuPaymentRoute) : Router()
    data class Replace(val route: KomojuPaymentRoute) : Router()
    data class ReplaceAll(val route: KomojuPaymentRoute) : Router()
    data class SetPaymentResultAndPop(val result: KomojuSDK.PaymentResult = KomojuSDK.PaymentResult(false)) : Router()
}

internal sealed interface KomojuPaymentRoute {
    data class KonbiniAwaitingPayment(val configuration: KomojuSDK.Configuration, val payment: Payment) : KomojuPaymentRoute

    data class WebView(val url: String, val canComeBack: Boolean = false, val isJavaScriptEnabled: Boolean = false) : KomojuPaymentRoute

    data object PaymentSuccess : KomojuPaymentRoute
    data class PaymentFailed(val reason: Reason) : KomojuPaymentRoute
    data class ProcessPayment(val configuration: KomojuSDK.Configuration, val processType: ProcessType) : KomojuPaymentRoute {
        sealed interface ProcessType {
            data object Session : ProcessType
            data class VerifyTokenAndPay(val token: String, val amount: String, val currency: String) : ProcessType

            data class PayByToken(val token: String, val amount: String, val currency: String) : ProcessType
        }
    }

    val screen
        get() = when (this) {
            is WebView -> WebViewScreen(this)
            is KonbiniAwaitingPayment -> KonbiniAwaitingPaymentScreen(this)
            is PaymentFailed -> PaymentFailedScreen(this)
            is PaymentSuccess -> PaymentSuccessScreen()
            is ProcessPayment -> ProcessPaymentScreen(this)
        }
}

@Composable
internal fun RouterEffect(routerState: State<Router?>, onHandled: () -> Unit) {
    val navigator = LocalNavigator.currentOrThrow
    val router = routerState.value
    val backPressDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val resultScreenModel = navigator.paymentResultScreenModel()
    LaunchedEffect(router) {
        when (router) {
            is Router.Pop -> if (navigator.pop().not()) backPressDispatcher?.onBackPressed()
            is Router.PopAll -> navigator.popAll()
            is Router.PopToRoot -> navigator.popUntilRoot()
            is Router.Push -> navigator.push(router.route.screen)
            is Router.Replace -> navigator.replace(router.route.screen)
            is Router.ReplaceAll -> navigator.replaceAll(router.route.screen)

            null -> Unit
            is Router.SetPaymentResultAndPop -> {
                resultScreenModel.setResult(router.result)
                if (navigator.pop().not()) backPressDispatcher?.onBackPressed()
            }
        }
        onHandled()
    }
}
