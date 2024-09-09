package com.degica.komoju.android.sdk

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.content.IntentCompat
import com.degica.komoju.android.sdk.ui.screens.KomojuPaymentScreenNavHost
import com.degica.komoju.android.sdk.ui.theme.KomojuMobileSdkTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val ANIMATION_DURATION = 400

internal class KomojuPaymentActivity : ComponentActivity() {

    private val configuration: KomojuSDK.Configuration by lazy {
        IntentCompat.getParcelableExtra(
            /* in = */ intent,
            /* name = */ KomojuSDK.CONFIGURATION_KEY,
            /* clazz = */ KomojuSDK.Configuration::class.java,
        ) ?: error("komoju sdk configuration is null")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isClosed by remember { mutableStateOf(true) }
            val animatedAlpha by animateFloatAsState(
                targetValue = if (isClosed) .0f else .3f,
                label = "scrim_alpha_animation",
                animationSpec = tween(durationMillis = ANIMATION_DURATION),
            )
            val coroutineScope = rememberCoroutineScope()
            Surface(modifier = Modifier.fillMaxSize(), color = Color.Black.copy(alpha = animatedAlpha)) {
                AnimatedVisibility(
                    isClosed.not(), enter = slideInVertically(animationSpec = tween(ANIMATION_DURATION)) { it },
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
                            KomojuPaymentScreenNavHost(
                                configuration,
                                onCompleted = {
                                    coroutineScope.launch {
                                        isClosed = true
                                        delay(ANIMATION_DURATION.toLong()) // Let the animation finish
                                        finish()
                                    }
                                },
                            )
                        }
                    }
                }
            }
            LaunchedEffect(Unit) {
                isClosed = false
            }
        }
    }

    override fun finish() {
        super.finish()
        // TODO: Set Result
    }

    @SuppressLint("MissingSuperCall")
    @Deprecated(
        "This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.",
    )
    override fun onBackPressed() {
        // super.onBackPressed()
    }
}
