package com.komoju.mobile.sdk.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.komoju.mobile.sdk.KomojuMobileSDKConfiguration
import com.komoju.mobile.sdk.ui.screens.payment.KomojuPaymentScreen
import com.komoju.mobile.sdk.ui.theme.KomojuMobileSdkTheme
import com.komoju.mobile.sdk.utils.Logger

@Composable
fun KomojuPaymentEntryPoint(configuration: KomojuMobileSDKConfiguration, onCreated: @Composable (Navigator) -> Unit) {
    Logger.setDebugMode(configuration.isDebugMode)
    KomojuMobileSdkTheme(configuration) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.9f),
        ) {
            Navigator(
                KomojuPaymentScreen(configuration),
            ) { navigator ->
                onCreated(navigator)
                SlideTransition(navigator)
            }
        }
    }
}
