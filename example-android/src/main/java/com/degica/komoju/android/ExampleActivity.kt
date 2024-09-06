package com.degica.komoju.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.degica.komoju.android.ui.screens.example.ExampleScreen
import com.degica.komoju.android.ui.theme.KomojuMobileSdkDemoTheme

class ExampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KomojuMobileSdkDemoTheme {
                ExampleScreen()
            }
        }
    }
}
