package com.komoju.mobile.sdk.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val KomojuIcon.Cvv: ImageVector
    get() {
        if (_KomojuIcCvv != null) {
            return _KomojuIcCvv!!
        }
        _KomojuIcCvv = ImageVector.Builder(
            name = "KomojuIcCvv",
            defaultWidth = 25.dp,
            defaultHeight = 17.dp,
            viewportWidth = 25f,
            viewportHeight = 17f,
        ).apply {
            group {
                path(
                    fill = SolidColor(Color(0xFF565656)),
                    fillAlpha = 0.2f,
                    strokeAlpha = 0.2f,
                    pathFillType = PathFillType.EvenOdd,
                ) {
                    moveTo(16.52f, 2.02f)
                    horizontalLineToRelative(-15f)
                    curveTo(0.82f, 2.02f, 0f, 2.83f, 0f, 3.55f)
                    verticalLineToRelative(11.43f)
                    curveToRelative(0f, 0.71f, 0.82f, 1.52f, 1.52f, 1.52f)
                    horizontalLineToRelative(19.05f)
                    curveToRelative(0.7f, 0f, 1.53f, -0.8f, 1.53f, -1.52f)
                    verticalLineTo(9.24f)
                    curveToRelative(-0.65f, 0.58f, -1.44f, 0.96f, -2.3f, 1.1f)
                    verticalLineToRelative(0.06f)
                    curveToRelative(0f, 0.5f, -0.26f, 0.77f, -0.75f, 0.77f)
                    horizontalLineTo(2.29f)
                    curveToRelative(-0.5f, 0f, -0.77f, -0.26f, -0.77f, -0.77f)
                    verticalLineTo(9.64f)
                    curveToRelative(0f, -0.5f, 0.28f, -0.76f, 0.77f, -0.76f)
                    horizontalLineToRelative(13.35f)
                    arcToRelative(4.56f, 4.56f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.88f, -6.86f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF565656)),
                    fillAlpha = 0.3f,
                    strokeAlpha = 0.3f,
                    pathFillType = PathFillType.EvenOdd,
                ) {
                    moveTo(14.74f, 4.31f)
                    horizontalLineTo(0f)
                    verticalLineTo(6.6f)
                    horizontalLineToRelative(14.54f)
                    arcToRelative(4.6f, 4.6f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.2f, -2.29f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF565656)),
                    pathFillType = PathFillType.EvenOdd,
                ) {
                    moveTo(19.05f, 11.17f)
                    arcToRelative(5.33f, 5.33f, 0f, isMoreThanHalf = true, isPositiveArc = true, 0f, -10.67f)
                    arcToRelative(5.33f, 5.33f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 10.67f)
                    close()
                    moveToRelative(-2.12f, -7.55f)
                    horizontalLineToRelative(-0.6f)
                    lineToRelative(-1.47f, 0.75f)
                    verticalLineToRelative(0.76f)
                    lineToRelative(1.16f, -0.6f)
                    verticalLineToRelative(3.51f)
                    horizontalLineToRelative(0.92f)
                    verticalLineTo(3.62f)
                    horizontalLineToRelative(-0.01f)
                    close()
                    moveToRelative(1.75f, 0.61f)
                    curveToRelative(0.44f, 0f, 0.74f, 0.25f, 0.74f, 0.6f)
                    curveToRelative(0f, 0.38f, -0.36f, 0.65f, -0.88f, 0.65f)
                    horizontalLineToRelative(-0.22f)
                    verticalLineToRelative(0.64f)
                    horizontalLineToRelative(0.27f)
                    curveToRelative(0.55f, 0f, 0.92f, 0.28f, 0.92f, 0.67f)
                    curveToRelative(0f, 0.38f, -0.36f, 0.64f, -0.88f, 0.64f)
                    curveToRelative(-0.38f, 0f, -0.76f, -0.12f, -1.16f, -0.35f)
                    verticalLineToRelative(0.76f)
                    curveToRelative(0.43f, 0.18f, 0.85f, 0.28f, 1.27f, 0.28f)
                    curveToRelative(1f, 0f, 1.69f, -0.51f, 1.69f, -1.25f)
                    curveToRelative(0f, -0.52f, -0.32f, -0.94f, -0.86f, -1.1f)
                    curveToRelative(0.46f, -0.16f, 0.76f, -0.56f, 0.76f, -1.02f)
                    curveToRelative(0f, -0.71f, -0.63f, -1.2f, -1.55f, -1.2f)
                    curveToRelative(-0.41f, 0f, -0.82f, 0.09f, -1.2f, 0.26f)
                    verticalLineToRelative(0.74f)
                    curveToRelative(0.37f, -0.2f, 0.74f, -0.32f, 1.1f, -0.32f)
                    close()
                    moveToRelative(3.3f, 1.66f)
                    curveToRelative(0.55f, 0f, 0.94f, 0.33f, 0.94f, 0.76f)
                    curveToRelative(0f, 0.45f, -0.39f, 0.76f, -0.95f, 0.76f)
                    curveToRelative(-0.33f, 0f, -0.68f, -0.1f, -1.04f, -0.33f)
                    verticalLineToRelative(0.79f)
                    curveToRelative(0.37f, 0.17f, 0.75f, 0.25f, 1.13f, 0.25f)
                    curveToRelative(0.2f, 0f, 0.38f, -0.03f, 0.55f, -0.08f)
                    curveToRelative(0.4f, -0.64f, 0.63f, -1.4f, 0.63f, -2.2f)
                    lineToRelative(-0.02f, -0.31f)
                    curveToRelative(-0.28f, -0.17f, -0.6f, -0.25f, -0.93f, -0.25f)
                    curveToRelative(-0.14f, 0f, -0.29f, 0.01f, -0.44f, 0.04f)
                    verticalLineToRelative(-1f)
                    horizontalLineToRelative(1.1f)
                    curveToRelative(-0.1f, -0.24f, -0.21f, -0.47f, -0.35f, -0.7f)
                    horizontalLineToRelative(-1.54f)
                    verticalLineToRelative(2.4f)
                    curveToRelative(0.3f, -0.07f, 0.6f, -0.13f, 0.91f, -0.13f)
                    close()
                }
            }
        }.build()

        return _KomojuIcCvv!!
    }

@Suppress("ObjectPropertyName")
private var _KomojuIcCvv: ImageVector? = null
