package com.cold0.crier.social.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchScreen() {
	var value by remember { mutableStateOf("") }

	Box(Modifier.fillMaxSize()) {
		OutlinedTextField(
			value = value, onValueChange = { value = it }, modifier = Modifier
				.align(Alignment.TopCenter)
				.fillMaxWidth()
				.padding(16.dp)
		)
		Text(
			"Search Screen",
			Modifier
				.align(Alignment.Center)
		)
	}
}