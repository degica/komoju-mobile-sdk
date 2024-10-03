package com.komoju.android.sdk.utils

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

/**
 * An Empty Screen to Support Android Studio Previews
 */
internal object PreviewScreen : Screen {
    private fun readResolve(): Any = PreviewScreen

    @Composable
    override fun Content() = Unit
}
