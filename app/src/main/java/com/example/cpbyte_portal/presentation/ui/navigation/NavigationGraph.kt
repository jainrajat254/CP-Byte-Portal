package com.example.cpbyte_portal.presentation.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cpbyte_portal.presentation.ui.screens.AccountSetting
import com.example.cpbyte_portal.presentation.ui.screens.attndanceScreens.AttendanceScreen
import com.example.cpbyte_portal.presentation.ui.screens.LoginScreen
import com.example.cpbyte_portal.presentation.ui.screens.scheduleScreens.AddEventsScreen
import com.example.cpbyte_portal.presentation.ui.screens.scheduleScreens.PreviewScheduleScreen
import com.example.cpbyte_portal.presentation.ui.screens.trackerScreens.AddProjectScreen
import com.example.cpbyte_portal.presentation.viewmodel.CoordinatorViewModel
import com.example.cpbyte_portal.presentation.viewmodel.EventViewModel
import com.example.cpbyte_portal.util.SharedPrefsManager
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationGraph(navController: NavHostController, sharedPrefsManager: SharedPrefsManager) {

    val eventViewModel: EventViewModel = koinViewModel()
    val coordinatorViewModel: CoordinatorViewModel = koinViewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {
        composable(Routes.Login.route) {
            LoginScreen(sharedPrefsManager = sharedPrefsManager, navController = navController)
        }
        composable(Routes.AddProject.route) {
            AddProjectScreen()
        }
        composable(Routes.Schedule.route) {
            PreviewScheduleScreen(navController = navController)
        }
        composable(Routes.MarkAttendance.route) {
            AttendanceScreen(coordinatorViewModel = coordinatorViewModel)
        }
        composable(
            route = "add_event/{selectedDate}",
            arguments = listOf(navArgument("selectedDate") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val selectedDateStr = backStackEntry.arguments?.getString("selectedDate")
            val selectedDate = selectedDateStr?.let { LocalDate.parse(it) }
            if (selectedDate != null) {
                AddEventsScreen(selectedDate = selectedDate, eventViewModel = eventViewModel, navController = navController)
            }
        }

        composable(Routes.AccountSettings.route) {
            AccountSetting(sharedPrefsManager = sharedPrefsManager)
        }
    }
}