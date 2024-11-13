package com.komoju.mobile.sdk.ui.screens.success

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.komoju.mobile.sdk.KomojuMobileSDKPaymentResult
import com.komoju.mobile.sdk.i18n.I18nStringKey
import com.komoju.mobile.sdk.i18n.i18nStringResource
import com.komoju.mobile.sdk.navigation.paymentResultScreenModel
import com.komoju.mobile.sdk.ui.composables.PrimaryButton
import com.komoju.mobile.sdk.ui.icon.KomojuIcon
import com.komoju.mobile.sdk.ui.icon.PaymentStatusCompleted
import com.komoju.mobile.sdk.ui.screens.RouterEffect

internal class PaymentSuccessScreen : Screen {
    @Composable
    override fun Content() {
        PaymentSuccessScreenContent()
    }
}

@Composable
private fun Screen.PaymentSuccessScreenContent() {
    val screenModel = rememberScreenModel { PaymentSuccessScreenModel() }
    RouterEffect(screenModel.router.collectAsStateWithLifecycle(), screenModel::onRouteConsumed)
    val navigator = LocalNavigator.currentOrThrow
    val resultScreenModel = navigator.paymentResultScreenModel()
    LaunchedEffect(Unit) {
        resultScreenModel.setResult(KomojuMobileSDKPaymentResult(isSuccessFul = true))
    }
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            Icon(
                Icons.Rounded.Clear,
                "Close Button",
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        screenModel.onCloseButtonClicked()
                    },
            )
        }
        Image(rememberVectorPainter(KomojuIcon.PaymentStatusCompleted), "status_icon")
        Spacer(Modifier.height(16.dp))
        Text(i18nStringResource(I18nStringKey.payment_success), fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        Text(i18nStringResource(I18nStringKey.thank_you_for_your_order))
        Spacer(Modifier.weight(1f))
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = i18nStringResource(I18nStringKey.back_to_store),
        ) {
            screenModel.onBackToStoreButtonClicked()
        }
    }
}
