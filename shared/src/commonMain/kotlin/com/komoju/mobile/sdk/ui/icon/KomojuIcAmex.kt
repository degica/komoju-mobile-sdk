package com.komoju.mobile.sdk.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val KomojuIcon.Amex: ImageVector
    get() {
        if (_KomojuIcAmex != null) {
            return _KomojuIcAmex!!
        }
        _KomojuIcAmex = ImageVector.Builder(
            name = "KomojuIcAmex",
            defaultWidth = 24.dp,
            defaultHeight = 17.dp,
            viewportWidth = 24f,
            viewportHeight = 17f,
        ).apply {
            group {
                path(fill = SolidColor(Color(0xFF016FD0))) {
                    moveTo(22f, 0.5f)
                    horizontalLineTo(2f)
                    curveToRelative(-1.105f, 0f, -2f, 0.895f, -2f, 2f)
                    verticalLineToRelative(12f)
                    curveToRelative(0f, 1.105f, 0.895f, 2f, 2f, 2f)
                    horizontalLineToRelative(20f)
                    curveToRelative(1.105f, 0f, 2f, -0.895f, 2f, -2f)
                    verticalLineToRelative(-12f)
                    curveToRelative(0f, -1.105f, -0.895f, -2f, -2f, -2f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFFFFFFE)),
                    pathFillType = PathFillType.EvenOdd,
                ) {
                    moveTo(13.764f, 13.894f)
                    verticalLineTo(8.193f)
                    lineToRelative(10.148f, 0.009f)
                    verticalLineToRelative(1.574f)
                    lineToRelative(-1.173f, 1.254f)
                    lineToRelative(1.173f, 1.265f)
                    verticalLineToRelative(1.608f)
                    horizontalLineToRelative(-1.873f)
                    lineToRelative(-0.995f, -1.098f)
                    lineToRelative(-0.988f, 1.102f)
                    lineToRelative(-6.292f, -0.013f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF016FD0)),
                    pathFillType = PathFillType.EvenOdd,
                ) {
                    moveTo(14.442f, 13.269f)
                    verticalLineTo(8.82f)
                    horizontalLineToRelative(3.772f)
                    verticalLineToRelative(1.025f)
                    horizontalLineToRelative(-2.551f)
                    verticalLineToRelative(0.695f)
                    horizontalLineToRelative(2.49f)
                    verticalLineToRelative(1.008f)
                    horizontalLineToRelative(-2.49f)
                    verticalLineToRelative(0.684f)
                    horizontalLineToRelative(2.551f)
                    verticalLineToRelative(1.037f)
                    horizontalLineToRelative(-3.772f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF016FD0)),
                    pathFillType = PathFillType.EvenOdd,
                ) {
                    moveTo(18.195f, 13.269f)
                    lineToRelative(2.088f, -2.227f)
                    lineToRelative(-2.088f, -2.222f)
                    horizontalLineToRelative(1.616f)
                    lineToRelative(1.275f, 1.41f)
                    lineToRelative(1.279f, -1.41f)
                    horizontalLineToRelative(1.547f)
                    verticalLineToRelative(0.035f)
                    lineToRelative(-2.043f, 2.187f)
                    lineToRelative(2.043f, 2.164f)
                    verticalLineToRelative(0.063f)
                    horizontalLineTo(22.35f)
                    lineToRelative(-1.298f, -1.424f)
                    lineToRelative(-1.285f, 1.424f)
                    horizontalLineToRelative(-1.572f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFFFFFFE)),
                    pathFillType = PathFillType.EvenOdd,
                ) {
                    moveTo(14.237f, 3.132f)
                    horizontalLineToRelative(2.446f)
                    lineToRelative(0.86f, 1.951f)
                    verticalLineTo(3.132f)
                    horizontalLineToRelative(3.019f)
                    lineToRelative(0.521f, 1.462f)
                    lineToRelative(0.523f, -1.462f)
                    horizontalLineToRelative(2.306f)
                    verticalLineToRelative(5.701f)
                    horizontalLineTo(11.725f)
                    lineToRelative(2.512f, -5.701f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF016FD0)),
                    pathFillType = PathFillType.EvenOdd,
                ) {
                    moveTo(14.7f, 3.751f)
                    lineToRelative(-1.974f, 4.446f)
                    horizontalLineToRelative(1.354f)
                    lineToRelative(0.373f, -0.891f)
                    horizontalLineToRelative(2.018f)
                    lineToRelative(0.372f, 0.891f)
                    horizontalLineToRelative(1.387f)
                    lineToRelative(-1.965f, -4.446f)
                    horizontalLineTo(14.7f)
                    close()
                    moveToRelative(0.17f, 2.558f)
                    lineToRelative(0.592f, -1.415f)
                    lineToRelative(0.591f, 1.415f)
                    horizontalLineTo(14.87f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF016FD0)),
                    pathFillType = PathFillType.EvenOdd,
                ) {
                    moveTo(18.212f, 8.196f)
                    verticalLineTo(3.751f)
                    lineToRelative(1.903f, 0.006f)
                    lineToRelative(0.979f, 2.733f)
                    lineToRelative(0.986f, -2.739f)
                    horizontalLineToRelative(1.832f)
                    verticalLineToRelative(4.445f)
                    lineToRelative(-1.179f, 0.01f)
                    verticalLineTo(5.153f)
                    lineTo(21.62f, 8.196f)
                    horizontalLineToRelative(-1.075f)
                    lineToRelative(-1.136f, -3.054f)
                    verticalLineToRelative(3.054f)
                    horizontalLineToRelative(-1.197f)
                    close()
                }
            }
        }.build()

        return _KomojuIcAmex!!
    }

@Suppress("ObjectPropertyName")
private var _KomojuIcAmex: ImageVector? = null
