package com.degica.komoju.android.ui.screens.example

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.degica.komoju.android.R
import com.degica.komoju.android.sdk.KomojuSDK
import com.degica.komoju.android.sdk.canProcessPayment
import com.degica.komoju.android.sdk.types.Currency
import com.degica.komoju.android.sdk.types.Language
import com.degica.komoju.android.ui.theme.KomojuDarkGreen
import com.degica.komoju.android.ui.theme.KomojuLightGreen

@Composable
@Preview
fun ExampleScreen() {
    val viewModel = viewModel<ExampleScreenViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    val komojuSDKConfiguration by viewModel.komojuSDKConfiguration.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(komojuSDKConfiguration) {
        if (komojuSDKConfiguration?.canProcessPayment() == true) {
            KomojuSDK.show(context, komojuSDKConfiguration!!)
        }
    }
    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            viewModel.onErrorShown()
        }
    }
    Scaffold {
        Box {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top,
            ) {
                Image(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, top = 54.dp)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(R.drawable.logo_komoju),
                    contentDescription = "Komoju",
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Payment SDK Example",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = KomojuLightGreen,
                    style = LocalTextStyle.current.copy(fontWeight = FontWeight.Medium),
                )
                Spacer(modifier = Modifier.height(54.dp))

                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = "Select Language",
                    style = LocalTextStyle.current.copy(fontWeight = FontWeight.Bold),
                )
                Options(
                    Language.entries.toList(),
                    uiState.selectedLanguage,
                    viewModel::onLanguageChanged,
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = "Select Currency",
                    style = LocalTextStyle.current.copy(fontWeight = FontWeight.Bold),
                )

                Options(
                    Currency.entries.toList(),
                    uiState.selectedCurrency,
                    viewModel::onCurrencyChanged,
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = "Enter Amount to Pay",
                    style = LocalTextStyle.current.copy(fontWeight = FontWeight.Bold),
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    value = uiState.amount,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    placeholder = {
                        Text("Enter Amount")
                    },
                    onValueChange = viewModel::onAmountChanged,
                )

                Button(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp),
                    onClick = viewModel::processPayment,
                    colors = ButtonDefaults.buttonColors().copy(containerColor = KomojuDarkGreen),
                ) {
                    Text("Checkout!")
                }
            }
            if (uiState.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(false) {} // Block click through
                        .background(Color.Black.copy(alpha = 0.5f)),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(color = KomojuLightGreen)
                }
            }
        }
    }
}

@Composable
private fun <T> Options(options: List<T>, selected: T, onOptionSelected: (T) -> Unit) {
    Column {
        options.forEach { option ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selected == option,
                    onClick = {
                        onOptionSelected(option)
                    },
                    enabled = true,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = KomojuLightGreen,
                    ),
                )
                Text(text = option.toString())
            }
        }
    }
}
