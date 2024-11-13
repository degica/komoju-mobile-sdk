package com.komoju.mobile.sdk.ui.composables

import androidx.compose.runtime.Composable

@Composable
actual fun launchCustomTab(onResult: (Int) -> Unit): Launcher<String> = Launcher()
