package com.komoju.mobile.sdk.navigation

import cafe.adriel.voyager.core.model.ScreenModel
import com.komoju.mobile.sdk.ui.screens.Router
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal abstract class RouterStateScreenModel<S>(initialState: S) : ScreenModel {

    protected val mutableState: MutableStateFlow<S> = MutableStateFlow(initialState)
    val state: StateFlow<S> = mutableState.asStateFlow()

    protected val mutableRouter = MutableStateFlow<Router?>(null)
    val router = mutableRouter.asStateFlow()

    fun onRouteConsumed() {
        mutableRouter.value = null
    }

    protected fun MutableStateFlow<Router?>.pop() {
        value = Router.Pop
    }
}
