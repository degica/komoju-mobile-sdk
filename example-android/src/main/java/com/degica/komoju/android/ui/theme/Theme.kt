package com.degica.komoju.android.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = KomojuDarkGreen,
    secondary = KomojuDarkGreen,
    tertiary = KomojuDarkGreen,
    surfaceContainer = Color.White,
    surface = Color.White,
    background = Color.White,
)

@Composable
fun KomojuFakeStoreTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content,
    )
}
