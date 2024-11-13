package com.komoju.mobile.sdk.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val KomojuIcon.Master: ImageVector
    get() {
        if (_KomojuIcMaster != null) {
            return _KomojuIcMaster!!
        }
        _KomojuIcMaster = ImageVector.Builder(
            name = "KomojuIcMaster",
            defaultWidth = 24.dp,
            defaultHeight = 17.dp,
            viewportWidth = 24f,
            viewportHeight = 17f,
        ).apply {
            group {
                path(fill = SolidColor(Color(0xFF252525))) {
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
                path(fill = SolidColor(Color(0xFFEB001B))) {
                    moveTo(9f, 13.5f)
                    curveToRelative(2.761f, 0f, 5f, -2.239f, 5f, -5f)
                    reflectiveCurveToRelative(-2.239f, -5f, -5f, -5f)
                    reflectiveCurveToRelative(-5f, 2.239f, -5f, 5f)
                    reflectiveCurveToRelative(2.239f, 5f, 5f, 5f)
                    close()
                }
                path(fill = SolidColor(Color(0xFFF79E1B))) {
                    moveTo(15f, 13.5f)
                    curveToRelative(2.761f, 0f, 5f, -2.239f, 5f, -5f)
                    reflectiveCurveToRelative(-2.239f, -5f, -5f, -5f)
                    reflectiveCurveToRelative(-5f, 2.239f, -5f, 5f)
                    reflectiveCurveToRelative(2.239f, 5f, 5f, 5f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFFF5F00)),
                    pathFillType = PathFillType.EvenOdd,
                ) {
                    moveTo(12f, 4.5f)
                    curveToRelative(1.214f, 0.912f, 2f, 2.364f, 2f, 4f)
                    curveToRelative(0f, 1.636f, -0.786f, 3.088f, -2f, 4f)
                    curveToRelative(-1.214f, -0.912f, -2f, -2.364f, -2f, -4f)
                    curveToRelative(0f, -1.636f, 0.786f, -3.088f, 2f, -4f)
                    close()
                }
            }
        }.build()

        return _KomojuIcMaster!!
    }

@Suppress("ObjectPropertyName")
private var _KomojuIcMaster: ImageVector? = null
