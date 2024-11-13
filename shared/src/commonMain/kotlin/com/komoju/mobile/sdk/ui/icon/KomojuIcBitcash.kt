package com.komoju.mobile.sdk.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val KomojuIcon.Bitcash: ImageVector
    get() {
        if (_KomojuIcBitcash != null) {
            return _KomojuIcBitcash!!
        }
        _KomojuIcBitcash = ImageVector.Builder(
            name = "KomojuIcBitcash",
            defaultWidth = 56.dp,
            defaultHeight = 54.dp,
            viewportWidth = 218f,
            viewportHeight = 211f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF00388B)),
                pathFillType = PathFillType.EvenOdd,
            ) {
                moveTo(196f, 135.2f)
                arcToRelative(67.2f, 67.2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -64.3f, 66.9f)
                horizontalLineToRelative(-2.5f)
                lineToRelative(-3.9f, -0.1f)
                lineToRelative(-6.3f, -0.5f)
                arcTo(98.5f, 98.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 129.2f, 5f)
                horizontalLineToRelative(2f)
                arcToRelative(51.6f, 51.6f, 0f, isMoreThanHalf = false, isPositiveArc = true, 41.6f, 79f)
                arcToRelative(67.6f, 67.6f, 0f, isMoreThanHalf = false, isPositiveArc = true, 23.2f, 51.2f)
                close()
                moveToRelative(-40.5f, -0.5f)
                arcToRelative(26.7f, 26.7f, 0f, isMoreThanHalf = false, isPositiveArc = false, -25.1f, -26.5f)
                horizontalLineToRelative(-1.5f)
                arcToRelative(20.3f, 20.3f, 0f, isMoreThanHalf = false, isPositiveArc = true, -20.3f, -20.3f)
                arcToRelative(20f, 20f, 0f, isMoreThanHalf = false, isPositiveArc = true, 3.7f, -11.5f)
                arcToRelative(20f, 20f, 0f, isMoreThanHalf = false, isPositiveArc = true, 16.6f, -8.8f)
                arcToRelative(11f, 11f, 0f, isMoreThanHalf = false, isPositiveArc = false, 11.1f, -11f)
                arcToRelative(11f, 11f, 0f, isMoreThanHalf = false, isPositiveArc = false, -3.3f, -7.8f)
                curveToRelative(-2f, -2.1f, -4.9f, -3.3f, -7.8f, -3.3f)
                horizontalLineToRelative(-3f)
                curveToRelative(-0.9f, 0.2f, -1.8f, 0.4f, -2.8f, 0.4f)
                arcToRelative(57.6f, 57.6f, 0f, isMoreThanHalf = false, isPositiveArc = false, -42f, 90f)
                curveToRelative(2.2f, 2.9f, 4.3f, 5.7f, 6.9f, 8.4f)
                arcToRelative(58.6f, 58.6f, 0f, isMoreThanHalf = false, isPositiveArc = false, 37.9f, 17f)
                lineToRelative(3f, 0.1f)
                horizontalLineToRelative(1.5f)
                curveToRelative(14f, -0.8f, 25.1f, -12.5f, 25.1f, -26.7f)
                close()
            }
            path(fill = SolidColor(Color(0xFFABCD03))) {
                moveTo(155.5f, 134.9f)
                arcToRelative(26.7f, 26.7f, 0f, isMoreThanHalf = false, isPositiveArc = true, -25.1f, 26.5f)
                horizontalLineToRelative(-4f)
                arcToRelative(47f, 47f, 0f, isMoreThanHalf = false, isPositiveArc = true, -30.6f, -79.9f)
                curveToRelative(7.9f, -7.9f, 18.8f, -13f, 30.6f, -13.7f)
                horizontalLineToRelative(2.5f)
                arcToRelative(20.3f, 20.3f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0f, 40.6f)
                horizontalLineToRelative(1.5f)
                curveToRelative(14f, 0.6f, 25.1f, 12.3f, 25.1f, 26.5f)
                close()
            }
            path(fill = SolidColor(Color(0xFFE62E8B))) {
                moveTo(130.6f, 161.4f)
                horizontalLineToRelative(-4.5f)
                arcToRelative(57.7f, 57.7f, 0f, isMoreThanHalf = false, isPositiveArc = true, -54.9f, -57.8f)
                arcToRelative(57.8f, 57.8f, 0f, isMoreThanHalf = false, isPositiveArc = true, 54.9f, -57.9f)
                horizontalLineToRelative(3f)
                curveToRelative(2.9f, 0f, 5.7f, 1.2f, 7.8f, 3.2f)
                arcToRelative(11f, 11f, 0f, isMoreThanHalf = false, isPositiveArc = true, 3.2f, 7.9f)
                arcToRelative(11f, 11f, 0f, isMoreThanHalf = false, isPositiveArc = true, -11f, 11f)
                horizontalLineToRelative(-2.5f)
                curveToRelative(-12f, 0.7f, -22.7f, 5.8f, -30.7f, 13.7f)
                arcToRelative(47.3f, 47.3f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0f, 66.2f)
                curveToRelative(8f, 8f, 18.8f, 13.1f, 30.7f, 13.7f)
                horizontalLineToRelative(2.5f)
                close()
            }
        }.build()

        return _KomojuIcBitcash!!
    }

@Suppress("ObjectPropertyName")
private var _KomojuIcBitcash: ImageVector? = null
