package com.komoju.android.sdk.ui.composables

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.komoju.android.sdk.ui.theme.LocalConfigurableTheme

@Composable
internal fun ThemedCircularProgressIndicator() {
    val configuration = LocalConfigurableTheme.current
    CircularProgressIndicator(color = Color(configuration.loaderColor))
}
