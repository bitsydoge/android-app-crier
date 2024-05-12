package com.cold0.crier.social.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cold0.crier.social.MainViewModel
import com.cold0.crier.social.ui.navigation.BottomBar
import com.cold0.crier.social.ui.navigation.Drawer
import com.cold0.crier.social.ui.navigation.TopBarExtra
import com.cold0.crier.social.ui.screens.HomeScreen
import com.cold0.crier.social.ui.screens.MessageScreen
import com.cold0.crier.social.ui.screens.NotificationScreen
import com.cold0.crier.social.ui.screens.SearchScreen
import com.cold0.crier.social.utils.NotImplementedAlert
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Locale


@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController().also { viewModel.navHostController = it }
    val scope = rememberCoroutineScope()

    var notImplementedAlertShow by remember { mutableStateOf(false) }
    if (notImplementedAlertShow) {
        NotImplementedAlert { notImplementedAlertShow = false }
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""

    Scaffold(scaffoldState = scaffoldState, topBar = {
        TopBarHandler(currentRoute, scope, scaffoldState) {
            notImplementedAlertShow = true
        }
    }, drawerContent = { drawerHandler(currentRoute) }, floatingActionButton = {
        FloatingActionButtonHandler(currentRoute) {
            notImplementedAlertShow = true
        }
    }, bottomBar = { BottomBarHandler(currentRoute, navController) }) { paddingValues ->
        BackHandlerInterceptor(scaffoldState, scope)
        MainNavHost(navController, viewModel, paddingValues)
    }
}

@Composable
private fun TopBarHandler(
	route: String, scope: CoroutineScope, scaffoldState: ScaffoldState, onAction: () -> Unit
) {
	TopBarExtra(onNavIconPressed = { ToggleDrawer(scope, scaffoldState) },
		onActionIconPressed = onAction,
		title = route.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
	)
}

@Composable
private fun ToggleDrawer(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    LaunchedEffect(scope) {
        if (scaffoldState.drawerState.isClosed) scaffoldState.drawerState.open() else scaffoldState.drawerState.close()
    }
}

@Composable
private fun FloatingActionButtonHandler(route: String, onAction: () -> Unit) {
    if (route == ScreenNavitationsItems.Home.route) {
        FloatingActionButton(
            onClick = onAction,
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
        ) {
            Icon(Icons.Filled.Add, "")
        }
    }
}

@Composable
private fun BottomBarHandler(route: String, navController: NavController) {
    if (route in ScreenNavitationsItemsBottomBar.map { it.route }) {
        BottomBar(navController)
    }
}

@Composable
private fun drawerHandler(route: String): @Composable (() -> Unit)? =
    if (route in ScreenNavitationsItemsBottomBar.map { it.route }) {
        { Drawer() }
    } else null

@Composable
private fun BackHandlerInterceptor(scaffoldState: ScaffoldState, scope: CoroutineScope) {
    if (scaffoldState.drawerState.isOpen) {
        BackHandler {
            scope.launch { scaffoldState.drawerState.close() }
        }
    }
}

@Composable
private fun MainNavHost(
    navController: NavHostController, viewModel: MainViewModel, paddingValues: PaddingValues
) {
    NavHost(navController, startDestination = ScreenNavitationsItems.Home.route) {
        composable(ScreenNavitationsItems.Home.route) {
            val postState by viewModel.postList.observeAsState()
            val userState by viewModel.userList.observeAsState()
            val postList = postState?.data ?: emptyList()
            val userList = userState?.data ?: emptyList()

            HomeScreen(
                viewModel = viewModel,
                padding = paddingValues,
                postList = postList,
                userList = userList
            )
        }
        composable(ScreenNavitationsItems.Search.route) { SearchScreen() }
        composable(ScreenNavitationsItems.Notification.route) { NotificationScreen() }
        composable(ScreenNavitationsItems.Message.route) { MessageScreen() }
    }
}
