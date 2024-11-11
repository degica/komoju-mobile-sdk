package com.komoju.mobile.sdk.navigation

import androidx.compose.runtime.Composable

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
internal expect class PlatformBackPressDispatcher private constructor() {
    fun onBackPressed()
}

@Composable
internal expect fun rememberPlatformBackPressDispatcher(): PlatformBackPressDispatcher
