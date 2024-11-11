package com.komoju.mobile.sdk.ui.screens.payment.composables

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.komoju.mobile.sdk.entities.PaymentMethod
import com.komoju.mobile.sdk.i18n.I18nStringKey
import com.komoju.mobile.sdk.i18n.i18nStringResource
import com.komoju.mobile.sdk.ui.composables.InlinedPaymentPrimaryButton
import com.komoju.mobile.sdk.ui.composables.PrimaryButton
import com.komoju.mobile.sdk.ui.icon.Cvv
import com.komoju.mobile.sdk.ui.icon.KomojuIcon
import com.komoju.mobile.sdk.ui.screens.payment.CreditCardDisplayData
import com.komoju.mobile.sdk.ui.theme.Gray200
import com.komoju.mobile.sdk.ui.theme.Gray500
import com.komoju.mobile.sdk.ui.theme.KomojuMobileSdkTheme
import com.komoju.mobile.sdk.ui.theme.Red600
import com.komoju.mobile.sdk.utils.AmountUtils
import com.komoju.mobile.sdk.utils.CardScheme
import com.komoju.mobile.sdk.utils.CreditCardUtils.formatAmex
import com.komoju.mobile.sdk.utils.CreditCardUtils.formatDinnersClub
import com.komoju.mobile.sdk.utils.CreditCardUtils.formatOtherCardNumbers
import com.komoju.mobile.sdk.utils.CreditCardUtils.identifyCardScheme
import com.komoju.mobile.sdk.utils.CreditCardUtils.makeExpirationFilter
import com.komoju.mobile.sdk.utils.testID
import org.jetbrains.compose.ui.tooling.preview.Preview

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
            AmountUtils.formatToDecimal(creditCard.currency, creditCard.amount)
        }
    }
    val dividerColor = if (creditCardDisplayData.creditCardErrorI18nStringKey == null) Gray200 else Red600
    Column {
        TextField(
            creditCardDisplayData.fullNameOnCard,
            title = i18nStringResource(I18nStringKey.cardholder_name),
            placeholder = i18nStringResource(I18nStringKey.full_name_on_card),
            capitalization = KeyboardCapitalization.Characters,
            error = creditCardDisplayData.fullNameOnCardErrorI18nStringKey?.let { i18nStringResource(it) },
            onValueChange = {
                onCreditCardDisplayDataChange(creditCardDisplayData.copy(fullNameOnCard = it.toUpperCase(Locale.current)))
            },
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = i18nStringResource(I18nStringKey.card_number),
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
                                text = i18nStringResource(I18nStringKey.mm_yy),
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
                                    text = i18nStringResource(I18nStringKey.cvv),
                                    style = TextStyle(fontSize = 16.sp, color = Gray500),
                                )
                            }
                        }

                        Image(
                            painter = rememberVectorPainter(KomojuIcon.Cvv),
                            contentDescription = null,
                            modifier = Modifier.padding(start = 16.dp),
                        )
                    }
                }
            }
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = creditCardDisplayData.creditCardErrorI18nStringKey?.let { i18nStringResource(it) }.orEmpty(),
            style = TextStyle(fontSize = 16.sp, color = Red600),
        )

        Spacer(modifier = Modifier.height(8.dp))
        if (creditCardDisplayData.inlinePaymentEnabled) {
            InlinedPaymentPrimaryButton(
                modifier = Modifier
                    .testID("credit_card_pay")
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                text = i18nStringResource(I18nStringKey.pay, displayPayableAmount),
                onClick = onPayButtonClicked,
                state = creditCardDisplayData.inlinedPaymentPrimaryButtonState,
            )
        } else {
            PrimaryButton(
                modifier = Modifier
                    .testID("credit_card_pay")
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                text = i18nStringResource(I18nStringKey.pay, displayPayableAmount),
                onClick = onPayButtonClicked,
            )
        }

        if (creditCardDisplayData.canSaveCard) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 8.dp)) {
                Checkbox(
                    creditCardDisplayData.saveCard,
                    onCheckedChange = {
                        onCreditCardDisplayDataChange(creditCardDisplayData.copy(saveCard = it))
                    },
                    colors = CheckboxDefaults.colors(checkedColor = Color.Black, uncheckedColor = Color.Black),
                )
                Text(i18nStringResource(I18nStringKey.save_this_card_for_future_payments))
            }
        }
    }
}

@Composable
@Preview
private fun CreditCardFormPreview() {
    val creditCard = PaymentMethod.CreditCard(
        hashedGateway = "",
        exchangeRate = 0.0,
        currency = "",
        amount = "0",
        additionalFields = listOf(),
        brands = listOf(),
    )
    var creditCardDisplayData by remember { mutableStateOf(CreditCardDisplayData()) }
    KomojuMobileSdkTheme {
        CreditCardForm(
            creditCard,
            creditCardDisplayData = creditCardDisplayData,
            onCreditCardDisplayDataChange = {
                creditCardDisplayData = it
            },
            onPayButtonClicked = {},
        )
    }
}
