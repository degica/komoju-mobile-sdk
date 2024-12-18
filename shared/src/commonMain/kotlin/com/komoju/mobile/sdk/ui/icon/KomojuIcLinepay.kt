package com.komoju.mobile.sdk.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val KomojuIcon.Linepay: ImageVector
    get() {
        if (_KomojuIcLinepay != null) {
            return _KomojuIcLinepay!!
        }
        _KomojuIcLinepay = ImageVector.Builder(
            name = "KomojuIcLinepay",
            defaultWidth = 44.dp,
            defaultHeight = 47.dp,
            viewportWidth = 44f,
            viewportHeight = 47f,
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(15.5f, 0f)
                horizontalLineToRelative(-2f)
                curveToRelative(-0.4f, 0f, -0.7f, 0.3f, -0.7f, 0.6f)
                verticalLineToRelative(13.2f)
                curveToRelative(0f, 0.3f, 0.3f, 0.6f, 0.6f, 0.6f)
                horizontalLineToRelative(2.1f)
                curveToRelative(0.3f, 0f, 0.6f, -0.3f, 0.6f, -0.6f)
                verticalLineTo(0.6f)
                curveToRelative(0f, -0.3f, -0.3f, -0.6f, -0.6f, -0.6f)
                close()
                moveToRelative(-5.1f, 11f)
                horizontalLineTo(4.8f)
                verticalLineTo(0.7f)
                curveToRelative(0f, -0.3f, -0.3f, -0.6f, -0.6f, -0.6f)
                horizontalLineTo(2f)
                curveToRelative(-0.3f, 0f, -0.6f, 0.3f, -0.6f, 0.6f)
                verticalLineToRelative(13.2f)
                curveToRelative(0f, 0.3f, 0.3f, 0.5f, 0.6f, 0.5f)
                horizontalLineToRelative(8.3f)
                curveToRelative(0.4f, 0f, 0.6f, -0.2f, 0.6f, -0.5f)
                verticalLineToRelative(-2.2f)
                curveToRelative(0f, -0.3f, -0.2f, -0.5f, -0.6f, -0.5f)
                close()
                moveToRelative(31.3f, -7.7f)
                curveToRelative(0.3f, 0f, 0.6f, -0.3f, 0.6f, -0.6f)
                verticalLineTo(0.6f)
                curveTo(42.3f, 0.3f, 42f, 0f, 41.7f, 0f)
                horizontalLineToRelative(-8.4f)
                curveToRelative(-0.3f, 0f, -0.5f, 0.3f, -0.5f, 0.6f)
                verticalLineToRelative(13.2f)
                curveToRelative(0f, 0.3f, 0.2f, 0.5f, 0.5f, 0.5f)
                horizontalLineToRelative(8.4f)
                curveToRelative(0.3f, 0f, 0.6f, -0.2f, 0.6f, -0.5f)
                verticalLineToRelative(-2.2f)
                curveToRelative(0f, -0.3f, -0.3f, -0.5f, -0.6f, -0.5f)
                horizontalLineTo(36f)
                verticalLineTo(8.8f)
                horizontalLineToRelative(5.7f)
                curveToRelative(0.3f, 0f, 0.6f, -0.2f, 0.6f, -0.6f)
                verticalLineToRelative(-2f)
                curveToRelative(0f, -0.4f, -0.3f, -0.7f, -0.6f, -0.7f)
                horizontalLineTo(36f)
                verticalLineTo(3.3f)
                horizontalLineToRelative(5.7f)
                close()
                moveTo(30f, 0f)
                horizontalLineToRelative(-2.2f)
                curveToRelative(-0.3f, 0f, -0.5f, 0.3f, -0.5f, 0.6f)
                verticalLineToRelative(7.6f)
                lineToRelative(-5.5f, -8f)
                lineTo(21f, 0f)
                horizontalLineToRelative(-2.2f)
                curveToRelative(-0.3f, 0f, -0.6f, 0.3f, -0.6f, 0.6f)
                verticalLineToRelative(13.2f)
                curveToRelative(0f, 0.3f, 0.3f, 0.5f, 0.6f, 0.5f)
                horizontalLineTo(21f)
                curveToRelative(0.3f, 0f, 0.6f, -0.2f, 0.6f, -0.5f)
                verticalLineToRelative(-8f)
                lineToRelative(5.7f, 8.3f)
                horizontalLineToRelative(0.1f)
                curveToRelative(0.1f, 0.2f, 0.2f, 0.2f, 0.4f, 0.2f)
                horizontalLineTo(30f)
                curveToRelative(0.4f, 0f, 0.6f, -0.2f, 0.6f, -0.5f)
                verticalLineTo(0.6f)
                curveTo(30.6f, 0.3f, 30.4f, 0f, 30f, 0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF08BF5B))) {
                moveTo(42.4f, 47f)
                horizontalLineTo(1.6f)
                curveTo(0.7f, 47f, 0f, 46.1f, 0f, 45.2f)
                verticalLineTo(20.8f)
                curveToRelative(0f, -1f, 0.7f, -1.7f, 1.6f, -1.7f)
                horizontalLineToRelative(40.8f)
                curveToRelative(0.9f, 0f, 1.6f, 0.8f, 1.6f, 1.7f)
                verticalLineToRelative(24.5f)
                curveToRelative(0f, 0.9f, -0.7f, 1.6f, -1.6f, 1.6f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(15.9f, 30f)
                curveToRelative(0f, 2.4f, -2f, 4.4f, -4.5f, 4.4f)
                horizontalLineToRelative(-2f)
                verticalLineTo(38f)
                curveToRelative(0f, 0.2f, -0.1f, 0.4f, -0.3f, 0.4f)
                horizontalLineTo(7f)
                arcTo(0.4f, 0.4f, 0f, isMoreThanHalf = false, isPositiveArc = true, 6.6f, 38f)
                verticalLineTo(26f)
                curveToRelative(0f, -0.2f, 0.2f, -0.4f, 0.4f, -0.4f)
                horizontalLineToRelative(4.4f)
                curveToRelative(2.5f, 0f, 4.5f, 2f, 4.5f, 4.4f)
                close()
                moveToRelative(-3f, 0f)
                curveToRelative(0f, -1f, -0.6f, -1.7f, -1.5f, -1.7f)
                horizontalLineToRelative(-2f)
                verticalLineToRelative(3.3f)
                horizontalLineToRelative(2f)
                curveToRelative(0.9f, 0f, 1.5f, -0.7f, 1.5f, -1.6f)
                close()
                moveToRelative(13.8f, -0.5f)
                verticalLineToRelative(8.7f)
                lineToRelative(-0.2f, 0.2f)
                horizontalLineToRelative(-2.3f)
                arcToRelative(0.2f, 0.2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.2f, -0.2f)
                verticalLineToRelative(-0.6f)
                curveToRelative(-0.6f, 0.7f, -1.5f, 1f, -2.8f, 1f)
                curveToRelative(-2.3f, 0f, -4.3f, -2f, -4.3f, -4.8f)
                curveToRelative(0f, -2.7f, 2f, -4.8f, 4.3f, -4.8f)
                curveToRelative(1.3f, 0f, 2.2f, 0.4f, 2.8f, 1.1f)
                verticalLineToRelative(-0.6f)
                curveToRelative(0f, -0.1f, 0f, -0.2f, 0.2f, -0.2f)
                horizontalLineToRelative(2.3f)
                reflectiveCurveToRelative(0.2f, 0f, 0.2f, 0.2f)
                close()
                moveTo(24f, 33.8f)
                curveToRelative(0f, -1.3f, -1f, -2.2f, -2.2f, -2.2f)
                curveToRelative(-1.3f, 0f, -2.2f, 0.9f, -2.2f, 2.2f)
                curveToRelative(0f, 1.4f, 1f, 2.3f, 2.2f, 2.3f)
                curveToRelative(1.2f, 0f, 2.2f, -0.9f, 2.2f, -2.3f)
                close()
                moveToRelative(11.8f, -4.3f)
                lineToRelative(-2.1f, 5.3f)
                lineToRelative(-2.2f, -5.3f)
                curveToRelative(0f, -0.2f, -0.2f, -0.3f, -0.3f, -0.3f)
                horizontalLineToRelative(-2.4f)
                lineToRelative(-0.2f, 0.4f)
                lineToRelative(3.7f, 8.8f)
                lineToRelative(-1.4f, 3.4f)
                curveToRelative(0f, 0.1f, 0f, 0.3f, 0.2f, 0.3f)
                horizontalLineToRelative(2.4f)
                lineToRelative(0.3f, -0.2f)
                lineToRelative(4.9f, -12.3f)
                curveToRelative(0f, -0.2f, 0f, -0.4f, -0.2f, -0.4f)
                horizontalLineTo(36f)
                curveToRelative(-0.1f, 0f, -0.3f, 0.1f, -0.3f, 0.3f)
                close()
            }
        }.build()

        return _KomojuIcLinepay!!
    }

@Suppress("ObjectPropertyName")
private var _KomojuIcLinepay: ImageVector? = null
