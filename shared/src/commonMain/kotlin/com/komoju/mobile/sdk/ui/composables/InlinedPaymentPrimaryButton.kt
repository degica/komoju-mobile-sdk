package com.komoju.mobile.sdk.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.komoju.mobile.sdk.ui.theme.KomojuMobileSdkTheme
import com.komoju.mobile.sdk.ui.theme.LocalConfigurableTheme
import com.komoju.mobile.sdk.ui.theme.toColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Duration.Companion.seconds

@Composable
internal fun InlinedPaymentPrimaryButton(
    text: String,
    state: InlinedPaymentPrimaryButtonState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val configurableTheme = LocalConfigurableTheme.current
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = configurableTheme.primaryColor.toColor(),
            contentColor = configurableTheme.primaryContentColor.toColor(),
        ),
        shape = RoundedCornerShape(configurableTheme.primaryShapeCornerRadiusInDp.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(38.dp),
            contentAlignment = Alignment.Center,
        ) {
            when (state) {
                InlinedPaymentPrimaryButtonState.LOADING -> CircularProgressIndicator(
                    strokeWidth = 2.dp,
                    color = LocalContentColor.current,
                    modifier = Modifier.size(24.dp),
                )
                InlinedPaymentPrimaryButtonState.IDLE -> Text(
                    modifier = Modifier.padding(8.dp),
                    text = text,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    maxLines = 1,
                )
                InlinedPaymentPrimaryButtonState.SUCCESS -> Icon(
                    Icons.Rounded.CheckCircle,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                )
                InlinedPaymentPrimaryButtonState.ERROR -> Icon(
                    Icons.Rounded.Close,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                )
            }
        }
    }
}

enum class InlinedPaymentPrimaryButtonState {
    LOADING,
    IDLE,
    SUCCESS,
    ERROR,
}

@Composable
fun rememberInlinedPaymentPrimaryButtonState(
    default: InlinedPaymentPrimaryButtonState = InlinedPaymentPrimaryButtonState.IDLE,
): MutableState<InlinedPaymentPrimaryButtonState> = rememberSaveable { mutableStateOf(default) }

@Composable
@Preview
private fun PaymentButtonPreview() {
    var state by rememberInlinedPaymentPrimaryButtonState()
    val coroutineScope = rememberCoroutineScope()
    KomojuMobileSdkTheme {
        InlinedPaymentPrimaryButton(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            state = state,
            text = "Pay \$100.00",
        ) {
            if (state == InlinedPaymentPrimaryButtonState.IDLE) {
                coroutineScope.launch {
                    state = InlinedPaymentPrimaryButtonState.LOADING
                    delay(2.seconds)
                    state = InlinedPaymentPrimaryButtonState.SUCCESS
                    delay(2.seconds)
                    state = InlinedPaymentPrimaryButtonState.ERROR
                    delay(2.seconds)
                    state = InlinedPaymentPrimaryButtonState.IDLE
                }
            }
        }
    }
}
