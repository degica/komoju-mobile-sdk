package com.komoju.android.sdk.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import com.komoju.android.sdk.KomojuSDK

internal val KomojuLightGreen = Color(0xFF3CC239)
internal val KomojuDarkGreen = Color(0xFF172E44)
internal val Gray50 = Color(0xFFF6F7F9)
internal val Gray200 = Color(0xFFD7DCE0)
internal val Gray500 = Color(0xFF6D7D88)
internal val Gray700 = Color(0xFF45535E)
internal val Red600 = Color(0xFFF04438)

internal val LocalConfigurableTheme = compositionLocalOf<ConfigurableTheme> {
    error("Use KomojuMobileSdkTheme to provide ConfigurableTheme")
}

private val LightColorScheme = lightColorScheme(
    primary = KomojuLightGreen,
    secondary = KomojuDarkGreen,
    tertiary = KomojuLightGreen,
    background = Color.White,
    surfaceContainer = Color.White,
)

@Composable
internal fun KomojuMobileSdkTheme(configuration: KomojuSDK.Configuration = EmptyConfiguration, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalConfigurableTheme provides configuration.configurableTheme) {
        Surface(color = Color.White) {
            MaterialTheme(
                colorScheme = LightColorScheme,
                content = content,
                typography = interTypography(),
            )
        }
    }
}

private val EmptyConfiguration = KomojuSDK.Configuration.Builder("", "").build()
