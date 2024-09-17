package com.degica.komoju.android.sdk.ui.screens.status

import com.degica.komoju.mobile.sdk.entities.Payment

internal data class PaymentStatusUiState(val payment: Payment?, val isLoading: Boolean = false)
