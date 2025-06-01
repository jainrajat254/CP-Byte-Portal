package com.example.cpbyte_portal.presentation.ui.screens.trackerScreens

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.domain.model.Github
import com.example.cpbyte_portal.domain.model.LeetCode
import com.example.cpbyte_portal.domain.model.ProjectResponse
import com.example.cpbyte_portal.domain.model.UserDashboardResponse
import com.example.cpbyte_portal.presentation.ui.navigation.BottomBar
import com.example.cpbyte_portal.presentation.ui.screens.components.CommonHeader
import com.example.cpbyte_portal.presentation.ui.screens.components.CustomLoader
import com.example.cpbyte_portal.presentation.ui.screens.components.EnhancedPullToRefresh
import com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents.ProgrammingStatsComposable
import com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents.ProgressTrackerViewCard
import com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents.ProjectsComposable
import com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents.SelectorTabsForDashboard
import com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents.TechnicalSkills
import com.example.cpbyte_portal.presentation.viewmodel.AuthViewModel
import com.example.cpbyte_portal.presentation.viewmodel.TrackerViewModel
import com.example.cpbyte_portal.util.ResultState
import com.example.cpbyte_portal.util.SharedPrefsManager
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TrackerDashboardScreen(
    authViewModel: AuthViewModel,
    trackerViewModel: TrackerViewModel,
    sharedPrefsManager: SharedPrefsManager,
    navController: NavHostController,
    onLogoutClicked: () -> Unit,
) {
    val context = LocalContext.current

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val libraryId = sharedPrefsManager.getProfile()?.data?.library_id ?: "2327CSE1241"
    Log.d("TrackerScreen", "Library ID: $libraryId")

    LaunchedEffect(libraryId) {
        Log.d("TrackerScreen", "Calling loadDataIfNotLoadedForDashboard()")
        trackerViewModel.loadDataIfNotLoadedForDashboard(libraryId = libraryId)
    }

    val dashboardState by trackerViewModel.getUserDashboardState.collectAsState()

    when (val state = dashboardState) {
        is ResultState.Loading -> {
            Log.d("TrackerScreen", "State: Loading")
            CustomLoader()
        }

        is ResultState.Failure -> {
            val errorMessage = state.error.message ?: "Unknown error"
            Log.e("TrackerScreen", "Error occurred: $errorMessage")
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }

        is ResultState.Success -> {
            Log.d("TrackerScreen", "Success: ${state.data}")

            DashboardContent(
                userDashboard = state.data,
                navController = navController,
                currentRoute = currentRoute,
                displayName = sharedPrefsManager.getProfile()?.data?.name ?: "User",
                trackerViewModel = trackerViewModel,
                libraryId = libraryId,
                onLogoutClicked = onLogoutClicked,
                authViewModel = authViewModel,
                sharedPrefsManager = sharedPrefsManager
            )
        }

        ResultState.Idle -> {
            Log.d("TrackerScreen", "State: Idle")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DashboardContent(
    authViewModel: AuthViewModel,
    userDashboard: UserDashboardResponse,
    navController: NavHostController,
    currentRoute: String?,
    displayName: String,
    trackerViewModel: TrackerViewModel,
    libraryId: String,
    onLogoutClicked: () -> Unit,
    sharedPrefsManager: SharedPrefsManager
) {
    val tracker = userDashboard.tracker
    val leetcode = tracker.leetcode
    val github = tracker.github
    val skills = tracker.skills
    val projects = tracker.projects
    val heatMap = tracker.past5.toIntArray()

    val tabs = listOf("Stats", "Projects", "Skills")
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val coroutineScope = rememberCoroutineScope()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    var isRefreshing by remember {
        mutableStateOf(false)
    }

    val onRefresh = {
        isRefreshing = true
        trackerViewModel.refreshProfile(libraryId = libraryId)
        isRefreshing = false
    }

    ModalNavigationDrawer(
        drawerContent = {
            EnhancedDrawerContent(
                navController = navController,
                displayName = displayName,
                closeDrawer = { scope.launch { drawerState.close() } },
                onLogoutClicked = onLogoutClicked, //onLogoutClicked
                skills = skills,
                libraryId = userDashboard.library_id,
                leetcode = tracker.leetcode.username,
                github = tracker.github.username,
                authViewModel = authViewModel,
                sharedPrefsManager = sharedPrefsManager
            )
        },
        drawerState = drawerState
    ) {

        Scaffold(
            containerColor = Color(0xFF0F172A),
            topBar = {
                CommonHeader(
                    text = "Profile",
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "Menu",
                                tint = Color.White
                            )
                        }
                    }
                )
            },
            bottomBar = {
                BottomBar(navController = navController, currentRoute = currentRoute)
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
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF1F305A)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.user),
                                contentDescription = "Avatar",
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(CircleShape)
                            )

                            Spacer(modifier = Modifier.width(16.dp))

                            Column {
                                Text(
                                    text = userDashboard.name,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Text(
                                    text = userDashboard.library_id,
                                    color = Color(0xFFCBD5E1),
                                    fontSize = 12.sp
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "Dev Domain: ${userDashboard.domain_dev}",
                                    fontSize = 13.sp,
                                    color = Color.Gray
                                )
                                Text(
                                    text = "DSA Domain: ${userDashboard.domain_dsa}",
                                    fontSize = 13.sp,
                                    color = Color.Gray
                                )
                                Text(
                                    text = "Year: ${userDashboard.year}",
                                    fontSize = 13.sp,
                                    color = Color.Gray
                                )
                                Text(
                                    text = "Email: ${userDashboard.email}",
                                    fontSize = 13.sp,
                                    color = Color.Gray
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // 📊 Tracker Cards
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ProgressTrackerViewCard(
                            title = "Solved",
                            totalQuestions = leetcode.solvedProblems.toString(),
                            color = Color(0xFF1F305A),
                            arr = heatMap
                        )
                        ProgressTrackerViewCard(
                            title = "Ranking",
                            totalQuestions = tracker.rank.toString(),
                            color = Color(0xFF1F305A),
                            arr = heatMap
                        )
                        ProgressTrackerViewCard(
                            title = "Heatmap",
                            totalQuestions = heatMap.sum().toString(),
                            color = Color(0xFF1F305A),
                            arr = heatMap
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // 🧭 Tab Selector
                    SelectorTabsForDashboard(
                        currentSelection = tabs[pagerState.currentPage],
                        onTabSelected = { selected ->
                            val selectedIndex = tabs.indexOf(selected)
                            if (selectedIndex != pagerState.currentPage) {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(selectedIndex)
                                }
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // 🧱 Pager Content
                    HorizontalPager(state = pagerState) { page ->
                        when (page) {
                            0 -> ProgrammingStatsComposableCaller(
                                leetcode = leetcode,
                                github = github
                            )

                            1 -> ProjectsComposableCaller(projects)
                            2 -> TechnicalSkillsCaller(skills = skills)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ProgrammingStatsComposableCaller(
    leetcode: LeetCode,
    github: Github,
) {
    ProgrammingStatsComposable(leetcode = leetcode, github = github)
}

@Composable
fun ProjectsComposableCaller(projects: List<ProjectResponse>) {
    ProjectsComposable(projects = projects)
}

@Composable
fun TechnicalSkillsCaller(
    skills: List<String>,
) {
    TechnicalSkills(
        skills = skills,
    )
}