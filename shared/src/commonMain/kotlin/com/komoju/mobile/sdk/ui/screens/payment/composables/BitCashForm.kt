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
import androidx.compose.ui.unit.dp
import com.komoju.mobile.sdk.entities.PaymentMethod
import com.komoju.mobile.sdk.i18n.I18nStringKey
import com.komoju.mobile.sdk.i18n.i18nStringResource
import com.komoju.mobile.sdk.ui.composables.PrimaryButton
import com.komoju.mobile.sdk.ui.screens.payment.BitCashDisplayData
import com.komoju.mobile.sdk.utils.AmountUtils

@Composable
internal fun BitCashForm(
    bitCash: PaymentMethod.BitCash,
    bitCashDisplayData: BitCashDisplayData,
    onBitCashDisplayDataChange: (BitCashDisplayData) -> Unit,
    onPayButtonClicked: () -> Unit,
) {
    val displayPayableAmount by remember(bitCash.amount) {
        derivedStateOf {
            AmountUtils.formatToDecimal(bitCash.currency, bitCash.amount)
        }
    }
    Column {
        TextField(
            value = bitCashDisplayData.bitCashId,
            title = i18nStringResource(I18nStringKey.bit_cash_information),
            placeholder = i18nStringResource(I18nStringKey.hiragana_id),
            onValueChange = {
                onBitCashDisplayDataChange(bitCashDisplayData.copy(bitCashId = it))
            },
            error = bitCashDisplayData.bitCashErrorI18nStringKey?.let { i18nStringResource(it) },
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
