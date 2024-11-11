package com.komoju.mobile.sdk.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val KomojuIcon.Visa: ImageVector
    get() {
        if (_KomojuIcVisa != null) {
            return _KomojuIcVisa!!
        }
        _KomojuIcVisa = ImageVector.Builder(
            name = "KomojuIcVisa",
            defaultWidth = 24.dp,
            defaultHeight = 17.dp,
            viewportWidth = 24f,
            viewportHeight = 17f,
        ).apply {
            group {
                path(fill = SolidColor(Color(0xFFFFFFFF))) {
                    moveTo(21.96f, 0.5f)
                    horizontalLineTo(2.04f)
                    curveTo(0.91f, 0.5f, 0f, 1.42f, 0f, 2.56f)
                    verticalLineToRelative(11.88f)
                    curveToRelative(0f, 1.14f, 0.91f, 2.06f, 2.04f, 2.06f)
                    horizontalLineToRelative(19.92f)
                    curveToRelative(1.13f, 0f, 2.04f, -0.92f, 2.04f, -2.06f)
                    verticalLineTo(2.56f)
                    curveToRelative(0f, -1.14f, -0.91f, -2.06f, -2.04f, -2.06f)
                    close()
                }
                path(
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 0.2f,
                    strokeLineWidth = 0.5f,
                ) {
                    moveTo(2.04f, 0.75f)
                    horizontalLineToRelative(19.92f)
                    curveToRelative(0.98f, 0f, 1.79f, 0.81f, 1.79f, 1.81f)
                    verticalLineToRelative(11.88f)
                    curveToRelative(0f, 1f, -0.8f, 1.81f, -1.8f, 1.81f)
                    horizontalLineTo(2.06f)
                    arcToRelative(1.8f, 1.8f, 0f, isMoreThanHalf = false, isPositiveArc = true, -1.8f, -1.81f)
                    verticalLineTo(2.56f)
                    curveToRelative(0f, -1f, 0.8f, -1.81f, 1.8f, -1.81f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF1434CB))) {
                    moveTo(2.6f, 6.35f)
                    arcToRelative(7.3f, 7.3f, 0f, isMoreThanHalf = false, isPositiveArc = false, -1.83f, -0.7f)
                    lineToRelative(0.02f, -0.13f)
                    horizontalLineToRelative(2.8f)
                    curveToRelative(0.38f, 0.01f, 0.69f, 0.13f, 0.79f, 0.53f)
                    lineToRelative(0.6f, 2.93f)
                    lineToRelative(0.19f, 0.88f)
                    lineToRelative(1.7f, -4.34f)
                    horizontalLineToRelative(1.84f)
                    lineToRelative(-2.73f, 6.36f)
                    horizontalLineTo(4.14f)
                    lineTo(2.59f, 6.35f)
                    close()
                    moveToRelative(7.46f, 5.54f)
                    horizontalLineTo(8.32f)
                    lineToRelative(1.09f, -6.37f)
                    horizontalLineToRelative(1.74f)
                    lineToRelative(-1.1f, 6.37f)
                    close()
                    moveToRelative(6.3f, -6.22f)
                    lineToRelative(-0.24f, 1.38f)
                    lineToRelative(-0.16f, -0.07f)
                    arcToRelative(3.09f, 3.09f, 0f, isMoreThanHalf = false, isPositiveArc = false, -1.29f, -0.24f)
                    curveToRelative(-0.69f, 0f, -1f, 0.28f, -1f, 0.55f)
                    curveToRelative(0f, 0.3f, 0.37f, 0.5f, 0.98f, 0.78f)
                    curveToRelative(1f, 0.46f, 1.46f, 1.01f, 1.46f, 1.74f)
                    curveToRelative(-0.02f, 1.32f, -1.2f, 2.17f, -3.03f, 2.17f)
                    curveToRelative(-0.78f, 0f, -1.53f, -0.16f, -1.93f, -0.33f)
                    lineToRelative(0.24f, -1.43f)
                    lineToRelative(0.23f, 0.1f)
                    curveToRelative(0.56f, 0.23f, 0.93f, 0.34f, 1.63f, 0.34f)
                    curveToRelative(0.5f, 0f, 1.03f, -0.2f, 1.04f, -0.63f)
                    curveToRelative(0f, -0.28f, -0.23f, -0.48f, -0.9f, -0.79f)
                    curveToRelative(-0.66f, -0.3f, -1.54f, -0.81f, -1.52f, -1.73f)
                    curveToRelative(0f, -1.24f, 1.21f, -2.1f, 2.93f, -2.1f)
                    curveToRelative(0.67f, 0f, 1.21f, 0.14f, 1.56f, 0.26f)
                    close()
                    moveToRelative(2.3f, 3.96f)
                    horizontalLineToRelative(1.45f)
                    lineToRelative(-0.4f, -1.84f)
                    lineToRelative(-0.12f, -0.55f)
                    lineToRelative(-0.23f, 0.61f)
                    lineToRelative(-0.7f, 1.78f)
                    close()
                    moveToRelative(2.15f, -4.11f)
                    lineToRelative(1.4f, 6.37f)
                    horizontalLineToRelative(-1.6f)
                    lineToRelative(-0.21f, -0.96f)
                    horizontalLineToRelative(-2.23f)
                    lineToRelative(-0.37f, 0.96f)
                    horizontalLineToRelative(-1.82f)
                    lineToRelative(2.58f, -5.84f)
                    curveToRelative(0.18f, -0.42f, 0.5f, -0.53f, 0.9f, -0.53f)
                    horizontalLineToRelative(1.35f)
                    close()
                }
            }
        }.build()

        return _KomojuIcVisa!!
    }

@Suppress("ObjectPropertyName")
private var _KomojuIcVisa: ImageVector? = null
