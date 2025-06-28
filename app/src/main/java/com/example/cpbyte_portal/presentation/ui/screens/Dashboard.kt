package com.example.cpbyte_portal.presentation.ui.screens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cpbyte_portal.domain.model.ProfileData
import com.example.cpbyte_portal.presentation.ui.navigation.BottomBar
import com.example.cpbyte_portal.presentation.ui.screens.components.*
import com.example.cpbyte_portal.presentation.ui.theme.AbsentColor
import com.example.cpbyte_portal.presentation.ui.theme.AbsentWithReasonColor
import com.example.cpbyte_portal.presentation.ui.theme.CPByteTheme
import com.example.cpbyte_portal.presentation.ui.theme.DeepDark
import com.example.cpbyte_portal.presentation.ui.theme.LightText
import com.example.cpbyte_portal.presentation.ui.theme.PresentColor
import com.example.cpbyte_portal.presentation.ui.theme.textColor
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
    val presentColor = PresentColor
    val absentWithReasonColor = AbsentWithReasonColor
    val absentWithoutReasonColor = AbsentColor
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
        containerColor = DeepDark,
        contentColor = LightText,
        topBar = { CommonHeader(text = "Hello", sharedPrefsManager.getProfile()?.data?.name) },
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
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Handle profile state
                when (val result = profileState) {
                    is ResultState.Success -> {
                        val data = result.data.data

                        //This section shows the attendance in dev and dsa with the use of Doughnut Chart
                        AttendanceParentComposable(innerPadding, data, presentColor,absentWithoutReasonColor,absentWithReasonColor)

                        // Show mentors only if not null or blank
                        val mentorDsa = data.mentor_dsa?.takeIf { it.isNotBlank() }
                        val mentorDev = data.mentor_dev?.takeIf { it.isNotBlank() }

                        //DSA AND DEV Section Composable - This section displays Language, and Mentor of DSA and DEV
                        DsaAndDevSectionComposable(mentorDsa, mentorDev, data)

                        Upcoming10DaysEvents(
                            eventViewModel = eventViewModel,
                            onEventsLoaded = { showEvents ->
                                showEventsSection = showEvents
                            }
                        )

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

@Composable
private fun DsaAndDevSectionComposable(
    mentorDsa: String?,
    mentorDev: String?,
    data: ProfileData
) {
    if (mentorDsa != null || mentorDev != null) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            mentorDsa?.let {
                Row {
                    Spacer(modifier = Modifier.width(2.dp))
                    SectionTitle("DSA")
                }
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    MentorCard("Language", "${data.domain_dsa}")
                    MentorCard("Mentor", mentorDsa)
                }
            }
            mentorDev?.let {
                Row {
                    Spacer(modifier = Modifier.width(2.dp))
                    SectionTitle("DEV")
                }
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    MentorCard("Language", "${data.domain_dev}")
                    MentorCard("Mentor", mentorDev)
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
private fun AttendanceParentComposable(
    innerPadding: PaddingValues,
    data: ProfileData,
    presentColor: Color,
    absentWithoutReasonColor: Color,
    absentWithReasonColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(12.dp)
                ),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF272f3c))

        ) {
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                AttendanceCard("DSA", data.dsaAttendance ?: 0, presentColor)
                AttendanceCard("DEV", data.devAttendance ?: 0, presentColor)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 33.dp)
            ) {
                AttendanceLegendItem(
                    color = PresentColor,
                    title = "Present",

                    )
                AttendanceLegendItem(
                    color = absentWithReasonColor,
                    title = "Absent with Reason",
                )
                AttendanceLegendItem(
                    color = absentWithoutReasonColor,
                    title = "Absent without Reason",
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

@Composable
fun AttendanceCard(label: String, percentage: Int, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            label, color = LightText, fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        DoughnutChartWithPercentage(
            percentagePresent = percentage,
            presentColor = color,
            modifier = Modifier.size(140.dp),
            percentageAbsentWithReason = 8,
        )
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        color = Color.White,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 12.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonHeader(
    text: String,
    name: String?,
    backgroundColor: Color = DeepDark,
    contentColor: Color = LightText
) {
    TopAppBar(
        title = {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Row {
                    Text(
                        text = text+", ",
                        color = contentColor,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )

                        Text(
                            text = name ?:"Coordinator",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(
                                brush = textColor
                            )
                        )
                    }
                }


        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = backgroundColor,
            titleContentColor = contentColor
        )
    )
}

@Composable
fun AttendanceLegendItem(
    color: Color,
    title: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(color)
                .padding(end = 12.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }
    }
}

