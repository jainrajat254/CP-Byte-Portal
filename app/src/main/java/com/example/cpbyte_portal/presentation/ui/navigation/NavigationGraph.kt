package com.example.cpbyte_portal.presentation.ui.navigation

import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cpbyte_portal.presentation.ui.screens.home.AttendanceDashboardScreen
import com.example.cpbyte_portal.presentation.ui.screens.auth.SplashScreen
import com.example.cpbyte_portal.presentation.ui.screens.attendanceScreens.CheckAttendanceScreen
import com.example.cpbyte_portal.presentation.ui.screens.attendanceScreens.MarkAttendanceScreen
import com.example.cpbyte_portal.presentation.ui.screens.auth.LoginScreen
import com.example.cpbyte_portal.presentation.ui.screens.leaderboard.LeetCodeLeaderboardScreen
import com.example.cpbyte_portal.presentation.ui.screens.schedule.ScheduleScreen
import com.example.cpbyte_portal.presentation.ui.screens.schedule.components.AddEventsScreen
import com.example.cpbyte_portal.presentation.ui.screens.tracker.AddProjectScreen
import com.example.cpbyte_portal.presentation.ui.screens.tracker.EditGithubScreen
import com.example.cpbyte_portal.presentation.ui.screens.tracker.EditLeetcodeScreen
import com.example.cpbyte_portal.presentation.ui.screens.tracker.EditPasswordScreen
import com.example.cpbyte_portal.presentation.ui.screens.tracker.RemoveProjectScreen
import com.example.cpbyte_portal.presentation.ui.screens.tracker.SkillsScreen
import com.example.cpbyte_portal.presentation.ui.screens.tracker.TrackerDashboardScreen
import com.example.cpbyte_portal.presentation.viewmodel.AuthViewModel
import com.example.cpbyte_portal.presentation.viewmodel.CoordinatorViewModel
import com.example.cpbyte_portal.presentation.viewmodel.EventViewModel
import com.example.cpbyte_portal.presentation.viewmodel.SettingsViewModel
import com.example.cpbyte_portal.presentation.viewmodel.TrackerViewModel
import com.example.cpbyte_portal.presentation.viewmodel.UserViewModel
import com.example.cpbyte_portal.util.SharedPrefsManager
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationGraph(navController: NavHostController, sharedPrefsManager: SharedPrefsManager) {

    val authViewModel: AuthViewModel = koinViewModel()
    val userViewModel: UserViewModel = koinViewModel()
    val eventViewModel: EventViewModel = koinViewModel()
    val coordinatorViewModel: CoordinatorViewModel = koinViewModel()
    val trackerViewModel: TrackerViewModel = koinViewModel()
    val settingsViewModel: SettingsViewModel = koinViewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.Splash.route
    ) {
        composable(Routes.Splash.route) {
            SplashScreen(
                sharedPrefsManager = sharedPrefsManager,
                navController = navController
            )
        }
        composable(Routes.Login.route) {
            LoginScreen(
                authViewModel = authViewModel,
                sharedPrefsManager = sharedPrefsManager,
                navController = navController,

                )
        }
        composable(Routes.Home.route) {
            AttendanceDashboardScreen(
                sharedPrefsManager = sharedPrefsManager,
                eventViewModel = eventViewModel,
                navController = navController,
                userViewModel = userViewModel
            )
        }
        composable(Routes.AddProject.route) {
            AddProjectScreen(trackerViewModel = trackerViewModel, navController = navController)
        }
        composable(Routes.Schedule.route) {
            ScheduleScreen(navController = navController)
        }
        composable(Routes.CheckAttendance.route) {
            CheckAttendanceScreen(
                navController = navController,
                coordinatorViewModel = coordinatorViewModel
            )
        }
        composable(Routes.Leaderboard.route) {
            LeetCodeLeaderboardScreen(navController = navController, vm = trackerViewModel)
        }

        composable(Routes.TrackerDashboard.route) {
            TrackerDashboardScreen(
                authViewModel = authViewModel,
                trackerViewModel = trackerViewModel,
                sharedPrefsManager = sharedPrefsManager,
                navController = navController,
                onLogoutClicked = {
                    Log.d("LogoutProcess", "Logout started")
                    authViewModel.logoutUser()
                },
                userViewModel = userViewModel,
                eventViewModel = eventViewModel,
                settingsViewModel = settingsViewModel,
                coordinatorViewModel = coordinatorViewModel
            )
        }

        composable(
            route = Routes.EditSkills.route,
            arguments = listOf(navArgument("skills") { type = NavType.StringType })
        ) { backStackEntry ->
            val skillsArg = backStackEntry.arguments?.getString("skills")
            val skills = skillsArg?.split(",")?.map { Uri.decode(it) } ?: emptyList()

            SkillsScreen(
                skill = skills,
                trackerViewModel = trackerViewModel,
                navController = navController
            )
        }

        composable(
            route = Routes.AddLeetcode.route,
            arguments = listOf(
                navArgument(Routes.AddLeetcode.ARG_LEETCODE) { defaultValue = "" },
                navArgument(Routes.AddLeetcode.ARG_LIBRARY_ID) { defaultValue = "" })
        ) { backStackEntry ->
            val leetcodeUsername =
                backStackEntry.arguments?.getString(Routes.AddLeetcode.ARG_LEETCODE) ?: ""
            val libraryId =
                backStackEntry.arguments?.getString(Routes.AddLeetcode.ARG_LIBRARY_ID) ?: ""
            EditLeetcodeScreen(
                initialUsername = leetcodeUsername,
                trackerViewModel = trackerViewModel,
                libraryId = libraryId,
                navController = navController
            )
        }

        composable(
            route = Routes.AddGithub.route,
            arguments = listOf(
                navArgument(Routes.AddGithub.ARG_GITHUB) { defaultValue = "" },
                navArgument(Routes.AddGithub.ARG_LIBRARY_ID) { defaultValue = "" }),
        ) { backStackEntry ->
            val githubUsername =
                backStackEntry.arguments?.getString(Routes.AddGithub.ARG_GITHUB) ?: ""
            val libraryId =
                backStackEntry.arguments?.getString(Routes.AddGithub.ARG_LIBRARY_ID) ?: ""
            EditGithubScreen(
                initialUsername = githubUsername,
                trackerViewModel = trackerViewModel,
                libraryId = libraryId,
                navController = navController
            )
        }


        composable(
            route = "mark_attendance/{subject}/{date}",
            arguments = listOf(
                navArgument("subject") { type = NavType.StringType },
                navArgument("date") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val subject = backStackEntry.arguments?.getString("subject")
            val date = backStackEntry.arguments?.getString("date")
            if (subject != null && date != null) {
                MarkAttendanceScreen(
                    subject = subject,
                    coordinatorViewModel = coordinatorViewModel,
                    date = date,
                    navController = navController
                )
            }
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
                AddEventsScreen(
                    selectedDate = selectedDate,
                    eventViewModel = eventViewModel,
                    navController = navController
                )
            }
        }

        composable(Routes.RemoveProject.route) {
            RemoveProjectScreen(
                userViewModel = userViewModel,
                trackerViewModel = trackerViewModel,
                navController = navController
            )
        }

        composable(Routes.EditPassword.route) {
            EditPasswordScreen(settingsViewModel = settingsViewModel, navController = navController)
        }
    }
}