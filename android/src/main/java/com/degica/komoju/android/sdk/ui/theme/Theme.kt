package com.degica.komoju.android.sdk.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import com.degica.komoju.android.sdk.types.Language
import com.degica.komoju.mobile.sdk.i18n.I18nTexts

internal val KomojuLightGreen = Color(0xFF3CC239)
internal val KomojuDarkGreen = Color(0xFF172E44)
internal val Gray200 = Color(0xFFD7DCE0)
internal val Gray500 = Color(0xFF6D7D88)
internal val Blue600 = Color(0xFF297FE7)

internal val LocalI18nTextsProvider = compositionLocalOf<I18nTexts> {
    error("Use KomojuMobileSdkTheme to provide I18nTexts")
}

private val LightColorScheme = lightColorScheme(
    primary = KomojuLightGreen,
    secondary = KomojuDarkGreen,
    tertiary = KomojuLightGreen,
)

@Composable
internal fun KomojuMobileSdkTheme(language: Language, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalI18nTextsProvider provides I18nTexts(language.languageCode)) {
        Surface {
            MaterialTheme(
                colorScheme = LightColorScheme,
                content = content,
            )
        }
    }
}
