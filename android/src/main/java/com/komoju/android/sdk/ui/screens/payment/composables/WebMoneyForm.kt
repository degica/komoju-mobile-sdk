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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.komoju.android.sdk.R
import com.komoju.android.sdk.types.Currency
import com.komoju.android.sdk.ui.composables.PrimaryButton
import com.komoju.android.sdk.ui.screens.payment.WebMoneyDisplayData
import com.komoju.android.sdk.utils.AmountUtils
import com.komoju.mobile.sdk.entities.PaymentMethod

@Composable
internal fun WebMoneyForm(
    webMoney: PaymentMethod.WebMoney,
    webMoneyDisplayData: WebMoneyDisplayData,
    onWebMoneyDisplayDataChange: (WebMoneyDisplayData) -> Unit,
    onPayButtonClicked: () -> Unit,
) {
    val displayPayableAmount by remember(webMoney.amount) {
        derivedStateOf {
            AmountUtils.formatToDecimal(Currency.parse(webMoney.currency), webMoney.amount)
        }
    }
    Column {
        TextField(
            value = webMoneyDisplayData.prepaidNumber,
            title = stringResource(R.string.komoju_webmoney_information),
            placeholder = stringResource(R.string.komoju_prepaid_number),
            onValueChange = {
                onWebMoneyDisplayDataChange(webMoneyDisplayData.copy(prepaidNumber = it))
            },
            error = webMoneyDisplayData.prepaidNumberErrorStringResource?.let { stringResource(it) },
        )
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryButton(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            text = stringResource(R.string.komoju_pay, displayPayableAmount),
            onClick = onPayButtonClicked,
        )
    }
}
