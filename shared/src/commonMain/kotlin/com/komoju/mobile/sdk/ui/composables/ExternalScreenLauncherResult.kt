package com.komoju.mobile.sdk.ui.composables

import androidx.compose.runtime.Composable

@Composable
expect fun launchCustomTab(onResult: (Int) -> Unit): Launcher<String>

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class Launcher<I> {
    fun launch(input: I)
}
