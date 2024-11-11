package com.komoju.mobile.sdk.ui.screens.awating

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.komoju.mobile.sdk.entities.Payment
import com.komoju.mobile.sdk.entities.PaymentStatus
import com.komoju.mobile.sdk.ui.composables.PrimaryButton
import com.komoju.mobile.sdk.ui.composables.TextButton
import com.komoju.mobile.sdk.ui.composables.ThemedCircularProgressIndicator
import com.komoju.mobile.sdk.ui.screens.KomojuPaymentRoute
import com.komoju.mobile.sdk.ui.screens.RouterEffect
import com.komoju.mobile.sdk.ui.theme.Gray200
import com.komoju.mobile.sdk.ui.theme.Gray50
import com.komoju.mobile.sdk.ui.theme.Gray700
import com.komoju.mobile.sdk.utils.AmountUtils
import komoju_mobile_sdk.shared.generated.resources.Res
import komoju_mobile_sdk.shared.generated.resources.komoju_awaiting_payment
import komoju_mobile_sdk.shared.generated.resources.komoju_awaiting_payment_instruction
import komoju_mobile_sdk.shared.generated.resources.komoju_confirmation_code
import komoju_mobile_sdk.shared.generated.resources.komoju_done
import komoju_mobile_sdk.shared.generated.resources.komoju_error
import komoju_mobile_sdk.shared.generated.resources.komoju_have_a_question_contact_us
import komoju_mobile_sdk.shared.generated.resources.komoju_i_will_do_it_later
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_payment_status_completed
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_payment_status_failed
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_payment_status_konbini_pending
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_payment_status_prenidng
import komoju_mobile_sdk.shared.generated.resources.komoju_okay
import komoju_mobile_sdk.shared.generated.resources.komoju_payment_failed
import komoju_mobile_sdk.shared.generated.resources.komoju_payment_successful
import komoju_mobile_sdk.shared.generated.resources.komoju_receipt_number
import komoju_mobile_sdk.shared.generated.resources.komoju_total_payment
import komoju_mobile_sdk.shared.generated.resources.komoju_update_payment_method
import komoju_mobile_sdk.shared.generated.resources.komoju_view_instructions
import komoju_mobile_sdk.shared.generated.resources.komoju_your_payment_has_been_processed_successfully
import komoju_mobile_sdk.shared.generated.resources.komoju_your_payment_has_failed
import komoju_mobile_sdk.shared.generated.resources.komoju_your_payment_is_awaiting_processing
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

internal data class KonbiniAwaitingPaymentScreen(val route: KomojuPaymentRoute.KonbiniAwaitingPayment) : Screen {
    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { KonbiniAwaitingPaymentScreenModel(route.payment) }
        val uiState by screenModel.state.collectAsStateWithLifecycle()
        RouterEffect(screenModel.router.collectAsStateWithLifecycle(), screenModel::onRouteConsumed)
        uiState.payment?.let {
            ProcessForPaymentStatus(
                payment = it,
                onPrimaryButtonClicked = {
                    screenModel.onPrimaryButtonClicked(route.configuration)
                },
                onSecondaryButtonClicked = screenModel::onSecondaryButtonClicked,
            )
        }
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .background(Color.Black.copy(alpha = .1f))
                    .fillMaxSize()
                    .clickable { },
                contentAlignment = Alignment.Center,
            ) {
                ThemedCircularProgressIndicator()
            }
        }
    }
}

@Composable
private fun ProcessForPaymentStatus(payment: Payment, onPrimaryButtonClicked: () -> Unit, onSecondaryButtonClicked: () -> Unit) {
    val displayPayableAmount by remember(payment.amount) {
        derivedStateOf {
            AmountUtils.formatToDecimal(payment.currency, payment.amount)
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
        Text(
            text = payment.description,
            fontSize = 16.sp,
            color = Gray700,
            style = TextStyle(fontWeight = FontWeight.Normal, textAlign = TextAlign.Center),
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 16.dp),
        ) {
            Column(modifier = Modifier.background(Gray50, RoundedCornerShape(8.dp))) {
                InformationItem(title = stringResource(Res.string.komoju_total_payment), displayPayableAmount)
                payment.additionalInformation.forEach {
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
private fun InformationItem(title: String, description: String) {
    Row(Modifier.padding(16.dp)) {
        Text(title, maxLines = 1, style = TextStyle(color = Gray700))
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = description,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
        )
    }
}

private val Payment.icon
    get() = when {
        status == PaymentStatus.COMPLETED -> Res.drawable.komoju_ic_payment_status_completed
        status == PaymentStatus.FAILED -> Res.drawable.komoju_ic_payment_status_failed
        this is Payment.Konbini && status == PaymentStatus.AUTHORIZED -> Res.drawable.komoju_ic_payment_status_konbini_pending
        else -> Res.drawable.komoju_ic_payment_status_prenidng
    }

private val Payment.title
    @Composable
    get() = when (status) {
        PaymentStatus.COMPLETED -> stringResource(Res.string.komoju_payment_successful)
        PaymentStatus.FAILED -> stringResource(Res.string.komoju_payment_failed)
        else -> stringResource(Res.string.komoju_awaiting_payment)
    }

private val Payment.description
    @Composable
    get() = when {
        status == PaymentStatus.COMPLETED -> stringResource(Res.string.komoju_your_payment_has_been_processed_successfully)
        status == PaymentStatus.FAILED -> stringResource(Res.string.komoju_your_payment_has_failed)
        this is Payment.Konbini && status == PaymentStatus.AUTHORIZED ->
            stringResource(Res.string.komoju_awaiting_payment_instruction, this.konbiniStoreKey)
        else -> stringResource(Res.string.komoju_your_payment_is_awaiting_processing)
    }

private val Payment.additionalInformation
    @Composable
    get() = when {
        this is Payment.Error -> listOf(stringResource(Res.string.komoju_error) to code + message)
        this is Payment.Konbini && status == PaymentStatus.AUTHORIZED -> listOfNotNull(
            receiptNumber?.let { stringResource(Res.string.komoju_receipt_number) to it },
            confirmationCode?.let { stringResource(Res.string.komoju_confirmation_code) to it },
        )

        else -> emptyList()
    }

private val Payment.primaryButtonText
    @Composable
    get() = when {
        status == PaymentStatus.COMPLETED -> stringResource(Res.string.komoju_done)
        status == PaymentStatus.FAILED -> stringResource(Res.string.komoju_update_payment_method)
        this is Payment.Konbini && status == PaymentStatus.AUTHORIZED -> stringResource(Res.string.komoju_view_instructions)
        else -> stringResource(Res.string.komoju_okay)
    }

private val Payment.secondaryButtonText
    @Composable
    get() = when {
        status == PaymentStatus.FAILED -> stringResource(Res.string.komoju_have_a_question_contact_us)
        this is Payment.Konbini && status == PaymentStatus.AUTHORIZED -> stringResource(Res.string.komoju_i_will_do_it_later)
        else -> null
    }
