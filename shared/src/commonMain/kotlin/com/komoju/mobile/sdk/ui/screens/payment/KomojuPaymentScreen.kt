package com.komoju.mobile.sdk.ui.screens.payment

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.komoju.mobile.sdk.KomojuMobileSDKConfiguration
import com.komoju.mobile.sdk.entities.PaymentMethod
import com.komoju.mobile.sdk.i18n.I18nStringKey
import com.komoju.mobile.sdk.i18n.i18nStringResource
import com.komoju.mobile.sdk.ui.composables.InlinedWebView
import com.komoju.mobile.sdk.ui.composables.ThemedCircularProgressIndicator
import com.komoju.mobile.sdk.ui.composables.launchCustomTab
import com.komoju.mobile.sdk.ui.icon.KomojuIcon
import com.komoju.mobile.sdk.ui.icon.KomojuImgPaymentFooter
import com.komoju.mobile.sdk.ui.screens.RouterEffect
import com.komoju.mobile.sdk.ui.screens.payment.composables.PaymentMethodForm
import com.komoju.mobile.sdk.ui.screens.payment.composables.PaymentMethodsRow
import com.komoju.mobile.sdk.ui.screens.payment.composables.PaymentSheetHandle

internal data class KomojuPaymentScreen(private val sdkConfiguration: KomojuMobileSDKConfiguration) : Screen {
    @Composable
    override fun Content() {
        val screenViewModel = rememberScreenModel { KomojuPaymentScreenModel(sdkConfiguration) }
        val uiState by screenViewModel.state.collectAsStateWithLifecycle()
        val offSitePaymentURL by screenViewModel.offSitePaymentURL.collectAsStateWithLifecycle()
        val offsitePaymentLauncher = launchCustomTab {
            screenViewModel.onOffsitePaymentResult()
        }
        LaunchedEffect(sdkConfiguration.sessionId) {
            screenViewModel.init()
        }
        LaunchedEffect(offSitePaymentURL) {
            val url = offSitePaymentURL
            if (url != null) {
                offsitePaymentLauncher.launch(url)
                screenViewModel.onOffSitePaymentURLConsumed()
            }
        }
        RouterEffect(screenViewModel.router.collectAsStateWithLifecycle(), screenViewModel::onRouteConsumed)
        val inlineWebViewURL = uiState.inlinedCreditCardProcessingURL
        var shouldShowFullScreenWebView by remember { mutableStateOf(false) }
        Box {
            if (uiState.session != null) {
                Column {
                    PaymentSheetHandle(
                        i18nStringResource(I18nStringKey.payment_options),
                        onCloseClicked = {
                            screenViewModel.onCloseClicked()
                        },
                    )
                    PaymentMethodsRow(
                        paymentMethods = uiState.session!!.paymentMethods,
                        selectedPaymentMethod = uiState.selectedPaymentMethod,
                        onSelected = screenViewModel::onNewPaymentMethodSelected,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())) {
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
                                paidyDisplayData = uiState.paidyDisplayData,
                                onPaidyDisplayDataChange = screenViewModel::onPaidyDisplayDataChange,
                                onPaymentRequested = screenViewModel::onPaymentRequested,
                            )
                        }
                    }
                    Image(
                        modifier = Modifier.fillMaxWidth().height(54.dp).padding(horizontal = 16.dp),
                        painter = rememberVectorPainter(KomojuIcon.KomojuImgPaymentFooter),
                        contentDescription = "payment footer",
                    )
                }
            }
            if (uiState.selectedPaymentMethod is PaymentMethod.CreditCard &&
                sdkConfiguration.inlinedProcessing.not() &&
                uiState.isLoading ||
                uiState.selectedPaymentMethod !is PaymentMethod.CreditCard &&
                uiState.isLoading
            ) {
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
            if (inlineWebViewURL != null) {
                InlinedWebView(
                    modifier = Modifier
                        .background(Color.White)
                        .align(Alignment.CenterEnd)
                        .animateContentSize()
                        .then(
                            when {
                                shouldShowFullScreenWebView -> Modifier.fillMaxSize()
                                else ->
                                    Modifier
                                        .width(Dp.Hairline)
                                        .fillMaxHeight()
                            },
                        ),
                    url = inlineWebViewURL,
                    appScheme = sdkConfiguration.appScheme,
                    onDone = {
                        screenViewModel.onInlinedDeeplinkCaptured(it)
                    },
                    onChallengePresented = {
                        shouldShowFullScreenWebView = true
                    },
                ) {
                    screenViewModel.onCloseClicked()
                }
            }
        }
    }
}
