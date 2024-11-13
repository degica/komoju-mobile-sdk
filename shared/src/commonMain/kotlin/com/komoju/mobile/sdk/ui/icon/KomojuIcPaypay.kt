package com.komoju.mobile.sdk.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val KomojuIcon.Paypay: ImageVector
    get() {
        if (_KomojuIcPaypay != null) {
            return _KomojuIcPaypay!!
        }
        _KomojuIcPaypay = ImageVector.Builder(
            name = "KomojuIcPaypay",
            defaultWidth = 35.dp,
            defaultHeight = 33.dp,
            viewportWidth = 35f,
            viewportHeight = 33f,
        ).apply {
            path(fill = SolidColor(Color(0xFFFF0033))) {
                moveTo(8.507f, 31.451f)
                horizontalLineTo(7.104f)
                curveToRelative(-2.441f, 0f, -4.437f, -1.99f, -4.437f, -4.423f)
                verticalLineTo(10.902f)
                curveToRelative(3.981f, -0.401f, 7.693f, -0.39f, 10.949f, -0.079f)
                lineTo(8.507f, 31.451f)
                close()
                moveToRelative(18.044f, -13.94f)
                curveToRelative(0.705f, -2.856f, -3.918f, -5.5f, -11.315f, -6.498f)
                lineTo(12.552f, 22.15f)
                curveToRelative(6.2f, 0.296f, 13.242f, -1.567f, 13.999f, -4.639f)
                close()
                moveTo(28.23f, 1.544f)
                horizontalLineTo(7.104f)
                curveToRelative(-2.362f, 0f, -4.3f, 1.863f, -4.427f, 4.186f)
                curveToRelative(17.69f, -0.496f, 26.294f, 5.637f, 24.944f, 11.754f)
                curveToRelative(-1.218f, 5.506f, -6.83f, 7.617f, -15.948f, 8.314f)
                lineToRelative(-1.366f, 5.658f)
                horizontalLineTo(28.23f)
                curveToRelative(2.441f, 0f, 4.437f, -1.99f, 4.437f, -4.423f)
                verticalLineTo(5.967f)
                curveToRelative(0f, -2.433f, -1.996f, -4.423f, -4.437f, -4.423f)
                close()
            }
        }.build()

        return _KomojuIcPaypay!!
    }

@Suppress("ObjectPropertyName")
private var _KomojuIcPaypay: ImageVector? = null
