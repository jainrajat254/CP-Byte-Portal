package com.example.cpbyte_portal.presentation.ui.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

val bottomNavItems = listOf(
    BottomNavItem("Home", Icons.Default.Home, Routes.Home.route),
    BottomNavItem("Calendar", Icons.Default.DateRange, Routes.Schedule.route),
    BottomNavItem("Checklist", Icons.Default.CheckCircle, Routes.CheckAttendance.route),
    BottomNavItem("Settings", Icons.Default.Settings, Routes.TrackerDashboard.route)
)

@Composable
fun BottomBar(
    navController: NavHostController,
    currentRoute: String?,
) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        containerColor = Color.Black,
        contentColor = Color.White
    ) {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.name,
                        modifier = Modifier.size(22.dp)
                    )
                },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

