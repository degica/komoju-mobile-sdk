package com.komoju.android.sdk.ui.screens.payment

import android.os.Parcelable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.komoju.android.sdk.KomojuSDK
import com.komoju.android.sdk.R
import com.komoju.android.sdk.ui.composables.ThemedCircularProgressIndicator
import com.komoju.android.sdk.ui.screens.RouterEffect
import com.komoju.android.sdk.ui.screens.payment.composables.PaymentMethodForm
import com.komoju.android.sdk.ui.screens.payment.composables.PaymentMethodsRow
import com.komoju.android.sdk.ui.screens.payment.composables.PaymentSheetHandle
import com.komoju.android.sdk.ui.theme.LocalI18nTexts
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class KomojuPaymentScreen(private val sdkConfiguration: KomojuSDK.Configuration) :
    Screen,
    Parcelable {
    @Composable
    override fun Content() {
        val screenViewModel = rememberScreenModel { KomojuPaymentScreenModel(sdkConfiguration) }
        val uiState by screenViewModel.state.collectAsStateWithLifecycle()
        LaunchedEffect(sdkConfiguration.sessionId) {
            screenViewModel.init()
        }
        RouterEffect(screenViewModel.router.collectAsStateWithLifecycle(), screenViewModel::onRouteConsumed)
        Box {
            if (uiState.session != null) {
                Column {
                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        PaymentSheetHandle(LocalI18nTexts.current["PAYMENT_OPTIONS"], onCloseClicked = {
                            screenViewModel.onCloseClicked()
                        })
                        PaymentMethodsRow(
                            paymentMethods = uiState.session!!.paymentMethods,
                            selectedPaymentMethod = uiState.selectedPaymentMethod,
                            onSelected = screenViewModel::onNewPaymentMethodSelected,
                        )
                        uiState.selectedPaymentMethod?.let { paymentMethod ->
                            PaymentMethodForm(
                                paymentMethod = paymentMethod,
                                commonDisplayData = uiState.commonDisplayData,
                                onCommonDisplayDataChange = screenViewModel::onCommonDisplayDataChange,
                                creditCardDisplayData = uiState.creditCardDisplayData,
                                onCreditCardDisplayDataChange = screenViewModel::onCreditCardDisplayDataChange,
                                konbiniDisplayData = uiState.konbiniDisplayData,
                                onKonbiniDisplayDataChange = screenViewModel::onKonbiniDisplayDataChange,
                                bitCashDisplayData = uiState.bitCashDisplayData,
                                onBitCashDisplayDataChange = screenViewModel::onBitCashDisplayDataChange,
                                netCashDisplayData = uiState.netCashDisplayData,
                                onNetCashDisplayDataChange = screenViewModel::onNetCashDisplayDataChange,
                                webMoneyDisplayData = uiState.webMoneyDisplayData,
                                onWebMoneyDisplayDataChange = screenViewModel::onWebMoneyDisplayDataChange,
                                onPaymentRequested = screenViewModel::onPaymentRequested,
                            )
                        }
                    }
                    Spacer(Modifier.weight(1f))
                    Image(
                        modifier = Modifier.padding(16.dp),
                        painter = painterResource(R.drawable.komoju_img_payment_footer),
                        contentDescription = "payment footer",
                    )
                }
            }
            if (uiState.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = .1f))
                        .clickable {},
                    contentAlignment = Alignment.Center,
                ) {
                    ThemedCircularProgressIndicator()
                }
            }
        }
    }
}
