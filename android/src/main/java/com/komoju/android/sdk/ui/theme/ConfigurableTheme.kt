package com.komoju.android.sdk.ui.theme

import android.graphics.Color
import android.os.Parcelable
import androidx.annotation.ColorInt
import kotlinx.parcelize.Parcelize

interface ConfigurableTheme : Parcelable {
    @get:ColorInt val primaryButtonColor: Int

    @get:ColorInt val primaryButtonContentColor: Int

    @get:ColorInt val primaryButtonCornerRadiusInDP: Int

    @get:ColorInt val loaderColor: Int

    companion object {
        val default = DefaultConfigurableTheme()
    }
}

@Parcelize
data class DefaultConfigurableTheme(
    override val primaryButtonColor: Int = Color.parseColor("#FF297FE7"),
    override val primaryButtonContentColor: Int = Color.WHITE,
    override val primaryButtonCornerRadiusInDP: Int = 8,
    override val loaderColor: Int = Color.parseColor("#FF3CC239"),
) : ConfigurableTheme
