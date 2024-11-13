package com.komoju.mobile.sdk.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val KomojuIcon.PaymentStatusFailed: ImageVector
    get() {
        if (_KomojuIcPaymentStatusFailed != null) {
            return _KomojuIcPaymentStatusFailed!!
        }
        _KomojuIcPaymentStatusFailed = ImageVector.Builder(
            name = "KomojuIcPaymentStatusFailed",
            defaultWidth = 49.dp,
            defaultHeight = 49.dp,
            viewportWidth = 49f,
            viewportHeight = 49f,
        ).apply {
            path(fill = SolidColor(Color(0xFFF24D49))) {
                moveTo(46.207f, 15.1f)
                lineTo(33.907f, 2.8f)
                curveTo(33.721f, 2.61f, 33.466f, 2.502f, 33.2f, 2.5f)
                horizontalLineTo(15.8f)
                curveTo(15.535f, 2.5f, 15.28f, 2.605f, 15.093f, 2.793f)
                lineTo(2.793f, 15.1f)
                curveTo(2.607f, 15.286f, 2.502f, 15.537f, 2.5f, 15.8f)
                verticalLineTo(33.2f)
                curveTo(2.5f, 33.465f, 2.605f, 33.72f, 2.793f, 33.907f)
                lineTo(15.093f, 46.207f)
                curveTo(15.28f, 46.395f, 15.535f, 46.5f, 15.8f, 46.5f)
                horizontalLineTo(33.2f)
                curveTo(33.465f, 46.5f, 33.72f, 46.395f, 33.907f, 46.207f)
                lineTo(46.207f, 33.907f)
                curveTo(46.395f, 33.72f, 46.5f, 33.465f, 46.5f, 33.2f)
                verticalLineTo(15.8f)
                curveTo(46.498f, 15.537f, 46.393f, 15.286f, 46.207f, 15.1f)
                close()
                moveTo(24.5f, 38.5f)
                curveTo(23.395f, 38.5f, 22.5f, 37.605f, 22.5f, 36.5f)
                curveTo(22.5f, 35.395f, 23.395f, 34.5f, 24.5f, 34.5f)
                curveTo(25.605f, 34.5f, 26.5f, 35.395f, 26.5f, 36.5f)
                curveTo(26.5f, 37.605f, 25.605f, 38.5f, 24.5f, 38.5f)
                close()
                moveTo(25.025f, 30.5f)
                horizontalLineTo(23.976f)
                curveTo(23.433f, 30.5f, 22.99f, 30.067f, 22.976f, 29.525f)
                lineTo(22.526f, 11.525f)
                curveTo(22.512f, 10.963f, 22.964f, 10.5f, 23.526f, 10.5f)
                horizontalLineTo(25.475f)
                curveTo(26.037f, 10.5f, 26.489f, 10.963f, 26.475f, 11.525f)
                lineTo(26.025f, 29.525f)
                curveTo(26.011f, 30.067f, 25.568f, 30.5f, 25.025f, 30.5f)
                close()
            }
        }.build()

        return _KomojuIcPaymentStatusFailed!!
    }

@Suppress("ObjectPropertyName")
private var _KomojuIcPaymentStatusFailed: ImageVector? = null
