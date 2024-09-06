package com.degica.komoju.android.sdk.ui.screens.payment.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.degica.komoju.mobile.sdk.entities.PaymentMethod

@Composable
internal fun PaymentMethodForm(paymentMethod: PaymentMethod) {
    var cardHolderName by remember { mutableStateOf("") }
    var creditCardNumber by remember { mutableStateOf("") }
    var creditCardExpiryDate by remember { mutableStateOf("") }
    var creditCardCvv by remember { mutableStateOf("") }
    var saveCard by remember { mutableStateOf(false) }

    var konbiniReceiptName by remember { mutableStateOf("") }
    var konbiniBrand by remember { mutableStateOf<PaymentMethod.Konbini.KonbiniBrand?>(null) }
    var userEmailAddress by remember { mutableStateOf("") }

    var lastName by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastNamePhonetic by remember { mutableStateOf("") }
    var firstNamePhonetic by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    when (paymentMethod) {
        is PaymentMethod.CreditCard -> CreditCardForm(
            creditCard = paymentMethod,
            fullNameOnCard = cardHolderName,
            onFullNameOnCardChange = {
                cardHolderName = it
            },
            creditCardNumber = creditCardNumber,
            onCardNumberChange = {
                creditCardNumber = it
            },
            creditCardExpiryDate = creditCardExpiryDate,
            onCardExpiryDateChange = {
                creditCardExpiryDate = it
            },
            creditCardCvv = creditCardCvv,
            onCardCvvChange = {
                creditCardCvv = it
            },
            saveCard = saveCard,
            onSaveCardChange = {
                saveCard = it
            },
        )

        is PaymentMethod.Konbini -> KonbiniForm(
            konbini = paymentMethod,
            receiptName = konbiniReceiptName,
            onReceiptNameChange = {
                konbiniReceiptName = it
            },
            email = userEmailAddress,
            onEmailChange = {
                userEmailAddress = it
            },
            selectedKonbiniBrand = konbiniBrand ?: paymentMethod.brands.firstOrNull(),
            onKonbiniBrandChange = {
                konbiniBrand = it
            },
        )

        is PaymentMethod.AliPay,
        is PaymentMethod.AuPay,
        is PaymentMethod.LinePay,
        is PaymentMethod.MerPay,
        is PaymentMethod.PayPay,
        is PaymentMethod.RakutenPay,
        -> AppPayForm(paymentMethod)

        is PaymentMethod.BankTransfer -> BankForm(
            bankTransfer = paymentMethod,
            lastName = lastName,
            onLastNameChange = {
                lastName = it
            },
            firstName = firstName,
            onFirstNameChange = {
                firstName = it
            },
            lastNamePhonetic = lastNamePhonetic,
            onLastNamePhoneticChange = {
                lastNamePhonetic = it
            },
            firstNamePhonetic = firstNamePhonetic,
            onFirstNamePhoneticChange = {
                firstNamePhonetic = it
            },
            email = email,
            onEmailChange = {
                email = it
            },
            phoneNumber = phoneNumber,
            onPhoneNumberChange = {
                phoneNumber = it
            },
        )
        is PaymentMethod.BitCash -> Unit
        is PaymentMethod.NetCash -> Unit
        is PaymentMethod.Paidy -> Unit
        is PaymentMethod.PayEasy -> Unit
        is PaymentMethod.WebMoney -> Unit
        is PaymentMethod.Other -> Unit
    }
}
