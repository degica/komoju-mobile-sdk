package com.degica.komoju.android

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.degica.komoju.android.sdk.KomojuSDK
import com.degica.komoju.android.sdk.canProcessPayment
import com.degica.komoju.android.ui.screens.example.ExampleScreenViewModel
import kotlinx.coroutines.launch

class LegacyExampleActivity : AppCompatActivity() {
    private val viewModel by viewModels<ExampleScreenViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.komojuSDKConfiguration.collect {
                    if (it.canProcessPayment()) {
                        showPaymentScreen(it!!)
                    }
                }
            }
        }
        viewModel.onAmountChanged("100")
        viewModel.processPayment()
    }

    private fun showPaymentScreen(configuration: KomojuSDK.Configuration) {
        KomojuSDK.show(this, configuration) {
        }
    }
}
