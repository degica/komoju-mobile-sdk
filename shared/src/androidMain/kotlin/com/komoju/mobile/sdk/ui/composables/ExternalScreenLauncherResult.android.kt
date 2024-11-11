package com.komoju.mobile.sdk.ui.composables

import androidx.activity.result.ActivityResultLauncher

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class Launcher<I>(private val activityLauncher: ActivityResultLauncher<I>) {
    actual fun launch(input: I) {
        activityLauncher.launch(input)
    }
}
