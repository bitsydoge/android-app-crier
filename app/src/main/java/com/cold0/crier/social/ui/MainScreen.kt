package com.cold0.crier.social.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
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
	viewModel.navHostController = rememberNavController()
	val postList by viewModel.postList.observeAsState()
	val userList by viewModel.userList.observeAsState()

	val scaffoldState = rememberScaffoldState()
	val scope = rememberCoroutineScope() // For open() and close() of the drawer

	var notImplementedAlertShow by remember { mutableStateOf(false) }
	if (notImplementedAlertShow) {
		NotImplementedAlert { notImplementedAlertShow = false }
	}

	val navBackStackEntry by viewModel.navHostController.currentBackStackEntryAsState()
	val currentRoute = navBackStackEntry?.destination?.route ?: ""

	Scaffold(
		scaffoldState = scaffoldState,
		topBar = {
			when (currentRoute) {
				ScreenNavitationsItems.Home.route ->
					TopBar(
						onNavIconPressed = { scope.launch { if (scaffoldState.drawerState.isClosed) scaffoldState.drawerState.open() else scaffoldState.drawerState.close() } },
						onActionIconPressed = { notImplementedAlertShow = true }
					)
				ScreenNavitationsItems.Search.route ->
					TopBarExtra(
						onNavIconPressed = { scope.launch { if (scaffoldState.drawerState.isClosed) scaffoldState.drawerState.open() else scaffoldState.drawerState.close() } },
						onActionIconPressed = { notImplementedAlertShow = true },
						title = "Search"
					)
				ScreenNavitationsItems.Notification.route ->
					TopBarExtra(
						onNavIconPressed = { scope.launch { if (scaffoldState.drawerState.isClosed) scaffoldState.drawerState.open() else scaffoldState.drawerState.close() } },
						onActionIconPressed = { notImplementedAlertShow = true },
						title = "Notification"
					)
				ScreenNavitationsItems.Message.route ->
					TopBarExtra(
						onNavIconPressed = { scope.launch { if (scaffoldState.drawerState.isClosed) scaffoldState.drawerState.open() else scaffoldState.drawerState.close() } },
						onActionIconPressed = { notImplementedAlertShow = true },
						title = "Message"
					)
			}
		},
		drawerContent = if (currentRoute in ScreenNavitationsItemsBottomBar.map { it.route }) {
			{ Drawer() }
		} else null,
		drawerScrimColor = Color.Black.copy(alpha = 0.5f),
		drawerElevation = 0.dp,
		floatingActionButton = {
			if (currentRoute == ScreenNavitationsItems.Home.route)
				FloatingActionButton(
					onClick = { notImplementedAlertShow = true },
					backgroundColor = MaterialTheme.colors.primary,
					contentColor = MaterialTheme.colors.onPrimary
				) {
					Icon(Icons.Filled.Add, "")
				}
		},
		bottomBar = {
			if (currentRoute in ScreenNavitationsItemsBottomBar.map { it.route }) // Only show if current route is one of the botbar
				BottomBar(viewModel.navHostController)
		}
	) { paddingValues: PaddingValues ->

		if (scaffoldState.drawerState.isOpen) { // Handle back to close Drawer
			BackHandler {
				scope.launch {
					scaffoldState.drawerState.close()
				}
			}
		}

		Surface {
			NavHost(viewModel.navHostController, startDestination = ScreenNavitationsItems.Home.route) {
				composable(ScreenNavitationsItems.Home.route) {
					HomeScreen(viewModel = viewModel, padding = paddingValues, postList = postList?.data ?: listOf(), userList = userList?.data ?: listOf())
				}
				composable(ScreenNavitationsItems.Search.route) {
					SearchScreen()
				}
				composable(ScreenNavitationsItems.Notification.route) {
					NotificationScreen()
				}
				composable(ScreenNavitationsItems.Message.route) {
					MessageScreen()
				}
				composable(
					ScreenNavitationsItems.Profile.route
				) {
					Box(Modifier.fillMaxSize()) {
						Text("Hello World")
					}
				}
			}

		}
	}
}