package com.komoju.android.sdk.ui.screens.payment.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.komoju.android.sdk.ui.composables.PrimaryButton
import com.komoju.android.sdk.ui.screens.payment.PaidyDisplayData
import com.komoju.android.sdk.ui.theme.KomojuMobileSdkTheme
import com.komoju.android.sdk.ui.theme.LocalI18nTexts
import com.komoju.mobile.sdk.entities.PaymentMethod

@Composable
internal fun PaidyForm(paidy: PaymentMethod.Paidy, paidyDisplayData: PaidyDisplayData, onPaidyDisplayDataChange: (PaidyDisplayData) -> Unit, onPayButtonClicked: () -> Unit) {
    Column {
        TextField(
            value = paidyDisplayData.fullName,
            titleKey = "FULL_NAME",
            placeholderKey = "FULL_NAME_PLACEHOLDER",
            onValueChange = {
                onPaidyDisplayDataChange(paidyDisplayData.copy(fullName = it))
            },
            error = paidyDisplayData.fullNameError,
        )
        TextField(
            value = paidyDisplayData.phoneNumber,
            titleKey = "TELEPHONE_NUMBER",
            placeholderKey = "TELEPHONE_NUMBER_PLACEHOLDER",
            keyboardType = KeyboardType.Phone,
            onValueChange = {
                onPaidyDisplayDataChange(paidyDisplayData.copy(phoneNumber = it))
            },
            error = paidyDisplayData.phoneNumberError,
        )
        PrimaryButton(LocalI18nTexts.current["CONTINUE_TO_PAIDY"], modifier = Modifier.fillMaxWidth().padding(all = 16.dp), onPayButtonClicked)
    }
}

@Composable
@Preview(showBackground = true)
private fun PaidyFormPreview() {
    val paidy = PaymentMethod.Paidy(
        displayName = "Paidy",
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
