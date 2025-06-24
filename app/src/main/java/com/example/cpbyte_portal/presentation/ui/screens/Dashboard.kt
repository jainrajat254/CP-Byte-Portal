package com.example.cpbyte_portal.presentation.ui.screens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cpbyte_portal.presentation.ui.navigation.BottomBar
import com.example.cpbyte_portal.presentation.ui.screens.components.CommonHeader
import com.example.cpbyte_portal.presentation.ui.screens.components.CustomLoader
import com.example.cpbyte_portal.presentation.ui.screens.components.EnhancedPullToRefresh
import com.example.cpbyte_portal.presentation.ui.screens.components.MentorCard
import com.example.cpbyte_portal.presentation.ui.screens.components.Upcoming10DaysEvents
import com.example.cpbyte_portal.presentation.ui.theme.DarkBackground
import com.example.cpbyte_portal.presentation.ui.theme.SuccessGreen
import com.example.cpbyte_portal.presentation.viewmodel.EventViewModel
import com.example.cpbyte_portal.presentation.viewmodel.UserViewModel
import com.example.cpbyte_portal.util.ResultState
import com.example.cpbyte_portal.util.SharedPrefsManager
import org.koin.androidx.compose.koinViewModel

@Composable
fun DoughnutChartWithPercentage(
    percentage: Int,
    modifier: Modifier = Modifier,
    color: Color = SuccessGreen,
    backgroundColor: Color = Color(0xFFF44336),
    bgColor: Color = MaterialTheme.colorScheme.background,
    innerCircleRadiusFraction: Float = 0.6f,
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier
            .fillMaxSize()
            .clickable { }) {
            val size = Size(size.width, size.height)
            val sweepAngle = (percentage / 100f) * 360f

            drawArc(
                color = backgroundColor,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = true,
                size = size
            )

            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = true,
                size = size
            )

            drawCircle(
                color = bgColor,
                radius = size.width * innerCircleRadiusFraction / 2f,
                center = center
            )
        }

        Text(
            text = "$percentage%",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

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

    val presentColor = SuccessGreen
    var isDialog by remember { mutableStateOf(false) }

    var isRefreshing by remember {
        mutableStateOf(false)
    }

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
                isRefreshing = false // Stop spinner when data is successfully loaded
            }
            is ResultState.Failure -> {
                isDialog = false
                isRefreshing = false // Stop spinner if there is an error
                Toast.makeText(context, "Failed to load profile", Toast.LENGTH_SHORT).show()
            }
            ResultState.Idle -> {
                isDialog = false
                isRefreshing = false
            }
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = { CommonHeader(text = "Hello, ${sharedPrefsManager.getProfile()?.data?.name}") },
        bottomBar = { BottomBar(navController, currentRoute) }
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

                // Handle profile state
                when (val result = profileState) {
                    is ResultState.Success -> {
                        val data = result.data.data

                        // Attendance cards
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 24.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            AttendanceCard("DSA", data.dsaAttendance ?: 0, presentColor)
                            AttendanceCard("DEV", data.devAttendance ?: 0, presentColor)
                        }

                        // Show mentors only if not null or blank
                        val mentorDsa = data.mentor_dsa?.takeIf { it.isNotBlank() }
                        val mentorDev = data.mentor_dev?.takeIf { it.isNotBlank() }

                        if (mentorDsa != null || mentorDev != null) {
                            SectionTitle("Mentors")
                            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                mentorDsa?.let {
                                    MentorCard(
                                        mentorDomain = "${data.domain_dsa} MENTOR",
                                        name = it
                                    )
                                }
                                mentorDev?.let {
                                    MentorCard(
                                        mentorDomain = "${data.domain_dev} MENTOR",
                                        name = it
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(24.dp))
                        }

                        // Handle events section
                        Upcoming10DaysEvents(
                            eventViewModel = eventViewModel,
                            onEventsLoaded = { showEvents ->
                                showEventsSection = showEvents
                            }
                        )

                        // Show upcoming events section if data exists
                        if (showEventsSection) {
                            Spacer(modifier = Modifier.height(24.dp))
                            SectionTitle("Upcoming Events")
                            Upcoming10DaysEvents(
                                eventViewModel = eventViewModel,
                                onEventsLoaded = {} // No need to repeat
                            )
                        }
                    }

                    is ResultState.Loading,
                    ResultState.Idle,
                    -> {
                        Spacer(modifier = Modifier.height(32.dp))
                        Text("Loading...", color = MaterialTheme.colorScheme.onBackground)
                    }

                    is ResultState.Failure -> {
                        Spacer(modifier = Modifier.height(32.dp))
                        Text("Something went wrong", color = MaterialTheme.colorScheme.error)
                        // You can add retry logic here
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

@Composable
fun AttendanceCard(label: String, percentage: Int, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        DoughnutChartWithPercentage(
            percentage = percentage,
            modifier = Modifier.size(140.dp),
            color = color
        )
    }
}


@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        color = MaterialTheme.colorScheme.onBackground,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 12.dp)
    )
}
