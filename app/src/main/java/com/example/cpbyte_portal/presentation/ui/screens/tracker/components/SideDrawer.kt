package com.example.cpbyte_portal.presentation.ui.screens.tracker.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.di.TokenProvider
import com.example.cpbyte_portal.presentation.ui.navigation.Routes
import com.example.cpbyte_portal.presentation.viewmodel.AuthViewModel
import com.example.cpbyte_portal.presentation.viewmodel.CoordinatorViewModel
import com.example.cpbyte_portal.presentation.viewmodel.EventViewModel
import com.example.cpbyte_portal.presentation.viewmodel.SettingsViewModel
import com.example.cpbyte_portal.presentation.viewmodel.TrackerViewModel
import com.example.cpbyte_portal.presentation.viewmodel.UserViewModel
import com.example.cpbyte_portal.util.ResultState
import com.example.cpbyte_portal.util.SharedPrefsManager

@Composable
fun EnhancedDrawerContent(
    authViewModel: AuthViewModel,
    sharedPrefsManager: SharedPrefsManager,
    navController: NavController,
    displayName: String,
    leetcode: String,
    github: String,
    closeDrawer: () -> Unit,
    onLogoutClicked: () -> Unit,
    skills: List<String>,
    libraryId: String,
    userViewModel:UserViewModel,
    eventViewModel: EventViewModel,
    settingsViewModel: SettingsViewModel,
    trackerViewModel: TrackerViewModel,
    coordinatorViewModel:CoordinatorViewModel
) {

    val logoutState by authViewModel.logoutState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(logoutState) {
        when (logoutState) {
            is ResultState.Success -> {
                Log.d("LogoutProcess", "Logout successful")

                // Clear everything only after API logout succeeds
                sharedPrefsManager.clearAll()
                sharedPrefsManager.clearToken()
                sharedPrefsManager.clearProfile()

                userViewModel.clear()
                eventViewModel.clear()
                settingsViewModel.clear()
                trackerViewModel.clear()
                coordinatorViewModel.clear()
                TokenProvider.token = null
                navController.navigate(Routes.Login.route) {
                    popUpTo(0) { inclusive = true }
                    launchSingleTop = true
                }



                Log.d("LogoutProcess", "Navigated to Login and cleared state")
                authViewModel.resetLogoutState()
            }

            is ResultState.Failure -> {
                val error = (logoutState as ResultState.Failure).error.localizedMessage
                Toast.makeText(context, "Logout failed: $error", Toast.LENGTH_SHORT).show()
            }

            is ResultState.Loading -> {
                Log.d("LogoutProcess", "Logout request in progress...")
            }

            else -> Unit
        }
    }


    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(260.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(16.dp, top = 24.dp, bottom = 10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = displayName,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                modifier = Modifier.padding(bottom = 12.dp)
            )

            DrawerItem(
                label = "Leaderboard",
                iconRes = R.drawable.baseline_leaderboard_24,
                onClick = {
                    closeDrawer()
                    navController.navigate(Routes.Leaderboard.route)
                }
            )

            DrawerItem(
                label = "Leetcode",
                iconRes = R.drawable.leetcode_logoo,
                onClick = {
                    closeDrawer()
                    navController.navigate(Routes.AddLeetcode.createRoute(leetcode =leetcode, libraryId = libraryId))
                }
            )

            DrawerItem(
                label = "Github",
                iconRes = R.drawable.github,
                onClick = {
                    closeDrawer()
                    navController.navigate(Routes.AddGithub.createRoute(github = github, libraryId = libraryId))
                }
            )

            DrawerItem(
                label = "Add Project",
                iconRes = R.drawable.baseline_library_add_24,
                onClick = {
                    closeDrawer()
                    navController.navigate(Routes.AddProject.route)
                }
            )

            DrawerItem(
                label = "Remove Project",
                iconRes = R.drawable.baseline_remove_circle_24,
                onClick = {
                    closeDrawer()
                    navController.navigate(Routes.RemoveProject.route)
                }
            )

            DrawerItem(
                label = "Skills",
                iconRes = R.drawable.baseline_query_stats_24,
                onClick = {
                    closeDrawer()
                    navController.navigate(Routes.EditSkills.createRoute(skills = skills))
                }
            )

            DrawerItem(
                label = "Change Password",
                iconRes = R.drawable.baseline_lock_24,
                onClick = {
                    closeDrawer()
                    navController.navigate(Routes.EditPassword.route)
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant,
                thickness = 0.8.dp
            )

            DrawerItem(
                label = "Log Out",
                iconRes = R.drawable.baseline_logout_24,
                labelColor = MaterialTheme.colorScheme.error,
                onClick = {
                    onLogoutClicked()
                }
            )
        }
    }
}

