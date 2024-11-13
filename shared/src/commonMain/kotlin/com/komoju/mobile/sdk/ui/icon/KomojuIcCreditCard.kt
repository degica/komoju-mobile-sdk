package com.komoju.mobile.sdk.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val KomojuIcon.CreditCard: ImageVector
    get() {
        if (_KomojuIcCreditCard != null) {
            return _KomojuIcCreditCard!!
        }
        _KomojuIcCreditCard = ImageVector.Builder(
            name = "KomojuIcCreditCard",
            defaultWidth = 32.dp,
            defaultHeight = 33.dp,
            viewportWidth = 32f,
            viewportHeight = 33f,
        ).apply {
            path(fill = SolidColor(Color(0xFF79C3F8))) {
                moveTo(2.667f, 11.433f)
                verticalLineToRelative(2.4f)
                horizontalLineToRelative(26.666f)
                verticalLineToRelative(-2.4f)
                curveToRelative(0f, -1.493f, 0f, -2.24f, -0.29f, -2.81f)
                curveToRelative(-0.256f, -0.502f, -0.664f, -0.91f, -1.166f, -1.166f)
                curveToRelative(-0.57f, -0.29f, -1.317f, -0.29f, -2.81f, -0.29f)
                horizontalLineTo(6.933f)
                curveToRelative(-1.493f, 0f, -2.24f, 0f, -2.81f, 0.29f)
                curveToRelative(-0.502f, 0.256f, -0.91f, 0.664f, -1.166f, 1.166f)
                curveToRelative(-0.29f, 0.57f, -0.29f, 1.317f, -0.29f, 2.81f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFF0F1C29)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
            ) {
                moveTo(29.333f, 13.833f)
                horizontalLineTo(2.667f)
                moveToRelative(12f, 5.334f)
                horizontalLineTo(8f)
                moveToRelative(-5.333f, -7.734f)
                verticalLineToRelative(10.134f)
                curveToRelative(0f, 1.493f, 0f, 2.24f, 0.29f, 2.81f)
                curveToRelative(0.256f, 0.502f, 0.664f, 0.91f, 1.166f, 1.166f)
                curveToRelative(0.57f, 0.29f, 1.317f, 0.29f, 2.81f, 0.29f)
                horizontalLineToRelative(18.134f)
                curveToRelative(1.493f, 0f, 2.24f, 0f, 2.81f, -0.29f)
                curveToRelative(0.502f, -0.256f, 0.91f, -0.664f, 1.166f, -1.166f)
                curveToRelative(0.29f, -0.57f, 0.29f, -1.317f, 0.29f, -2.81f)
                verticalLineTo(11.433f)
                curveToRelative(0f, -1.493f, 0f, -2.24f, -0.29f, -2.81f)
                curveToRelative(-0.256f, -0.502f, -0.664f, -0.91f, -1.166f, -1.166f)
                curveToRelative(-0.57f, -0.29f, -1.317f, -0.29f, -2.81f, -0.29f)
                horizontalLineTo(6.933f)
                curveToRelative(-1.493f, 0f, -2.24f, 0f, -2.81f, 0.29f)
                curveToRelative(-0.502f, 0.256f, -0.91f, 0.664f, -1.166f, 1.166f)
                curveToRelative(-0.29f, 0.57f, -0.29f, 1.317f, -0.29f, 2.81f)
                close()
            }
        }.build()

        return _KomojuIcCreditCard!!
    }

@Suppress("ObjectPropertyName")
private var _KomojuIcCreditCard: ImageVector? = null
