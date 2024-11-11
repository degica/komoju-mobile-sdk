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
import komoju_mobile_sdk.shared.generated.resources.Res
import com.komoju.mobile.sdk.ui.composables.PrimaryButton
import com.komoju.mobile.sdk.ui.screens.payment.NetCashDisplayData
import com.komoju.mobile.sdk.utils.AmountUtils
import komoju_mobile_sdk.shared.generated.resources.komoju_net_cash_id
import komoju_mobile_sdk.shared.generated.resources.komoju_net_cash_information
import komoju_mobile_sdk.shared.generated.resources.komoju_pay
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun NetCashForm(
    netCash: PaymentMethod.NetCash,
    netCashDisplayData: NetCashDisplayData,
    onNetCashDisplayDataChange: (NetCashDisplayData) -> Unit,
    onPayButtonClicked: () -> Unit,
) {
    val displayPayableAmount by remember(netCash.amount) {
        derivedStateOf {
            AmountUtils.formatToDecimal(netCash.currency, netCash.amount)
        }
    }
    Column {
        TextField(
            value = netCashDisplayData.netCashId,
            title = stringResource(Res.string.komoju_net_cash_information),
            placeholder = stringResource(Res.string.komoju_net_cash_id),
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
            text = stringResource(Res.string.komoju_pay, displayPayableAmount),
            onClick = onPayButtonClicked,
        )
    }
}
