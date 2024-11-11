package com.komoju.mobile.sdk.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val KomojuIcon.BankTransfer: ImageVector
    get() {
        if (_KomojuIcBankTransfer != null) {
            return _KomojuIcBankTransfer!!
        }
        _KomojuIcBankTransfer = ImageVector.Builder(
            name = "KomojuIcBankTransfer",
            defaultWidth = 47.dp,
            defaultHeight = 49.dp,
            viewportWidth = 47f,
            viewportHeight = 49f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF1D354F)),
                pathFillType = PathFillType.EvenOdd,
            ) {
                moveTo(0f, 12.408f)
                lineTo(23.5f, 0f)
                lineTo(47f, 12.408f)
                verticalLineToRelative(4.136f)
                horizontalLineTo(28.577f)
                verticalLineToRelative(5.64f)
                horizontalLineToRelative(-9.776f)
                verticalLineToRelative(-5.64f)
                horizontalLineToRelative(-6.017f)
                verticalLineToRelative(5.64f)
                horizontalLineTo(3.008f)
                verticalLineToRelative(-5.64f)
                horizontalLineTo(0f)
                verticalLineToRelative(-4.136f)
                close()
                moveTo(47f, 43.24f)
                verticalLineToRelative(5.64f)
                horizontalLineTo(0f)
                verticalLineToRelative(-5.64f)
                horizontalLineToRelative(3.008f)
                verticalLineToRelative(-4.512f)
                horizontalLineToRelative(1.883f)
                verticalLineTo(22.184f)
                horizontalLineToRelative(6.016f)
                verticalLineToRelative(16.544f)
                horizontalLineToRelative(1.877f)
                verticalLineToRelative(4.512f)
                horizontalLineToRelative(6.017f)
                verticalLineToRelative(-4.512f)
                horizontalLineToRelative(1.879f)
                verticalLineTo(22.184f)
                horizontalLineToRelative(6.016f)
                verticalLineToRelative(16.544f)
                horizontalLineToRelative(1.881f)
                verticalLineToRelative(4.512f)
                horizontalLineToRelative(6.017f)
                verticalLineToRelative(-4.511f)
                horizontalLineToRelative(9.776f)
                verticalLineToRelative(4.511f)
                horizontalLineTo(47f)
                close()
                moveTo(34.594f, 16.544f)
                horizontalLineToRelative(9.776f)
                verticalLineToRelative(5.64f)
                horizontalLineToRelative(-1.881f)
                verticalLineToRelative(16.544f)
                horizontalLineToRelative(-6.016f)
                verticalLineTo(22.184f)
                horizontalLineToRelative(-1.879f)
                verticalLineToRelative(-5.64f)
                close()
            }
        }.build()

        return _KomojuIcBankTransfer!!
    }

@Suppress("ObjectPropertyName")
private var _KomojuIcBankTransfer: ImageVector? = null
