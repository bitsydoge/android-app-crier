package com.cold0.crier.social.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationScreenItem(var route: String, var icon: ImageVector, var title: String) {
	object Home : NavigationScreenItem("home", Icons.Filled.Home, "Home")
	object Search : NavigationScreenItem("search", Icons.Filled.Search, "Search")
	object Notification : NavigationScreenItem("notification", Icons.Filled.Notifications, "Notification")
	object Message : NavigationScreenItem("message", Icons.Filled.Message, "Message")
}