package com.degica.komoju.android.sdk.ui.screens.payment.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.degica.komoju.android.sdk.types.Currency
import com.degica.komoju.android.sdk.types.Language
import com.degica.komoju.android.sdk.ui.theme.KomojuMobileSdkTheme
import com.degica.komoju.android.sdk.ui.theme.LocalI18nTextsProvider
import com.degica.komoju.android.sdk.utils.AmountUtils
import com.degica.komoju.mobile.sdk.entities.PaymentMethod
import com.degica.komoju.mobile.sdk.entities.PaymentMethod.Konbini.KonbiniBrand

@Composable
internal fun KonbiniForm(
    konbini: PaymentMethod.Konbini,
    receiptName: String,
    onReceiptNameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    selectedKonbiniBrand: KonbiniBrand?,
    onKonbiniBrandChange: (KonbiniBrand) -> Unit,
) {
    val displayPayableAmount by remember {
        derivedStateOf {
            AmountUtils.formatToDecimal(Currency.parse(konbini.currency), konbini.amount)
        }
    }
    Column {
        TextField(
            receiptName,
            "NAME_SHOWN_ON_RECEIPT",
            "FULL_NAME_ON_RECEIPT",
            onReceiptNameChange,
        )
        TextField(
            email,
            "EMAIL",
            "EXAMPLE_EMAIL",
            onEmailChange,
        )
        Spacer(modifier = Modifier.height(8.dp))
        KonbiniBrandsRow(konbini.brands, selectedKonbiniBrand, onKonbiniBrandChange)
        PaymentButton(modifier = Modifier.padding(16.dp).fillMaxWidth(), text = "${LocalI18nTextsProvider.current["PAY"]} $displayPayableAmount") { }
    }
}

@Composable
@Preview(showBackground = true)
private fun KonbiniFormPreview() {
    val konbini = PaymentMethod.Konbini(
        hashedGateway = "",
        exchangeRate = 0.0,
        currency = "",
        amount = 0.0,
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
        displayName = "Konbini",
        customerFee = 0,
    )
    var reciptName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    KomojuMobileSdkTheme(language = Language.ENGLISH) {
        KonbiniForm(
            konbini,
            reciptName,
            onReceiptNameChange = {
                reciptName = it
            },
            email,
            onEmailChange = {
                email = it
            },
            selectedKonbiniBrand = konbini.brands.first(),
            onKonbiniBrandChange = {
            },
        )
    }
}
