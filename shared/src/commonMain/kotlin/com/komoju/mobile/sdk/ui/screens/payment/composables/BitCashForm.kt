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
import com.komoju.mobile.sdk.ui.composables.PrimaryButton
import com.komoju.mobile.sdk.ui.screens.payment.BitCashDisplayData
import com.komoju.mobile.sdk.utils.AmountUtils
import komoju_mobile_sdk.shared.generated.resources.Res
import komoju_mobile_sdk.shared.generated.resources.komoju_bitcash_information
import komoju_mobile_sdk.shared.generated.resources.komoju_hiragana_id
import komoju_mobile_sdk.shared.generated.resources.komoju_pay
import org.jetbrains.compose.resources.stringResource

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
            title = stringResource(Res.string.komoju_bitcash_information),
            placeholder = stringResource(Res.string.komoju_hiragana_id),
            onValueChange = {
                onBitCashDisplayDataChange(bitCashDisplayData.copy(bitCashId = it))
            },
            error = bitCashDisplayData.bitCashErrorStringResource?.let { stringResource(it) },
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
