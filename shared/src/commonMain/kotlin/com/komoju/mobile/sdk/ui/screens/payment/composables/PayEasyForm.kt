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
import komoju_mobile_sdk.shared.generated.resources.Res
import com.komoju.mobile.sdk.ui.composables.PrimaryButton
import com.komoju.mobile.sdk.ui.screens.payment.CommonDisplayData
import com.komoju.mobile.sdk.utils.AmountUtils
import komoju_mobile_sdk.shared.generated.resources.komoju_email
import komoju_mobile_sdk.shared.generated.resources.komoju_enter_your_email_address
import komoju_mobile_sdk.shared.generated.resources.komoju_enter_your_phone_number
import komoju_mobile_sdk.shared.generated.resources.komoju_first_name
import komoju_mobile_sdk.shared.generated.resources.komoju_first_name_phonetic
import komoju_mobile_sdk.shared.generated.resources.komoju_last_name
import komoju_mobile_sdk.shared.generated.resources.komoju_last_name_phonetic
import komoju_mobile_sdk.shared.generated.resources.komoju_pay
import komoju_mobile_sdk.shared.generated.resources.komoju_phone_number
import org.jetbrains.compose.resources.stringResource

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
            title = stringResource(Res.string.komoju_last_name),
            placeholder = stringResource(Res.string.komoju_last_name),
            onValueChange = {
                onCommonDisplayDataChange(commonDisplayData.copy(lastName = it))
            },
            keyboardType = KeyboardType.Text,
            imeActions = ImeAction.Next,
            singleLine = true,
            error = commonDisplayData.lastNameErrorStringResource?.let { stringResource(it) },
        )
        CompatTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = commonDisplayData.firstName,
            title = stringResource(Res.string.komoju_first_name),
            placeholder = stringResource(Res.string.komoju_first_name),
            onValueChange = {
                onCommonDisplayDataChange(commonDisplayData.copy(firstName = it))
            },
            keyboardType = KeyboardType.Text,
            imeActions = ImeAction.Next,
            singleLine = true,
            error = commonDisplayData.firstNameErrorStringResource?.let { stringResource(it) },
        )
        CompatTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = commonDisplayData.lastNamePhonetic,
            title = stringResource(Res.string.komoju_last_name_phonetic),
            placeholder = stringResource(Res.string.komoju_last_name_phonetic),
            onValueChange = {
                onCommonDisplayDataChange(commonDisplayData.copy(lastNamePhonetic = it))
            },
            keyboardType = KeyboardType.Text,
            keyBoardLocale = JAPANESE_LOCALE,
            imeActions = ImeAction.Next,
            singleLine = true,
            error = commonDisplayData.lastNamePhoneticErrorStringResource?.let { stringResource(it) },
        )
        CompatTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = commonDisplayData.firstNamePhonetic,
            title = stringResource(Res.string.komoju_first_name_phonetic),
            placeholder = stringResource(Res.string.komoju_first_name_phonetic),
            onValueChange = {
                onCommonDisplayDataChange(commonDisplayData.copy(firstNamePhonetic = it))
            },
            keyboardType = KeyboardType.Text,
            keyBoardLocale = JAPANESE_LOCALE,
            imeActions = ImeAction.Next,
            singleLine = true,
            error = commonDisplayData.firstNamePhoneticErrorStringResource?.let { stringResource(it) },
        )
        CompatTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = commonDisplayData.email,
            title = stringResource(Res.string.komoju_email),
            placeholder = stringResource(Res.string.komoju_enter_your_email_address),
            onValueChange = {
                onCommonDisplayDataChange(commonDisplayData.copy(email = it))
            },
            keyboardType = KeyboardType.Email,
            imeActions = ImeAction.Next,
            singleLine = true,
            error = commonDisplayData.emailErrorStringResource?.let { stringResource(it) },
        )
        CompatTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = commonDisplayData.phoneNumber,
            title = stringResource(Res.string.komoju_phone_number),
            placeholder = stringResource(Res.string.komoju_enter_your_phone_number),
            onValueChange = {
                onCommonDisplayDataChange(commonDisplayData.copy(phoneNumber = it))
            },
            keyboardType = KeyboardType.Number,
            imeActions = ImeAction.Done,
            singleLine = true,
            error = commonDisplayData.phoneNumberErrorStringResource?.let { stringResource(it) },
        )
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryButton(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            text = stringResource(Res.string.komoju_pay, displayPayableAmount),
            onClick = onPayButtonClicked,
        )
    }
}
