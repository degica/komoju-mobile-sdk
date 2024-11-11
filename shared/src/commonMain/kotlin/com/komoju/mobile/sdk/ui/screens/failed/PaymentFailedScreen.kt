package com.komoju.mobile.sdk.ui.screens.failed

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.komoju.mobile.sdk.ui.composables.PrimaryButton
import com.komoju.mobile.sdk.ui.screens.KomojuPaymentRoute
import com.komoju.mobile.sdk.ui.screens.RouterEffect
import komoju_mobile_sdk.shared.generated.resources.Res
import komoju_mobile_sdk.shared.generated.resources.komoju_back_to_store
import komoju_mobile_sdk.shared.generated.resources.komoju_credit_card_error
import komoju_mobile_sdk.shared.generated.resources.komoju_error_other
import komoju_mobile_sdk.shared.generated.resources.komoju_error_user_cancel
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_payment_status_failed
import komoju_mobile_sdk.shared.generated.resources.komoju_payment_failed
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

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
        Image(painterResource(Res.drawable.komoju_ic_payment_status_failed), "status_icon")
        Spacer(Modifier.height(16.dp))
        Text(stringResource(Res.string.komoju_payment_failed), fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(
            text = when (route.reason) {
                Reason.USER_CANCEL -> stringResource(Res.string.komoju_error_user_cancel)
                Reason.OTHER -> stringResource(Res.string.komoju_error_other)
                Reason.CREDIT_CARD_ERROR -> stringResource(Res.string.komoju_credit_card_error)
            },
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
        )
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
