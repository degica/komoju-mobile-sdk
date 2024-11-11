package com.komoju.mobile.sdk.navigation

import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class PlatformBackPressDispatcher {
    private var onBackPressedDispatcher: OnBackPressedDispatcher? = null

    @Composable
    fun Create() {
        onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
        DisposableEffect(Unit) {
            onDispose {
                onBackPressedDispatcher = null
            }
        }
    }

    actual fun onBackPressed() {
        onBackPressedDispatcher?.onBackPressed()
    }
}

@Composable
actual fun rememberPlatformBackPressDispatcher(): PlatformBackPressDispatcher = remember {
    PlatformBackPressDispatcher()
}.apply {
    Create()
}
