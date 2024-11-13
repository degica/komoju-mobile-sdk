package com.komoju.mobile.sdk.ui.icon

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val KomojuIcon.Paidy: ImageVector
    get() {
        if (_KomojuIcPaidy != null) {
            return _KomojuIcPaidy!!
        }
        _KomojuIcPaidy = ImageVector.Builder(
            name = "KomojuIcPaidy",
            defaultWidth = 81.dp,
            defaultHeight = 24.dp,
            viewportWidth = 300f,
            viewportHeight = 90f,
        ).apply {
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color(0xFFA6009C),
                        1f to Color(0xFFFF009C),
                    ),
                    start = Offset(7f, 78.2f),
                    end = Offset(79f, 12f),
                ),
            ) {
                moveTo(42.8f, 43.5f)
                lineToRelative(0.1f, 20.2f)
                curveToRelative(0f, 2.5f, -2.1f, 4.5f, -4.6f, 4.5f)
                curveToRelative(-2.5f, 0f, -4.5f, -2f, -4.5f, -4.5f)
                verticalLineTo(33.1f)
                lineToRelative(4.3f, 5.3f)
                curveToRelative(2f, 2.5f, 3.3f, 3.9f, 4.7f, 5.1f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color(0xFFA6009C),
                        1f to Color(0xFFFF009C),
                    ),
                    start = Offset(7.4f, 102.2f),
                    end = Offset(56.3f, 17.4f),
                ),
            ) {
                moveTo(84.5f, 55.2f)
                curveToRelative(0.4f, 6.7f, -3.8f, 12.2f, -8.9f, 15.6f)
                curveToRelative(-1.1f, 0.7f, -18.6f, 11.9f, -24f, 14.6f)
                arcToRelative(22.7f, 22.7f, 0f, isMoreThanHalf = false, isPositiveArc = true, -11.3f, 2.7f)
                arcTo(26f, 26f, 0f, isMoreThanHalf = false, isPositiveArc = true, 27.2f, 85f)
                arcTo(911f, 911f, 0f, isMoreThanHalf = false, isPositiveArc = true, 9.3f, 74.5f)
                curveToRelative(-4.9f, -3f, -7.2f, -5.8f, -8.5f, -10.7f)
                curveTo(0f, 60.8f, 0f, 57.6f, 0f, 56.7f)
                verticalLineTo(31.2f)
                curveToRelative(0f, -11.8f, 6.7f, -20.4f, 14.6f, -23f)
                curveToRelative(8f, -2.7f, 13.9f, -1f, 18.2f, 1.9f)
                curveToRelative(3.4f, 2.3f, 8.1f, 8f, 8.7f, 8.8f)
                curveToRelative(0f, 0f, 6.9f, 9.1f, 9.6f, 11.5f)
                curveToRelative(2.8f, 2.5f, 5f, 3.7f, 8.5f, 3.7f)
                curveToRelative(5.8f, 0f, 10.6f, -4.8f, 10.6f, -10.8f)
                curveToRelative(0f, -5.6f, -4.2f, -10.7f, -10.8f, -10.7f)
                curveToRelative(-6.4f, 0f, -10.6f, 6.6f, -11.1f, 7.2f)
                lineToRelative(-3.1f, -4.1f)
                lineToRelative(-2.7f, -3.1f)
                arcToRelative(20.1f, 20.1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 37.1f, 10.7f)
                curveToRelative(0f, 8.5f, -5.6f, 20f, -20.2f, 20f)
                curveToRelative(-5.4f, 0f, -9.9f, -2f, -14.4f, -5.9f)
                arcToRelative(165.7f, 165.7f, 0f, isMoreThanHalf = false, isPositiveArc = true, -11.6f, -13.8f)
                curveToRelative(-4f, -4.9f, -8.2f, -9.4f, -15.9f, -6.5f)
                curveToRelative(-7.2f, 3.1f, -8.4f, 10.2f, -8.4f, 14.1f)
                verticalLineToRelative(25.5f)
                reflectiveCurveToRelative(0f, 3f, 0.6f, 4.9f)
                curveToRelative(0.7f, 2.2f, 2.7f, 4f, 4f, 4.8f)
                arcToRelative(527f, 527f, 0f, isMoreThanHalf = false, isPositiveArc = false, 18.9f, 11.1f)
                curveToRelative(3.4f, 1.8f, 9.2f, 2.6f, 14.5f, -0.1f)
                curveToRelative(4f, -2f, 23.3f, -14.2f, 23.6f, -14.4f)
                curveToRelative(4.8f, -3.5f, 4.4f, -6.6f, 4.5f, -7.5f)
                arcToRelative(10.8f, 10.8f, 0f, isMoreThanHalf = false, isPositiveArc = false, 9.3f, -0.3f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color(0xFFA6009C),
                        1f to Color(0xFFFF009C),
                    ),
                    start = Offset(23.4f, 97.9f),
                    end = Offset(98.1f, 29.2f),
                ),
            ) {
                moveTo(79.6f, 51.5f)
                arcToRelative(5.3f, 5.3f, 0f, isMoreThanHalf = false, isPositiveArc = true, -5.3f, -5.3f)
                arcToRelative(5.4f, 5.4f, 0f, isMoreThanHalf = false, isPositiveArc = true, 10.7f, 0f)
                curveToRelative(0f, 3f, -2.4f, 5.3f, -5.4f, 5.3f)
                close()
            }
            path(fill = SolidColor(Color(0xFF1C1C1C))) {
                moveTo(300.5f, 35f)
                curveToRelative(-0.4f, 0.8f, -1f, 1.4f, -1.2f, 2.1f)
                lineToRelative(-11.5f, 37.8f)
                curveToRelative(-0.5f, 1.6f, -1.1f, 3.2f, -2f, 4.7f)
                curveToRelative(-3f, 4.7f, -8.7f, 6.8f, -14.1f, 5.1f)
                curveToRelative(-0.4f, -0.1f, -1.1f, -0.4f, -1.2f, -0.7f)
                curveToRelative(-0.4f, -2f, -1.1f, -4.1f, -0.7f, -6f)
                curveToRelative(0.2f, -1f, 2.1f, -2.2f, 3.5f, -2.4f)
                curveToRelative(3.7f, -0.7f, 4.7f, -1.8f, 3.6f, -5.5f)
                lineToRelative(-3.7f, -12.3f)
                lineToRelative(-7.1f, -23.2f)
                curveToRelative(-1.2f, -3.9f, 0.1f, -6.7f, 3.2f, -7.6f)
                curveToRelative(3.8f, -1.1f, 6.6f, 0.5f, 7.8f, 4.5f)
                curveToRelative(2f, 6.7f, 4f, 13.3f, 6.5f, 20f)
                lineToRelative(2.7f, -9.3f)
                curveToRelative(1f, -3.2f, 2.2f, -6.3f, 2.8f, -9.6f)
                curveToRelative(1f, -5.6f, 5.9f, -8.5f, 11.2f, -4.4f)
                curveToRelative(0.2f, 2.2f, 0.2f, 4.4f, 0.2f, 6.8f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF1C1C1C)),
                pathFillType = PathFillType.EvenOdd,
            ) {
                moveTo(247f, 65.3f)
                arcToRelative(27.4f, 27.4f, 0f, isMoreThanHalf = false, isPositiveArc = true, -17f, 0.1f)
                curveToRelative(-5.5f, -1.7f, -8.4f, -5.8f, -9.5f, -11f)
                arcToRelative(29.1f, 29.1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2.9f, -22.3f)
                curveToRelative(4.4f, -6.9f, 15.8f, -8.2f, 21f, -3.6f)
                curveToRelative(0.5f, 0.5f, 1.1f, 0.7f, 1.9f, 1.3f)
                lineToRelative(0.2f, -2.5f)
                verticalLineTo(13.5f)
                curveToRelative(0.1f, -3.7f, 2.3f, -5.9f, 5.7f, -5.9f)
                curveToRelative(3.2f, 0.1f, 5.5f, 2.6f, 5.5f, 6f)
                curveToRelative(0.1f, 12.3f, 0.2f, 24.5f, 0f, 36.8f)
                curveToRelative(-0.1f, 7.2f, -3.3f, 12.5f, -10.7f, 14.9f)
                close()
                moveToRelative(-13.2f, -28.4f)
                curveToRelative(-4.1f, 5f, -4f, 10.5f, -1.6f, 16.2f)
                curveToRelative(1.5f, 3.5f, 3.7f, 4.5f, 7.8f, 4.1f)
                arcToRelative(6.4f, 6.4f, 0f, isMoreThanHalf = false, isPositiveArc = false, 5.8f, -5.3f)
                curveToRelative(0.5f, -2.9f, 0.9f, -5.9f, 0.5f, -8.8f)
                curveToRelative(-0.8f, -7.1f, -6.5f, -10f, -12.5f, -6.2f)
                close()
                moveTo(121.1f, 68f)
                verticalLineToRelative(11.5f)
                curveToRelative(-0.1f, 3.6f, -2.4f, 5.6f, -5.9f, 5.4f)
                curveToRelative(-2.6f, -0.2f, -4.4f, -1.8f, -4.4f, -4.3f)
                curveToRelative(0f, -13.1f, -0.2f, -26.2f, 0.1f, -39.3f)
                curveToRelative(0.2f, -8.1f, 8.3f, -15.3f, 16.7f, -15.4f)
                curveToRelative(3.3f, 0f, 6.8f, 0.3f, 9.9f, 1.3f)
                curveToRelative(6.2f, 1.9f, 9f, 6.9f, 10.2f, 12.9f)
                curveToRelative(1f, 5.4f, 0.6f, 10.7f, -0.9f, 15.9f)
                curveToRelative(-3.4f, 11.9f, -16.8f, 13f, -23.8f, 7.4f)
                lineToRelative(-1.9f, -1.3f)
                verticalLineTo(68f)
                close()
                moveToRelative(0.1f, -19.6f)
                lineToRelative(0.5f, 2.5f)
                arcToRelative(8f, 8f, 0f, isMoreThanHalf = false, isPositiveArc = false, 7.1f, 6.1f)
                curveToRelative(2.7f, 0.4f, 5.9f, -1.5f, 7.3f, -4.4f)
                curveToRelative(2.3f, -4.5f, 1.8f, -9.1f, 0f, -13.6f)
                arcToRelative(7.3f, 7.3f, 0f, isMoreThanHalf = false, isPositiveArc = false, -6.9f, -4.6f)
                curveToRelative(-3.5f, 0.2f, -6.3f, 2.1f, -7.1f, 5.5f)
                curveToRelative(-0.7f, 2.6f, -0.7f, 5.3f, -0.9f, 8.5f)
                close()
                moveTo(162f, 27.7f)
                curveToRelative(7f, -4.1f, 19.7f, -2.9f, 25f, 3.6f)
                arcToRelative(20f, 20f, 0f, isMoreThanHalf = false, isPositiveArc = true, 4.5f, 12.1f)
                curveToRelative(0.2f, 5.5f, 0.1f, 11f, 0f, 16.6f)
                curveToRelative(0f, 2.1f, -0.4f, 4f, -2.4f, 5.2f)
                curveToRelative(-3.4f, 2.1f, -6.5f, 0.7f, -8.1f, -3.7f)
                curveToRelative(-1.9f, 1.2f, -3.7f, 2.7f, -5.7f, 3.5f)
                curveToRelative(-7.4f, 3f, -17.5f, 0.1f, -20f, -9.4f)
                curveToRelative(-2.1f, -8.2f, -2.7f, -16.4f, 2.3f, -23.8f)
                curveToRelative(1.1f, -1.6f, 2.8f, -2.7f, 4.4f, -4.1f)
                close()
                moveToRelative(18.3f, 13f)
                curveToRelative(-1f, -4.3f, -3.7f, -6.9f, -7.2f, -6.8f)
                curveToRelative(-3.5f, 0.2f, -6.5f, 1.3f, -7.9f, 6.4f)
                curveToRelative(-0.9f, 3.3f, -1.1f, 6.5f, -0.2f, 9.9f)
                curveToRelative(1.1f, 4f, 3.7f, 6.1f, 7.7f, 6.1f)
                curveToRelative(3.8f, 0f, 6.8f, -2.2f, 7.5f, -6.2f)
                curveToRelative(0.5f, -3f, 0.2f, -6f, 0.1f, -9.4f)
                close()
            }
            path(fill = SolidColor(Color(0xFF1C1C1C))) {
                moveTo(199.5f, 41.8f)
                verticalLineToRelative(-9.4f)
                curveToRelative(0.1f, -3.5f, 2.4f, -5.7f, 6.1f, -5.6f)
                curveToRelative(3.6f, 0.1f, 5.7f, 2f, 5.7f, 5.6f)
                verticalLineTo(60f)
                curveToRelative(0f, 3.7f, -2.3f, 6.1f, -5.7f, 6.1f)
                curveToRelative(-3.5f, 0f, -6f, -2.5f, -6.1f, -6.1f)
                verticalLineTo(41.8f)
                close()
                moveToRelative(2.3f, -31.6f)
                curveToRelative(3.1f, -1.9f, 6.2f, -1.5f, 8.4f, 0.8f)
                curveToRelative(1.9f, 2f, 2f, 4.9f, 0.4f, 7.3f)
                curveToRelative(-2f, 2.8f, -6.5f, 3.3f, -9.2f, 1.1f)
                curveToRelative(-2.1f, -1.7f, -3f, -6.7f, 0.4f, -9.2f)
                close()
            }
        }.build()

        return _KomojuIcPaidy!!
    }

@Suppress("ObjectPropertyName")
private var _KomojuIcPaidy: ImageVector? = null
