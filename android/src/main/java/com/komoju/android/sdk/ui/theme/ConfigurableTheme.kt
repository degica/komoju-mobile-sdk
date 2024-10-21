package com.komoju.android.sdk.ui.theme

import android.graphics.Color
import android.os.Parcelable
import androidx.annotation.ColorInt
import kotlinx.parcelize.Parcelize

/**
 * Interface representing a configurable theme with customizable UI properties.
 * This interface extends [Parcelable], allowing themes to be passed between Android components.
 */
interface ConfigurableTheme : Parcelable {

    /**
     * Color value for the primary button background.
     *
     * @return An integer representing a color value annotated with [ColorInt].
     */
    @get:ColorInt
    val primaryButtonColor: Int

    /**
     * Color value for the content (e.g., text or icon) inside the primary button.
     *
     * @return An integer representing a color value annotated with [ColorInt].
     */
    @get:ColorInt
    val primaryButtonContentColor: Int

    /**
     * Corner radius for the primary button in density-independent pixels (DP).
     *
     * @return An integer representing the corner radius in DP annotated with [ColorInt].
     */
    @get:ColorInt
    val primaryButtonCornerRadiusInDP: Int

    /**
     * Color value for the loader/spinner.
     *
     * @return An integer representing a color value annotated with [ColorInt].
     */
    @get:ColorInt
    val loaderColor: Int

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
 * It is annotated with [Parcelize] to allow it to be passed between Android components.
 *
 * @param primaryButtonColor The background color of the primary button.
 * @param primaryButtonContentColor The color of the content inside the primary button.
 * @param primaryButtonCornerRadiusInDP The corner radius of the primary button in DP.
 * @param loaderColor The color of the loader/spinner.
 */
@Parcelize
data class DefaultConfigurableTheme(
    override val primaryButtonColor: Int = Color.parseColor("#FF297FE7"), // Default blue color for primary button.
    override val primaryButtonContentColor: Int = Color.WHITE, // Default white color for primary button content.
    override val primaryButtonCornerRadiusInDP: Int = 8, // Default corner radius of 8 DP for primary button.
    override val loaderColor: Int = Color.parseColor("#FF3CC239"), // Default green color for loader.
) : ConfigurableTheme
