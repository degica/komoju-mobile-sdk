package com.komoju.mobile.sdk.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val KomojuIcon.AuPay: ImageVector
    get() {
        if (_KomojuIcAuPay != null) {
            return _KomojuIcAuPay!!
        }
        _KomojuIcAuPay = ImageVector.Builder(
            name = "KomojuIcAuPay",
            defaultWidth = 84.dp,
            defaultHeight = 16.dp,
            viewportWidth = 84f,
            viewportHeight = 16f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFFEB560C)),
                pathFillType = PathFillType.EvenOdd,
            ) {
                moveTo(49.52f, 16f)
                horizontalLineToRelative(-2.77f)
                verticalLineTo(0f)
                horizontalLineTo(53f)
                arcToRelative(5.55f, 5.55f, 0f, isMoreThanHalf = false, isPositiveArc = true, 5.24f, 5.6f)
                arcTo(5.58f, 5.58f, 0f, isMoreThanHalf = false, isPositiveArc = true, 53f, 11.23f)
                horizontalLineToRelative(-3.47f)
                verticalLineTo(16f)
                close()
                moveToRelative(22.16f, 0f)
                horizontalLineTo(68.7f)
                lineToRelative(-0.92f, -3.09f)
                horizontalLineToRelative(-5.65f)
                lineTo(61.05f, 16f)
                horizontalLineToRelative(-2.98f)
                lineToRelative(5.68f, -16f)
                horizontalLineToRelative(2.23f)
                lineToRelative(5.7f, 16f)
                close()
                moveToRelative(6.49f, 0f)
                horizontalLineToRelative(-2.6f)
                verticalLineToRelative(-4.66f)
                lineTo(69.72f, 0f)
                horizontalLineToRelative(3.34f)
                lineToRelative(3.8f, 8.06f)
                lineTo(80.65f, 0f)
                horizontalLineTo(84f)
                lineToRelative(-5.83f, 11.34f)
                verticalLineTo(16f)
                close()
                moveToRelative(-11.3f, -5.64f)
                lineTo(64.9f, 4.64f)
                lineToRelative(-1.93f, 5.72f)
                horizontalLineToRelative(3.9f)
                close()
                moveTo(52.6f, 8.64f)
                curveToRelative(0.53f, 0f, 2.73f, -0.59f, 2.73f, -3.06f)
                curveToRelative(0f, -2.48f, -2.23f, -2.92f, -2.73f, -2.92f)
                horizontalLineToRelative(-3.13f)
                verticalLineToRelative(5.98f)
                horizontalLineToRelative(3.13f)
                close()
                moveTo(17.41f, 0.06f)
                curveToRelative(-2.05f, 9.23f, -4.24f, 13.43f, -2.87f, 13.67f)
                curveToRelative(1.37f, 0.24f, 3f, -1.6f, 3.78f, -2.96f)
                curveToRelative(0.94f, -1.63f, 1.33f, -5.65f, 2.49f, -8.04f)
                curveToRelative(0.96f, -2f, 3.4f, -2.88f, 5.04f, -2.67f)
                curveToRelative(-1.96f, 8.88f, -4.14f, 13.5f, -2.54f, 13.78f)
                curveToRelative(2.02f, 0.35f, 4.1f, -1.24f, 5.03f, -2.87f)
                curveToRelative(1.57f, -2.73f, 0.82f, -3.66f, 2.7f, -8.19f)
                arcToRelative(5.36f, 5.36f, 0f, isMoreThanHalf = false, isPositiveArc = true, 5.03f, -2.76f)
                curveTo(35.87f, 1.22f, 33f, 12.04f, 33f, 12.04f)
                reflectiveCurveToRelative(-0.4f, 1.44f, 0.34f, 1.72f)
                curveToRelative(1.47f, 0.57f, 6.1f, -5.59f, 7.63f, -8.42f)
                horizontalLineToRelative(2.34f)
                curveToRelative(-3.85f, 5.98f, -7.29f, 10.95f, -11.43f, 10.46f)
                curveToRelative(-3.06f, -0.37f, -2.9f, -2.73f, -2.9f, -2.73f)
                reflectiveCurveToRelative(-2.14f, 3.05f, -6.11f, 2.88f)
                curveToRelative(-3.81f, -0.17f, -4.08f, -2.84f, -4.08f, -2.84f)
                reflectiveCurveToRelative(-2.48f, 3f, -5.4f, 2.69f)
                curveToRelative(-3.23f, -0.33f, -2.97f, -2.84f, -2.97f, -2.84f)
                reflectiveCurveToRelative(-2.36f, 3.51f, -6.7f, 2.99f)
                curveToRelative(-1.09f, -0.14f, -4.76f, -0.83f, -3.44f, -5.83f)
                curveToRelative(2.43f, -9.2f, 9.4f, -10.4f, 17.13f, -10.06f)
                close()
                moveTo(12.4f, 1.9f)
                curveToRelative(-0.85f, 4f, -1.68f, 7.18f, -2.82f, 9.13f)
                curveToRelative(-1.5f, 2.57f, -3.63f, 3.13f, -4.7f, 2.8f)
                curveToRelative(-1.5f, -0.45f, -0.36f, -4f, 0.42f, -5.61f)
                curveToRelative(0.93f, -1.8f, 2.4f, -5.42f, 7.1f, -6.32f)
                close()
            }
        }.build()

        return _KomojuIcAuPay!!
    }

@Suppress("ObjectPropertyName")
private var _KomojuIcAuPay: ImageVector? = null
