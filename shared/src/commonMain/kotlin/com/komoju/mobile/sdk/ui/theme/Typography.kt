package com.komoju.mobile.sdk.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.komoju.mobile.sdk.shared.generated.resources.Res
import com.komoju.mobile.sdk.shared.generated.resources.komoju_font_inter_bold
import com.komoju.mobile.sdk.shared.generated.resources.komoju_font_inter_light
import com.komoju.mobile.sdk.shared.generated.resources.komoju_font_inter_medium
import com.komoju.mobile.sdk.shared.generated.resources.komoju_font_inter_regular
import com.komoju.mobile.sdk.shared.generated.resources.komoju_font_inter_semibold
import org.jetbrains.compose.resources.Font

@Composable
private fun interFontFamily() = FontFamily(
    Font(Res.font.komoju_font_inter_light, weight = FontWeight.Light),
    Font(Res.font.komoju_font_inter_regular, weight = FontWeight.Normal),
    Font(Res.font.komoju_font_inter_medium, weight = FontWeight.Medium),
    Font(Res.font.komoju_font_inter_semibold, weight = FontWeight.SemiBold),
    Font(Res.font.komoju_font_inter_bold, weight = FontWeight.Bold),
)

@Composable
internal fun interTypography() = Typography().run {
    val fontFamily = interFontFamily()
    copy(
        displayLarge = displayLarge.copy(fontFamily = fontFamily),
        displayMedium = displayMedium.copy(fontFamily = fontFamily),
        displaySmall = displaySmall.copy(fontFamily = fontFamily),
        headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
        headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
        headlineSmall = headlineSmall.copy(fontFamily = fontFamily),
        titleLarge = titleLarge.copy(fontFamily = fontFamily),
        titleMedium = titleMedium.copy(fontFamily = fontFamily),
        titleSmall = titleSmall.copy(fontFamily = fontFamily),
        bodyLarge = bodyLarge.copy(fontFamily = fontFamily),
        bodyMedium = bodyMedium.copy(fontFamily = fontFamily),
        bodySmall = bodySmall.copy(fontFamily = fontFamily),
        labelLarge = labelLarge.copy(fontFamily = fontFamily),
        labelMedium = labelMedium.copy(fontFamily = fontFamily),
        labelSmall = labelSmall.copy(fontFamily = fontFamily),
    )
}
