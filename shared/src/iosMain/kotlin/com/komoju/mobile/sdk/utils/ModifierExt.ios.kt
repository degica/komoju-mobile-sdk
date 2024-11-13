package com.komoju.mobile.sdk.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics

internal actual fun Modifier.testID(id: String) = semantics {
}.testTag(id)
