package com.example.cpbyte_portal.presentation.ui.screens.home

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cpbyte_portal.presentation.ui.navigation.BottomBar
import com.example.cpbyte_portal.presentation.ui.screens.components.CommonHeader
import com.example.cpbyte_portal.presentation.ui.screens.components.CustomLoader
import com.example.cpbyte_portal.presentation.ui.screens.components.EnhancedPullToRefresh
import com.example.cpbyte_portal.presentation.ui.screens.home.components.AttendanceGraphCard
import com.example.cpbyte_portal.presentation.ui.screens.home.components.DsaAndDevSection
import com.example.cpbyte_portal.presentation.ui.screens.home.components.InfoBox
import com.example.cpbyte_portal.presentation.ui.screens.home.components.SectionTitle
import com.example.cpbyte_portal.presentation.ui.screens.home.components.Upcoming10DaysEvents
import com.example.cpbyte_portal.presentation.viewmodel.EventViewModel
import com.example.cpbyte_portal.presentation.viewmodel.UserViewModel
import com.example.cpbyte_portal.util.ResultState
import com.example.cpbyte_portal.util.SharedPrefsManager
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AttendanceDashboardScreen(
    eventViewModel: EventViewModel = koinViewModel(),
    userViewModel: UserViewModel = koinViewModel(),
    sharedPrefsManager: SharedPrefsManager,
    navController: NavHostController = rememberNavController(),
) {
    val context = LocalContext.current
    val profileState by userViewModel.profileState.collectAsState()
    var showEventsSection by remember { mutableStateOf(false) }
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    var isDialog by remember { mutableStateOf(false) }
    var isRefreshing by remember { mutableStateOf(false) }

    val onRefresh = {
        isRefreshing = true
        userViewModel.refreshDashboard()
        isRefreshing = false
    }

    LaunchedEffect(Unit) {
        userViewModel.loadDashboardData()
    }

    LaunchedEffect(profileState) {
        when (profileState) {
            is ResultState.Loading -> {
                isDialog = true
            }

            is ResultState.Success -> {
                isDialog = false
                isRefreshing = false
            }

            is ResultState.Failure -> {
                isDialog = false
                isRefreshing = false
                Toast.makeText(context, "Failed to load profile", Toast.LENGTH_SHORT).show()
            }

            ResultState.Idle -> {
                isDialog = false
                isRefreshing = false
            }
        }
    }

    Scaffold(
        topBar = {
            CommonHeader(
                text = "Hello, ",
                otherText = sharedPrefsManager.getProfile()?.data?.name
            )
        },
        bottomBar = {
            BottomBar(navController, currentRoute)
        }
    ) { innerPadding ->

        EnhancedPullToRefresh(
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                when (val result = profileState) {
                    is ResultState.Success -> {
                        val data = result.data.data

                        AttendanceGraphCard(data = data)

                        Spacer(modifier = Modifier.height(20.dp))

                        val mentorDsa = data.mentor_dsa?.takeIf { it.isNotBlank() }
                        val mentorDev = data.mentor_dev?.takeIf { it.isNotBlank() }

                        if (mentorDsa != null || mentorDev != null) {
                            DsaAndDevSection(mentorDsa, mentorDev, data)
                        } else {
                            InfoBox(message = "No mentors assigned yet.")
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        if (showEventsSection) {
                            Spacer(modifier = Modifier.height(24.dp))
                            SectionTitle("Upcoming Events")
                            Upcoming10DaysEvents(
                                eventViewModel = eventViewModel,
                                onEventsLoaded = {} // No-op: already know events exist
                            )
                        } else {
                            Upcoming10DaysEvents(
                                eventViewModel = eventViewModel,
                                onEventsLoaded = { hasEvents -> showEventsSection = hasEvents }
                            )
                            InfoBox(message = "No upcoming events.")
                        }
                    }

                    is ResultState.Loading,
                    ResultState.Idle,
                        -> {
                        Spacer(modifier = Modifier.height(32.dp))
                        Text("Loading...", color = Color.White)
                    }

                    is ResultState.Failure -> {
                        Spacer(modifier = Modifier.height(32.dp))
                        Text("Something went wrong", color = Color.Red)
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }

        if (isDialog) {
            CustomLoader()
        }
    }
}
