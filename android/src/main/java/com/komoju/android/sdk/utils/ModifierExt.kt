package com.komoju.android.sdk.utils

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId

@OptIn(ExperimentalComposeUiApi::class)
internal fun Modifier.testID(id: String) = this
    .semantics { testTagsAsResourceId = true }
    .testTag(id)
