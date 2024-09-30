package com.komoju.android.sdk.ui.screens.success

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.komoju.android.sdk.R
import com.komoju.android.sdk.types.Language
import com.komoju.android.sdk.ui.composables.PrimaryButton
import com.komoju.android.sdk.ui.theme.KomojuMobileSdkTheme
import com.komoju.android.sdk.ui.theme.LocalI18nTextsProvider

internal class PaymentSuccessScreen : Screen {
    @Composable
    override fun Content() {
        PaymentSuccessScreenContent()
    }
}

@Composable
private fun PaymentSuccessScreenContent() {
    val onBackPressDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val i18nTexts = LocalI18nTextsProvider.current
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            Icon(
                Icons.Rounded.Clear,
                "Close Button",
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        onBackPressDispatcher?.onBackPressed()
                    },
            )
        }
        Image(painterResource(R.drawable.komoju_ic_payment_status_completed), "status_icon")
        Spacer(Modifier.height(16.dp))
        Text(i18nTexts["PAYMENT_SUCCESS"], fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        Text(i18nTexts["ORDER_THANK_YOU_NOTE"])
        Spacer(Modifier.weight(1f))
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = i18nTexts["BACK_TO_STORE"],
        ) {
            onBackPressDispatcher?.onBackPressed()
        }
    }
}

@Composable
@Preview
private fun PaymentSuccessScreenContentPreview() {
    KomojuMobileSdkTheme(Language.ENGLISH) {
        PaymentSuccessScreenContent()
    }
}
