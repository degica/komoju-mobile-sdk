package com.komoju.android.sdk.ui.screens.payment.composables

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.komoju.android.sdk.R
import com.komoju.android.sdk.types.Currency
import com.komoju.android.sdk.ui.composables.PrimaryButton
import com.komoju.android.sdk.ui.screens.payment.CreditCardDisplayData
import com.komoju.android.sdk.ui.theme.Gray200
import com.komoju.android.sdk.ui.theme.Gray500
import com.komoju.android.sdk.ui.theme.KomojuMobileSdkTheme
import com.komoju.android.sdk.ui.theme.LocalI18nTexts
import com.komoju.android.sdk.ui.theme.Red600
import com.komoju.android.sdk.utils.AmountUtils
import com.komoju.android.sdk.utils.CardScheme
import com.komoju.android.sdk.utils.CreditCardUtils.formatAmex
import com.komoju.android.sdk.utils.CreditCardUtils.formatDinnersClub
import com.komoju.android.sdk.utils.CreditCardUtils.formatOtherCardNumbers
import com.komoju.android.sdk.utils.CreditCardUtils.identifyCardScheme
import com.komoju.android.sdk.utils.CreditCardUtils.makeExpirationFilter
import com.komoju.android.sdk.utils.testID
import com.komoju.mobile.sdk.entities.PaymentMethod

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun CreditCardForm(
    creditCard: PaymentMethod.CreditCard,
    creditCardDisplayData: CreditCardDisplayData,
    onCreditCardDisplayDataChange: (CreditCardDisplayData) -> Unit,
    onPayButtonClicked: () -> Unit,
) {
    var cardScheme by remember { mutableStateOf(CardScheme.UNKNOWN) }
    var expiryCvvExpiryHeight by remember { mutableStateOf(0.dp) }
    val localDensity = LocalDensity.current
    val displayPayableAmount by remember(creditCard.amount) {
        derivedStateOf {
            AmountUtils.formatToDecimal(Currency.parse(creditCard.currency), creditCard.amount)
        }
    }
    val dividerColor = if (creditCardDisplayData.creditCardError == null) Gray200 else Red600
    Column {
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            creditCardDisplayData.fullNameOnCard,
            titleKey = "CARD_HOLDER_NAME",
            placeholderKey = "FULL_NAME_ON_CARD",
            capitalization = KeyboardCapitalization.Characters,
            error = creditCardDisplayData.fullNameOnCardError,
            onValueChange = {
                onCreditCardDisplayDataChange(creditCardDisplayData.copy(fullNameOnCard = it.toUpperCase(Locale.current)))
            },
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = LocalI18nTexts.current["CARD_NUMBER"],
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .border(1.dp, dividerColor, shape = RoundedCornerShape(8.dp))
                .onGloballyPositioned {
                    expiryCvvExpiryHeight = with(localDensity) {
                        it.size.height
                            .div(2)
                            .toDp()
                    }
                },
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(16.dp),
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        BasicTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = creditCardDisplayData.creditCardNumber,
                            onValueChange = {
                                onCreditCardDisplayDataChange(creditCardDisplayData.copy(creditCardNumber = it))
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                            singleLine = true,
                            visualTransformation = { number ->
                                cardScheme = identifyCardScheme(creditCardDisplayData.creditCardNumber)
                                when (cardScheme) {
                                    CardScheme.AMEX -> formatAmex(number)
                                    CardScheme.DINERS_CLUB -> formatDinnersClub(number)
                                    else -> formatOtherCardNumbers(number)
                                }
                            },
                        )
                        if (creditCardDisplayData.creditCardNumber.isEmpty()) {
                            Text(
                                text = "1234 1234 1234 1234",
                                style = TextStyle(fontSize = 16.sp, color = Gray500),
                            )
                        }
                    }
                    CreditCardSchemeIcons(cardScheme)
                }

                HorizontalDivider(thickness = 1.dp, color = dividerColor)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(16.dp),
                    ) {
                        BasicTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = creditCardDisplayData.creditCardExpiryDate,
                            onValueChange = {
                                onCreditCardDisplayDataChange(creditCardDisplayData.copy(creditCardExpiryDate = it))
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                            singleLine = true,
                            visualTransformation = { number ->
                                makeExpirationFilter(number)
                            },
                        )
                        if (creditCardDisplayData.creditCardExpiryDate.isEmpty()) {
                            Text(
                                text = LocalI18nTexts.current["EXPIRY_DATE"],
                                style = TextStyle(fontSize = 16.sp, color = Gray500),
                            )
                        }
                    }
                    VerticalDivider(thickness = 1.dp, color = dividerColor, modifier = Modifier.height(expiryCvvExpiryHeight))
                    Spacer(Modifier.width(16.dp))
                    Row(modifier = Modifier.weight(1f)) {
                        Box {
                            BasicTextField(
                                value = creditCardDisplayData.creditCardCvv,
                                onValueChange = {
                                    onCreditCardDisplayDataChange(creditCardDisplayData.copy(creditCardCvv = it))
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                                singleLine = true,
                            )
                            if (creditCardDisplayData.creditCardCvv.isEmpty()) {
                                Text(
                                    text = LocalI18nTexts.current["CVV"],
                                    style = TextStyle(fontSize = 16.sp, color = Gray500),
                                )
                            }
                        }

                        Image(painter = painterResource(R.drawable.komoju_ic_cvv), contentDescription = null, modifier = Modifier.padding(start = 16.dp))
                    }
                }
            }
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = creditCardDisplayData.creditCardError.orEmpty(),
            style = TextStyle(fontSize = 16.sp, color = Red600),
        )

        Spacer(modifier = Modifier.height(8.dp))
        PrimaryButton(
            modifier = Modifier.testID("credit_card_pay")
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            text = "${LocalI18nTexts.current["PAY"]} $displayPayableAmount",
            onClick = onPayButtonClicked,
        )

        if (creditCardDisplayData.canSaveCard) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 8.dp)) {
                Checkbox(
                    creditCardDisplayData.saveCard,
                    onCheckedChange = {
                        onCreditCardDisplayDataChange(creditCardDisplayData.copy(saveCard = it))
                    },
                    colors = CheckboxDefaults.colors(checkedColor = Color.Black, uncheckedColor = Color.Black),
                )
                Text(LocalI18nTexts.current["SAVE_CARD"])
            }
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
        amount = "0",
        additionalFields = listOf(),
        brands = listOf(),
        displayName = "Credit Card",
    )
    var creditCardDisplayData by remember { mutableStateOf(CreditCardDisplayData()) }
    KomojuMobileSdkTheme {
        CreditCardForm(
            creditCard,
            creditCardDisplayData = creditCardDisplayData,
            onCreditCardDisplayDataChange = {
                creditCardDisplayData = it
            },
            onPayButtonClicked = {
            },
        )
    }
}
