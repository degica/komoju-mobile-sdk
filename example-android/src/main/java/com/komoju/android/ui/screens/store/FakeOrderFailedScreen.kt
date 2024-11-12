package com.komoju.android.ui.screens.store

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.komoju.android.R
import com.komoju.android.ui.theme.KomojuDarkGreen

class FakeOrderFailedScreen : Screen {
    @Composable
    override fun Content() {
        FakeOrderFailedScreenContent()
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun FakeOrderFailedScreenContent() {
    val navigator = LocalNavigator.current
    Column(
        Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(16.dp),
            painter = painterResource(R.drawable.ic_sentiment_dissatisfied_24),
            tint = Color.Red.copy(alpha = .5f),
            contentDescription = null,
        )

        Text(stringResource(R.string.payment_failed), fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(16.dp))
        Text(
            stringResource(R.string.payment_failed_description),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            Column {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = KomojuDarkGreen),
                    onClick = {
                        navigator?.pop()
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        stringResource(R.string.retry_payment),
                    )
                }
                Spacer(modifier = Modifier.padding(8.dp))
                OutlinedButton(
                    onClick = {
                        navigator?.popUntilRoot()
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        stringResource(R.string.close),
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(16.dp))
    }
}
