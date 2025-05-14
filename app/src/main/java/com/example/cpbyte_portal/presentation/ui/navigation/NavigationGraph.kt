package com.example.cpbyte_portal.presentation.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cpbyte_portal.presentation.ui.screens.LoginScreen
import com.example.cpbyte_portal.presentation.ui.screens.PreviewScheduleScreen
import com.example.cpbyte_portal.presentation.ui.screens.ProjectSettingsScreen
import com.example.cpbyte_portal.util.SharedPrefsManager

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun  NavigationGraph(navController: NavHostController,sharedPrefsManager: SharedPrefsManager) {

    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {
        composable(Routes.Login.route) {
            LoginScreen(sharedPrefsManager = sharedPrefsManager, navController = navController )
        }
        composable(Routes.AddProject.route) {
            ProjectSettingsScreen()
        }
        composable(Routes.Schedule.route) {
            PreviewScheduleScreen()
        }
        composable(Routes.Attendance.route) {

        }
        composable(Routes.AccountSettings.route) {

        }
    }
}