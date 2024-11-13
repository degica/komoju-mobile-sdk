package com.komoju.mobile.sdk.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val KomojuIcon.SevenEleven: ImageVector
    get() {
        if (_KomojuIcSevenEleven != null) {
            return _KomojuIcSevenEleven!!
        }
        _KomojuIcSevenEleven = ImageVector.Builder(
            name = "KomojuIcSevenEleven",
            defaultWidth = 32.dp,
            defaultHeight = 32.dp,
            viewportWidth = 32f,
            viewportHeight = 32f,
        ).apply {
            path(fill = SolidColor(Color(0xFFDBF0EA))) {
                moveTo(5.333f, 0f)
                horizontalLineToRelative(21.334f)
                arcTo(5.333f, 5.333f, 0f, isMoreThanHalf = false, isPositiveArc = true, 32f, 5.333f)
                verticalLineToRelative(21.334f)
                arcTo(5.333f, 5.333f, 0f, isMoreThanHalf = false, isPositiveArc = true, 26.667f, 32f)
                horizontalLineTo(5.333f)
                arcTo(5.333f, 5.333f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 26.667f)
                verticalLineTo(5.333f)
                arcTo(5.333f, 5.333f, 0f, isMoreThanHalf = false, isPositiveArc = true, 5.333f, 0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF008160))) {
                moveTo(16f, 26.667f)
                curveToRelative(5.891f, 0f, 10.667f, -4.776f, 10.667f, -10.667f)
                reflectiveCurveTo(21.891f, 5.333f, 16f, 5.333f)
                reflectiveCurveTo(5.333f, 10.109f, 5.333f, 16f)
                reflectiveCurveTo(10.109f, 26.667f, 16f, 26.667f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(17.994f, 24.052f)
                lineToRelative(0.009f, 0.639f)
                horizontalLineToRelative(-4.296f)
                lineToRelative(0.01f, -0.628f)
                lineToRelative(-3.145f, -0.014f)
                curveToRelative(-0.32f, 0f, -0.682f, -0.225f, -0.682f, -0.642f)
                lineTo(8.639f, 9.08f)
                curveTo(8.631f, 8.609f, 8.85f, 8.374f, 9.378f, 8.382f)
                lineTo(22.6f, 8.391f)
                curveToRelative(0.488f, 0.016f, 0.812f, 0.17f, 0.771f, 0.893f)
                lineToRelative(-1.477f, 14.099f)
                curveToRelative(0f, 0.417f, -0.289f, 0.666f, -0.609f, 0.666f)
                lineToRelative(-3.291f, 0.003f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFF8300))) {
                moveTo(10.986f, 10.519f)
                lineToRelative(8.84f, -0.005f)
                curveToRelative(-0.88f, 0.33f, -3.35f, 2.198f, -4.021f, 3.414f)
                lineToRelative(-4.807f, -0.01f)
                lineToRelative(-0.012f, -3.399f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFF142B))) {
                moveTo(17.945f, 20.361f)
                curveToRelative(-0.128f, 1.221f, -0.128f, 2.614f, -0.128f, 4.176f)
                lineToRelative(-3.942f, 0.011f)
                curveToRelative(0f, -1.556f, 0.084f, -2.966f, 0.211f, -4.187f)
                horizontalLineToRelative(3.859f)
                close()
            }
            path(fill = SolidColor(Color(0xFF008061))) {
                moveTo(12.044f, 18.619f)
                verticalLineToRelative(0.474f)
                horizontalLineToRelative(-0.62f)
                verticalLineToRelative(0.472f)
                horizontalLineToRelative(0.62f)
                verticalLineToRelative(0.539f)
                horizontalLineToRelative(-1.419f)
                verticalLineToRelative(-2.517f)
                horizontalLineToRelative(1.419f)
                verticalLineToRelative(0.564f)
                horizontalLineToRelative(-0.62f)
                verticalLineToRelative(0.468f)
                horizontalLineToRelative(0.62f)
                close()
                moveToRelative(3.134f, 0f)
                verticalLineToRelative(0.474f)
                horizontalLineToRelative(-0.62f)
                verticalLineToRelative(0.472f)
                horizontalLineToRelative(0.62f)
                verticalLineToRelative(0.539f)
                horizontalLineToRelative(-1.414f)
                verticalLineToRelative(-2.517f)
                horizontalLineToRelative(1.414f)
                verticalLineToRelative(0.564f)
                horizontalLineToRelative(-0.62f)
                verticalLineToRelative(0.468f)
                horizontalLineToRelative(0.62f)
                close()
                moveToRelative(3.754f, -0.017f)
                verticalLineToRelative(0.473f)
                horizontalLineToRelative(-0.62f)
                verticalLineToRelative(0.474f)
                horizontalLineToRelative(0.62f)
                verticalLineToRelative(0.538f)
                horizontalLineToRelative(-1.418f)
                verticalLineToRelative(-2.518f)
                horizontalLineToRelative(1.418f)
                verticalLineToRelative(0.565f)
                horizontalLineToRelative(-0.62f)
                verticalLineToRelative(0.468f)
                horizontalLineToRelative(0.62f)
                close()
                moveToRelative(-5.856f, -0.998f)
                verticalLineToRelative(1.978f)
                lineToRelative(0.478f, -0.007f)
                lineToRelative(0.006f, 0.546f)
                lineToRelative(-1.282f, 0.001f)
                verticalLineToRelative(-2.518f)
                horizontalLineToRelative(0.798f)
                close()
                moveToRelative(3.35f, 1.692f)
                lineToRelative(-0.292f, -1.71f)
                horizontalLineToRelative(-0.815f)
                lineToRelative(0.357f, 2.52f)
                lineToRelative(1.172f, 0.004f)
                lineToRelative(0.479f, -2.529f)
                lineToRelative(-0.584f, -0.002f)
                lineToRelative(-0.317f, 1.717f)
                close()
                moveToRelative(2.824f, -1.727f)
                horizontalLineToRelative(0.763f)
                verticalLineToRelative(2.518f)
                horizontalLineTo(19.25f)
                verticalLineToRelative(-2.518f)
                close()
            }
            path(fill = SolidColor(Color(0xFF008160))) {
                moveTo(20.014f, 18.316f)
                curveToRelative(0.04f, -0.274f, 0.433f, -0.203f, 0.433f, -0.045f)
                verticalLineToRelative(1.816f)
                horizontalLineToRelative(0.768f)
                verticalLineToRelative(-2.136f)
                curveToRelative(0f, -0.453f, -0.636f, -0.636f, -1.195f, -0.234f)
                lineToRelative(-0.006f, 0.599f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFF142B))) {
                moveTo(18.247f, 17.347f)
                curveToRelative(0.06f, -1.008f, 1.149f, -2.739f, 2.849f, -3.314f)
                lineToRelative(0.001f, -3.779f)
                curveToRelative(-3.521f, 1.572f, -5.981f, 4.249f, -6.472f, 7.089f)
                lineToRelative(3.622f, 0.004f)
                close()
            }
        }.build()

        return _KomojuIcSevenEleven!!
    }

@Suppress("ObjectPropertyName")
private var _KomojuIcSevenEleven: ImageVector? = null
