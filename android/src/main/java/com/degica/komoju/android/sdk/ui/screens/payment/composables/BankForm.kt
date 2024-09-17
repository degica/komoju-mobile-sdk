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
import com.degica.komoju.android.sdk.ui.composables.PrimaryButton
import com.degica.komoju.android.sdk.ui.screens.payment.CommonDisplayData
import com.degica.komoju.android.sdk.ui.theme.KomojuMobileSdkTheme
import com.degica.komoju.android.sdk.ui.theme.LocalI18nTextsProvider
import com.degica.komoju.android.sdk.utils.AmountUtils
import com.degica.komoju.mobile.sdk.entities.PaymentMethod

@Composable
internal fun BankForm(
    bankTransfer: PaymentMethod.BankTransfer,
    commonDisplayData: CommonDisplayData,
    onCommonDisplayDataChange: (CommonDisplayData) -> Unit,
    onPayButtonClicked: () -> Unit,
) {
    val displayPayableAmount by remember(bankTransfer.amount) {
        derivedStateOf {
            AmountUtils.formatToDecimal(Currency.parse(bankTransfer.currency), bankTransfer.amount)
        }
    }
    Column {
        TextField(
            value = commonDisplayData.lastName,
            titleKey = "LAST_NAME",
            placeholderKey = "LAST_NAME",
            onValueChange = {
                onCommonDisplayDataChange(commonDisplayData.copy(lastName = it))
            },
        )
        TextField(
            value = commonDisplayData.firstName,
            titleKey = "FIRST_NAME",
            placeholderKey = "FIRST_NAME",
            onValueChange = {
                onCommonDisplayDataChange(commonDisplayData.copy(firstName = it))
            },
        )
        TextField(
            value = commonDisplayData.lastNamePhonetic,
            titleKey = "LAST_NAME_PHONETIC",
            placeholderKey = "LAST_NAME_PHONETIC",
            onValueChange = {
                onCommonDisplayDataChange(commonDisplayData.copy(lastNamePhonetic = it))
            },
        )
        TextField(
            value = commonDisplayData.firstNamePhonetic,
            titleKey = "FIRST_NAME_PHONETIC",
            placeholderKey = "FIRST_NAME_PHONETIC",
            onValueChange = {
                onCommonDisplayDataChange(commonDisplayData.copy(firstNamePhonetic = it))
            },
        )
        TextField(
            value = commonDisplayData.email,
            titleKey = "EMAIL",
            placeholderKey = "EXAMPLE_EMAIL",
            onValueChange = {
                onCommonDisplayDataChange(commonDisplayData.copy(email = it))
            },
            keyboardType = KeyboardType.Email,
        )
        TextField(
            value = commonDisplayData.phoneNumber,
            titleKey = "TELEPHONE_NUMBER",
            placeholderKey = "TELEPHONE_NUMBER_PLACEHOLDER",
            onValueChange = {
                onCommonDisplayDataChange(commonDisplayData.copy(phoneNumber = it))
            },
            keyboardType = KeyboardType.Number,
        )
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryButton(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            text = "${LocalI18nTextsProvider.current["PAY"]} $displayPayableAmount",
            onClick = onPayButtonClicked,
        )
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
        var commonDisplayData by remember { mutableStateOf(CommonDisplayData()) }
        BankForm(
            bankTransfer,
            commonDisplayData,
            onCommonDisplayDataChange = {
                commonDisplayData = it
            },
            onPayButtonClicked = {
            },
        )
    }
}
