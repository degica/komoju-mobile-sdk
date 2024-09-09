package com.degica.komoju.android.sdk.ui.screens.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.degica.komoju.android.sdk.KomojuSDK
import com.degica.komoju.android.sdk.R
import com.degica.komoju.android.sdk.ui.screens.payment.composables.PaymentMethodForm
import com.degica.komoju.android.sdk.ui.screens.payment.composables.PaymentMethodsRow
import com.degica.komoju.android.sdk.ui.screens.payment.composables.PaymentSheetHandle
import com.degica.komoju.android.sdk.ui.theme.LocalI18nTextsProvider

@Composable
internal fun KomojuPaymentScreen(sdkConfiguration: KomojuSDK.Configuration, onCompleted: () -> Unit) {
    val viewModel = viewModel<KomojuPaymentViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(sdkConfiguration.publishableKey, sdkConfiguration.sessionId) {
        viewModel.init(sdkConfiguration)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.9f),
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (uiState.session != null) {
            Column {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    PaymentSheetHandle(LocalI18nTextsProvider.current["PAYMENT_OPTIONS"], onCloseClicked = onCompleted)
                    PaymentMethodsRow(
                        paymentMethods = uiState.session!!.paymentMethods,
                        selectedPaymentMethod = uiState.selectedPaymentMethod,
                        onSelected = viewModel::onNewPaymentMethodSelected,
                    )
                    uiState.selectedPaymentMethod?.let { paymentMethod ->
                        PaymentMethodForm(
                            paymentMethod = paymentMethod,
                            commonDisplayData = uiState.commonDisplayData,
                            onCommonDisplayDataChange = viewModel::onCommonDisplayDataChange,
                            creditCardDisplayData = uiState.creditCardDisplayData,
                            onCreditCardDisplayDataChange = viewModel::onCreditCardDisplayDataChange,
                            konbiniDisplayData = uiState.konbiniDisplayData,
                            onKonbiniDisplayDataChange = viewModel::onKonbiniDisplayDataChange,
                            bitCashDisplayData = uiState.bitCashDisplayData,
                            onBitCashDisplayDataChange = viewModel::onBitCashDisplayDataChange,
                            netCashDisplayData = uiState.netCashDisplayData,
                            onNetCashDisplayDataChange = viewModel::onNetCashDisplayDataChange,
                            webMoneyDisplayData = uiState.webMoneyDisplayData,
                            onWebMoneyDisplayDataChange = viewModel::onWebMoneyDisplayDataChange,
                            onPaymentRequested = viewModel::onPaymentRequested,
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
    }
}
