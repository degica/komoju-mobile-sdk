package com.komoju.android.ui.screens.store

import androidx.compose.ui.graphics.toArgb
import com.komoju.android.sdk.KomojuAndroidSDK
import com.komoju.android.ui.theme.KomojuDarkGreen

val komojuConfigurableTheme = KomojuAndroidSDK.ConfigurableTheme.default.copy(
    primaryColorInt = KomojuDarkGreen.toArgb(),
    primaryShapeCornerRadiusInDp = 24,
    loaderColorInt = KomojuDarkGreen.toArgb(),
)
