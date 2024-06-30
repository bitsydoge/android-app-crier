package com.cold0.crier.social.utils

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun NotImplementedAlert(onDismiss: () -> Unit) {
	AlertDialog(
		onDismissRequest = onDismiss,
		text = {
			Text(
				text = "Not implemented yet \uD83D\uDC77",
				style = MaterialTheme.typography.bodyMedium
			)
		},
		confirmButton = {
			TextButton(onClick = onDismiss) {
				Text(text = "CLOSE")
			}
		}
	)
}