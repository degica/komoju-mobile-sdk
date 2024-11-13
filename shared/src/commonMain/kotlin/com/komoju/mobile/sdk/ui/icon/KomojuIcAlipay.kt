package com.komoju.mobile.sdk.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val KomojuIcon.Alipay: ImageVector
    get() {
        if (_KomojuIcAlipay != null) {
            return _KomojuIcAlipay!!
        }
        _KomojuIcAlipay = ImageVector.Builder(
            name = "KomojuIcAlipay",
            defaultWidth = 38.dp,
            defaultHeight = 38.dp,
            viewportWidth = 38f,
            viewportHeight = 38f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF1677FF)),
                pathFillType = PathFillType.EvenOdd,
            ) {
                moveTo(30.862f, 23.864f)
                curveTo(36.233f, 25.688f, 37.519f, 25.764f, 37.519f, 25.764f)
                verticalLineTo(6.08f)
                curveTo(37.519f, 2.736f, 34.796f, 0f, 31.543f, 0f)
                horizontalLineTo(5.976f)
                curveTo(2.723f, 0f, 0f, 2.736f, 0f, 6.08f)
                verticalLineTo(31.92f)
                curveTo(0f, 35.264f, 2.723f, 38f, 5.976f, 38f)
                horizontalLineTo(31.468f)
                curveTo(34.796f, 38f, 37.443f, 35.264f, 37.443f, 31.92f)
                verticalLineTo(31.692f)
                curveTo(37.443f, 31.692f, 27.685f, 27.588f, 22.768f, 25.156f)
                curveTo(19.44f, 29.26f, 15.204f, 31.768f, 10.817f, 31.768f)
                curveTo(3.328f, 31.768f, 0.756f, 25.156f, 4.312f, 20.748f)
                curveTo(5.068f, 19.76f, 6.43f, 18.924f, 8.472f, 18.392f)
                curveTo(11.649f, 17.632f, 16.717f, 18.924f, 21.483f, 20.444f)
                curveTo(22.315f, 18.848f, 23.071f, 17.1f, 23.601f, 15.2f)
                horizontalLineTo(8.926f)
                verticalLineTo(13.68f)
                horizontalLineTo(16.49f)
                verticalLineTo(11.02f)
                horizontalLineTo(7.337f)
                verticalLineTo(9.5f)
                horizontalLineTo(16.49f)
                verticalLineTo(5.7f)
                curveTo(16.49f, 5.7f, 16.49f, 5.016f, 17.171f, 5.016f)
                horizontalLineTo(20.878f)
                verticalLineTo(9.5f)
                horizontalLineTo(29.955f)
                verticalLineTo(11.02f)
                horizontalLineTo(20.878f)
                verticalLineTo(13.68f)
                horizontalLineTo(28.291f)
                curveTo(27.61f, 16.644f, 26.475f, 19.304f, 25.189f, 21.66f)
                curveTo(27.383f, 22.572f, 29.35f, 23.332f, 30.862f, 23.864f)
                close()
            }
        }.build()

        return _KomojuIcAlipay!!
    }

@Suppress("ObjectPropertyName")
private var _KomojuIcAlipay: ImageVector? = null
