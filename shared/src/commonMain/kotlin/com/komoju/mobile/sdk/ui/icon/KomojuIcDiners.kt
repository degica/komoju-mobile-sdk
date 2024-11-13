package com.komoju.mobile.sdk.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val KomojuIcon.Diners: ImageVector
    get() {
        if (_KomojuIcDiners != null) {
            return _KomojuIcDiners!!
        }
        _KomojuIcDiners = ImageVector.Builder(
            name = "KomojuIcDiners",
            defaultWidth = 21.dp,
            defaultHeight = 16.dp,
            viewportWidth = 21f,
            viewportHeight = 16f,
        ).apply {
            path(fill = SolidColor(Color(0xFF0079BE))) {
                moveTo(11.783f, 15.906f)
                curveToRelative(4.336f, 0.02f, 8.294f, -3.537f, 8.294f, -7.864f)
                curveToRelative(0f, -4.732f, -3.958f, -8.003f, -8.294f, -8.001f)
                horizontalLineTo(8.051f)
                curveToRelative(-4.388f, -0.002f, -8f, 3.27f, -8f, 8.001f)
                curveToRelative(0f, 4.328f, 3.612f, 7.884f, 8f, 7.864f)
                horizontalLineToRelative(3.732f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(8.069f, 0.697f)
                curveToRelative(-4.01f, 0.001f, -7.259f, 3.251f, -7.26f, 7.262f)
                curveToRelative(0.001f, 4.011f, 3.25f, 7.26f, 7.26f, 7.262f)
                curveToRelative(4.01f, -0.002f, 7.26f, -3.251f, 7.261f, -7.262f)
                curveToRelative(-0.001f, -4.011f, -3.251f, -7.261f, -7.261f, -7.262f)
                close()
                moveTo(3.467f, 7.959f)
                curveToRelative(0.004f, -1.96f, 1.228f, -3.631f, 2.954f, -4.296f)
                verticalLineToRelative(8.591f)
                curveTo(4.695f, 11.59f, 3.471f, 9.92f, 3.467f, 7.959f)
                close()
                moveToRelative(6.249f, 4.297f)
                verticalLineTo(3.663f)
                curveToRelative(1.727f, 0.663f, 2.953f, 2.335f, 2.956f, 4.296f)
                curveToRelative(-0.003f, 1.962f, -1.229f, 3.633f, -2.956f, 4.297f)
                close()
            }
            path(fill = SolidColor(Color(0xFF0079BE))) {
                moveTo(11.783f, 15.906f)
                curveToRelative(4.336f, 0.02f, 8.294f, -3.537f, 8.294f, -7.864f)
                curveToRelative(0f, -4.732f, -3.958f, -8.003f, -8.294f, -8.001f)
                horizontalLineTo(8.051f)
                curveToRelative(-4.388f, -0.002f, -8f, 3.27f, -8f, 8.001f)
                curveToRelative(0f, 4.328f, 3.612f, 7.884f, 8f, 7.864f)
                horizontalLineToRelative(3.732f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(8.069f, 0.697f)
                curveToRelative(-4.01f, 0.001f, -7.259f, 3.251f, -7.26f, 7.262f)
                curveToRelative(0.001f, 4.011f, 3.25f, 7.26f, 7.26f, 7.262f)
                curveToRelative(4.01f, -0.002f, 7.26f, -3.251f, 7.261f, -7.262f)
                curveToRelative(-0.001f, -4.011f, -3.251f, -7.261f, -7.261f, -7.262f)
                close()
                moveTo(3.467f, 7.959f)
                curveToRelative(0.004f, -1.96f, 1.228f, -3.631f, 2.954f, -4.296f)
                verticalLineToRelative(8.591f)
                curveTo(4.695f, 11.59f, 3.471f, 9.92f, 3.467f, 7.959f)
                close()
                moveToRelative(6.249f, 4.297f)
                verticalLineTo(3.663f)
                curveToRelative(1.727f, 0.663f, 2.953f, 2.335f, 2.956f, 4.296f)
                curveToRelative(-0.003f, 1.962f, -1.229f, 3.633f, -2.956f, 4.297f)
                close()
            }
        }.build()

        return _KomojuIcDiners!!
    }

@Suppress("ObjectPropertyName")
private var _KomojuIcDiners: ImageVector? = null
