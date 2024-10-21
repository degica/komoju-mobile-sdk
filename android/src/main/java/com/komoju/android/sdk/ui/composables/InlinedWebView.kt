package com.komoju.android.sdk.ui.composables

import android.annotation.SuppressLint
import android.graphics.Color
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.kevinnzou.web.AccompanistWebViewClient
import com.kevinnzou.web.WebView
import com.kevinnzou.web.rememberWebViewState
import com.komoju.android.sdk.R

@SuppressLint("SetJavaScriptEnabled")
@Composable
internal fun InlinedWebView(
    modifier: Modifier,
    url: String,
    onDone: (String) -> Unit,
    onChallengePresented: () -> Unit,
    onCloseButtonClicked: () -> Unit,
) {
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
                        indication = ripple(bounded = true, radius = 24.dp),
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = {
                            onCloseButtonClicked()
                        },
                    )
                    .padding(16.dp),
            )
        }
        WebView(
            modifier = Modifier.weight(1f),
            state = state,
            onCreated = { nativeWebView ->
                nativeWebView.clipToOutline = true
                nativeWebView.setBackgroundColor(Color.TRANSPARENT)
                nativeWebView.settings.apply {
                    domStorageEnabled = true
                    javaScriptEnabled = true
                }
            },
            captureBackPresses = false,
            client = remember { InlinedWebViewClient(onDone, onChallengePresented) },
        )
    }
}

private class InlinedWebViewClient(private val onDeeplinkCaptured: (String) -> Unit, private val onChallengePresented: () -> Unit) :
    AccompanistWebViewClient() {
    @Deprecated("Deprecated in Java")
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean = view.checkAndOpen(url)
    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean = view.checkAndOpen(request.url.toString())

    private fun WebView.checkAndOpen(url: String): Boolean {
        try {
            val uri = url.toUri()
            if (uri.scheme == resources.getString(R.string.komoju_consumer_app_scheme)) {
                onDeeplinkCaptured(url)
                return true
            } else {
                error("Unsupported scheme for deeplink, load in webView Instead.")
            }
        } catch (_: Exception) {
            loadUrl(url)
            return false
        }
    }

    override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest?): WebResourceResponse? {
        if (request?.url.toString().contains("acs-challenge.testlab.3dsecure.cloud")) {
            onChallengePresented()
        }
        return super.shouldInterceptRequest(view, request)
    }
}
