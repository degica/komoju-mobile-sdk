package com.komoju.mobile.sdk.ui.screens.webview

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.internal.BackHandler
import komoju_mobile_sdk.shared.generated.resources.Res
import com.komoju.mobile.sdk.ui.screens.KomojuPaymentRoute
import com.komoju.mobile.sdk.ui.screens.RouterEffect
import com.komoju.mobile.sdk.ui.theme.LocalConfigurableTheme
import com.komoju.mobile.sdk.ui.theme.toColor
import com.multiplatform.webview.web.LoadingState
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewNavigator
import com.multiplatform.webview.web.rememberWebViewState
import komoju_mobile_sdk.shared.generated.resources.komoju_are_you_sure_you_want_to_cancel_the_payment
import komoju_mobile_sdk.shared.generated.resources.komoju_cancel_payment
import komoju_mobile_sdk.shared.generated.resources.komoju_no
import komoju_mobile_sdk.shared.generated.resources.komoju_yes
import org.jetbrains.compose.resources.stringResource

internal data class WebViewScreen(val route: KomojuPaymentRoute.WebView) : Screen {
    @Composable
    override fun Content() {
        WebViewScreenContent(route)
    }
}

@OptIn(InternalVoyagerApi::class)
@Composable
private fun Screen.WebViewScreenContent(route: KomojuPaymentRoute.WebView) {
    val state = rememberWebViewState(route.url)
    var showBackPressDialog by remember { mutableStateOf(false) }
    val screenModel = rememberScreenModel { WebViewScreenModel() }
    RouterEffect(screenModel.router.collectAsStateWithLifecycle(), screenModel::onRouteConsumed)
    BackHandler(enabled = route.canComeBack.not() && showBackPressDialog.not()) {
        showBackPressDialog = true
    }
    Column(modifier = Modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
        ) {
            if (route.canComeBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .clickable(
                            onClick = {
                                screenModel.onBackPressed()
                            },
                        )
                        .padding(16.dp),
                )
            }
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterVertically),
                text = state.pageTitle.orEmpty(),
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            if (route.canComeBack.not()) {
                Image(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Close Payment Sheet",
                    modifier = Modifier
                        .clickable(
                            onClick = {
                                showBackPressDialog = true
                            },
                        )
                        .padding(16.dp),
                )
            }
        }
        val loadingState = state.loadingState
        if (loadingState is LoadingState.Loading) {
            LinearProgressIndicator(
                progress = { loadingState.progress },
                modifier = Modifier.fillMaxWidth(),
                color = LocalConfigurableTheme.current.loaderColor.toColor(),
            )
        }
        WebView(
            modifier = Modifier
                .fillMaxSize(),
            state = state,
            captureBackPresses = false,
            navigator = rememberWebViewNavigator(
                requestInterceptor = WebViewRequestInterceptor(route.configuration.appScheme) {
                    screenModel.onNewDeeplink(route.configuration, it)
                },
            ),
        )
        if (showBackPressDialog) {
            AlertDialog(
                onDismissRequest = {
                    showBackPressDialog = false
                },
                confirmButton = {
                    Text(
                        text = stringResource(Res.string.komoju_yes),
                        modifier = Modifier
                            .clickable {
                                screenModel.onBackPressed()
                            }
                            .padding(16.dp),
                    )
                },
                dismissButton = {
                    Text(
                        text = stringResource(Res.string.komoju_no),
                        modifier = Modifier
                            .clickable {
                                showBackPressDialog = false
                            }
                            .padding(16.dp),
                    )
                },
                text = {
                    Text(
                        text = stringResource(Res.string.komoju_are_you_sure_you_want_to_cancel_the_payment),
                        modifier = Modifier.padding(8.dp),
                    )
                },
                title = {
                    Text(text = stringResource(Res.string.komoju_cancel_payment))
                },
            )
        }
    }
}
