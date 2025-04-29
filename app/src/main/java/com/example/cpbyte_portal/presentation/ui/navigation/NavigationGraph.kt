package com.example.cpbyte_portal.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun  NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {

        }
        composable(Screen.Member.route) {

        }
        composable(Screen.Schedule.route) {

        }
        composable(Screen.Attendance.route) {

        }
        composable(Screen.Settings.route) {

        }
    }
}