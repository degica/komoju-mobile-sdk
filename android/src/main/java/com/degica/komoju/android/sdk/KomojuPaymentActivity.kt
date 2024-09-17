package com.degica.komoju.android.sdk

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.content.IntentCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.degica.komoju.android.sdk.ui.screens.payment.KomojuPaymentScreen
import com.degica.komoju.android.sdk.ui.theme.KomojuMobileSdkTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val ANIMATION_DURATION = 500

internal class KomojuPaymentViewModel : ViewModel() {
    private val _isVisible = MutableStateFlow(false)
    val isVisible = _isVisible.asStateFlow()

    fun toggleVisiblity(value: Boolean){
        _isVisible.value = value
    }
}

internal class KomojuPaymentActivity : ComponentActivity() {
    private val viewModel by viewModels<KomojuPaymentViewModel>()

    private val configuration: KomojuSDK.Configuration by lazy {
        IntentCompat.getParcelableExtra(
            /* in = */
            intent,
            /* name = */
            KomojuSDK.CONFIGURATION_KEY,
            /* clazz = */
            KomojuSDK.Configuration::class.java,
        ) ?: error("komoju sdk configuration is null")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isVisible by viewModel.isVisible.collectAsState()
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
                        KomojuMobileSdkTheme(configuration.language) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(.9f),
                            ) {
                                Navigator(
                                    KomojuPaymentScreen(configuration),
                                ) { navigator ->
                                    SlideTransition(navigator)
                                }
                            }
                        }
                    }
                }
            }
            LaunchedEffect(Unit) {
                viewModel.toggleVisiblity(true)
            }
        }
    }

    private fun handleIntentAction(intent: Intent, navigator: Navigator) {
        // Handle onNewIntent
    }

    override fun finish() {
        lifecycleScope.launch {
            viewModel.toggleVisiblity(false)
            delay(ANIMATION_DURATION.toLong()) // Let the animation finish
            super.finish()
        }
        // TODO: Set Result
    }
}
