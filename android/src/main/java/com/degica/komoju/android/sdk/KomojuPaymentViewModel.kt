package com.degica.komoju.android.sdk

import android.util.Log
import androidx.lifecycle.ViewModel
import com.degica.komoju.android.sdk.ui.screens.Router
import com.degica.komoju.android.sdk.utils.DeeplinkEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class KomojuPaymentViewModel : ViewModel() {

    private val _isVisible = MutableStateFlow(false)
    val isVisible = _isVisible.asStateFlow()

    private val _router = MutableStateFlow<Router?>(null)
    val router = _router.asStateFlow()

    fun toggleVisiblity(value: Boolean) {
        _isVisible.value = value
    }

    fun onRouteConsumed() {
        _router.value = null
    }

    fun onNewDeeplink(deeplink: String) {
        val deeplinkEntity = DeeplinkEntity.from(deeplink)
        Log.d("Aman", "handleIntentAction $deeplinkEntity")
    }
}
