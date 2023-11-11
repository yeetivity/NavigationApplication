package com.example.navigationapplication.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseBottomNavigationLayout(
    navController: NavController,
    content: @Composable () -> Unit
){
    // Define the routes that the bottom navigation should host
    val bottomNavRoutes = listOf(
        "screen1",
        "screen2",
        "screen3"
    )

    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.White
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                bottomNavRoutes.forEach { screenRoute ->
                    // Check if the item is currently being shown
                    val selected = currentDestination?.hierarchy?.any { it.route == screenRoute } == true
                    // Create the item
                    BottomNavigationItem(
                        selected = selected,
                        onClick = { navController.navigate(screenRoute){
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo("screen1") {
                                inclusive = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        } },
                        icon = { Text(screenRoute) }
                    )
                }
            }
        }
    ) {
        Box(modifier = Modifier.padding(it).fillMaxSize()){
            content()
        }
    }
}