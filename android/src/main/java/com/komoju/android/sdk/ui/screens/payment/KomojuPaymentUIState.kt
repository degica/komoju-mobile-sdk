package com.komoju.android.sdk.ui.screens.payment

import com.komoju.android.sdk.ui.composables.InlinedPaymentPrimaryButtonState
import com.komoju.android.sdk.utils.empty
import com.komoju.mobile.sdk.entities.PaymentMethod
import com.komoju.mobile.sdk.entities.Session

internal data class KomojuPaymentUIState(
    val isLoading: Boolean = true,
    val session: Session? = null,
    val selectedPaymentMethod: PaymentMethod? = null,
    val errorMessage: String? = null,
    val commonDisplayData: CommonDisplayData = CommonDisplayData(),
    val creditCardDisplayData: CreditCardDisplayData = CreditCardDisplayData(),
    val konbiniDisplayData: KonbiniDisplayData = KonbiniDisplayData(),
    val bitCashDisplayData: BitCashDisplayData = BitCashDisplayData(),
    val netCashDisplayData: NetCashDisplayData = NetCashDisplayData(),
    val webMoneyDisplayData: WebMoneyDisplayData = WebMoneyDisplayData(),
    val inlinedCreditCardProcessingURL: String? = null,
)

internal data class CommonDisplayData(
    val lastName: String = String.empty,
    val firstName: String = String.empty,
    val lastNamePhonetic: String = String.empty,
    val firstNamePhonetic: String = String.empty,
    val email: String = String.empty,
    val phoneNumber: String = String.empty,
)

internal data class CreditCardDisplayData(
    val fullNameOnCard: String = String.empty,
    val fullNameOnCardError: String? = null,
    val creditCardNumber: String = String.empty,
    val creditCardExpiryDate: String = String.empty,
    val creditCardCvv: String = String.empty,
    val creditCardError: String? = null,
    val canSaveCard: Boolean = false,
    val saveCard: Boolean = false,
    val inlinePaymentEnabled: Boolean = false,
    val inlinedPaymentPrimaryButtonState: InlinedPaymentPrimaryButtonState = InlinedPaymentPrimaryButtonState.IDLE,
)

internal data class KonbiniDisplayData(
    val receiptName: String = String.empty,
    val receiptNameError: String? = null,
    val receiptEmailError: String? = null,
    val selectedKonbiniBrand: PaymentMethod.Konbini.KonbiniBrand? = null,
    val konbiniBrandNullError: String? = null,
)

internal data class BitCashDisplayData(val bitCashId: String = String.empty)

internal data class NetCashDisplayData(val netCashId: String = String.empty)

internal data class WebMoneyDisplayData(val prepaidNumber: String = String.empty)
