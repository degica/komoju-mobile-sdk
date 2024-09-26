package com.degica.komoju.android.ui.screens.store

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.model.rememberNavigatorScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.degica.komoju.android.R
import com.degica.komoju.android.sdk.KomojuSDK
import com.degica.komoju.android.sdk.canProcessPayment
import com.degica.komoju.android.ui.theme.KomojuDarkGreen

data class FakeItemDetailScreen(private val index: Int) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val backPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
        val screenModel = navigator.rememberNavigatorScreenModel { FakeStoreScreenModel() }
        val uiState by screenModel.uiState.collectAsStateWithLifecycle()
        val item by remember { derivedStateOf { uiState.items[index] } }
        val komojuSDKConfiguration by screenModel.komojuSDKConfiguration.collectAsStateWithLifecycle()
        val komojuPaymentLauncher = rememberLauncherForActivityResult(KomojuSDK.KomojuPaymentResultContract) {
            screenModel.onKomojuPaymentCompleted()
        }
        LaunchedEffect(komojuSDKConfiguration) {
            val configuration = komojuSDKConfiguration
            if (configuration.canProcessPayment()) {
                komojuPaymentLauncher.launch(configuration)
            }
        }

        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = rememberScrollState()),
            ) {
                Box {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .padding(8.dp)
                            .clip(RoundedCornerShape(24.dp)),
                        painter = painterResource(item.imageID),
                        contentDescription = "",
                    )
                    Icon(
                        modifier = Modifier
                            .padding(16.dp)
                            .clip(CircleShape)
                            .clickable {
                                backPressedDispatcher?.onBackPressed()
                            }
                            .background(Color.LightGray.copy(alpha = .2f), CircleShape)
                            .padding(12.dp)
                            .align(Alignment.TopStart),
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "",
                        tint = Color.DarkGray,
                    )
                    Icon(
                        modifier = Modifier
                            .padding(16.dp)
                            .clip(CircleShape)
                            .clickable {
                                screenModel.onItemChanged(item.copy(isFavorite = item.isFavorite.not()))
                            }
                            .background(Color.LightGray.copy(alpha = .2f), CircleShape)
                            .padding(12.dp)
                            .align(Alignment.TopEnd),
                        imageVector = if (item.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "",
                        tint = if (item.isFavorite) Color.Red else Color.DarkGray,
                    )
                }

                Text(
                    "¥" + item.price + ".00",
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp),
                )
                Text(
                    stringResource(item.name),
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 16.sp,
                    modifier = Modifier.padding(horizontal = 15.dp),
                )
                Text(
                    stringResource(R.string.model) + ": " + item.model + ", " + stringResource(item.color),
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    stringResource(item.description),
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .weight(1f),
                    lineHeight = 16.sp,
                )
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = KomojuDarkGreen),
                    onClick = {
                        screenModel.onBuyClicked(item)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Text(
                        "Buy this Item",
                    )
                }
            }
            if (uiState.isCreatingSession) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray.copy(alpha = .1f))
                        .clickable {},
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}
