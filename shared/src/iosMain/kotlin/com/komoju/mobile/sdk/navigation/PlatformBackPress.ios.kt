package com.komoju.mobile.sdk.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.interop.LocalUIViewController
import platform.UIKit.UIViewController

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
internal actual class PlatformBackPressDispatcher {
    private var uiViewController: UIViewController? = null

    @Composable
    fun Create() {
        uiViewController = LocalUIViewController.current
    }

    actual fun onBackPressed() {
        uiViewController?.dismissViewControllerAnimated(true) {
            // Nothing to do here
        }
    }
}

@Composable
internal actual fun rememberPlatformBackPressDispatcher(): PlatformBackPressDispatcher = remember {
    PlatformBackPressDispatcher()
}.apply {
    Create()
}
