package com.komoju.mobile.sdk.ui.screens.payment

import cafe.adriel.voyager.core.model.screenModelScope
import com.komoju.mobile.sdk.KomojuMobileSDKConfiguration
import com.komoju.mobile.sdk.KomojuMobileSDKPaymentResult
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
import com.komoju.mobile.sdk.i18n.I18nStringKey
import com.komoju.mobile.sdk.navigation.RouterStateScreenModel
import com.komoju.mobile.sdk.remote.apis.KomojuRemoteApi
import com.komoju.mobile.sdk.ui.composables.InlinedPaymentPrimaryButtonState
import com.komoju.mobile.sdk.ui.screens.KomojuPaymentRoute
import com.komoju.mobile.sdk.ui.screens.Router
import com.komoju.mobile.sdk.ui.screens.failed.Reason
import com.komoju.mobile.sdk.utils.CreditCardUtils.isValidCVV
import com.komoju.mobile.sdk.utils.CreditCardUtils.isValidCardHolderNameChar
import com.komoju.mobile.sdk.utils.CreditCardUtils.isValidCardNumber
import com.komoju.mobile.sdk.utils.CreditCardUtils.isValidExpiryDate
import com.komoju.mobile.sdk.utils.DeeplinkEntity
import com.komoju.mobile.sdk.utils.Logger
import com.komoju.mobile.sdk.utils.Platform
import com.komoju.mobile.sdk.utils.currentPlatform
import com.komoju.mobile.sdk.utils.isDigitsOnly
import com.komoju.mobile.sdk.utils.isKanaOnly
import com.komoju.mobile.sdk.utils.isValidEmail
import com.komoju.mobile.sdk.utils.verifyTokenAndProcessPayment
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class KomojuPaymentScreenModel(private val config: KomojuMobileSDKConfiguration) :
    RouterStateScreenModel<KomojuPaymentUIState>(KomojuPaymentUIState()) {
    private val komojuApi: KomojuRemoteApi = KomojuRemoteApi.create(config)
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
                        creditCardErrorI18nStringKey = null,
                        fullNameOnCardErrorI18nStringKey = null,
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
                                    processType = KomojuPaymentRoute.ProcessPayment.ProcessType.PayByToken(
                                        tokens.id,
                                        request.amount,
                                        request.currency,
                                    ),
                                ),
                            )
                        }
                    }

                    NEEDS_VERIFY -> {
                        if (config.inlinedProcessing) {
                            mutableState.update { it.copy(inlinedCreditCardProcessingURL = tokens.authURL) }
                        } else {
                            mutableRouter.value =
                                Router.ReplaceAll(KomojuPaymentRoute.WebView(config, url = tokens.authURL, isJavaScriptEnabled = true))
                        }
                    }

                    ERRORED, UNKNOWN -> {
                        Logger.d("Error creating secure tokens status ${tokens.status}")
                        mutableRouter.value = Router.ReplaceAll(KomojuPaymentRoute.PaymentFailed(Reason.CREDIT_CARD_ERROR))
                    }
                }
            }.onFailure {
                Logger.e(it)
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
                delay(400.milliseconds) // Wait till Check icon is at-least visible to the User
                mutableRouter.value = Router.SetPaymentResultAndPop(KomojuMobileSDKPaymentResult(isSuccessFul = it.isSuccessful()))
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
            is Payment.OffSitePayment -> when (currentPlatform) {
                // we will use WebView in IOS first.
                Platform.IOS ->
                    mutableRouter.value =
                        Router.Replace(
                            KomojuPaymentRoute.WebView(config, url = redirectURL, isJavaScriptEnabled = true, canComeBack = false),
                        )

                else -> _offSitePaymentURL.value = redirectURL
            }

            is Payment.Completed ->
                mutableRouter.value =
                    Router.SetPaymentResultAndPop(KomojuMobileSDKPaymentResult(isSuccessFul = status == PaymentStatus.CAPTURED))

            is Payment.BankTransfer -> mutableRouter.value = Router.ReplaceAll(KomojuPaymentRoute.WebView(config, url = instructionURL))
            is Payment.PayEasy -> mutableRouter.value = Router.ReplaceAll(KomojuPaymentRoute.WebView(config, url = instructionURL))
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
        is PaymentMethod.BankTransfer,
        is PaymentMethod.PayEasy,
        -> state.value.commonDisplayData.validate()

        is PaymentMethod.OffSitePayment -> true // No input required for Offsite payment
        else -> false
    }

    private fun CommonDisplayData.validate(): Boolean {
        val lastNameError = if (lastName.isBlank()) I18nStringKey.the_entered_last_name_cannot_be_empty else null
        val firstNameError = if (firstName.isBlank()) I18nStringKey.the_entered_first_name_cannot_be_empty else null
        val firstNamePhoneticError = when {
            firstNamePhonetic.isBlank() -> I18nStringKey.the_entered_first_name_phonetic_cannot_be_empty
            firstNamePhonetic.isKanaOnly.not() -> I18nStringKey.the_entered_first_name_phonetic_must_be_a_kana
            else -> null
        }
        val lastNamePhoneticError = when {
            lastNamePhonetic.isBlank() -> I18nStringKey.the_entered_last_name_phonetic_cannot_be_empty
            lastNamePhonetic.isKanaOnly.not() -> I18nStringKey.the_entered_last_name_phonetic_must_be_a_kana
            else -> null
        }
        val emailError = if (email.isValidEmail.not()) I18nStringKey.the_entered_email_is_not_valid else null
        val phoneNumberError = when {
            phoneNumber.isBlank() -> I18nStringKey.the_entered_phone_number_cannot_be_empty
            phoneNumber.length < 7 -> I18nStringKey.the_entered_phone_number_is_not_valid
            phoneNumber.isDigitsOnly.not() -> I18nStringKey.the_entered_phone_number_is_not_valid
            else -> null
        }
        mutableState.update {
            it.copy(
                commonDisplayData = it.commonDisplayData.copy(
                    lastNameErrorI18nStringKey = lastNameError,
                    firstNameErrorI18nStringKey = firstNameError,
                    firstNamePhoneticErrorI18nStringKey = firstNamePhoneticError,
                    lastNamePhoneticErrorI18nStringKey = lastNamePhoneticError,
                    emailErrorI18nStringKey = emailError,
                    phoneNumberErrorI18nStringKey = phoneNumberError,
                ),
            )
        }
        return lastNameError == null &&
            firstNameError == null &&
            firstNamePhoneticError == null &&
            lastNamePhoneticError == null &&
            emailError == null &&
            phoneNumberError == null
    }

    private fun WebMoneyDisplayData.validate(): Boolean {
        val prepaidNumberError = when {
            prepaidNumber.isBlank() -> I18nStringKey.the_entered_prepaid_number_cannot_be_empty
            prepaidNumber.length != 16 -> I18nStringKey.the_entered_prepaid_number_is_not_valid
            else -> null
        }
        mutableState.update {
            it.copy(
                webMoneyDisplayData = it.webMoneyDisplayData.copy(
                    prepaidNumberErrorI18nStringKey = prepaidNumberError,
                ),
            )
        }
        return prepaidNumberError == null
    }

    private fun BitCashDisplayData.validate(): Boolean {
        val idError = when {
            bitCashId.isBlank() -> I18nStringKey.the_entered_bit_cash_id_cannot_be_empty
            bitCashId.length != 16 -> I18nStringKey.the_entered_bit_cash_id_is_not_valid
            else -> null
        }
        mutableState.update {
            it.copy(
                bitCashDisplayData = it.bitCashDisplayData.copy(
                    bitCashErrorI18nStringKey = idError,
                ),
            )
        }
        return idError == null
    }

    private fun NetCashDisplayData.validate(): Boolean {
        val idError = when {
            netCashId.isBlank() -> I18nStringKey.the_entered_net_cash_id_cannot_be_empty
            netCashId.length !in 16..20 -> I18nStringKey.the_entered_net_cash_id_is_not_valid
            else -> null
        }
        mutableState.update {
            it.copy(
                netCashDisplayData = it.netCashDisplayData.copy(
                    netCashErrorI18nStringKey = idError,
                ),
            )
        }
        return idError == null
    }

    private fun PaidyDisplayData.validate(): Boolean {
        val fullNameError = when {
            fullName.isBlank() -> I18nStringKey.the_entered_name_cannot_be_empty
            else -> null
        }
        val phoneNumberError = when {
            phoneNumber.isBlank() -> I18nStringKey.the_entered_phone_number_cannot_be_empty
            phoneNumber.length < 7 -> I18nStringKey.the_entered_phone_number_is_not_valid
            phoneNumber.isDigitsOnly.not() -> I18nStringKey.the_entered_phone_number_is_not_valid
            else -> null
        }
        mutableState.update {
            it.copy(
                paidyDisplayData = it.paidyDisplayData.copy(
                    fullNameErrorI18nStringKey = fullNameError,
                    phoneNumberErrorI18nStringKey = phoneNumberError,
                ),
            )
        }
        return fullNameError == null && phoneNumberError == null
    }

    private fun CreditCardDisplayData.validate(): Boolean {
        val fullNameOnCardError = when {
            fullNameOnCard.isBlank() -> I18nStringKey.cardholder_name_cannot_be_empty
            fullNameOnCard.all { char -> char.isValidCardHolderNameChar() } -> null
            else -> I18nStringKey.the_entered_cardholder_name_is_not_valid
        }
        val creditCardError = run {
            when {
                creditCardNumber.isValidCardNumber().not() -> I18nStringKey.the_entered_card_number_is_not_valid
                creditCardExpiryDate.isValidExpiryDate().not() -> I18nStringKey.the_entered_expiry_date_is_not_valid
                creditCardCvv.isValidCVV().not() -> I18nStringKey.the_entered_cvv_is_not_valid
                else -> null
            }
        }
        mutableState.update {
            it.copy(
                creditCardDisplayData = it.creditCardDisplayData.copy(
                    fullNameOnCardErrorI18nStringKey = fullNameOnCardError,
                    creditCardErrorI18nStringKey = creditCardError,
                ),
            )
        }
        return fullNameOnCardError == null && creditCardError == null
    }

    private fun KonbiniDisplayData.validate(commonDisplayData: CommonDisplayData): Boolean {
        val nameError = if (receiptName.trim().isEmpty()) I18nStringKey.the_entered_name_cannot_be_empty else null
        val emailError = if (commonDisplayData.email.isValidEmail.not()) I18nStringKey.the_entered_email_is_not_valid else null
        val konbiniBrandNullError = if (selectedKonbiniBrand == null) I18nStringKey.please_select_a_konbini_brand else null
        mutableState.update {
            it.copy(
                konbiniDisplayData = it.konbiniDisplayData.copy(
                    receiptNameErrorI18nStringKey = nameError,
                    receiptEmailErrorI18nStringKey = emailError,
                    konbiniBrandNullErrorI18nStringKey = konbiniBrandNullError,
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

        is PaymentMethod.Konbini -> PaymentRequest.Konbini(
            paymentMethod = this,
            konbiniBrand = state.value.konbiniDisplayData.selectedKonbiniBrand!!,
            email = state.value.commonDisplayData.email,
        )

        is PaymentMethod.NetCash -> PaymentRequest.NetCash(
            paymentMethod = this,
            netCashId = state.value.netCashDisplayData.netCashId,
        )

        is PaymentMethod.Paidy -> PaymentRequest.Paidy(
            paymentMethod = this,
            fullName = state.value.paidyDisplayData.fullName,
            phoneNumber = state.value.paidyDisplayData.phoneNumber,
        )

        is PaymentMethod.BankTransfer -> PaymentRequest.BankTransfer(
            paymentMethod = this,
            lastName = state.value.commonDisplayData.lastName,
            firstName = state.value.commonDisplayData.firstName,
            lastNamePhonetic = state.value.commonDisplayData.lastNamePhonetic,
            firstNamePhonetic = state.value.commonDisplayData.firstNamePhonetic,
            email = state.value.commonDisplayData.email,
            phoneNumber = state.value.commonDisplayData.phoneNumber,
        )

        is PaymentMethod.PayEasy -> PaymentRequest.PayEasy(
            paymentMethod = this,
            lastName = state.value.commonDisplayData.lastName,
            firstName = state.value.commonDisplayData.firstName,
            lastNamePhonetic = state.value.commonDisplayData.lastNamePhonetic,
            firstNamePhonetic = state.value.commonDisplayData.firstNamePhonetic,
            email = state.value.commonDisplayData.email,
            phoneNumber = state.value.commonDisplayData.phoneNumber,
        )

        is PaymentMethod.WebMoney -> PaymentRequest.WebMoney(
            paymentMethod = this,
            prepaidNumber = state.value.webMoneyDisplayData.prepaidNumber,
        )

        is PaymentMethod.OffSitePayment -> PaymentRequest.OffSitePaymentRequest(this)
        is PaymentMethod.CreditCard -> error("Credit Card needs to generate tokens first!")
        is PaymentMethod.Other -> error("payment method is not supported!")
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

    override fun onDispose() {
        komojuApi.close()
        super.onDispose()
    }
}
