package com.komoju.mobile.sdk.ui.screens.payment.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.komoju.mobile.sdk.entities.PaymentMethod
import com.komoju.mobile.sdk.i18n.I18nStringKey
import com.komoju.mobile.sdk.i18n.i18nStringResource
import com.komoju.mobile.sdk.ui.composables.PrimaryButton
import com.komoju.mobile.sdk.ui.screens.payment.CommonDisplayData
import com.komoju.mobile.sdk.utils.AmountUtils

@Composable
internal fun PayEasyForm(
    payEasy: PaymentMethod.PayEasy,
    commonDisplayData: CommonDisplayData,
    onCommonDisplayDataChange: (CommonDisplayData) -> Unit,
    onPayButtonClicked: () -> Unit,
) {
    val displayPayableAmount by remember(payEasy.amount) {
        derivedStateOf {
            AmountUtils.formatToDecimal(payEasy.currency, payEasy.amount)
        }
    }
    Column {
        CompatTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = commonDisplayData.lastName,
            title = i18nStringResource(I18nStringKey.last_name),
            placeholder = i18nStringResource(I18nStringKey.last_name),
            onValueChange = {
                onCommonDisplayDataChange(commonDisplayData.copy(lastName = it))
            },
            keyboardType = KeyboardType.Text,
            imeActions = ImeAction.Next,
            singleLine = true,
            error = commonDisplayData.lastNameErrorI18nStringKey,
        )
        CompatTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = commonDisplayData.firstName,
            title = i18nStringResource(I18nStringKey.first_name),
            placeholder = i18nStringResource(I18nStringKey.first_name),
            onValueChange = {
                onCommonDisplayDataChange(commonDisplayData.copy(firstName = it))
            },
            keyboardType = KeyboardType.Text,
            imeActions = ImeAction.Next,
            singleLine = true,
            error = commonDisplayData.firstNameErrorI18nStringKey,
        )
        CompatTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = commonDisplayData.lastNamePhonetic,
            title = i18nStringResource(I18nStringKey.last_name_phonetic),
            placeholder = i18nStringResource(I18nStringKey.last_name_phonetic),
            onValueChange = {
                onCommonDisplayDataChange(commonDisplayData.copy(lastNamePhonetic = it))
            },
            keyboardType = KeyboardType.Text,
            keyBoardLocale = JAPANESE_LOCALE,
            imeActions = ImeAction.Next,
            singleLine = true,
            error = commonDisplayData.lastNamePhoneticErrorI18nStringKey,
        )
        CompatTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = commonDisplayData.firstNamePhonetic,
            title = i18nStringResource(I18nStringKey.first_name_phonetic),
            placeholder = i18nStringResource(I18nStringKey.first_name_phonetic),
            onValueChange = {
                onCommonDisplayDataChange(commonDisplayData.copy(firstNamePhonetic = it))
            },
            keyboardType = KeyboardType.Text,
            keyBoardLocale = JAPANESE_LOCALE,
            imeActions = ImeAction.Next,
            singleLine = true,
            error = commonDisplayData.firstNamePhoneticErrorI18nStringKey,
        )
        CompatTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = commonDisplayData.email,
            title = i18nStringResource(I18nStringKey.email),
            placeholder = i18nStringResource(I18nStringKey.enter_your_email_address),
            onValueChange = {
                onCommonDisplayDataChange(commonDisplayData.copy(email = it))
            },
            keyboardType = KeyboardType.Email,
            imeActions = ImeAction.Next,
            singleLine = true,
            error = commonDisplayData.emailErrorI18nStringKey,
        )
        CompatTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = commonDisplayData.phoneNumber,
            title = i18nStringResource(I18nStringKey.phone_number),
            placeholder = i18nStringResource(I18nStringKey.enter_your_phone_number),
            onValueChange = {
                onCommonDisplayDataChange(commonDisplayData.copy(phoneNumber = it))
            },
            keyboardType = KeyboardType.Number,
            imeActions = ImeAction.Done,
            singleLine = true,
            error = commonDisplayData.phoneNumberErrorI18nStringKey,
        )
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryButton(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            text = i18nStringResource(I18nStringKey.pay, displayPayableAmount),
            onClick = onPayButtonClicked,
        )
    }
}
