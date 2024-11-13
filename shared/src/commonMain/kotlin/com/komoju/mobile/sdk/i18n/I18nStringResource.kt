package com.komoju.mobile.sdk.i18n

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.komoju.mobile.sdk.ui.theme.LocalKomojuLanguage

@Composable
fun i18nStringResource(key: I18nStringKey, vararg args: Any): String {
    val languageCode = LocalKomojuLanguage.current.code
    val string = remember(key, languageCode) {
        val strings = when (languageCode.lowercase()) {
            "en", "eng", "english" -> EnglishStrings
            else -> JapaneseStrings
        }
        when {
            args.isEmpty() -> strings.get(key)
            else -> strings.get(key).replaceWithArgs(args.map { it.toString() })
        }
    }
    return string
}

internal fun String.replaceWithArgs(args: List<String>) = replace("%s", args.first())
