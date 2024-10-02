package com.komoju.android.ui.screens.store

import androidx.compose.ui.graphics.toArgb
import com.komoju.android.sdk.ui.theme.ConfigurableTheme
import com.komoju.android.ui.theme.KomojuDarkGreen

val komojuConfigurableTheme = ConfigurableTheme.default.copy(
    primaryButtonColor = KomojuDarkGreen.toArgb(),
    primaryButtonCornerRadiusInDP = 24,
    loaderColor = KomojuDarkGreen.toArgb(),
)
