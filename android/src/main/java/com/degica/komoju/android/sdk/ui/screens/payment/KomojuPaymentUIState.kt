package com.degica.komoju.android.sdk.ui.screens.payment

import com.degica.komoju.android.sdk.utils.empty
import com.degica.komoju.mobile.sdk.entities.PaymentMethod
import com.degica.komoju.mobile.sdk.entities.Session

internal data class KomojuPaymentUIState(
    val isLoading: Boolean = false,
    val session: Session? = null,
    val selectedPaymentMethod: PaymentMethod? = null,
    val errorMessage: String? = null,
    val commonDisplayData: CommonDisplayData = CommonDisplayData(),
    val creditCardDisplayData: CreditCardDisplayData = CreditCardDisplayData(),
    val konbiniDisplayData: KonbiniDisplayData = KonbiniDisplayData(),
    val bitCashDisplayData: BitCashDisplayData = BitCashDisplayData(),
    val netCashDisplayData: NetCashDisplayData = NetCashDisplayData(),
    val webMoneyDisplayData: WebMoneyDisplayData = WebMoneyDisplayData(),
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
    val creditCardNumber: String = String.empty,
    val creditCardExpiryDate: String = String.empty,
    val creditCardCvv: String = String.empty,
    val saveCard: Boolean = false,
)

internal data class KonbiniDisplayData(val receiptName: String = String.empty, val selectedKonbiniBrand: PaymentMethod.Konbini.KonbiniBrand? = null)

internal data class BitCashDisplayData(val bitCashId: String = String.empty)

internal data class NetCashDisplayData(val netCashId: String = String.empty)

internal data class WebMoneyDisplayData(val prepaidNumber: String = String.empty)
