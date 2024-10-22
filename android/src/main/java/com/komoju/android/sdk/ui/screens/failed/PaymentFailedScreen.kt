package com.komoju.android.sdk.ui.screens.failed

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.komoju.android.sdk.R
import com.komoju.android.sdk.ui.composables.PrimaryButton
import com.komoju.android.sdk.ui.screens.KomojuPaymentRoute
import com.komoju.android.sdk.ui.screens.RouterEffect

internal class PaymentFailedScreen(private val route: KomojuPaymentRoute.PaymentFailed) : Screen {
    @Composable
    override fun Content() {
        PaymentFailedScreenContent(route)
    }
}

@Composable
private fun Screen.PaymentFailedScreenContent(route: KomojuPaymentRoute.PaymentFailed) {
    val screenModel = rememberScreenModel { PaymentFailedScreenModel() }
    RouterEffect(screenModel.router.collectAsStateWithLifecycle(), screenModel::onRouteConsumed)
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            Icon(
                Icons.Rounded.Clear,
                "Close Button",
                modifier = Modifier
                    .padding(16.dp)
                    .clickable(onClick = screenModel::onCloseButtonClicked),
            )
        }
        Image(painterResource(R.drawable.komoju_ic_payment_status_failed), "status_icon")
        Spacer(Modifier.height(16.dp))
        Text(stringResource(R.string.komoju_payment_failed), fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(
            text = when (route.reason) {
                Reason.USER_CANCEL -> stringResource(R.string.komoju_error_user_cancel)
                Reason.OTHER -> stringResource(R.string.komoju_error_other)
                Reason.CREDIT_CARD_ERROR -> stringResource(R.string.komoju_credit_card_error)
            },
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.weight(1f))
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = stringResource(R.string.komoju_back_to_store),
        ) {
            screenModel.onBackToStoreButtonClicked()
        }
    }
}
