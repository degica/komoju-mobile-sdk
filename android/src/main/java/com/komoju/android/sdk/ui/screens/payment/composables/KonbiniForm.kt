package com.komoju.android.sdk.ui.screens.payment.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.komoju.android.sdk.R
import com.komoju.android.sdk.types.Currency
import com.komoju.android.sdk.ui.composables.PrimaryButton
import com.komoju.android.sdk.ui.screens.payment.CommonDisplayData
import com.komoju.android.sdk.ui.screens.payment.KonbiniDisplayData
import com.komoju.android.sdk.ui.theme.KomojuMobileSdkTheme
import com.komoju.android.sdk.ui.theme.Red600
import com.komoju.android.sdk.utils.AmountUtils
import com.komoju.mobile.sdk.entities.PaymentMethod
import com.komoju.mobile.sdk.entities.PaymentMethod.Konbini.KonbiniBrand

@Composable
internal fun KonbiniForm(
    konbini: PaymentMethod.Konbini,
    commonDisplayData: CommonDisplayData,
    konbiniDisplayData: KonbiniDisplayData,
    onCommonDisplayDataChange: (CommonDisplayData) -> Unit,
    onKonbiniDisplayDataChange: (KonbiniDisplayData) -> Unit,
    onPayButtonClicked: () -> Unit,
) {
    val displayPayableAmount by remember {
        derivedStateOf {
            AmountUtils.formatToDecimal(Currency.parse(konbini.currency), konbini.amount)
        }
    }
    Column {
        TextField(
            konbiniDisplayData.receiptName,
            title = stringResource(R.string.komoju_name_shown_on_receipt),
            placeholder = stringResource(R.string.komoju_full_name_on_receipt),
            error = konbiniDisplayData.receiptNameErrorStringResource?.let { stringResource(it) },
            onValueChange = {
                onKonbiniDisplayDataChange(konbiniDisplayData.copy(receiptName = it))
            },
        )
        TextField(
            commonDisplayData.email,
            title = stringResource(R.string.komoju_email),
            placeholder = stringResource(R.string.komoju_enter_your_email_address),
            error = konbiniDisplayData.receiptEmailErrorStringResource?.let { stringResource(it) },
            onValueChange = {
                onCommonDisplayDataChange(commonDisplayData.copy(email = it))
            },
            keyboardType = KeyboardType.Email,
        )
        Spacer(modifier = Modifier.height(8.dp))
        KonbiniBrandsRow(
            konbini.brands,
            konbiniDisplayData.selectedKonbiniBrand,
            onSelected = {
                onKonbiniDisplayDataChange(konbiniDisplayData.copy(selectedKonbiniBrand = it))
            },
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = konbiniDisplayData.konbiniBrandNullErrorStringResource?.let { stringResource(it) }.orEmpty(),
            style = TextStyle(fontSize = 16.sp, color = Red600),
        )
        PrimaryButton(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            text = stringResource(R.string.komoju_pay, displayPayableAmount),
            onClick = onPayButtonClicked,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun KonbiniFormPreview() {
    val konbini = PaymentMethod.Konbini(
        hashedGateway = "",
        exchangeRate = 0.0,
        currency = "",
        amount = "0",
        additionalFields = listOf(),
        brands = listOf(
            KonbiniBrand.SevenEleven(
                key = "seven-eleven",
                merchantNumber = "",
            ),
            KonbiniBrand.Lawson(key = "lawson"),
            KonbiniBrand.FamilyMart(key = "family-mart"),
            KonbiniBrand.MiniStop(key = "ministop"),
            KonbiniBrand.DailyYamazaki(key = "daily-yamazaki"),
            KonbiniBrand.SeicoMart(key = "seicomart"),
        ),
        customerFee = 0,
    )

    var commonDisplayData by remember { mutableStateOf(CommonDisplayData()) }
    var konbiniDisplayData by remember { mutableStateOf(KonbiniDisplayData()) }

    KomojuMobileSdkTheme {
        KonbiniForm(
            konbini,
            konbiniDisplayData = konbiniDisplayData,
            commonDisplayData = commonDisplayData,
            onCommonDisplayDataChange = {
                commonDisplayData = it
            },
            onKonbiniDisplayDataChange = {
                konbiniDisplayData = it
            },
            onPayButtonClicked = {
            },
        )
    }
}
