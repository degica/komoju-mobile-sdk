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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.SecureFlagPolicy
import androidx.lifecycle.viewmodel.compose.viewModel
import com.degica.komoju.android.sdk.KomojuSDK
import com.degica.komoju.android.sdk.R
import com.degica.komoju.android.sdk.ui.screens.payment.composables.PaymentMethodForm
import com.degica.komoju.android.sdk.ui.screens.payment.composables.PaymentMethodsRow
import com.degica.komoju.android.sdk.ui.screens.payment.composables.PaymentSheetHandle
import com.degica.komoju.android.sdk.ui.theme.KomojuMobileSdkTheme
import com.degica.komoju.android.sdk.ui.theme.LocalI18nTextsProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KomojuPaymentScreen(sdkConfiguration: KomojuSDK.Configuration, onCompleted: () -> Unit) {
    if (sdkConfiguration.canProcessPayment().not()) return
    val modalBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = { newValue ->
            when (newValue) {
                SheetValue.Hidden -> {
                    false
                }

                SheetValue.PartiallyExpanded -> false
                SheetValue.Expanded -> true
            }
        },
    )

    val viewModel = viewModel<KomojuPaymentViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.init(sdkConfiguration)
    }

    KomojuMobileSdkTheme(sdkConfiguration.language) {
        ModalBottomSheet(
            onDismissRequest = {
                onCompleted()
            },
            containerColor = Color.White,
            shape = RectangleShape,
            sheetState = modalBottomSheetState,
            dragHandle = { PaymentSheetHandle(LocalI18nTextsProvider.current["PAYMENT_OPTIONS"], onCloseClicked = {}) },
            properties = ModalBottomSheetDefaults.properties(
                securePolicy = when {
                    sdkConfiguration.isDebugMode -> SecureFlagPolicy.Inherit
                    else -> SecureFlagPolicy.SecureOn
                },
                shouldDismissOnBackPress = false,
            ),
        ) {
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
                            PaymentMethodsRow(
                                paymentMethods = uiState.session!!.paymentMethods,
                                selectedPaymentMethod = uiState.selectedPaymentMethod,
                                onSelected = viewModel::onNewPaymentMethodSelected,
                            )

                            PaymentMethodForm(uiState.selectedPaymentMethod!!)
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
    }
}
