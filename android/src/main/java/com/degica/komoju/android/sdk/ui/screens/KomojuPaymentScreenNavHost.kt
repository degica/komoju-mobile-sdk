package com.degica.komoju.android.sdk.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.degica.komoju.android.sdk.KomojuSDK
import com.degica.komoju.android.sdk.ui.screens.payment.KomojuPaymentScreen

@Composable
internal fun KomojuPaymentScreenNavHost(sdkConfiguration: KomojuSDK.Configuration, onCompleted: () -> Unit) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "payment") {
        composable(route = "payment") {
            KomojuPaymentScreen(sdkConfiguration) {
                onCompleted()
            }
        }
    }
}
