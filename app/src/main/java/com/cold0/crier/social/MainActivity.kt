package com.cold0.crier.social

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.cold0.crier.social.theme.CrierSocialTheme
import com.cold0.crier.social.ui.MainScreen

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		installSplashScreen()
		setContent {
			CrierSocialTheme {
				MainScreen()
			}
		}
	}
}