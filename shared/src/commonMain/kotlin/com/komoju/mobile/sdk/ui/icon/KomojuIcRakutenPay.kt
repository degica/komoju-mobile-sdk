package com.komoju.mobile.sdk.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val KomojuIcon.RakutenPay: ImageVector
    get() {
        if (_KomojuIcRakutenPay != null) {
            return _KomojuIcRakutenPay!!
        }
        _KomojuIcRakutenPay = ImageVector.Builder(
            name = "KomojuIcRakutenPay",
            defaultWidth = 77.dp,
            defaultHeight = 29.dp,
            viewportWidth = 572f,
            viewportHeight = 215f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFFBF0000)),
                pathFillType = PathFillType.EvenOdd,
            ) {
                moveTo(571.6f, 41.5f)
                lineToRelative(-47f, 142.5f)
                curveToRelative(-5f, 15.2f, -16f, 23.7f, -30.2f, 23.7f)
                curveToRelative(-8.9f, 0f, -17.1f, -3.3f, -23.3f, -9.4f)
                lineToRelative(8.7f, -13.5f)
                curveToRelative(4.2f, 3.7f, 8.1f, 5.4f, 13.3f, 5.4f)
                curveToRelative(7.3f, 0f, 10.8f, -3.1f, 14.4f, -12.9f)
                lineToRelative(2.9f, -7.9f)
                lineToRelative(-45.4f, -128f)
                horizontalLineToRelative(19.4f)
                lineToRelative(34.8f, 101.8f)
                lineToRelative(32.9f, -101.8f)
                horizontalLineToRelative(19.5f)
                close()
                moveTo(428.4f, 158.2f)
                curveToRelative(-10.2f, 12.1f, -21.8f, 17.7f, -37f, 17.7f)
                curveToRelative(-34.3f, 0f, -57.8f, -28.1f, -57.8f, -68.9f)
                curveToRelative(0f, -40.8f, 23.7f, -68.7f, 58.3f, -68.7f)
                curveToRelative(15.6f, 0f, 26.4f, 4.6f, 36.8f, 15.4f)
                lineToRelative(2.7f, -12.3f)
                horizontalLineToRelative(16.7f)
                verticalLineToRelative(131.1f)
                horizontalLineToRelative(-16.7f)
                close()
                moveTo(353.5f, 107f)
                curveToRelative(0f, 29.8f, 16.2f, 51.4f, 38.3f, 51.4f)
                curveToRelative(22.1f, 0f, 38.1f, -21.4f, 38.1f, -51.2f)
                curveToRelative(0f, -30f, -16f, -51.4f, -38.3f, -51.4f)
                curveToRelative(-21.9f, 0f, -38.1f, 21.9f, -38.1f, 51.2f)
                close()
                moveTo(275.6f, 8.8f)
                curveToRelative(29.3f, 0f, 50.2f, 20.2f, 50.2f, 49.1f)
                curveToRelative(0f, 28.9f, -20.8f, 49.3f, -50.2f, 49.3f)
                horizontalLineTo(240f)
                verticalLineToRelative(65.3f)
                horizontalLineToRelative(-18.7f)
                verticalLineTo(8.8f)
                close()
                moveToRelative(-1.2f, 81f)
                curveToRelative(18.3f, 0f, 31.4f, -13.3f, 31.4f, -31.8f)
                reflectiveCurveToRelative(-12.9f, -31.6f, -31.4f, -31.6f)
                horizontalLineToRelative(-34.3f)
                verticalLineToRelative(63.5f)
                horizontalLineToRelative(34.3f)
                close()
                moveToRelative(-245.3f, 125f)
                lineTo(0.4f, 190.7f)
                horizontalLineTo(202f)
                close()
                moveToRelative(32.8f, -42.1f)
                horizontalLineTo(29.1f)
                verticalLineTo(0.3f)
                horizontalLineToRelative(54f)
                curveToRelative(32.8f, 0f, 59.5f, 26.6f, 59.5f, 59.4f)
                curveToRelative(0f, 19.9f, -9.9f, 37.6f, -25f, 48.4f)
                lineToRelative(48.5f, 64.5f)
                horizontalLineToRelative(-41f)
                lineTo(85f, 119.1f)
                horizontalLineTo(61.9f)
                close()
                moveToRelative(0f, -86.4f)
                horizontalLineToRelative(21.4f)
                curveTo(98f, 86.3f, 110f, 74.4f, 110f, 59.6f)
                curveToRelative(0f, -14.7f, -12f, -26.7f, -26.7f, -26.7f)
                horizontalLineTo(61.9f)
                close()
            }
        }.build()

        return _KomojuIcRakutenPay!!
    }

@Suppress("ObjectPropertyName")
private var _KomojuIcRakutenPay: ImageVector? = null
