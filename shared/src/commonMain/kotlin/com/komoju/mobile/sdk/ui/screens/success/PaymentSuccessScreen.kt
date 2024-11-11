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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.komoju.mobile.sdk.KomojuMobileSDKPaymentResult
import com.komoju.mobile.sdk.navigation.paymentResultScreenModel
import komoju_mobile_sdk.shared.generated.resources.Res
import com.komoju.mobile.sdk.ui.composables.PrimaryButton
import com.komoju.mobile.sdk.ui.screens.RouterEffect
import komoju_mobile_sdk.shared.generated.resources.komoju_back_to_store
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_payment_status_completed
import komoju_mobile_sdk.shared.generated.resources.komoju_payment_success
import komoju_mobile_sdk.shared.generated.resources.komoju_thank_you_for_your_order
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

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
        Image(painterResource(Res.drawable.komoju_ic_payment_status_completed), "status_icon")
        Spacer(Modifier.height(16.dp))
        Text(stringResource(Res.string.komoju_payment_success), fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        Text(stringResource(Res.string.komoju_thank_you_for_your_order))
        Spacer(Modifier.weight(1f))
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = stringResource(Res.string.komoju_back_to_store),
        ) {
            screenModel.onBackToStoreButtonClicked()
        }
    }
}
