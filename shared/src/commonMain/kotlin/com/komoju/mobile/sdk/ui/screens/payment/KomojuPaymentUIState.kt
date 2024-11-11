package com.komoju.mobile.sdk.ui.screens.payment

import com.komoju.mobile.sdk.entities.PaymentMethod
import com.komoju.mobile.sdk.entities.Session
import com.komoju.mobile.sdk.ui.composables.InlinedPaymentPrimaryButtonState
import com.komoju.mobile.sdk.utils.empty
import org.jetbrains.compose.resources.StringResource

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
    val fullNameErrorStringResource: StringResource? = null,
    val lastNameErrorStringResource: StringResource? = null,
    val firstNameErrorStringResource: StringResource? = null,
    val lastNamePhoneticErrorStringResource: StringResource? = null,
    val firstNamePhoneticErrorStringResource: StringResource? = null,
    val emailErrorStringResource: StringResource? = null,
    val phoneNumberErrorStringResource: StringResource? = null,
)

internal data class CreditCardDisplayData(
    val fullNameOnCard: String = String.empty,
    val fullNameOnCardErrorStringResource: StringResource? = null,
    val creditCardNumber: String = String.empty,
    val creditCardExpiryDate: String = String.empty,
    val creditCardCvv: String = String.empty,
    val creditCardErrorStringResource: StringResource? = null,
    val canSaveCard: Boolean = false,
    val saveCard: Boolean = false,
    val inlinePaymentEnabled: Boolean = false,
    val inlinedPaymentPrimaryButtonState: InlinedPaymentPrimaryButtonState = InlinedPaymentPrimaryButtonState.IDLE,
)

internal data class KonbiniDisplayData(
    val receiptName: String = String.empty,
    val receiptNameErrorStringResource: StringResource? = null,
    val receiptEmailErrorStringResource: StringResource? = null,
    val selectedKonbiniBrand: PaymentMethod.Konbini.KonbiniBrand? = null,
    val konbiniBrandNullErrorStringResource: StringResource? = null,
)

internal data class BitCashDisplayData(val bitCashId: String = String.empty, val bitCashErrorStringResource: StringResource? = null)

internal data class NetCashDisplayData(val netCashId: String = String.empty, val netCashErrorStringResource: StringResource? = null)

internal data class WebMoneyDisplayData(
    val prepaidNumber: String = String.empty,
    val prepaidNumberErrorStringResource: StringResource? = null,
)

internal data class PaidyDisplayData(
    val fullName: String = String.empty,
    val fullNameErrorStringResource: StringResource? = null,
    val phoneNumber: String = String.empty,
    val phoneNumberErrorStringResource: StringResource? = null,
)
