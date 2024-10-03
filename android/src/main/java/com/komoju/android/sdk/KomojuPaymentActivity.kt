package com.komoju.android.sdk

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.IntentCompat
import androidx.core.util.Consumer
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.komoju.android.sdk.navigation.PaymentResultScreenModel
import com.komoju.android.sdk.navigation.paymentResultScreenModel
import com.komoju.android.sdk.ui.screens.RouterEffect
import com.komoju.android.sdk.ui.screens.payment.KomojuPaymentScreen
import com.komoju.android.sdk.ui.theme.KomojuMobileSdkTheme
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val ANIMATION_DURATION = 500

internal class KomojuPaymentActivity : ComponentActivity() {
    private val viewModel by viewModels<KomojuPaymentViewModel>(
        factoryProducer = {
            KomojuPaymentViewModelFactory(
                configuration = IntentCompat.getParcelableExtra(
                    /* in = */
                    intent,
                    /* name = */
                    KomojuStartPaymentForResultContract.CONFIGURATION_KEY,
                    /* clazz = */
                    KomojuSDK.Configuration::class.java,
                ) ?: error("komoju sdk configuration is null"),
            )
        },
    )

    private var commonScreenModel: PaymentResultScreenModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isVisible by viewModel.isVisible.collectAsStateWithLifecycle()
            val animatedAlpha by animateFloatAsState(
                targetValue = if (isVisible) .3f else .0f,
                label = "scrim_alpha_animation",
                animationSpec = tween(durationMillis = ANIMATION_DURATION),
            )
            Surface(modifier = Modifier.fillMaxSize(), color = Color.Black.copy(alpha = animatedAlpha)) {
                AnimatedVisibility(
                    isVisible,
                    enter = slideInVertically(animationSpec = tween(ANIMATION_DURATION, easing = LinearEasing)) { it },
                    exit = slideOutVertically(
                        animationSpec = tween(ANIMATION_DURATION),
                    ) { it },
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent)
                            .navigationBarsPadding(),
                        contentAlignment = Alignment.BottomCenter,
                    ) {
                        KomojuMobileSdkTheme(viewModel.configuration) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(.9f),
                            ) {
                                Navigator(
                                    KomojuPaymentScreen(viewModel.configuration),
                                ) { navigator ->
                                    commonScreenModel = navigator.paymentResultScreenModel()
                                    SlideTransition(navigator)
                                    RouterEffect(viewModel.router.collectAsStateWithLifecycle(), viewModel::onRouteConsumed)
                                    NewIntentEffect(LocalContext.current, viewModel::onNewDeeplink)
                                }
                            }
                        }
                    }
                }
            }
            LaunchedEffect(Unit) {
                viewModel.toggleVisibility(true)
            }
        }
    }

    @Composable
    fun NewIntentEffect(context: Context, onNewDeeplink: (String) -> Unit) {
        LaunchedEffect(Unit) {
            callbackFlow {
                val componentActivity = context as ComponentActivity
                val consumer = Consumer<Intent> { trySend(it) }
                componentActivity.addOnNewIntentListener(consumer)
                awaitClose { componentActivity.removeOnNewIntentListener(consumer) }
            }.collectLatest { onNewDeeplink(it.data?.toString().orEmpty()) }
        }
    }

    override fun finish() {
        setResult(
            RESULT_OK,
            Intent().apply {
                putExtra(KomojuStartPaymentForResultContract.RESULT_KEY, commonScreenModel?.result)
            },
        )
        lifecycleScope.launch {
            viewModel.toggleVisibility(false)
            delay(ANIMATION_DURATION.toLong()) // Let the animation finish
            super.finish()
        }
    }
}
