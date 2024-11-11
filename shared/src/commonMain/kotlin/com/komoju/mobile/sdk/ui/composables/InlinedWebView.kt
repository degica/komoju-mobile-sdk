package com.komoju.mobile.sdk.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eygraber.uri.Uri
import com.multiplatform.webview.request.RequestInterceptor
import com.multiplatform.webview.request.WebRequest
import com.multiplatform.webview.request.WebRequestInterceptResult
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.WebViewNavigator
import com.multiplatform.webview.web.rememberWebViewNavigator
import com.multiplatform.webview.web.rememberWebViewState
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.delay

@Composable
internal fun InlinedWebView(
    modifier: Modifier,
    url: String,
    appScheme: String,
    onDone: (String) -> Unit,
    onChallengePresented: () -> Unit,
    onCloseButtonClicked: () -> Unit,
) {
    LaunchedEffect(url) {
        delay(5.seconds) // TODO: Workaround as of now, replace with actual challenge detection
        onChallengePresented()
    }
    val state = rememberWebViewState(url)
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
        ) {
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
            Image(
                imageVector = Icons.Rounded.Close,
                contentDescription = "Close Payment Sheet",
                modifier = Modifier
                    .clickable(
                        onClick = {
                            onCloseButtonClicked()
                        },
                    )
                    .padding(16.dp),
            )
        }
        WebView(
            modifier = Modifier.fillMaxSize(),
            state = state,
            captureBackPresses = false,
            navigator = rememberWebViewNavigator(
                requestInterceptor = WebViewRequestInterceptor(
                    onDeeplinkCaptured = onDone,
                    appScheme = appScheme,
                ),
            ),
        )
        DisposableEffect(Unit) {
            state.webSettings.apply {
                isJavaScriptEnabled = true
                androidWebSettings.apply {
                    backgroundColor = Color.Transparent
                    domStorageEnabled = true
                }
            }
            onDispose { }
        }
    }
}

private class WebViewRequestInterceptor(private val onDeeplinkCaptured: (String) -> Unit, private val appScheme: String) :
    RequestInterceptor {
    override fun onInterceptUrlRequest(request: WebRequest, navigator: WebViewNavigator): WebRequestInterceptResult = request.checkAndOpen()

    private fun WebRequest.checkAndOpen(): WebRequestInterceptResult {
        try {
            val uri = Uri.parse(url)
            if (uri.scheme == appScheme) {
                onDeeplinkCaptured(url)
                return WebRequestInterceptResult.Reject
            } else {
                error("Unsupported scheme for deeplink, load in webView Instead.")
            }
        } catch (_: Exception) {
            return WebRequestInterceptResult.Allow
        }
    }
}
