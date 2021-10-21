package com.cold0.crier.social.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cold0.crier.social.MainViewModel
import com.cold0.crier.social.ui.navigation.BottomBar
import com.cold0.crier.social.ui.navigation.Drawer
import com.cold0.crier.social.ui.navigation.TopBar
import com.cold0.crier.social.ui.navigation.TopBarExtra
import com.cold0.crier.social.ui.screens.HomeScreen
import com.cold0.crier.social.ui.screens.MessageScreen
import com.cold0.crier.social.ui.screens.NotificationScreen
import com.cold0.crier.social.ui.screens.SearchScreen
import com.cold0.crier.social.utils.NotImplementedAlert
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
	viewModel: MainViewModel = viewModel()
) {
	val postList by viewModel.postList.observeAsState()

	val navController = rememberNavController()
	val scaffoldState = rememberScaffoldState()
	val scope = rememberCoroutineScope() // For open() and close() of the drawer

	var notImplementedAlertShow by remember { mutableStateOf(false) }
	if (notImplementedAlertShow) {
		NotImplementedAlert { notImplementedAlertShow = false }
	}

	val navBackStackEntry by navController.currentBackStackEntryAsState()
	val currentRoute = navBackStackEntry?.destination?.route
	Scaffold(
		scaffoldState = scaffoldState,
		topBar = {
			when (currentRoute) {
				NavigationScreenItem.Home.route ->
					TopBar(
						onNavIconPressed = { scope.launch { if (scaffoldState.drawerState.isClosed) scaffoldState.drawerState.open() else scaffoldState.drawerState.close() } },
						onActionIconPressed = { notImplementedAlertShow = true }
					)
				NavigationScreenItem.Search.route -> TopBarExtra(
					onNavIconPressed = { scope.launch { if (scaffoldState.drawerState.isClosed) scaffoldState.drawerState.open() else scaffoldState.drawerState.close() } },
					onActionIconPressed = { notImplementedAlertShow = true },
					title = "Search"
				)
				NavigationScreenItem.Notification.route -> TopBarExtra(
					onNavIconPressed = { scope.launch { if (scaffoldState.drawerState.isClosed) scaffoldState.drawerState.open() else scaffoldState.drawerState.close() } },
					onActionIconPressed = { notImplementedAlertShow = true },
					title = "Notification"
				)
				NavigationScreenItem.Message.route ->
					TopBarExtra(
						onNavIconPressed = { scope.launch { if (scaffoldState.drawerState.isClosed) scaffoldState.drawerState.open() else scaffoldState.drawerState.close() } },
						onActionIconPressed = { notImplementedAlertShow = true },
						title = "Message"
					)
			}
		},
		drawerContent = { Drawer() },
		drawerScrimColor = Color.Black.copy(alpha = 0.5f),
		drawerElevation = 0.dp,
		floatingActionButton = {
			if (currentRoute == NavigationScreenItem.Home.route)
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
			BackHandler {
				scope.launch {
					scaffoldState.drawerState.close()
				}
			}
		}

		Surface {
			NavHost(navController, startDestination = NavigationScreenItem.Home.route) {
				composable(NavigationScreenItem.Home.route) {
					HomeScreen(padding = paddingValues, postList = postList ?: listOf())
				}
				composable(NavigationScreenItem.Search.route) {
					SearchScreen()
				}
				composable(NavigationScreenItem.Notification.route) {
					NotificationScreen()
				}
				composable(NavigationScreenItem.Message.route) {
					MessageScreen()
				}
			}
		}
	}
}