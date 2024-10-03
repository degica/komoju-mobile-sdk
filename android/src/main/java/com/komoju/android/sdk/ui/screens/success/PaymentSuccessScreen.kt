package com.komoju.android.sdk.ui.screens.success

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.komoju.android.sdk.KomojuSDK
import com.komoju.android.sdk.R
import com.komoju.android.sdk.navigation.paymentResultScreenModel
import com.komoju.android.sdk.ui.composables.PrimaryButton
import com.komoju.android.sdk.ui.screens.RouterEffect
import com.komoju.android.sdk.ui.theme.KomojuMobileSdkTheme
import com.komoju.android.sdk.ui.theme.LocalI18nTexts
import com.komoju.android.sdk.utils.PreviewScreen

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
    val i18nTexts = LocalI18nTexts.current
    val navigator = LocalNavigator.currentOrThrow
    val resultScreenModel = navigator.paymentResultScreenModel()
    LaunchedEffect(Unit) {
        resultScreenModel.setResult(KomojuSDK.PaymentResult(isSuccessFul = true))
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
        Image(painterResource(R.drawable.komoju_ic_payment_status_completed), "status_icon")
        Spacer(Modifier.height(16.dp))
        Text(i18nTexts["PAYMENT_SUCCESS"], fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        Text(i18nTexts["ORDER_THANK_YOU_NOTE"])
        Spacer(Modifier.weight(1f))
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = i18nTexts["BACK_TO_STORE"],
        ) {
            screenModel.onBackToStoreButtonClicked()
        }
    }
}

@Composable
@Preview
private fun PaymentSuccessScreenContentPreview() {
    KomojuMobileSdkTheme {
        PreviewScreen.PaymentSuccessScreenContent()
    }
}
