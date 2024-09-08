package com.degica.komoju.android.sdk.ui.screens.payment.composables

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
import com.degica.komoju.android.sdk.types.Currency
import com.degica.komoju.android.sdk.ui.theme.LocalI18nTextsProvider
import com.degica.komoju.android.sdk.utils.AmountUtils
import com.degica.komoju.mobile.sdk.entities.PaymentMethod

@Composable
internal fun BitCashForm(bitCash: PaymentMethod.BitCash, bitCashId: String, onBitCashIdChange: (String) -> Unit) {
    val displayPayableAmount by remember(bitCash.amount) {
        derivedStateOf {
            AmountUtils.formatToDecimal(Currency.parse(bitCash.currency), bitCash.amount)
        }
    }
    Column {
        TextField(
            value = bitCashId,
            titleKey = "BIT_CASH_INPUT_LABEL",
            placeholderKey = "BIT_CASH_INPUT_PLACEHOLDER",
            onValueChange = onBitCashIdChange,
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
