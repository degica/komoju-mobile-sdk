package com.komoju.mobile.sdk.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val KomojuIcon.Konbini: ImageVector
    get() {
        if (_KomojuIcKonbini != null) {
            return _KomojuIcKonbini!!
        }
        _KomojuIcKonbini = ImageVector.Builder(
            name = "KomojuIcKonbini",
            defaultWidth = 33.dp,
            defaultHeight = 33.dp,
            viewportWidth = 33f,
            viewportHeight = 33f,
        ).apply {
            path(
                stroke = SolidColor(Color(0xFF0F1C29)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
            ) {
                moveTo(6.151f, 14.591f)
                verticalLineToRelative(11.436f)
                curveToRelative(0f, 1.12f, 0f, 1.68f, 0.218f, 2.108f)
                curveToRelative(0.192f, 0.376f, 0.498f, 0.682f, 0.874f, 0.874f)
                curveToRelative(0.428f, 0.218f, 0.988f, 0.218f, 2.108f, 0.218f)
                horizontalLineToRelative(13.964f)
                curveToRelative(1.12f, 0f, 1.68f, 0f, 2.108f, -0.218f)
                curveToRelative(0.376f, -0.192f, 0.682f, -0.498f, 0.874f, -0.874f)
                curveToRelative(0.218f, -0.428f, 0.218f, -0.988f, 0.218f, -2.108f)
                verticalLineTo(14.591f)
            }
            path(
                fill = SolidColor(Color(0xFF79C3F8)),
                stroke = SolidColor(Color(0xFF0F1C29)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
            ) {
                moveTo(6.151f, 3.773f)
                lineToRelative(-3.818f, 6.363f)
                curveToRelative(-0.002f, 0.859f, 0.286f, 1.692f, 0.818f, 2.366f)
                curveToRelative(0.531f, 0.674f, 1.275f, 1.149f, 2.11f, 1.348f)
                curveToRelative(0.835f, 0.198f, 1.712f, 0.109f, 2.49f, -0.254f)
                curveToRelative(0.778f, -0.363f, 1.41f, -0.978f, 1.795f, -1.745f)
                curveToRelative(0.313f, 0.632f, 0.797f, 1.163f, 1.397f, 1.535f)
                curveToRelative(0.6f, 0.372f, 1.291f, 0.569f, 1.996f, 0.569f)
                curveToRelative(0.706f, 0f, 1.398f, -0.197f, 1.997f, -0.569f)
                curveToRelative(0.6f, -0.372f, 1.084f, -0.903f, 1.397f, -1.535f)
                curveToRelative(0.314f, 0.632f, 0.798f, 1.163f, 1.397f, 1.535f)
                curveToRelative(0.6f, 0.372f, 1.291f, 0.569f, 1.997f, 0.569f)
                curveToRelative(0.706f, 0f, 1.397f, -0.197f, 1.996f, -0.569f)
                curveToRelative(0.6f, -0.372f, 1.084f, -0.903f, 1.398f, -1.535f)
                curveToRelative(0.384f, 0.767f, 1.017f, 1.382f, 1.794f, 1.745f)
                curveToRelative(0.778f, 0.363f, 1.656f, 0.452f, 2.491f, 0.254f)
                curveToRelative(0.835f, -0.199f, 1.578f, -0.674f, 2.11f, -1.348f)
                curveToRelative(0.531f, -0.674f, 0.819f, -1.507f, 0.817f, -2.366f)
                lineToRelative(-3.818f, -6.363f)
                horizontalLineTo(6.151f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFF0F1C29)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
            ) {
                moveTo(13.788f, 29.227f)
                verticalLineToRelative(-5.091f)
                curveToRelative(0f, -0.507f, 0f, -0.76f, 0.045f, -0.97f)
                curveToRelative(0.167f, -0.765f, 0.765f, -1.363f, 1.53f, -1.53f)
                curveToRelative(0.21f, -0.045f, 0.463f, -0.045f, 0.97f, -0.045f)
                curveToRelative(0.507f, 0f, 0.761f, 0f, 0.971f, 0.045f)
                curveToRelative(0.765f, 0.167f, 1.363f, 0.765f, 1.529f, 1.53f)
                curveToRelative(0.046f, 0.21f, 0.046f, 0.463f, 0.046f, 0.97f)
                verticalLineToRelative(5.091f)
            }
        }.build()

        return _KomojuIcKonbini!!
    }

@Suppress("ObjectPropertyName")
private var _KomojuIcKonbini: ImageVector? = null
