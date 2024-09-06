package com.degica.komoju.android.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme =
    lightColorScheme(
        primary = KomojuLightGreen,
        secondary = KomojuDarkGreen,
        tertiary = KomojuLightGreen,
    )

@Composable
fun KomojuMobileSdkDemoTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content,
    )
}
