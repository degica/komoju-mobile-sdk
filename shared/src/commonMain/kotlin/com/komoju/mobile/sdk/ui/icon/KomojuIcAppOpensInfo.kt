package com.komoju.mobile.sdk.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val KomojuIcon.AppOpensInfo: ImageVector
    get() {
        if (_KomojuIcAppOpensInfo != null) {
            return _KomojuIcAppOpensInfo!!
        }
        _KomojuIcAppOpensInfo = ImageVector.Builder(
            name = "KomojuIcAppOpensInfo",
            defaultWidth = 32.dp,
            defaultHeight = 33.dp,
            viewportWidth = 32f,
            viewportHeight = 33f,
        ).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(16f, 0.5f)
                lineTo(16f, 0.5f)
                arcTo(16f, 16f, 0f, isMoreThanHalf = false, isPositiveArc = true, 32f, 16.5f)
                lineTo(32f, 16.5f)
                arcTo(16f, 16f, 0f, isMoreThanHalf = false, isPositiveArc = true, 16f, 32.5f)
                lineTo(16f, 32.5f)
                arcTo(16f, 16f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 16.5f)
                lineTo(0f, 16.5f)
                arcTo(16f, 16f, 0f, isMoreThanHalf = false, isPositiveArc = true, 16f, 0.5f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(6f, 6.5f)
                horizontalLineToRelative(20f)
                verticalLineToRelative(20f)
                horizontalLineToRelative(-20f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFFB800)),
                stroke = SolidColor(Color(0xFFFFB800)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
            ) {
                moveTo(16.833f, 8.167f)
                lineTo(9.411f, 17.073f)
                curveTo(9.121f, 17.422f, 8.975f, 17.596f, 8.973f, 17.744f)
                curveTo(8.971f, 17.872f, 9.028f, 17.994f, 9.128f, 18.074f)
                curveTo(9.242f, 18.167f, 9.469f, 18.167f, 9.923f, 18.167f)
                horizontalLineTo(16f)
                lineTo(15.167f, 24.833f)
                lineTo(22.589f, 15.927f)
                curveTo(22.879f, 15.578f, 23.025f, 15.404f, 23.027f, 15.256f)
                curveTo(23.029f, 15.128f, 22.972f, 15.006f, 22.872f, 14.926f)
                curveTo(22.758f, 14.833f, 22.531f, 14.833f, 22.077f, 14.833f)
                horizontalLineTo(16f)
                lineTo(16.833f, 8.167f)
                close()
            }
        }.build()

        return _KomojuIcAppOpensInfo!!
    }

@Suppress("ObjectPropertyName")
private var _KomojuIcAppOpensInfo: ImageVector? = null
