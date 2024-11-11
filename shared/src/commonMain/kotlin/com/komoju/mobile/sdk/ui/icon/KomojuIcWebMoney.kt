package com.komoju.mobile.sdk.ui.icon

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val KomojuIcon.WebMoney: ImageVector
    get() {
        if (_KomojuIcWebMoney != null) {
            return _KomojuIcWebMoney!!
        }
        _KomojuIcWebMoney = ImageVector.Builder(
            name = "KomojuIcWebMoney",
            defaultWidth = 26.dp,
            defaultHeight = 16.dp,
            viewportWidth = 26f,
            viewportHeight = 16f,
        ).apply {
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0.14f to Color(0xFF0A3289),
                        0.16f to Color(0xFF173B82),
                        0.18f to Color(0xFF395170),
                        0.22f to Color(0xFF717653),
                        0.26f to Color(0xFFBDA82B),
                        0.29f to Color(0xFFF3CC0F),
                        0.31f to Color(0xFFF1C20F),
                        0.36f to Color(0xFFECA911),
                        0.42f to Color(0xFFE37F13),
                        0.49f to Color(0xFFD74516),
                        0.55f to Color(0xFFCD1318),
                        0.64f to Color(0xFFCB1319),
                        0.67f to Color(0xFFC4141D),
                        0.69f to Color(0xFFB81624),
                        0.71f to Color(0xFFA7192E),
                        0.73f to Color(0xFF911C3B),
                        0.75f to Color(0xFF75204C),
                        0.76f to Color(0xFF54255F),
                        0.77f to Color(0xFF2F2A75),
                        0.78f to Color(0xFF09308B),
                        0.86f to Color(0xFF2CA53B),
                        0.87f to Color(0xFF35A739),
                        0.89f to Color(0xFF4FAC34),
                        0.91f to Color(0xFF79B32C),
                        0.94f to Color(0xFFB2BE21),
                        0.97f to Color(0xFFF1CA15),
                    ),
                    start = Offset(1.67f, 10.85f),
                    end = Offset(24.31f, 5.2f),
                ),
            ) {
                moveTo(2.26f, 0.78f)
                curveTo(2.26f, 0.78f, 1.16f, 0.6f, 0.71f, 2.37f)
                curveTo(0.26f, 4.14f, 1.59f, 11.91f, 2.48f, 13.28f)
                curveTo(3.37f, 14.65f, 3.19f, 14.6f, 3.58f, 14.91f)
                curveTo(3.837f, 15.147f, 4.153f, 15.309f, 4.495f, 15.38f)
                curveTo(4.836f, 15.45f, 5.191f, 15.426f, 5.52f, 15.31f)
                curveTo(6.62f, 15.05f, 7.29f, 12.53f, 7.46f, 11.87f)
                lineTo(7.56f, 11.69f)
                curveTo(7.56f, 11.69f, 9.11f, 15.64f, 10.63f, 15.41f)
                curveTo(12.15f, 15.18f, 12.48f, 14.41f, 12.95f, 13.33f)
                curveTo(13.42f, 12.25f, 15.2f, 4f, 15.2f, 4f)
                curveTo(15.688f, 4.657f, 16.029f, 5.41f, 16.2f, 6.21f)
                curveTo(16.55f, 7.65f, 16.38f, 9.21f, 17.2f, 9.21f)
                curveTo(18.02f, 9.21f, 18.2f, 9.21f, 18.79f, 8f)
                curveTo(19.38f, 6.79f, 20.58f, 4.1f, 20.58f, 4.1f)
                curveTo(20.998f, 5.107f, 21.202f, 6.19f, 21.18f, 7.28f)
                curveTo(21.36f, 9.82f, 21.73f, 14.21f, 22.62f, 14.81f)
                curveTo(22.872f, 15.036f, 23.183f, 15.183f, 23.517f, 15.235f)
                curveTo(23.851f, 15.286f, 24.192f, 15.239f, 24.5f, 15.1f)
                curveTo(24.96f, 14.9f, 25.74f, 13.97f, 25.21f, 10.2f)
                curveTo(24.68f, 6.43f, 24.14f, 4f, 23.48f, 2.71f)
                curveTo(22.82f, 1.42f, 22.22f, 0.66f, 21.07f, 0.59f)
                curveTo(19.92f, 0.52f, 19.26f, 1.91f, 19f, 2.48f)
                curveTo(18.774f, 3.047f, 18.583f, 3.629f, 18.43f, 4.22f)
                curveTo(17.972f, 3.139f, 17.363f, 2.129f, 16.62f, 1.22f)
                curveTo(16.348f, 0.931f, 15.985f, 0.745f, 15.592f, 0.692f)
                curveTo(15.198f, 0.639f, 14.799f, 0.723f, 14.46f, 0.93f)
                curveTo(13.756f, 1.438f, 13.242f, 2.166f, 13f, 3f)
                curveTo(12.5f, 4.46f, 10.87f, 11.9f, 10.87f, 11.9f)
                curveTo(10.87f, 11.9f, 10.05f, 11.44f, 9.87f, 9.74f)
                curveTo(9.859f, 8.89f, 9.717f, 8.047f, 9.45f, 7.24f)
                curveTo(9.25f, 6.86f, 9.19f, 6.73f, 8.74f, 6.75f)
                curveTo(8.489f, 6.739f, 8.24f, 6.81f, 8.033f, 6.953f)
                curveTo(7.826f, 7.095f, 7.67f, 7.301f, 7.59f, 7.54f)
                curveTo(7.3f, 8.2f, 6.27f, 10.19f, 6.18f, 10.48f)
                curveTo(6.09f, 10.77f, 5.48f, 12f, 5.48f, 12f)
                curveTo(5.156f, 10.723f, 4.935f, 9.422f, 4.82f, 8.11f)
                curveTo(4.699f, 6.092f, 4.424f, 4.086f, 4f, 2.11f)
                curveTo(3.67f, 1f, 3.25f, 0.92f, 2.87f, 0.85f)
                curveTo(2.669f, 0.811f, 2.465f, 0.787f, 2.26f, 0.78f)
                close()
            }
        }.build()

        return _KomojuIcWebMoney!!
    }

@Suppress("ObjectPropertyName")
private var _KomojuIcWebMoney: ImageVector? = null
