package com.komoju.mobile.sdk.ui.icon

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val KomojuIcon.Jcb: ImageVector
    get() {
        if (_KomojuIcJcb != null) {
            return _KomojuIcJcb!!
        }
        _KomojuIcJcb = ImageVector.Builder(
            name = "KomojuIcJcb",
            defaultWidth = 22.dp,
            defaultHeight = 17.dp,
            viewportWidth = 22f,
            viewportHeight = 17f,
        ).apply {
            group {
                path(fill = SolidColor(Color(0xFFFFFFFF))) {
                    moveTo(21.063f, 13.63f)
                    curveTo(21.063f, 15.393f, 19.61f, 16.828f, 17.825f, 16.828f)
                    horizontalLineTo(0.203f)
                    verticalLineTo(4.164f)
                    curveTo(0.203f, 2.401f, 1.657f, 0.966f, 3.442f, 0.966f)
                    horizontalLineTo(21.063f)
                    verticalLineTo(13.63f)
                    close()
                }
                path(
                    fill = Brush.linearGradient(
                        colorStops = arrayOf(
                            0f to Color(0xFF007940),
                            0.229f to Color(0xFF00873F),
                            0.743f to Color(0xFF40A737),
                            1f to Color(0xFF5CB531),
                        ),
                        start = Offset(14.188f, 9.78f),
                        end = Offset(19.918f, 9.78f),
                    ),
                ) {
                    moveTo(15.325f, 10.383f)
                    horizontalLineTo(16.664f)
                    curveTo(16.702f, 10.383f, 16.792f, 10.37f, 16.83f, 10.37f)
                    curveTo(17.085f, 10.32f, 17.302f, 10.093f, 17.302f, 9.778f)
                    curveTo(17.302f, 9.476f, 17.085f, 9.25f, 16.83f, 9.187f)
                    curveTo(16.792f, 9.174f, 16.715f, 9.174f, 16.664f, 9.174f)
                    horizontalLineTo(15.325f)
                    verticalLineTo(10.383f)
                    close()
                }
                path(
                    fill = Brush.linearGradient(
                        colorStops = arrayOf(
                            0f to Color(0xFF007940),
                            0.229f to Color(0xFF00873F),
                            0.743f to Color(0xFF40A737),
                            1f to Color(0xFF5CB531),
                        ),
                        start = Offset(14.188f, 8.898f),
                        end = Offset(19.918f, 8.898f),
                    ),
                ) {
                    moveTo(16.511f, 2.036f)
                    curveTo(15.236f, 2.036f, 14.191f, 3.056f, 14.191f, 4.327f)
                    verticalLineTo(6.707f)
                    horizontalLineTo(17.467f)
                    curveTo(17.544f, 6.707f, 17.633f, 6.707f, 17.697f, 6.719f)
                    curveTo(18.436f, 6.757f, 18.985f, 7.135f, 18.985f, 7.789f)
                    curveTo(18.985f, 8.305f, 18.615f, 8.746f, 17.926f, 8.834f)
                    verticalLineTo(8.859f)
                    curveTo(18.679f, 8.91f, 19.253f, 9.325f, 19.253f, 9.967f)
                    curveTo(19.253f, 10.66f, 18.615f, 11.113f, 17.773f, 11.113f)
                    horizontalLineTo(14.178f)
                    verticalLineTo(15.771f)
                    horizontalLineTo(17.582f)
                    curveTo(18.857f, 15.771f, 19.903f, 14.751f, 19.903f, 13.479f)
                    verticalLineTo(2.036f)
                    horizontalLineTo(16.511f)
                    close()
                }
                path(
                    fill = Brush.linearGradient(
                        colorStops = arrayOf(
                            0f to Color(0xFF007940),
                            0.229f to Color(0xFF00873F),
                            0.743f to Color(0xFF40A737),
                            1f to Color(0xFF5CB531),
                        ),
                        start = Offset(14.188f, 7.939f),
                        end = Offset(19.918f, 7.939f),
                    ),
                ) {
                    moveTo(17.136f, 7.94f)
                    curveTo(17.136f, 7.638f, 16.919f, 7.437f, 16.664f, 7.399f)
                    curveTo(16.639f, 7.399f, 16.575f, 7.386f, 16.537f, 7.386f)
                    horizontalLineTo(15.325f)
                    verticalLineTo(8.494f)
                    horizontalLineTo(16.537f)
                    curveTo(16.575f, 8.494f, 16.651f, 8.494f, 16.664f, 8.482f)
                    curveTo(16.919f, 8.444f, 17.136f, 8.242f, 17.136f, 7.94f)
                    close()
                }
                path(
                    fill = Brush.linearGradient(
                        colorStops = arrayOf(
                            0f to Color(0xFF1F286F),
                            0.475f to Color(0xFF004E94),
                            0.826f to Color(0xFF0066B1),
                            1f to Color(0xFF006FBC),
                        ),
                        start = Offset(1.361f, 8.898f),
                        end = Offset(7.179f, 8.898f),
                    ),
                ) {
                    moveTo(3.684f, 2.036f)
                    curveTo(2.409f, 2.036f, 1.363f, 3.056f, 1.363f, 4.327f)
                    verticalLineTo(9.98f)
                    curveTo(2.014f, 10.295f, 2.689f, 10.496f, 3.365f, 10.496f)
                    curveTo(4.168f, 10.496f, 4.602f, 10.017f, 4.602f, 9.363f)
                    verticalLineTo(6.694f)
                    horizontalLineTo(6.591f)
                    verticalLineTo(9.35f)
                    curveTo(6.591f, 10.383f, 5.941f, 11.226f, 3.735f, 11.226f)
                    curveTo(2.396f, 11.226f, 1.351f, 10.936f, 1.351f, 10.936f)
                    verticalLineTo(15.758f)
                    horizontalLineTo(4.755f)
                    curveTo(6.03f, 15.758f, 7.076f, 14.738f, 7.076f, 13.467f)
                    verticalLineTo(2.036f)
                    horizontalLineTo(3.684f)
                    close()
                }
                path(
                    fill = Brush.linearGradient(
                        colorStops = arrayOf(
                            0f to Color(0xFF6C2C2F),
                            0.173f to Color(0xFF882730),
                            0.573f to Color(0xFFBE1833),
                            0.859f to Color(0xFFDC0436),
                            1f to Color(0xFFE60039),
                        ),
                        start = Offset(7.744f, 8.898f),
                        end = Offset(13.395f, 8.898f),
                    ),
                ) {
                    moveTo(10.098f, 2.036f)
                    curveTo(8.823f, 2.036f, 7.777f, 3.056f, 7.777f, 4.327f)
                    verticalLineTo(7.324f)
                    curveTo(8.364f, 6.833f, 9.384f, 6.518f, 11.028f, 6.593f)
                    curveTo(11.908f, 6.631f, 12.852f, 6.87f, 12.852f, 6.87f)
                    verticalLineTo(7.84f)
                    curveTo(12.38f, 7.6f, 11.819f, 7.386f, 11.092f, 7.336f)
                    curveTo(9.843f, 7.248f, 9.09f, 7.852f, 9.09f, 8.91f)
                    curveTo(9.09f, 9.98f, 9.843f, 10.584f, 11.092f, 10.483f)
                    curveTo(11.819f, 10.433f, 12.38f, 10.206f, 12.852f, 9.98f)
                    verticalLineTo(10.949f)
                    curveTo(12.852f, 10.949f, 11.921f, 11.188f, 11.028f, 11.226f)
                    curveTo(9.384f, 11.302f, 8.364f, 10.987f, 7.777f, 10.496f)
                    verticalLineTo(15.783f)
                    horizontalLineTo(11.182f)
                    curveTo(12.457f, 15.783f, 13.502f, 14.763f, 13.502f, 13.492f)
                    verticalLineTo(2.036f)
                    horizontalLineTo(10.098f)
                    close()
                }
            }
        }.build()

        return _KomojuIcJcb!!
    }

@Suppress("ObjectPropertyName")
private var _KomojuIcJcb: ImageVector? = null
