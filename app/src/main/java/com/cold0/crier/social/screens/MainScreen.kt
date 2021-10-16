package com.cold0.crier.social.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cold0.crier.social.NotImplementedAlert
import com.cold0.crier.social.screens.navigation.BottomBar
import com.cold0.crier.social.screens.navigation.Drawer
import com.cold0.crier.social.screens.navigation.NavigationItem
import com.cold0.crier.social.screens.navigation.TopBar
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope() // For open() and close() of the drawer
    var notImplementedAlertShow by remember { mutableStateOf(false) }
    if (notImplementedAlertShow) {
        NotImplementedAlert { notImplementedAlertShow = false }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                onNavIconPressed = { scope.launch { if (scaffoldState.drawerState.isClosed) scaffoldState.drawerState.open() else scaffoldState.drawerState.close() } },
                onActionIconPressed = { notImplementedAlertShow = true }
            )
        },
        drawerContent = { Drawer() },
        drawerScrimColor = Color.Black.copy(alpha = 0.5f),
        drawerElevation = 0.dp,
        floatingActionButton = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            if (currentRoute == NavigationItem.Home.route)
                FloatingActionButton(
                    onClick = { notImplementedAlertShow = true },
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = MaterialTheme.colors.onPrimary
                ) {
                    Icon(Icons.Filled.Add, "")
                }
        },
        bottomBar = {
            BottomBar(navController)
        }
    ) { paddingValues: PaddingValues ->

        if (scaffoldState.drawerState.isOpen) {
            BackHandler() {
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        }

        Surface() {
            NavHost(navController, startDestination = NavigationItem.Home.route) {
                composable(NavigationItem.Home.route) {
                    HomeScreen(padding = paddingValues)
                }
                composable(NavigationItem.Search.route) {
                    SearchScreen()
                }
                composable(NavigationItem.Notification.route) {
                    NotificationScreen()
                }
                composable(NavigationItem.Message.route) {
                    MessageScreen()
                }
            }
        }
    }
}