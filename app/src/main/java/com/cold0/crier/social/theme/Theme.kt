package com.cold0.crier.social.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
	// Main Colors
	primary = Color(0xFFB33737),
	primaryContainer = Color(0xFF510000),

	secondary = Color(0xFFFF8282),
	secondaryContainer = Color(0xFFCB4D4D),

	// Surfaces Colors
	background = Color(0xFF181818),
	surface = Color(0xFF181818),
	error = Color(0xFFFF0000),

	onPrimary = Color.White,
	onSecondary = Color.Black,
	onBackground = Color.White,
	onSurface = Color.White,
	onError = Color.White,
)

private val LightColorPalette = lightColorScheme(
	// Main Colors
	primary = Color(0xFFB33737),
	primaryContainer = Color(0xFF510000),

	secondary = Color(0xFFFF8282),
	secondaryContainer = Color(0xFFCB4D4D),

	// Surfaces Colors
	background = Color(0xFFfafafa),
	surface = Color(0xFFfafafa),
	error = Color(0xFFFF0000),

	onPrimary = Color.White,
	onSecondary = Color.Black,
	onBackground = Color.Black,
	onSurface = Color.Black,
	onError = Color.Black,
)

@Composable
fun CrierSocialTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
	MaterialTheme(
		colorScheme = if (darkTheme) {
			DarkColorPalette
		} else {
			LightColorPalette
		},
		typography = Typography,
		shapes = Shapes,
		content = content
	)
}