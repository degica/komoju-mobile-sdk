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
import com.komoju.android.sdk.ui.screens.payment.NetCashDisplayData
import com.komoju.android.sdk.utils.AmountUtils
import com.komoju.mobile.sdk.entities.PaymentMethod

@Composable
internal fun NetCashForm(
    netCash: PaymentMethod.NetCash,
    netCashDisplayData: NetCashDisplayData,
    onNetCashDisplayDataChange: (NetCashDisplayData) -> Unit,
    onPayButtonClicked: () -> Unit,
) {
    val displayPayableAmount by remember(netCash.amount) {
        derivedStateOf {
            AmountUtils.formatToDecimal(Currency.parse(netCash.currency), netCash.amount)
        }
    }
    Column {
        TextField(
            value = netCashDisplayData.netCashId,
            title = stringResource(R.string.komoju_net_cash_information),
            placeholder = stringResource(R.string.komoju_net_cash_id),
            onValueChange = {
                onNetCashDisplayDataChange(netCashDisplayData.copy(netCashId = it))
            },
            error = netCashDisplayData.netCashErrorStringResource?.let { stringResource(it) },
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
