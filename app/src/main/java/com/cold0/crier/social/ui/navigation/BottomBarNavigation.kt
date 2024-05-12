package com.cold0.crier.social.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cold0.crier.social.theme.CrierSocialTheme
import com.cold0.crier.social.ui.ScreenNavitationsItemsBottomBar

@Composable
fun BottomBar(navController: NavController) {
	Column {
		HorizontalDivider()
		NavigationBar(
			Modifier.background(MaterialTheme.colorScheme.surface),
			contentColor = MaterialTheme.colorScheme.onSurface,
		) {
			val navBackStackEntry = navController.currentBackStackEntry
			val currentRoute = navBackStackEntry?.destination?.route
			ScreenNavitationsItemsBottomBar.forEach { item ->
				NavigationBarItem(
					icon = { Icon(item.icon, contentDescription = item.title) },
					selected = currentRoute == item.route,
					onClick = {
						navController.navigate(item.route) {
							navController.graph.startDestinationRoute?.let { route ->
								popUpTo(route) {
									saveState = true
								}
							}
							launchSingleTop = true
							restoreState = true
						}
					}
				)
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
	CrierSocialTheme(darkTheme = false) {
		BottomBar(rememberNavController())
	}
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreviewDark() {
	CrierSocialTheme(darkTheme = true) {
		BottomBar(rememberNavController())
	}
}