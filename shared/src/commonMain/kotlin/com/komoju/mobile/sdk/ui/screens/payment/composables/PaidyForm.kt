package com.komoju.mobile.sdk.ui.screens.payment.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.komoju.mobile.sdk.entities.PaymentMethod
import komoju_mobile_sdk.shared.generated.resources.Res
import com.komoju.mobile.sdk.ui.composables.PrimaryButton
import com.komoju.mobile.sdk.ui.screens.payment.PaidyDisplayData
import com.komoju.mobile.sdk.ui.theme.KomojuMobileSdkTheme
import com.komoju.mobile.sdk.utils.AmountUtils
import komoju_mobile_sdk.shared.generated.resources.komoju_enter_your_name
import komoju_mobile_sdk.shared.generated.resources.komoju_enter_your_phone_number
import komoju_mobile_sdk.shared.generated.resources.komoju_full_name
import komoju_mobile_sdk.shared.generated.resources.komoju_pay
import komoju_mobile_sdk.shared.generated.resources.komoju_phone_number
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun PaidyForm(
    paidy: PaymentMethod.Paidy,
    paidyDisplayData: PaidyDisplayData,
    onPaidyDisplayDataChange: (PaidyDisplayData) -> Unit,
    onPayButtonClicked: () -> Unit,
) {
    val displayPayableAmount by remember(paidy.amount) {
        derivedStateOf {
            AmountUtils.formatToDecimal(paidy.currency, paidy.amount)
        }
    }
    Column {
        TextField(
            value = paidyDisplayData.fullName,
            title = stringResource(Res.string.komoju_full_name),
            placeholder = stringResource(Res.string.komoju_enter_your_name),
            onValueChange = {
                onPaidyDisplayDataChange(paidyDisplayData.copy(fullName = it))
            },
            error = paidyDisplayData.fullNameErrorStringResource?.let { stringResource(it) },
        )
        TextField(
            value = paidyDisplayData.phoneNumber,
            title = stringResource(Res.string.komoju_phone_number),
            placeholder = stringResource(Res.string.komoju_enter_your_phone_number),
            keyboardType = KeyboardType.Phone,
            onValueChange = {
                onPaidyDisplayDataChange(paidyDisplayData.copy(phoneNumber = it))
            },
            error = paidyDisplayData.phoneNumberErrorStringResource?.let { stringResource(it) },
        )
        PrimaryButton(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            text = stringResource(Res.string.komoju_pay, displayPayableAmount),
            onClick = onPayButtonClicked,
        )
    }
}

@Composable
@Preview
private fun PaidyFormPreview() {
    val paidy = PaymentMethod.Paidy(
        hashedGateway = "hashedGateway",
        exchangeRate = 1.0,
        currency = "JPY",
        amount = "100",
        additionalFields = emptyList(),
        isOffsite = true,
    )

    var paidyDisplayData by remember {
        mutableStateOf(PaidyDisplayData())
    }
    KomojuMobileSdkTheme {
        PaidyForm(
            paidy,
            paidyDisplayData = paidyDisplayData,
            onPaidyDisplayDataChange = {
                paidyDisplayData = it
            },
            onPayButtonClicked = {
            },
        )
    }
}
