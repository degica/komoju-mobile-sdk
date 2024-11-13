package com.komoju.mobile.sdk.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import com.komoju.mobile.sdk.KomojuMobileSDKConfiguration
import com.komoju.mobile.sdk.i18n.KomojuLanguage

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

internal val LocalKomojuLanguage = compositionLocalOf<KomojuLanguage> {
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
internal fun KomojuMobileSdkTheme(configuration: KomojuMobileSDKConfiguration = EmptyConfiguration, content: @Composable () -> Unit) {
    Surface(color = Color.White) {
        MaterialTheme(
            colorScheme = LightColorScheme,
            content = {
                CompositionLocalProvider(LocalKomojuLanguage provides KomojuLanguage(configuration.language)) {
                    CompositionLocalProvider(LocalConfigurableTheme provides configuration.configurableTheme, content = content)
                }
            },
        )
    }
}

internal val EmptyConfiguration = KomojuMobileSDKConfiguration(
    language = "ja",
    currency = "jpy",
    publishableKey = null,
    isDebugMode = false,
    sessionId = null,
    redirectURL = "",
    appScheme = "",
    configurableTheme = DefaultConfigurableTheme(),
    inlinedProcessing = false,
)
