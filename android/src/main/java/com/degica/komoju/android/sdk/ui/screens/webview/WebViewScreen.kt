package com.degica.komoju.android.sdk.ui.screens.webview

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.degica.komoju.android.sdk.ui.screens.KomojuPaymentRoute
import com.kevinnzou.web.LoadingState
import com.kevinnzou.web.WebView
import com.kevinnzou.web.rememberWebViewState

internal data class WebViewScreen(val route: KomojuPaymentRoute.WebView) : Screen {
    @Composable
    override fun Content() {
        val state = rememberWebViewState(route.url)
        var showBackPressDialog by remember { mutableStateOf(false) }
        val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
        if (route.canComeBack.not() && showBackPressDialog.not()) {
            BackHandler {
                showBackPressDialog = true
            }
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
                                indication = ripple(bounded = true, radius = 24.dp),
                                interactionSource = remember { MutableInteractionSource() },
                                onClick = {
                                    onBackPressedDispatcher?.onBackPressed()
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
                                indication = ripple(bounded = true, radius = 24.dp),
                                interactionSource = remember { MutableInteractionSource() },
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
                )
            }
            WebView(
                modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface),
                state = state,
                captureBackPresses = false,
            )
            if (showBackPressDialog) {
                AlertDialog(
                    onDismissRequest = {
                        showBackPressDialog = false
                    },
                    confirmButton = {
                        Text(
                            text = "Yes",
                            modifier = Modifier
                                .clickable {
                                    onBackPressedDispatcher?.onBackPressed()
                                }
                                .padding(16.dp),
                        )
                    },
                    dismissButton = {
                        Text(
                            text = "No",
                            modifier = Modifier
                                .clickable {
                                    showBackPressDialog = false
                                }
                                .padding(16.dp),
                        )
                    },
                    text = {
                        Text(text = "Are you sure you want to cancel the payment?", modifier = Modifier.padding(8.dp))
                    },
                    title = {
                        Text(text = "Cancel Payment?")
                    },
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun WebViewScreenPreview() {
    WebViewScreen(KomojuPaymentRoute.WebView("https://www.komoju.com/"))
}
