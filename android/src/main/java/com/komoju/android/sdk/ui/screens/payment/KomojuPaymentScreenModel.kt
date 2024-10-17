package com.komoju.android.sdk.ui.screens.payment

import androidx.core.text.isDigitsOnly
import cafe.adriel.voyager.core.model.screenModelScope
import com.komoju.android.sdk.KomojuSDK
import com.komoju.android.sdk.navigation.RouterStateScreenModel
import com.komoju.android.sdk.ui.composables.InlinedPaymentPrimaryButtonState
import com.komoju.android.sdk.ui.screens.KomojuPaymentRoute
import com.komoju.android.sdk.ui.screens.Router
import com.komoju.android.sdk.ui.screens.failed.Reason
import com.komoju.android.sdk.utils.CreditCardUtils.isValidCVV
import com.komoju.android.sdk.utils.CreditCardUtils.isValidCardHolderNameChar
import com.komoju.android.sdk.utils.CreditCardUtils.isValidCardNumber
import com.komoju.android.sdk.utils.CreditCardUtils.isValidExpiryDate
import com.komoju.android.sdk.utils.DeeplinkEntity
import com.komoju.android.sdk.utils.isValidEmail
import com.komoju.mobile.sdk.entities.Payment
import com.komoju.mobile.sdk.entities.PaymentMethod
import com.komoju.mobile.sdk.entities.PaymentRequest
import com.komoju.mobile.sdk.entities.PaymentStatus
import com.komoju.mobile.sdk.entities.PaymentStatus.Companion.isSuccessful
import com.komoju.mobile.sdk.entities.SecureTokenRequest
import com.komoju.mobile.sdk.entities.SecureTokenResponse.Status.ERRORED
import com.komoju.mobile.sdk.entities.SecureTokenResponse.Status.NEEDS_VERIFY
import com.komoju.mobile.sdk.entities.SecureTokenResponse.Status.OK
import com.komoju.mobile.sdk.entities.SecureTokenResponse.Status.SKIPPED
import com.komoju.mobile.sdk.entities.SecureTokenResponse.Status.UNKNOWN
import com.komoju.mobile.sdk.remote.apis.KomojuRemoteApi
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class KomojuPaymentScreenModel(private val config: KomojuSDK.Configuration) : RouterStateScreenModel<KomojuPaymentUIState>(KomojuPaymentUIState()) {
    private val komojuApi: KomojuRemoteApi = KomojuRemoteApi(config.publishableKey, config.language.languageCode)
    private val _offSitePaymentURL = MutableStateFlow<String?>(null)
    val offSitePaymentURL = _offSitePaymentURL.asStateFlow()

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
                            creditCardDisplayData = it.creditCardDisplayData.copy(
                                inlinePaymentEnabled = config.inlinedProcessing,
                            ),
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

    fun onPaidyDisplayDataChange(paidyDisplayData: PaidyDisplayData) = mutableState.update {
        it.copy(paidyDisplayData = paidyDisplayData)
    }

    fun onPaymentRequested(paymentMethod: PaymentMethod) {
        if (paymentMethod.validate()) {
            changeInlinePaymentState(InlinedPaymentPrimaryButtonState.LOADING)
            mutableState.update { it.copy(isLoading = true) }
            if (paymentMethod is PaymentMethod.CreditCard) {
                paymentMethod.createSecureTokens()
            } else {
                val request = paymentMethod.toPaymentRequest()
                screenModelScope.launch {
                    komojuApi.sessions.pay(config.sessionId.orEmpty(), request).onSuccess { payment ->
                        if (payment is Payment.Error) {
                            mutableState.update { it.copy(isLoading = false) }
                            changeInlinePaymentState(InlinedPaymentPrimaryButtonState.ERROR)
                        } else {
                            mutableState.update { it.copy(isLoading = true) }
                            changeInlinePaymentState(InlinedPaymentPrimaryButtonState.LOADING)
                            payment.handle()
                        }
                    }.onFailure {
                        mutableState.update { it.copy(isLoading = false) }
                        changeInlinePaymentState(InlinedPaymentPrimaryButtonState.ERROR)
                    }
                }
            }
        }
    }

    private fun PaymentMethod.CreditCard.createSecureTokens() {
        val request = toSecureTokenRequest()
        screenModelScope.launch {
            komojuApi.tokens.generateSecureToken(request).onSuccess { tokens ->
                when (tokens.status) {
                    OK, SKIPPED -> {
                        if (config.inlinedProcessing) {
                            verifyAndProcessInlinedPayment(tokens.id, request.amount, request.currency)
                        } else {
                            mutableRouter.value = Router.ReplaceAll(
                                KomojuPaymentRoute.ProcessPayment(
                                    configuration = config,
                                    processType = KomojuPaymentRoute.ProcessPayment.ProcessType.PayByToken(tokens.id, request.amount, request.currency),
                                ),
                            )
                        }
                    }

                    NEEDS_VERIFY -> {
                        if (config.inlinedProcessing) {
                            mutableState.update { it.copy(inlinedCreditCardProcessingURL = tokens.authURL) }
                        } else {
                            mutableRouter.value = Router.ReplaceAll(KomojuPaymentRoute.WebView(url = tokens.authURL, isJavaScriptEnabled = true))
                        }
                    }
                    ERRORED, UNKNOWN -> mutableRouter.value = Router.ReplaceAll(KomojuPaymentRoute.PaymentFailed(Reason.CREDIT_CARD_ERROR))
                }
            }.onFailure {
                mutableRouter.value = Router.ReplaceAll(KomojuPaymentRoute.PaymentFailed(Reason.CREDIT_CARD_ERROR))
            }
        }
    }

    private fun changeInlinePaymentState(newState: InlinedPaymentPrimaryButtonState) {
        mutableState.update {
            it.copy(
                creditCardDisplayData = it.creditCardDisplayData.copy(
                    inlinedPaymentPrimaryButtonState = newState,
                ),
            )
        }
        if (newState == InlinedPaymentPrimaryButtonState.ERROR) {
            mutableRouter.value = Router.SetPaymentResultAndPop()
        }
    }

    private suspend fun verifyAndProcessInlinedPayment(token: String, amount: String, currency: String) {
        komojuApi.verifyTokenAndProcessPayment(
            sessionId = config.sessionId.orEmpty(),
            token = token,
            amount = amount,
            currency = currency,
            onError = {
                changeInlinePaymentState(InlinedPaymentPrimaryButtonState.ERROR)
            },
            onSuccess = {
                changeInlinePaymentState(InlinedPaymentPrimaryButtonState.SUCCESS)
                delay(400.milliseconds)
                mutableRouter.value = Router.SetPaymentResultAndPop(KomojuSDK.PaymentResult(isSuccessFul = it.isSuccessful()))
            },
        )
    }

    fun onInlinedDeeplinkCaptured(deeplink: String) {
        val deeplinkEntity = DeeplinkEntity.from(deeplink)
        if (deeplinkEntity is DeeplinkEntity.Verify.BySecureToken) {
            screenModelScope.launch {
                verifyAndProcessInlinedPayment(deeplinkEntity.secureTokenId, deeplinkEntity.amount, deeplinkEntity.currency)
            }
        }
    }

    private fun Payment.handle() {
        when (this) {
            is Payment.Konbini -> mutableRouter.value = Router.Replace(KomojuPaymentRoute.KonbiniAwaitingPayment(config, payment = this))
            is Payment.OffSitePayment -> _offSitePaymentURL.value = redirectURL
            is Payment.Completed -> mutableRouter.value = Router.SetPaymentResultAndPop(KomojuSDK.PaymentResult(isSuccessFul = status == PaymentStatus.CAPTURED))
            else -> Unit
        }
    }

    private fun PaymentMethod.validate() = when (this) {
        is PaymentMethod.CreditCard -> state.value.creditCardDisplayData.validate()
        is PaymentMethod.Konbini -> state.value.konbiniDisplayData.validate(state.value.commonDisplayData)
        is PaymentMethod.Paidy -> state.value.paidyDisplayData.validate()
        is PaymentMethod.NetCash -> state.value.netCashDisplayData.validate()
        is PaymentMethod.BitCash -> state.value.bitCashDisplayData.validate()
        is PaymentMethod.WebMoney -> state.value.webMoneyDisplayData.validate()
        is PaymentMethod.OffSitePayment -> true // No input required for Offsite payment
        else -> false
    }

    private fun WebMoneyDisplayData.validate(): Boolean {
        val prepaidNumberError = when {
            prepaidNumber.isBlank() -> "The entered prepaid number cannot be empty"
            prepaidNumber.length != 16 -> "The entered prepaid number is not valid"
            else -> null
        }
        mutableState.update {
            it.copy(
                webMoneyDisplayData = it.webMoneyDisplayData.copy(
                    prepaidNumberError = prepaidNumberError,
                ),
            )
        }
        return prepaidNumberError == null
    }

    private fun BitCashDisplayData.validate(): Boolean {
        val idError = when {
            bitCashId.isBlank() -> "The entered net cash id cannot be empty"
            bitCashId.length != 16 -> "The entered net cash id is not valid"
            else -> null
        }
        mutableState.update {
            it.copy(
                bitCashDisplayData = it.bitCashDisplayData.copy(
                    bitCashError = idError,
                ),
            )
        }
        return idError == null
    }
    private fun NetCashDisplayData.validate(): Boolean {
        val idError = when {
            netCashId.isBlank() -> "The entered net cash id cannot be empty"
            netCashId.length !in 16..20 -> "The entered net cash id is not valid"
            else -> null
        }
        mutableState.update {
            it.copy(
                netCashDisplayData = it.netCashDisplayData.copy(
                    netCashError = idError,
                ),
            )
        }
        return idError == null
    }

    private fun PaidyDisplayData.validate(): Boolean {
        val fullNameError = when {
            fullName.isBlank() -> "The entered name cannot be empty"
            else -> null
        }
        val phoneNumberError = when {
            phoneNumber.isBlank() -> "The entered phone number cannot be empty"
            phoneNumber.length < 7 -> "The entered phone number is not valid"
            phoneNumber.isDigitsOnly().not() -> "The entered phone number is not valid"
            else -> null
        }
        mutableState.update {
            it.copy(
                paidyDisplayData = it.paidyDisplayData.copy(
                    fullNameError = fullNameError,
                    phoneNumberError = phoneNumberError,
                ),
            )
        }
        return fullNameError == null && phoneNumberError == null
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

    private fun PaymentMethod.CreditCard.toSecureTokenRequest() = SecureTokenRequest(
        amount = amount.toInt().toString(),
        currency = currency,
        returnUrl = config.redirectURL + "creditCard?amount=$amount&currency=$currency",
        cardNumber = state.value.creditCardDisplayData.creditCardNumber,
        cardHolderName = state.value.creditCardDisplayData.fullNameOnCard,
        expiryMonth = state.value.creditCardDisplayData.creditCardExpiryDate.take(2),
        expiryYear = state.value.creditCardDisplayData.creditCardExpiryDate.takeLast(2),
        cvv = state.value.creditCardDisplayData.creditCardCvv,
    )

    private fun PaymentMethod.toPaymentRequest(): PaymentRequest = when (this) {
        is PaymentMethod.BitCash -> PaymentRequest.BitCash(
            paymentMethod = this,
            bitCashId = state.value.bitCashDisplayData.bitCashId,
        )
        is PaymentMethod.CreditCard -> error("Credit Card needs to generate tokens first!")
        is PaymentMethod.Konbini -> PaymentRequest.Konbini(
            paymentMethod = this,
            konbiniBrand = state.value.konbiniDisplayData.selectedKonbiniBrand!!,
            email = state.value.commonDisplayData.email,
        )

        is PaymentMethod.NetCash -> PaymentRequest.NetCash(
            paymentMethod = this,
            netCashId = state.value.netCashDisplayData.netCashId,
        )
        is PaymentMethod.Other -> error("payment method is not supported!")
        is PaymentMethod.Paidy -> PaymentRequest.Paidy(
            paymentMethod = this,
            fullName = state.value.paidyDisplayData.fullName,
            phoneNumber = state.value.paidyDisplayData.phoneNumber,
        )
        is PaymentMethod.BankTransfer -> TODO()
        is PaymentMethod.PayEasy -> TODO()
        is PaymentMethod.WebMoney -> PaymentRequest.WebMoney(
            paymentMethod = this,
            prepaidNumber = state.value.webMoneyDisplayData.prepaidNumber,
        )
        is PaymentMethod.OffSitePayment -> PaymentRequest.OffSitePaymentRequest(this)
    }

    fun onCloseClicked() {
        mutableRouter.pop()
    }

    fun onOffsitePaymentResult() {
        mutableRouter.value = Router.ReplaceAll(
            KomojuPaymentRoute.ProcessPayment(
                configuration = config,
                processType = KomojuPaymentRoute.ProcessPayment.ProcessType.Session,
            ),
        )
    }

    fun onOffSitePaymentURLConsumed() {
        _offSitePaymentURL.value = null
    }
}
