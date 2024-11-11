package com.komoju.mobile.sdk.i18n

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.komoju.mobile.sdk.ui.theme.LocalKomojuLanguage

@Composable
fun i18nStringResource(key: I18nStringKey, vararg args: Any): String {
    val languageCode = LocalKomojuLanguage.current.code
    val strings = when (languageCode) {
        "en" -> EnglishStrings
        else -> EnglishStrings
    }
    val string = remember(key, languageCode) { strings.get(key).replaceWithArgs(args.map { it.toString() }) }
    return string
}

private val SimpleStringFormatRegex = Regex("""%(\d)\$[ds]""")
internal fun String.replaceWithArgs(args: List<String>) = SimpleStringFormatRegex.replace(this) { matchResult ->
    args[matchResult.groupValues[1].toInt() - 1]
}
