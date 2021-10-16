package com.cold0.crier.social.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenNavItem(var route: String, var icon: ImageVector, var title: String) {
    object Home : ScreenNavItem("home", Icons.Filled.Home, "Home")
    object Search : ScreenNavItem("search", Icons.Filled.Search, "Search")
    object Notification : ScreenNavItem("notification", Icons.Filled.Notifications, "Notification")
    object Message : ScreenNavItem("message", Icons.Filled.Message, "Message")
    object Profile : ScreenNavItem("profile", Icons.Filled.Message, "Profile")
}