package com.degica.komoju.android.sdk.ui.screens.payment.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.degica.komoju.android.sdk.R
import com.degica.komoju.android.sdk.types.Currency
import com.degica.komoju.android.sdk.types.Language
import com.degica.komoju.android.sdk.ui.theme.Gray200
import com.degica.komoju.android.sdk.ui.theme.Gray500
import com.degica.komoju.android.sdk.ui.theme.KomojuMobileSdkTheme
import com.degica.komoju.android.sdk.ui.theme.LocalI18nTextsProvider
import com.degica.komoju.android.sdk.utils.AmountUtils
import com.degica.komoju.android.sdk.utils.CardScheme
import com.degica.komoju.android.sdk.utils.CreditCardUtils.formatAmex
import com.degica.komoju.android.sdk.utils.CreditCardUtils.formatDinnersClub
import com.degica.komoju.android.sdk.utils.CreditCardUtils.formatOtherCardNumbers
import com.degica.komoju.android.sdk.utils.CreditCardUtils.identifyCardScheme
import com.degica.komoju.android.sdk.utils.CreditCardUtils.makeExpirationFilter
import com.degica.komoju.mobile.sdk.entities.PaymentMethod

@Composable
internal fun CreditCardForm(
    creditCard: PaymentMethod.CreditCard,
    fullNameOnCard: String,
    onFullNameOnCardChange: (String) -> Unit,
    creditCardNumber: String,
    onCardNumberChange: (String) -> Unit,
    creditCardExpiryDate: String,
    onCardExpiryDateChange: (String) -> Unit,
    creditCardCvv: String,
    onCardCvvChange: (String) -> Unit,
    saveCard: Boolean = false,
    onSaveCardChange: (Boolean) -> Unit,
) {
    var cardScheme by remember { mutableStateOf(CardScheme.UNKNOWN) }
    var textFieldHeight by remember { mutableIntStateOf(0) }
    val displayPayableAmount by remember(creditCard.amount) {
        derivedStateOf {
            AmountUtils.formatToDecimal(Currency.parse(creditCard.currency), creditCard.amount)
        }
    }
    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = LocalI18nTextsProvider.current["CARD_HOLDER_NAME"],
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .border(1.dp, Gray200, shape = RoundedCornerShape(8.dp))
                .padding(16.dp),
        ) {
            BasicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = fullNameOnCard,
                onValueChange = onFullNameOnCardChange,
                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                singleLine = true,
            )
            if (fullNameOnCard.isEmpty()) {
                Text(
                    text = LocalI18nTextsProvider.current["FULL_NAME_ON_CARD"],
                    style = TextStyle(fontSize = 16.sp, color = Gray500),
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = LocalI18nTextsProvider.current["CARD_NUMBER"],
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .border(1.dp, Gray200, shape = RoundedCornerShape(8.dp)),
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(16.dp)
                        .onGloballyPositioned {
                            textFieldHeight = it.size.height
                        },
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        BasicTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = creditCardNumber,
                            onValueChange = {
                                if (it.length <= 16) onCardNumberChange(it)
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                            singleLine = true,
                            visualTransformation = { number ->
                                cardScheme = identifyCardScheme(creditCardNumber)
                                when (cardScheme) {
                                    CardScheme.AMEX -> formatAmex(number)
                                    CardScheme.DINERS_CLUB -> formatDinnersClub(number)
                                    else -> formatOtherCardNumbers(number)
                                }
                            },
                        )
                        if (creditCardNumber.isEmpty()) {
                            Text(
                                text = "1234 1234 1234 1234",
                                style = TextStyle(fontSize = 16.sp, color = Gray500),
                            )
                        }
                    }
                    CreditCardSchemeIcons(cardScheme)
                }

                HorizontalDivider(thickness = 1.dp, color = Gray200)
                Row(
                    modifier = Modifier
                        .height(textFieldHeight.dp)
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        BasicTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = creditCardExpiryDate,
                            onValueChange = {
                                if (it.length <= 4) onCardExpiryDateChange(it)
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                            singleLine = true,
                            visualTransformation = { number ->
                                makeExpirationFilter(number)
                            },
                        )
                        if (creditCardExpiryDate.isEmpty()) {
                            Text(
                                text = LocalI18nTextsProvider.current["EXPIRY_DATE"],
                                style = TextStyle(fontSize = 16.sp, color = Gray500),
                            )
                        }
                    }
                    VerticalDivider(thickness = 1.dp, color = Gray200)
                    Spacer(Modifier.width(16.dp))
                    Row(modifier = Modifier.weight(1f)) {
                        Box {
                            BasicTextField(
                                value = creditCardCvv,
                                onValueChange = {
                                    if (it.length <= 7) onCardCvvChange(it)
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                                singleLine = true,
                            )
                            if (creditCardCvv.isEmpty()) {
                                Text(
                                    text = LocalI18nTextsProvider.current["CVV"],
                                    style = TextStyle(fontSize = 16.sp, color = Gray500),
                                )
                            }
                        }

                        Image(painter = painterResource(R.drawable.komoju_ic_cvv), contentDescription = null, modifier = Modifier.padding(start = 16.dp))
                    }
                }
            }
        }

        PaymentButton(modifier = Modifier.padding(16.dp).fillMaxWidth(), text = "${LocalI18nTextsProvider.current["PAY"]} $displayPayableAmount") { }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(saveCard, onSaveCardChange, colors = CheckboxDefaults.colors(checkedColor = Color.Black, uncheckedColor = Color.Black))
            Text(LocalI18nTextsProvider.current["SAVE_CARD"])
        }
    }
}

@Composable
@Preview(
    showBackground = true,
)
private fun CreditCardFormPreview() {
    val creditCard = PaymentMethod.CreditCard(
        hashedGateway = "",
        exchangeRate = 0.0,
        currency = "",
        amount = 0.0,
        additionalFields = listOf(),
        brands = listOf(),
        displayName = "Credit Card",
    )
    val fullNameOnCard = remember { mutableStateOf("") }
    val creditCardNumber = remember { mutableStateOf("") }
    val creditCardExpiryDate = remember { mutableStateOf("") }
    val creditCardCvv = remember { mutableStateOf("") }
    var saveCard by remember { mutableStateOf(false) }
    KomojuMobileSdkTheme(Language.ENGLISH) {
        CreditCardForm(
            creditCard, fullNameOnCard.value,
            onFullNameOnCardChange = {
                fullNameOnCard.value = it
            },
            creditCardNumber.value,
            onCardNumberChange = {
                creditCardNumber.value = it
            },
            creditCardExpiryDate = creditCardExpiryDate.value,
            onCardExpiryDateChange = {
                creditCardExpiryDate.value = it
            },
            creditCardCvv.value,
            onCardCvvChange = {
                creditCardCvv.value = it
            },
            saveCard = saveCard,
            onSaveCardChange = {
                saveCard = it
            },
        )
    }
}
