package com.komoju.mobile.sdk.ui.composables

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.Composable
import com.komoju.mobile.sdk.utils.OffsiteCustomTabResultContract

@Composable
actual fun launchCustomTab(onResult: (Int) -> Unit): Launcher<String> =
    Launcher(rememberLauncherForActivityResult(OffsiteCustomTabResultContract(), onResult = onResult))
