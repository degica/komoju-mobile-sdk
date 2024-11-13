package com.komoju.mobile.sdk.ui.composables

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import com.komoju.mobile.sdk.ui.theme.LocalConfigurableTheme
import com.komoju.mobile.sdk.ui.theme.toColor

@Composable
internal fun ThemedCircularProgressIndicator() {
    val configuration = LocalConfigurableTheme.current
    CircularProgressIndicator(color = configuration.loaderColor.toColor())
}
