package com.cold0.crier.social

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import com.cold0.crier.social.screens.MainScreen
import com.cold0.crier.social.theme.CrierSocialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrierSocialTheme {
                MainScreen()
            }
        }
    }
}