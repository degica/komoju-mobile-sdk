package com.komoju.mobile.sdk.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val KomojuIcon.PaymentStatusPending: ImageVector
    get() {
        if (_KomojuIcPaymentStatusPending != null) {
            return _KomojuIcPaymentStatusPending!!
        }
        _KomojuIcPaymentStatusPending = ImageVector.Builder(
            name = "KomojuIcPaymentStatusPending",
            defaultWidth = 49.dp,
            defaultHeight = 49.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(16.24f, 7.76f)
                curveTo(15.07f, 6.59f, 13.54f, 6f, 12f, 6f)
                verticalLineToRelative(6f)
                lineToRelative(-4.24f, 4.24f)
                curveToRelative(2.34f, 2.34f, 6.14f, 2.34f, 8.49f, 0f)
                curveToRelative(2.34f, -2.34f, 2.34f, -6.14f, -0.01f, -8.48f)
                close()
                moveTo(12f, 2f)
                curveTo(6.48f, 2f, 2f, 6.48f, 2f, 12f)
                reflectiveCurveToRelative(4.48f, 10f, 10f, 10f)
                reflectiveCurveToRelative(10f, -4.48f, 10f, -10f)
                reflectiveCurveTo(17.52f, 2f, 12f, 2f)
                close()
                moveTo(12f, 20f)
                curveToRelative(-4.42f, 0f, -8f, -3.58f, -8f, -8f)
                reflectiveCurveToRelative(3.58f, -8f, 8f, -8f)
                reflectiveCurveToRelative(8f, 3.58f, 8f, 8f)
                reflectiveCurveToRelative(-3.58f, 8f, -8f, 8f)
                close()
            }
        }.build()

        return _KomojuIcPaymentStatusPending!!
    }

@Suppress("ObjectPropertyName")
private var _KomojuIcPaymentStatusPending: ImageVector? = null
