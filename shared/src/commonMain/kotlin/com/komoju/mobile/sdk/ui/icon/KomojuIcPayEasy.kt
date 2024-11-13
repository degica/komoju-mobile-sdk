package com.komoju.mobile.sdk.ui.icon

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val KomojuIcon.PayEasy: ImageVector
    get() {
        if (_KomojuIcPayEasy != null) {
            return _KomojuIcPayEasy!!
        }
        _KomojuIcPayEasy = ImageVector.Builder(
            name = "KomojuIcPayEasy",
            defaultWidth = 46.dp,
            defaultHeight = 42.dp,
            viewportWidth = 23f,
            viewportHeight = 21f,
        ).apply {
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color(0xFF1965AC),
                        0.46f to Color(0xFF166CB2),
                        0.6f to Color(0xFF0E7CC1),
                        0.84f to Color(0xFF0492D5),
                        1f to Color(0xFF009ADC),
                    ),
                    start = Offset(12.46f, 17.08f),
                    end = Offset(4.36f, 1.84f),
                ),
            ) {
                moveTo(6.05f, 20.49f)
                lineTo(4.22f, 19.22f)
                curveTo(2.712f, 16.622f, 2.045f, 13.622f, 2.31f, 10.63f)
                curveTo(2.71f, 4.83f, 4f, 2f, 4f, 2f)
                lineTo(5.07f, 1.88f)
                curveTo(5.784f, 1.353f, 6.597f, 0.975f, 7.46f, 0.77f)
                curveTo(8.46f, 0.69f, 11.36f, 0.77f, 11.36f, 0.77f)
                curveTo(12.028f, 0.95f, 12.653f, 1.261f, 13.199f, 1.685f)
                curveTo(13.745f, 2.109f, 14.201f, 2.638f, 14.54f, 3.24f)
                curveTo(15.333f, 4.84f, 15.613f, 6.645f, 15.34f, 8.41f)
                curveTo(15.09f, 9.597f, 14.574f, 10.712f, 13.83f, 11.67f)
                curveTo(12.83f, 12.67f, 11.44f, 14.06f, 10.01f, 14.06f)
                curveTo(9.228f, 14.077f, 8.448f, 13.969f, 7.7f, 13.74f)
                curveTo(7.483f, 16.059f, 6.927f, 18.333f, 6.05f, 20.49f)
                close()
            }
            path(fill = SolidColor(Color(0xFF009ADC))) {
                moveTo(4f, 2f)
                lineTo(6f, 2.16f)
                curveTo(6f, 2.16f, 3.81f, 7.49f, 4f, 10.91f)
                curveTo(4.19f, 14.33f, 3.92f, 15.04f, 4.65f, 17.35f)
                curveTo(4.935f, 18.453f, 5.402f, 19.5f, 6.03f, 20.45f)
                lineTo(4.16f, 19.18f)
                curveTo(3.379f, 17.795f, 2.828f, 16.292f, 2.53f, 14.73f)
                curveTo(2.37f, 12.9f, 1.97f, 12.9f, 2.2f, 10.2f)
                curveTo(2.438f, 8.221f, 2.792f, 6.258f, 3.26f, 4.32f)
                curveTo(3.425f, 3.523f, 3.673f, 2.746f, 4f, 2f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color(0xFF009ADC),
                        0.52f to Color(0xFFD4EDFF),
                        1f to Color(0xFF1965AC),
                    ),
                    start = Offset(11.63f, 11.43f),
                    end = Offset(11.63f, 2.84f),
                ),
            ) {
                moveTo(14.64f, 7.69f)
                curveTo(14.56f, 5.62f, 15.04f, 3.32f, 11.06f, 2.84f)
                curveTo(10.847f, 2.873f, 10.64f, 2.931f, 10.44f, 3.01f)
                curveTo(10.938f, 3.383f, 11.339f, 3.87f, 11.61f, 4.43f)
                curveTo(11.941f, 5.208f, 12.052f, 6.063f, 11.93f, 6.9f)
                curveTo(11.794f, 7.786f, 11.409f, 8.615f, 10.82f, 9.29f)
                curveTo(10.18f, 9.77f, 10.1f, 9.93f, 9.82f, 10.01f)
                curveTo(9.549f, 10.163f, 9.255f, 10.271f, 8.95f, 10.33f)
                horizontalLineTo(8.58f)
                curveTo(8.889f, 10.672f, 9.265f, 10.948f, 9.684f, 11.139f)
                curveTo(10.104f, 11.33f, 10.559f, 11.432f, 11.02f, 11.44f)
                curveTo(11.317f, 11.44f, 11.612f, 11.386f, 11.89f, 11.28f)
                curveTo(12.25f, 11.11f, 14.72f, 9.76f, 14.64f, 7.69f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(9.87f, 10f)
                curveTo(10.11f, 9.92f, 10.19f, 9.76f, 10.87f, 9.28f)
                curveTo(11.459f, 8.605f, 11.844f, 7.776f, 11.98f, 6.89f)
                curveTo(12.102f, 6.053f, 11.991f, 5.198f, 11.66f, 4.42f)
                curveTo(11.375f, 3.855f, 10.956f, 3.367f, 10.44f, 3f)
                curveTo(9.53f, 3.36f, 7.91f, 4.4f, 7.8f, 7.36f)
                curveTo(7.689f, 8.409f, 7.982f, 9.46f, 8.62f, 10.3f)
                horizontalLineTo(9f)
                curveTo(9.304f, 10.248f, 9.598f, 10.146f, 9.87f, 10f)
                close()
            }
            path(
                fill = Brush.radialGradient(
                    colorStops = arrayOf(
                        0f to Color(0xFFC3E400),
                        0.3f to Color(0xFFC1E300),
                        0.41f to Color(0xFFBAE101),
                        0.49f to Color(0xFFAFDE03),
                        0.55f to Color(0xFF9ED806),
                        0.61f to Color(0xFF88D109),
                        0.63f to Color(0xFF7ACD0B),
                        0.65f to Color(0xFF70CA11),
                        0.77f to Color(0xFF40B931),
                        0.87f to Color(0xFF1DAD49),
                        0.95f to Color(0xFF08A657),
                        1f to Color(0xFF00A35C),
                    ),
                    center = Offset(20.01f, 6.7f),
                    radius = 2.5f,
                ),
            ) {
                moveTo(20.01f, 9.2f)
                curveTo(21.391f, 9.2f, 22.51f, 8.081f, 22.51f, 6.7f)
                curveTo(22.51f, 5.319f, 21.391f, 4.2f, 20.01f, 4.2f)
                curveTo(18.629f, 4.2f, 17.51f, 5.319f, 17.51f, 6.7f)
                curveTo(17.51f, 8.081f, 18.629f, 9.2f, 20.01f, 9.2f)
                close()
            }
            path(
                fill = Brush.radialGradient(
                    colorStops = arrayOf(
                        0f to Color(0xFFC3E400),
                        0.3f to Color(0xFFC1E300),
                        0.41f to Color(0xFFBAE101),
                        0.49f to Color(0xFFAFDE03),
                        0.55f to Color(0xFF9ED806),
                        0.61f to Color(0xFF88D109),
                        0.63f to Color(0xFF7ACD0B),
                        0.65f to Color(0xFF70CA11),
                        0.77f to Color(0xFF40B931),
                        0.87f to Color(0xFF1DAD49),
                        0.95f to Color(0xFF08A657),
                        1f to Color(0xFF00A35C),
                    ),
                    center = Offset(14.48f, 6.74f),
                    radius = 1.99f,
                ),
            ) {
                moveTo(14.48f, 8.73f)
                curveTo(15.579f, 8.73f, 16.47f, 7.839f, 16.47f, 6.74f)
                curveTo(16.47f, 5.641f, 15.579f, 4.75f, 14.48f, 4.75f)
                curveTo(13.381f, 4.75f, 12.49f, 5.641f, 12.49f, 6.74f)
                curveTo(12.49f, 7.839f, 13.381f, 8.73f, 14.48f, 8.73f)
                close()
            }
            path(
                fill = Brush.radialGradient(
                    colorStops = arrayOf(
                        0f to Color(0xFFC3E400),
                        0.3f to Color(0xFFC1E300),
                        0.41f to Color(0xFFBAE101),
                        0.49f to Color(0xFFAFDE03),
                        0.55f to Color(0xFF9ED806),
                        0.61f to Color(0xFF88D109),
                        0.63f to Color(0xFF7ACD0B),
                        0.65f to Color(0xFF70CA11),
                        0.77f to Color(0xFF40B931),
                        0.87f to Color(0xFF1DAD49),
                        0.95f to Color(0xFF08A657),
                        1f to Color(0xFF00A35C),
                    ),
                    center = Offset(9.67f, 6.7f),
                    radius = 1.55f,
                ),
            ) {
                moveTo(9.67f, 8.25f)
                curveTo(10.526f, 8.25f, 11.22f, 7.556f, 11.22f, 6.7f)
                curveTo(11.22f, 5.844f, 10.526f, 5.15f, 9.67f, 5.15f)
                curveTo(8.814f, 5.15f, 8.12f, 5.844f, 8.12f, 6.7f)
                curveTo(8.12f, 7.556f, 8.814f, 8.25f, 9.67f, 8.25f)
                close()
            }
            path(
                fill = Brush.radialGradient(
                    colorStops = arrayOf(
                        0f to Color(0xFFC3E400),
                        0.3f to Color(0xFFC1E300),
                        0.41f to Color(0xFFBAE101),
                        0.49f to Color(0xFFAFDE03),
                        0.55f to Color(0xFF9ED806),
                        0.61f to Color(0xFF88D109),
                        0.63f to Color(0xFF7ACD0B),
                        0.65f to Color(0xFF70CA11),
                        0.77f to Color(0xFF40B931),
                        0.87f to Color(0xFF1DAD49),
                        0.95f to Color(0xFF08A657),
                        1f to Color(0xFF00A35C),
                    ),
                    center = Offset(1.32f, 6.7f),
                    radius = 0.83f,
                ),
            ) {
                moveTo(1.32f, 7.53f)
                curveTo(1.778f, 7.53f, 2.15f, 7.158f, 2.15f, 6.7f)
                curveTo(2.15f, 6.242f, 1.778f, 5.87f, 1.32f, 5.87f)
                curveTo(0.862f, 5.87f, 0.49f, 6.242f, 0.49f, 6.7f)
                curveTo(0.49f, 7.158f, 0.862f, 7.53f, 1.32f, 7.53f)
                close()
            }
        }.build()

        return _KomojuIcPayEasy!!
    }

@Suppress("ObjectPropertyName")
private var _KomojuIcPayEasy: ImageVector? = null
