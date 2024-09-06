package com.degica.komoju.android.sdk.ui.screens.payment.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.degica.komoju.mobile.sdk.entities.PaymentMethod

@Composable
internal fun PaymentMethodForm(paymentMethod: PaymentMethod) {
    var cardHolderName by remember { mutableStateOf("") }
    var creditCardNumber by remember { mutableStateOf("") }
    var creditCardExpiryDate by remember { mutableStateOf("") }
    var creditCardCvv by remember { mutableStateOf("") }
    var saveCard by remember { mutableStateOf(false) }

    var konbiniReceiptName by remember { mutableStateOf("") }
    var konbiniBrand by remember { mutableStateOf<PaymentMethod.Konbini.KonbiniBrand?>(null) }
    var userEmailAddress by remember { mutableStateOf("") }

    when (paymentMethod) {
        is PaymentMethod.CreditCard -> CreditCardForm(
            creditCard = paymentMethod,
            fullNameOnCard = cardHolderName,
            onFullNameOnCardChange = {
                cardHolderName = it
            },
            creditCardNumber = creditCardNumber,
            onCardNumberChange = {
                creditCardNumber = it
            },
            creditCardExpiryDate = creditCardExpiryDate,
            onCardExpiryDateChange = {
                creditCardExpiryDate = it
            },
            creditCardCvv = creditCardCvv,
            onCardCvvChange = {
                creditCardCvv = it
            },
            saveCard = saveCard,
            onSaveCardChange = {
                saveCard = it
            },
        )

        is PaymentMethod.Konbini -> KonbiniForm(
            konbini = paymentMethod,
            receiptName = konbiniReceiptName,
            onReceiptNameChange = {
                konbiniReceiptName = it
            },
            email = userEmailAddress,
            onEmailChange = {
                userEmailAddress = it
            },
            selectedKonbiniBrand = konbiniBrand ?: paymentMethod.brands.firstOrNull(),
            onKonbiniBrandChange = {
                konbiniBrand = it
            },
        )

        else -> Box(modifier = Modifier.height(420.dp).fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text("// TODO")
        }
    }
}
