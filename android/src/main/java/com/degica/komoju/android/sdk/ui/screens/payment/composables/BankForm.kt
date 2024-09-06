package com.degica.komoju.android.sdk.ui.screens.payment.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.degica.komoju.android.sdk.types.Currency
import com.degica.komoju.android.sdk.types.Language
import com.degica.komoju.android.sdk.ui.theme.KomojuMobileSdkTheme
import com.degica.komoju.android.sdk.ui.theme.LocalI18nTextsProvider
import com.degica.komoju.android.sdk.utils.AmountUtils
import com.degica.komoju.mobile.sdk.entities.PaymentMethod

@Composable
internal fun BankForm(
    bankTransfer: PaymentMethod.BankTransfer,
    lastName: String,
    onLastNameChange: (String) -> Unit,
    firstName: String,
    onFirstNameChange: (String) -> Unit,
    lastNamePhonetic: String,
    onLastNamePhoneticChange: (String) -> Unit,
    firstNamePhonetic: String,
    onFirstNamePhoneticChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
) {
    val displayPayableAmount by remember(bankTransfer.amount) {
        derivedStateOf {
            AmountUtils.formatToDecimal(Currency.parse(bankTransfer.currency), bankTransfer.amount)
        }
    }
    Column {
        TextField(
            value = lastName,
            titleKey = "LAST_NAME",
            placeholderKey = "LAST_NAME",
            onValueChange = onLastNameChange,
        )
        TextField(
            value = firstName,
            titleKey = "FIRST_NAME",
            placeholderKey = "FIRST_NAME",
            onValueChange = onFirstNameChange,
        )
        TextField(
            value = lastNamePhonetic,
            titleKey = "LAST_NAME_PHONETIC",
            placeholderKey = "LAST_NAME_PHONETIC",
            onValueChange = onLastNamePhoneticChange,
        )
        TextField(
            value = firstNamePhonetic,
            titleKey = "FIRST_NAME_PHONETIC",
            placeholderKey = "FIRST_NAME_PHONETIC",
            onValueChange = onFirstNamePhoneticChange,
        )
        TextField(
            value = email,
            titleKey = "EMAIL",
            placeholderKey = "EXAMPLE_EMAIL",
            onValueChange = onEmailChange,
            keyboardType = KeyboardType.Email,
        )
        TextField(
            value = phoneNumber,
            titleKey = "TELEPHONE_NUMBER",
            placeholderKey = "TELEPHONE_NUMBER_PLACEHOLDER",
            onValueChange = onPhoneNumberChange,
            keyboardType = KeyboardType.Number,
        )
        Spacer(modifier = Modifier.height(16.dp))
        PaymentButton(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            text = "${LocalI18nTextsProvider.current["PAY"]} $displayPayableAmount",
        ) { }
    }
}

@Composable
@Preview(showBackground = true)
private fun BankFormPreview() {
    val bankTransfer = PaymentMethod.BankTransfer(
        displayName = "Bank Transfer",
        hashedGateway = "hashedGateway",
        exchangeRate = 1.0,
        currency = "JPY",
        amount = 100.0,
        additionalFields = emptyList(),
        customerFee = 10,
    )
    KomojuMobileSdkTheme(Language.ENGLISH) {
        var lastName by remember { mutableStateOf("") }
        var firstName by remember { mutableStateOf("") }
        var lastNamePhonetic by remember { mutableStateOf("") }
        var firstNamePhonetic by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var phoneNumber by remember { mutableStateOf("") }
        BankForm(
            bankTransfer,
            lastName,
            onLastNameChange = {
                lastName = it
            },
            firstName,
            onFirstNameChange = {
                firstName = it
            },
            lastNamePhonetic,
            onLastNamePhoneticChange = {
                lastNamePhonetic = it
            },
            firstNamePhonetic,
            onFirstNamePhoneticChange = {
                firstNamePhonetic = it
            },
            email,
            onEmailChange = {
                email = it
            },
            phoneNumber,
            onPhoneNumberChange = {
                phoneNumber = it
            },
        )
    }
}
