package com.degica.komoju.android.sdk.ui.screens.payment

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.degica.komoju.android.sdk.KomojuSDK
import com.degica.komoju.android.sdk.ui.screens.KomojuPaymentRoute
import com.degica.komoju.android.sdk.ui.screens.Router
import com.degica.komoju.android.sdk.utils.CreditCardUtils.isValidCVV
import com.degica.komoju.android.sdk.utils.CreditCardUtils.isValidCardHolderNameChar
import com.degica.komoju.android.sdk.utils.CreditCardUtils.isValidCardNumber
import com.degica.komoju.android.sdk.utils.CreditCardUtils.isValidExpiryDate
import com.degica.komoju.android.sdk.utils.isValidEmail
import com.degica.komoju.mobile.sdk.entities.Payment
import com.degica.komoju.mobile.sdk.entities.PaymentMethod
import com.degica.komoju.mobile.sdk.entities.PaymentRequest
import com.degica.komoju.mobile.sdk.remote.apis.KomojuRemoteApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class KomojuPaymentScreenModel(private val config: KomojuSDK.Configuration) : StateScreenModel<KomojuPaymentUIState>(KomojuPaymentUIState()) {
    private val komojuApi: KomojuRemoteApi = KomojuRemoteApi(config.publishableKey, config.language.languageCode)

    private val _router = MutableStateFlow<Router?>(null)
    val router = _router.asStateFlow()

    fun init() {
        val sessionId = config.sessionId
        if (sessionId != null) {
            screenModelScope.launch {
                komojuApi.sessions.show(sessionId).onSuccess { session ->
                    mutableState.update {
                        it.copy(
                            isLoading = false,
                            session = session,
                            selectedPaymentMethod = session.paymentMethods.firstOrNull(),
                        )
                    }
                }
            }
        }
    }

    fun onNewPaymentMethodSelected(paymentMethod: PaymentMethod) {
        mutableState.update { it.copy(selectedPaymentMethod = paymentMethod) }
    }

    fun onCommonDisplayDataChange(commonDisplayData: CommonDisplayData) = mutableState.update {
        it.copy(commonDisplayData = commonDisplayData)
    }

    fun onCreditCardDisplayDataChange(creditCardDisplayData: CreditCardDisplayData) {
        if (creditCardDisplayData.creditCardNumber.length <= 16 &&
            creditCardDisplayData.creditCardExpiryDate.length <= 4 &&
            creditCardDisplayData.creditCardCvv.length <= 7
        ) {
            mutableState.update {
                it.copy(
                    creditCardDisplayData = creditCardDisplayData.copy(
                        creditCardError = null,
                        fullNameOnCardError = null,
                    ),
                )
            }
        }
    }

    fun onKonbiniDisplayDataChange(konbiniDisplayData: KonbiniDisplayData) = mutableState.update {
        it.copy(konbiniDisplayData = konbiniDisplayData)
    }

    fun onBitCashDisplayDataChange(bitCashDisplayData: BitCashDisplayData) = mutableState.update {
        it.copy(bitCashDisplayData = bitCashDisplayData)
    }

    fun onNetCashDisplayDataChange(netCashDisplayData: NetCashDisplayData) = mutableState.update {
        it.copy(netCashDisplayData = netCashDisplayData)
    }

    fun onWebMoneyDisplayDataChange(webMoneyDisplayData: WebMoneyDisplayData) = mutableState.update {
        it.copy(webMoneyDisplayData = webMoneyDisplayData)
    }

    fun onPaymentRequested(paymentMethod: PaymentMethod) {
        if (paymentMethod.validate()) {
            mutableState.update { it.copy(isLoading = true) }
            val request = paymentMethod.toPaymentRequest()
            screenModelScope.launch {
                komojuApi.sessions.pay(config.sessionId.orEmpty(), request).onSuccess { payment ->
                    mutableState.update { it.copy(isLoading = true) }
                    payment.handle()
                }.onFailure {
                    mutableState.update { it.copy(isLoading = false) }
                }
            }
        }
    }

    private fun Payment.handle() {
        when (this) {
            is Payment.Konbini -> {
                _router.value = Router.Replace(KomojuPaymentRoute.Status(config, payment = this))
            }

            else -> Unit
        }
    }

    private fun PaymentMethod.validate() = when (this) {
        is PaymentMethod.CreditCard -> state.value.creditCardDisplayData.validate()
        is PaymentMethod.Konbini -> state.value.konbiniDisplayData.validate(state.value.commonDisplayData)
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
        mutableState.update {
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
        mutableState.update {
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

    private fun PaymentMethod.toPaymentRequest(): PaymentRequest = when (this) {
        is PaymentMethod.AliPay -> TODO()
        is PaymentMethod.AuPay -> TODO()
        is PaymentMethod.BankTransfer -> TODO()
        is PaymentMethod.BitCash -> TODO()
        is PaymentMethod.CreditCard -> TODO()
        is PaymentMethod.Konbini -> PaymentRequest.Konbini(
            paymentMethod = this,
            konbiniBrand = state.value.konbiniDisplayData.selectedKonbiniBrand!!,
            email = state.value.commonDisplayData.email,
        )

        is PaymentMethod.LinePay -> TODO()
        is PaymentMethod.MerPay -> TODO()
        is PaymentMethod.NetCash -> TODO()
        is PaymentMethod.Other -> TODO()
        is PaymentMethod.Paidy -> TODO()
        is PaymentMethod.PayEasy -> TODO()
        is PaymentMethod.PayPay -> TODO()
        is PaymentMethod.RakutenPay -> TODO()
        is PaymentMethod.WebMoney -> TODO()
    }

    fun onCloseClicked() {
        _router.value = Router.Pop
    }

    fun onRouteHandled() {
        _router.value = null
    }
}
