package com.cold0.crier.social

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cold0.crier.social.component.CriComponent
import com.cold0.crier.social.data.DummyData.getCriList
import com.cold0.crier.social.navigation.BottomBar
import com.cold0.crier.social.navigation.Drawer
import com.cold0.crier.social.navigation.NavigationItem
import com.cold0.crier.social.navigation.TopBar
import com.cold0.crier.social.theme.CrierSocialTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrierSocialTheme {
                MainView()
            }
        }
    }
}

@Composable
fun MainView() {
    val navController = rememberNavController()
    val state = rememberScaffoldState()
    val scope = rememberCoroutineScope() // For open() and close() of the drawer
    var notImplementedAlertShow by remember { mutableStateOf(false) }
    if (notImplementedAlertShow) {
        NotImplementedAlert { notImplementedAlertShow = false }
    }
    Scaffold(
        scaffoldState = state,
        topBar = {
            TopBar(
                onNavIconPressed = { scope.launch { if (state.drawerState.isClosed) state.drawerState.open() else state.drawerState.close() } },
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

@Composable
fun HomeScreen(padding: PaddingValues = PaddingValues()) {
    val lazyListState = rememberLazyListState()
    Box(
        Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        LazyColumn(state = lazyListState) {
            items(getCriList()) { cri ->
                CriComponent(cri)
                Divider()
            }
        }
    }
}

@Composable
fun SearchScreen() {
    Box(Modifier.fillMaxSize())
    {
        Text("Search Screen", Modifier.align(Center))
    }
}

@Composable
fun NotificationScreen() {
    Box(Modifier.fillMaxSize())
    {
        Text("Notification Screen", Modifier.align(Center))
    }
}

@Composable
fun MessageScreen() {
    Box(Modifier.fillMaxSize())
    {
        Text("Message Screen", Modifier.align(Center))
    }
}

// ---------------------------------------------------------------
// COMPOSE PREVIEW
// ---------------------------------------------------------------
@Preview()
@Composable
fun HomeScreenPreview() {
    CrierSocialTheme {
        Surface() {
            HomeScreen()
        }
    }
}

@Preview()
@Composable
fun HomeScreenPreviewDark() {
    CrierSocialTheme(true) {
        Surface() {
            HomeScreen()
        }
    }
}

