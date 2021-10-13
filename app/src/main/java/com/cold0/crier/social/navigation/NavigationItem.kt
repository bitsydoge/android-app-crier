package com.cold0.crier.social.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(var route: String, var icon: ImageVector, var title: String) {
    object Home : NavigationItem("home", Icons.Filled.Home, "Home")
    object Search : NavigationItem("search", Icons.Filled.Search, "Search")
    object Notification : NavigationItem("notification", Icons.Filled.Notifications, "Notification")
    object Message : NavigationItem("message", Icons.Filled.Message, "Message")
    object Profile : NavigationItem("profile", Icons.Filled.Message, "Profile")
}