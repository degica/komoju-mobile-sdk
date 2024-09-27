package com.degica.komoju.android

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.degica.komoju.android.ui.screens.store.FakeStoreScreen
import com.degica.komoju.android.ui.theme.KomojuFakeStoreTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalVoyagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.BLACK, Color.BLACK),
            navigationBarStyle = SystemBarStyle.light(Color.BLACK, Color.BLACK),
        )
        setContent {
            KomojuFakeStoreTheme {
                Scaffold {
                    Box(modifier = Modifier.padding(it).fillMaxSize()) {
                        Navigator(FakeStoreScreen()) { navigator ->
                            SlideTransition(navigator, disposeScreenAfterTransitionEnd = true)
                        }
                    }
                }
            }
        }
    }
}
