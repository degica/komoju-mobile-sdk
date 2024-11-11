package com.komoju.mobile.sdk.ui.screens.payment

import com.komoju.mobile.sdk.entities.PaymentMethod
import com.komoju.mobile.sdk.entities.Session
import com.komoju.mobile.sdk.i18n.I18nStringKey
import com.komoju.mobile.sdk.ui.composables.InlinedPaymentPrimaryButtonState
import com.komoju.mobile.sdk.utils.empty

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
    val paidyDisplayData: PaidyDisplayData = PaidyDisplayData(),
    val inlinedCreditCardProcessingURL: String? = null,
)

internal data class CommonDisplayData(
    val fullName: String = String.empty,
    val lastName: String = String.empty,
    val firstName: String = String.empty,
    val lastNamePhonetic: String = String.empty,
    val firstNamePhonetic: String = String.empty,
    val email: String = String.empty,
    val phoneNumber: String = String.empty,
    val fullNameErrorI18nStringKey: I18nStringKey? = null,
    val lastNameErrorI18nStringKey: I18nStringKey? = null,
    val firstNameErrorI18nStringKey: I18nStringKey? = null,
    val lastNamePhoneticErrorI18nStringKey: I18nStringKey? = null,
    val firstNamePhoneticErrorI18nStringKey: I18nStringKey? = null,
    val emailErrorI18nStringKey: I18nStringKey? = null,
    val phoneNumberErrorI18nStringKey: I18nStringKey? = null,
)

internal data class CreditCardDisplayData(
    val fullNameOnCard: String = String.empty,
    val fullNameOnCardErrorI18nStringKey: I18nStringKey? = null,
    val creditCardNumber: String = String.empty,
    val creditCardExpiryDate: String = String.empty,
    val creditCardCvv: String = String.empty,
    val creditCardErrorI18nStringKey: I18nStringKey? = null,
    val canSaveCard: Boolean = false,
    val saveCard: Boolean = false,
    val inlinePaymentEnabled: Boolean = false,
    val inlinedPaymentPrimaryButtonState: InlinedPaymentPrimaryButtonState = InlinedPaymentPrimaryButtonState.IDLE,
)

internal data class KonbiniDisplayData(
    val receiptName: String = String.empty,
    val receiptNameErrorI18nStringKey: I18nStringKey? = null,
    val receiptEmailErrorI18nStringKey: I18nStringKey? = null,
    val selectedKonbiniBrand: PaymentMethod.Konbini.KonbiniBrand? = null,
    val konbiniBrandNullErrorI18nStringKey: I18nStringKey? = null,
)

internal data class BitCashDisplayData(val bitCashId: String = String.empty, val bitCashErrorI18nStringKey: I18nStringKey? = null)

internal data class NetCashDisplayData(val netCashId: String = String.empty, val netCashErrorI18nStringKey: I18nStringKey? = null)

internal data class WebMoneyDisplayData(
    val prepaidNumber: String = String.empty,
    val prepaidNumberErrorI18nStringKey: I18nStringKey? = null,
)

internal data class PaidyDisplayData(
    val fullName: String = String.empty,
    val fullNameErrorI18nStringKey: I18nStringKey? = null,
    val phoneNumber: String = String.empty,
    val phoneNumberErrorI18nStringKey: I18nStringKey? = null,
)
