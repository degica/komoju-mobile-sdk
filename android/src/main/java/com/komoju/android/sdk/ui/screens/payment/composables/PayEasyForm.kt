package com.komoju.android.sdk.ui.screens.payment.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.komoju.android.sdk.types.Currency
import com.komoju.android.sdk.ui.composables.PrimaryButton
import com.komoju.android.sdk.ui.screens.payment.CommonDisplayData
import com.komoju.android.sdk.ui.theme.LocalI18nTexts
import com.komoju.android.sdk.utils.AmountUtils
import com.komoju.mobile.sdk.entities.PaymentMethod

@Composable
internal fun PayEasyForm(
    payEasy: PaymentMethod.PayEasy,
    commonDisplayData: CommonDisplayData,
    onCommonDisplayDataChange: (CommonDisplayData) -> Unit,
    onPayButtonClicked: () -> Unit,
) {
    val displayPayableAmount by remember(payEasy.amount) {
        derivedStateOf {
            AmountUtils.formatToDecimal(Currency.parse(payEasy.currency), payEasy.amount)
        }
    }
    Column {
        CompatTextField(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            value = commonDisplayData.lastName,
            titleKey = "LAST_NAME",
            placeholderKey = "LAST_NAME",
            onValueChange = {
                onCommonDisplayDataChange(commonDisplayData.copy(lastName = it))
            },
            keyboardType = KeyboardType.Text,
            imeActions = ImeAction.Next,
            singleLine = true,
            error = commonDisplayData.lastNameError,
        )
        CompatTextField(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            value = commonDisplayData.firstName,
            titleKey = "FIRST_NAME",
            placeholderKey = "FIRST_NAME",
            onValueChange = {
                onCommonDisplayDataChange(commonDisplayData.copy(firstName = it))
            },
            keyboardType = KeyboardType.Text,
            imeActions = ImeAction.Next,
            singleLine = true,
            error = commonDisplayData.firstNameError,
        )
        CompatTextField(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            value = commonDisplayData.lastNamePhonetic,
            titleKey = "LAST_NAME_PHONETIC",
            placeholderKey = "LAST_NAME_PHONETIC",
            onValueChange = {
                onCommonDisplayDataChange(commonDisplayData.copy(lastNamePhonetic = it))
            },
            keyboardType = KeyboardType.Text,
            keyBoardLocale = JAPANESE_LOCALE,
            imeActions = ImeAction.Next,
            singleLine = true,
            error = commonDisplayData.lastNamePhoneticError,
        )
        CompatTextField(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            value = commonDisplayData.firstNamePhonetic,
            titleKey = "FIRST_NAME_PHONETIC",
            placeholderKey = "FIRST_NAME_PHONETIC",
            onValueChange = {
                onCommonDisplayDataChange(commonDisplayData.copy(firstNamePhonetic = it))
            },
            keyboardType = KeyboardType.Text,
            keyBoardLocale = JAPANESE_LOCALE,
            imeActions = ImeAction.Next,
            singleLine = true,
            error = commonDisplayData.firstNamePhoneticError,
        )
        CompatTextField(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            value = commonDisplayData.email,
            titleKey = "EMAIL",
            placeholderKey = "EXAMPLE_EMAIL",
            onValueChange = {
                onCommonDisplayDataChange(commonDisplayData.copy(email = it))
            },
            keyboardType = KeyboardType.Email,
            imeActions = ImeAction.Next,
            singleLine = true,
            error = commonDisplayData.emailError,
        )
        CompatTextField(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            value = commonDisplayData.phoneNumber,
            titleKey = "TELEPHONE_NUMBER",
            placeholderKey = "TELEPHONE_NUMBER_PLACEHOLDER",
            onValueChange = {
                onCommonDisplayDataChange(commonDisplayData.copy(phoneNumber = it))
            },
            keyboardType = KeyboardType.Number,
            imeActions = ImeAction.Done,
            singleLine = true,
            error = commonDisplayData.phoneNumberError,
        )
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryButton(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            text = "${LocalI18nTexts.current["PAY"]} $displayPayableAmount",
            onClick = onPayButtonClicked,
        )
    }
}
