package com.komoju.mobile.sdk.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val KomojuIcon.PaymentStatusCompleted: ImageVector
    get() {
        if (_KomojuIcPaymentStatusCompleted != null) {
            return _KomojuIcPaymentStatusCompleted!!
        }
        _KomojuIcPaymentStatusCompleted = ImageVector.Builder(
            name = "KomojuIcPaymentStatusCompleted",
            defaultWidth = 49.dp,
            defaultHeight = 49.dp,
            viewportWidth = 49f,
            viewportHeight = 49f,
        ).apply {
            path(fill = SolidColor(Color(0xFF3CC239))) {
                moveTo(24.5f, 1.5f)
                curveTo(11.8f, 1.5f, 1.5f, 11.8f, 1.5f, 24.5f)
                curveTo(1.5f, 37.2f, 11.8f, 47.5f, 24.5f, 47.5f)
                curveTo(37.2f, 47.5f, 47.5f, 37.2f, 47.5f, 24.5f)
                curveTo(47.5f, 11.8f, 37.2f, 1.5f, 24.5f, 1.5f)
                close()
                moveTo(37.2f, 17.2f)
                lineTo(21.2f, 33.2f)
                curveTo(21f, 33.4f, 20.8f, 33.5f, 20.5f, 33.5f)
                curveTo(20.2f, 33.5f, 20f, 33.4f, 19.8f, 33.2f)
                lineTo(11.8f, 25.2f)
                curveTo(11.4f, 24.8f, 11.4f, 24.2f, 11.8f, 23.8f)
                curveTo(12.2f, 23.4f, 12.8f, 23.4f, 13.2f, 23.8f)
                lineTo(20.5f, 31.1f)
                lineTo(35.8f, 15.8f)
                curveTo(36.2f, 15.4f, 36.8f, 15.4f, 37.2f, 15.8f)
                curveTo(37.6f, 16.2f, 37.6f, 16.8f, 37.2f, 17.2f)
                close()
            }
        }.build()

        return _KomojuIcPaymentStatusCompleted!!
    }

@Suppress("ObjectPropertyName")
private var _KomojuIcPaymentStatusCompleted: ImageVector? = null
