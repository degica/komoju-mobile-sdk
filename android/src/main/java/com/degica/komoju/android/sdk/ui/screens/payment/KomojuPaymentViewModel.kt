package com.degica.komoju.android.sdk.ui.screens.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.degica.komoju.android.sdk.KomojuSDK
import com.degica.komoju.android.sdk.utils.CreditCardUtils.isValidCVV
import com.degica.komoju.android.sdk.utils.CreditCardUtils.isValidCardHolderNameChar
import com.degica.komoju.android.sdk.utils.CreditCardUtils.isValidCardNumber
import com.degica.komoju.android.sdk.utils.CreditCardUtils.isValidExpiryDate
import com.degica.komoju.android.sdk.utils.isValidEmail
import com.degica.komoju.mobile.sdk.entities.PaymentMethod
import com.degica.komoju.mobile.sdk.remote.apis.KomojuRemoteApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class KomojuPaymentViewModel : ViewModel() {

    private var komojuApi: KomojuRemoteApi? = null

    private val _uiState = MutableStateFlow(KomojuPaymentUIState())
    val uiState = _uiState.asStateFlow()

    fun init(config: KomojuSDK.Configuration) {
        if (komojuApi == null) {
            komojuApi = KomojuRemoteApi(config.publishableKey!!, config.language.languageCode)
            viewModelScope.launch {
                komojuApi?.sessions?.show(config.sessionId!!)?.onSuccess { session ->
                    _uiState.update {
                        it.copy(isLoading = false, session = session, selectedPaymentMethod = session.paymentMethods.firstOrNull())
                    }
                }
            }
        }
    }

    fun onNewPaymentMethodSelected(paymentMethod: PaymentMethod) {
        _uiState.update { it.copy(selectedPaymentMethod = paymentMethod) }
    }

    fun onCommonDisplayDataChange(commonDisplayData: CommonDisplayData) = _uiState.update {
        it.copy(commonDisplayData = commonDisplayData)
    }

    fun onCreditCardDisplayDataChange(creditCardDisplayData: CreditCardDisplayData) {
        if (creditCardDisplayData.creditCardNumber.length <= 16 &&
            creditCardDisplayData.creditCardExpiryDate.length <= 4 &&
            creditCardDisplayData.creditCardCvv.length <= 7
        ) {
            _uiState.update {
                it.copy(
                    creditCardDisplayData = creditCardDisplayData.copy(
                        creditCardError = null,
                        fullNameOnCardError = null,
                    ),
                )
            }
        }
    }

    fun onKonbiniDisplayDataChange(konbiniDisplayData: KonbiniDisplayData) = _uiState.update {
        it.copy(konbiniDisplayData = konbiniDisplayData)
    }

    fun onBitCashDisplayDataChange(bitCashDisplayData: BitCashDisplayData) = _uiState.update {
        it.copy(bitCashDisplayData = bitCashDisplayData)
    }

    fun onNetCashDisplayDataChange(netCashDisplayData: NetCashDisplayData) = _uiState.update {
        it.copy(netCashDisplayData = netCashDisplayData)
    }

    fun onWebMoneyDisplayDataChange(webMoneyDisplayData: WebMoneyDisplayData) = _uiState.update {
        it.copy(webMoneyDisplayData = webMoneyDisplayData)
    }

    fun onPaymentRequested(paymentMethod: PaymentMethod) {
        if (paymentMethod.validate()) {
            // Process Payment
        }
    }

    private fun PaymentMethod.validate() = when (this) {
        is PaymentMethod.CreditCard -> uiState.value.creditCardDisplayData.validate()
        is PaymentMethod.Konbini -> uiState.value.konbiniDisplayData.validate(uiState.value.commonDisplayData)
        else -> false
    }

    private fun CreditCardDisplayData.validate(): Boolean {
        val fullNameOnCardError = when {
            fullNameOnCard.isBlank() -> "The entered cardholder name cannot be empty"
            fullNameOnCard.all { char -> char.isValidCardHolderNameChar() } -> null
            else -> "The entered cardholder name is not valid"
        }
        val creditCardError = run {
            when {
                creditCardNumber.isValidCardNumber().not() -> "The entered card number is not valid"
                creditCardExpiryDate.isValidExpiryDate().not() -> "The entered expiry date is not valid"
                creditCardCvv.isValidCVV().not() -> "The entered CVV is not valid"
                else -> null
            }
        }
        _uiState.update {
            it.copy(
                creditCardDisplayData = it.creditCardDisplayData.copy(
                    fullNameOnCardError = fullNameOnCardError,
                    creditCardError = creditCardError,
                ),
            )
        }
        return fullNameOnCardError == null && creditCardError == null
    }

    private fun KonbiniDisplayData.validate(commonDisplayData: CommonDisplayData): Boolean {
        val nameError = if (receiptName.trim().isEmpty()) "The entered name cannot be empty" else null
        val emailError = if (commonDisplayData.email.isValidEmail.not()) "The entered email is not valid" else null
        val konbiniBrandNullError = if (selectedKonbiniBrand == null) "Please select a konbini brand" else null
        _uiState.update {
            it.copy(
                konbiniDisplayData = it.konbiniDisplayData.copy(
                    receiptNameError = nameError,
                    receiptEmailError = emailError,
                    konbiniBrandNullError = konbiniBrandNullError,
                ),
            )
        }
        return nameError == null && emailError == null && konbiniBrandNullError == null
    }
}
