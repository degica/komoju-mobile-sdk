package com.degica.komoju.android.sdk.ui.screens.payment

import com.degica.komoju.mobile.sdk.entities.PaymentMethod
import com.degica.komoju.mobile.sdk.entities.Session

internal data class KomojuPaymentUIState(
    val isLoading: Boolean = false,
    val session: Session? = null,
    val selectedPaymentMethod: PaymentMethod? = null,
    val errorMessage: String? = null,
)
