package com.cold0.crier.social.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenNavitationsItems(var route: String, var icon: ImageVector, var title: String) {
	data object Home : ScreenNavitationsItems("home", Icons.Filled.Home, "Home")
	data object Search : ScreenNavitationsItems("search", Icons.Filled.Search, "Search")
	data object Notification : ScreenNavitationsItems("notification", Icons.Filled.Notifications, "Notification")
	data object Message : ScreenNavitationsItems("message", Icons.Filled.Message, "Message")
	data object Profile : ScreenNavitationsItems("profile", Icons.Filled.VerifiedUser, "Profile")
}

val ScreenNavitationsItemsBottomBar: List<ScreenNavitationsItems> = listOf(
	ScreenNavitationsItems.Home,
	ScreenNavitationsItems.Search,
	ScreenNavitationsItems.Notification,
	ScreenNavitationsItems.Message,
)