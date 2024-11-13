package com.komoju.mobile.sdk.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * Interface representing a configurable theme with customizable UI properties.
 */
interface ConfigurableTheme {

    /**
     * primary color of the SDK
     *
     * @return A value representing a color value annotated with [Color].
     */
    val primaryColor: Long

    /**
     * Color value for the content (e.g., text or icon) inside the primary button.
     *
     * @return A value representing a color value annotated with [Color].
     */
    val primaryContentColor: Long

    /**
     * Corner radius for the primary button in density-independent pixels (DP).
     *
     * @return A value representing the corner radius in DP annotated with [Int].
     */
    val primaryShapeCornerRadiusInDp: Int

    /**
     * Color value for the loader/spinner.
     *
     * @return A value representing a color value annotated with [Color].
     */
    val loaderColor: Long

    companion object {
        /**
         * Provides a default implementation of the [ConfigurableTheme].
         */
        val default = DefaultConfigurableTheme()
    }
}

/**
 * Data class representing the default implementation of [ConfigurableTheme].
 * This class uses default values for UI elements such as buttons and loaders.
 *
 * @param primaryColor The background color of the primary button.
 * @param primaryContentColor The color of the content inside the primary button.
 * @param primaryShapeCornerRadiusInDp The corner radius of the primary button in DP.
 * @param loaderColor The color of the loader/spinner.
 */

data class DefaultConfigurableTheme(
    override val primaryColor: Long = 0xFF297FE7, // Default blue color for primary button.
    override val primaryContentColor: Long = 0xFFFFFFFF, // Default white color for primary button content.
    override val primaryShapeCornerRadiusInDp: Int = 8, // Default corner radius of 8 DP for primary button.
    override val loaderColor: Long = 0xFF3CC239, // Default green color for loader.
) : ConfigurableTheme

fun Long.toColor(): Color = Color(this)
