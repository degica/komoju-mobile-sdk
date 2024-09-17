package com.degica.komoju.android.sdk.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.degica.komoju.android.sdk.R

@Composable
private fun interFontFamily() = FontFamily(
    Font(R.font.komoju_font_inter_light, weight = FontWeight.Light),
    Font(R.font.komoju_font_inter_regular, weight = FontWeight.Normal),
    Font(R.font.komoju_font_inter_medium, weight = FontWeight.Medium),
    Font(R.font.komoju_font_inter_semibold, weight = FontWeight.SemiBold),
    Font(R.font.komoju_font_inter_bold, weight = FontWeight.Bold)
)

@Composable
fun interTypography() = Typography().run {

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
        bodyLarge = bodyLarge.copy(fontFamily =  fontFamily),
        bodyMedium = bodyMedium.copy(fontFamily = fontFamily),
        bodySmall = bodySmall.copy(fontFamily = fontFamily),
        labelLarge = labelLarge.copy(fontFamily = fontFamily),
        labelMedium = labelMedium.copy(fontFamily = fontFamily),
        labelSmall = labelSmall.copy(fontFamily = fontFamily)
    )
}
