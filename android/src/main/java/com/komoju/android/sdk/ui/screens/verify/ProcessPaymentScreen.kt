package com.komoju.android.sdk.ui.screens.verify

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.komoju.android.sdk.ui.screens.KomojuPaymentRoute
import com.komoju.android.sdk.ui.screens.RouterEffect

internal class ProcessPaymentScreen(private val route: KomojuPaymentRoute.ProcessPayment) : Screen {
    @Composable
    override fun Content() {
        VerifyPaymentScreenContent(route)
    }
}

@Composable
private fun Screen.VerifyPaymentScreenContent(route: KomojuPaymentRoute.ProcessPayment) {
    val screenViewModel = rememberScreenModel { VerifyPaymentScreenModel(route.configuration) }
    val router by screenViewModel.router.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        screenViewModel.process(route.processType)
    }
    RouterEffect(router, screenViewModel::onRouteHandled)
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}
