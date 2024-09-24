package com.degica.komoju.android.sdk.ui.screens.awating

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.degica.komoju.android.sdk.R
import com.degica.komoju.android.sdk.types.Currency
import com.degica.komoju.android.sdk.ui.composables.PrimaryButton
import com.degica.komoju.android.sdk.ui.composables.TextButton
import com.degica.komoju.android.sdk.ui.screens.KomojuPaymentRoute
import com.degica.komoju.android.sdk.ui.screens.RouterEffect
import com.degica.komoju.android.sdk.ui.theme.Gray200
import com.degica.komoju.android.sdk.ui.theme.Gray50
import com.degica.komoju.android.sdk.ui.theme.Gray700
import com.degica.komoju.android.sdk.utils.AmountUtils
import com.degica.komoju.mobile.sdk.entities.Payment
import com.degica.komoju.mobile.sdk.entities.PaymentStatus

internal data class KonbiniAwaitingPaymentScreen(val route: KomojuPaymentRoute.KonbiniAwaitingPayment) : Screen {
    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { KonbiniAwaitingPaymentScreenModel(route.configuration, route.payment) }
        val uiState by screenModel.state.collectAsStateWithLifecycle()
        val router by screenModel.router.collectAsStateWithLifecycle()
        RouterEffect(router, screenModel::onRouteConsumed)
        uiState.payment?.let {
            PaymentStatus(it, onPrimaryButtonClicked = screenModel::onPrimaryButtonClicked, onSecondaryButtonClicked = screenModel::onSecondaryButtonClicked)
        }
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .background(Color.Black.copy(alpha = .1f))
                    .fillMaxSize()
                    .clickable { },
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
private fun PaymentStatus(payment: Payment, onPrimaryButtonClicked: () -> Unit, onSecondaryButtonClicked: () -> Unit) {
    val displayPayableAmount by remember(payment.amount) {
        derivedStateOf {
            AmountUtils.formatToDecimal(Currency.parse(payment.currency), payment.amount)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(painterResource(payment.icon), "status_icon")
        Spacer(modifier = Modifier.padding(16.dp))
        Text(payment.title, fontSize = 32.sp, style = TextStyle(fontWeight = FontWeight.Medium))
        Spacer(modifier = Modifier.padding(8.dp))
        Text(payment.description, fontSize = 16.sp, color = Gray700, style = TextStyle(fontWeight = FontWeight.Normal, textAlign = TextAlign.Center))
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 16.dp),
        ) {
            LazyColumn(
                modifier = Modifier.background(Gray50, RoundedCornerShape(8.dp)),
            ) {
                item {
                    InformationItem("Total Payment", displayPayableAmount)
                }
                items(payment.additionalInformation) {
                    HorizontalDivider(color = Gray200, modifier = Modifier.padding(horizontal = 16.dp))
                    InformationItem(it.first, it.second)
                }
            }
        }
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = payment.primaryButtonText,
            onClick = onPrimaryButtonClicked,
        )
        payment.secondaryButtonText?.let {
            Spacer(Modifier.height(8.dp))
            TextButton(modifier = Modifier.fillMaxWidth(), it, onSecondaryButtonClicked)
        }
    }
}

@Composable
private fun InformationItem(key: String, value: String) {
    Row(Modifier.padding(16.dp)) {
        Text(key, maxLines = 1, style = TextStyle(color = Gray700))
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = value,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun InformationItemPreview() {
    InformationItem("Total payment", "$200.00")
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun KonbiniPaymentStatusPreview() {
    val konbini = Payment.Konbini(
        status = PaymentStatus.AUTHORIZED,
        redirectURL = "",
        konbiniStoreKey = "lawson",
        email = "",
        instructionURL = "",
        amount = 110.00,
        currency = "JPY",
        receiptNumber = "123456789",
        confirmationCode = "123456",
    )
    PaymentStatus(
        konbini,
        {
        },
    ) {
    }
}

private val Payment.icon
    get() = when {
        status == PaymentStatus.COMPLETED -> R.drawable.komoju_ic_payment_status_completed
        status == PaymentStatus.FAILED -> R.drawable.komoju_ic_payment_status_failed
        this is Payment.Konbini && status == PaymentStatus.AUTHORIZED -> R.drawable.komoju_ic_payment_status_konbini_pending
        else -> R.drawable.komoju_ic_payment_status_prenidng
    }

private val Payment.title
    get() = when (status) {
        PaymentStatus.COMPLETED -> "Payment successful"
        PaymentStatus.FAILED -> "Payment failed"
        else -> "Awaiting payment"
    }

private val Payment.description
    get() = when {
        status == PaymentStatus.COMPLETED -> "Your payment has been processed successfully."
        status == PaymentStatus.FAILED -> "Your payment has failed."
        this is Payment.Konbini && status == PaymentStatus.AUTHORIZED -> "You need to go to your local ${this.konbiniStoreKey} and make the payment to proceed."
        else -> "Your payment is awaiting processing."
    }

private val Payment.additionalInformation
    get() = when {
        this is Payment.Error -> listOf("Error" to code + message)
        this is Payment.Konbini && status == PaymentStatus.AUTHORIZED -> listOfNotNull(
            receiptNumber?.let { "Receipt Number" to it },
            confirmationCode?.let { "Confirmation Code" to it },
        )

        else -> emptyList()
    }

private val Payment.primaryButtonText
    get() = when {
        status == PaymentStatus.COMPLETED -> "Done"
        status == PaymentStatus.FAILED -> "Update Payment method"
        this is Payment.Konbini && status == PaymentStatus.AUTHORIZED -> "View instructions"
        else -> "Okay"
    }

private val Payment.secondaryButtonText
    get() = when {
        status == PaymentStatus.FAILED -> "Have a question? Contact us"
        this is Payment.Konbini && status == PaymentStatus.AUTHORIZED -> "I will do it later"
        else -> null
    }
