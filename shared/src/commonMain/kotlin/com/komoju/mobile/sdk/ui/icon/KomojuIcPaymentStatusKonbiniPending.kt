package com.komoju.mobile.sdk.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val KomojuIcon.PaymentStatusKonbiniPending: ImageVector
    get() {
        if (_KomojuIcPaymentStatusKonbiniPending != null) {
            return _KomojuIcPaymentStatusKonbiniPending!!
        }
        _KomojuIcPaymentStatusKonbiniPending = ImageVector.Builder(
            name = "KomojuIcPaymentStatusKonbiniPending",
            defaultWidth = 49.dp,
            defaultHeight = 49.dp,
            viewportWidth = 49f,
            viewportHeight = 49f,
        ).apply {
            group {
                path(fill = SolidColor(Color(0xFF0F1C29))) {
                    moveTo(46.7f, 13.1f)
                    lineTo(37.7f, 1.1f)
                    curveTo(37.4f, 0.65f, 36.95f, 0.5f, 36.5f, 0.5f)
                    horizontalLineTo(12.5f)
                    curveTo(12.05f, 0.5f, 11.6f, 0.65f, 11.3f, 1.1f)
                    lineTo(2.3f, 13.1f)
                    curveTo(2.15f, 13.4f, 2f, 13.7f, 2f, 14f)
                    curveTo(2f, 19.25f, 5.45f, 21.5f, 8.75f, 21.5f)
                    curveTo(11f, 21.5f, 12.8f, 20.75f, 14f, 19.4f)
                    curveTo(15.2f, 20.75f, 17.15f, 21.5f, 19.4f, 21.5f)
                    curveTo(21.5f, 21.5f, 23.3f, 20.75f, 24.5f, 19.55f)
                    curveTo(25.7f, 20.75f, 27.5f, 21.5f, 29.6f, 21.5f)
                    curveTo(31.85f, 21.5f, 33.8f, 20.75f, 35f, 19.4f)
                    curveTo(36.2f, 20.75f, 38f, 21.5f, 40.25f, 21.5f)
                    curveTo(43.55f, 21.5f, 47f, 19.25f, 47f, 14f)
                    curveTo(47f, 13.7f, 46.85f, 13.4f, 46.7f, 13.1f)
                    close()
                }
                path(fill = SolidColor(Color(0xFFFF993B))) {
                    moveTo(39.5f, 24.5f)
                    curveTo(35.15f, 24.5f, 30.5f, 27.8f, 30.5f, 33.5f)
                    curveTo(30.5f, 38.6f, 37.7f, 45.95f, 38.45f, 46.7f)
                    curveTo(38.75f, 47f, 39.2f, 47.15f, 39.5f, 47.15f)
                    curveTo(39.8f, 47.15f, 40.25f, 47f, 40.55f, 46.7f)
                    curveTo(41.3f, 45.95f, 48.5f, 38.6f, 48.5f, 33.5f)
                    curveTo(48.5f, 27.8f, 43.85f, 24.5f, 39.5f, 24.5f)
                    close()
                    moveTo(39.5f, 36.5f)
                    curveTo(37.85f, 36.5f, 36.5f, 35.15f, 36.5f, 33.5f)
                    curveTo(36.5f, 31.85f, 37.85f, 30.5f, 39.5f, 30.5f)
                    curveTo(41.15f, 30.5f, 42.5f, 31.85f, 42.5f, 33.5f)
                    curveTo(42.5f, 35.15f, 41.15f, 36.5f, 39.5f, 36.5f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF0F1C29))) {
                    moveTo(27.5f, 33.5f)
                    curveTo(27.5f, 29.45f, 29.3f, 26.3f, 31.7f, 24.35f)
                    curveTo(30.95f, 24.5f, 30.35f, 24.5f, 29.6f, 24.5f)
                    curveTo(27.65f, 24.5f, 26f, 24.05f, 24.5f, 23.3f)
                    curveTo(23f, 24.05f, 21.35f, 24.5f, 19.4f, 24.5f)
                    curveTo(17.45f, 24.5f, 15.65f, 24.05f, 14f, 23.15f)
                    curveTo(12.5f, 24.05f, 10.7f, 24.5f, 8.75f, 24.5f)
                    curveTo(8f, 24.5f, 7.25f, 24.35f, 6.5f, 24.2f)
                    verticalLineTo(44f)
                    curveTo(6.5f, 44.9f, 7.1f, 45.5f, 8f, 45.5f)
                    horizontalLineTo(33.35f)
                    curveTo(30.65f, 42.35f, 27.5f, 37.55f, 27.5f, 33.5f)
                    close()
                }
            }
        }.build()

        return _KomojuIcPaymentStatusKonbiniPending!!
    }

@Suppress("ObjectPropertyName")
private var _KomojuIcPaymentStatusKonbiniPending: ImageVector? = null
