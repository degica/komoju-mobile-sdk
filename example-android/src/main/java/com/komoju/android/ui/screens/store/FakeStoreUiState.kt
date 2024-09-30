package com.komoju.android.ui.screens.store

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class FakeStoreUiState(val items: List<Item> = emptyList(), val isCreatingSession: Boolean = false, val error: String? = null)

data class Item(
    @DrawableRes val imageID: Int,
    @StringRes val name: Int,
    @StringRes val description: Int,
    val price: String,
    val model: String,
    @StringRes val color: Int,
    val isFavorite: Boolean = false,
)
