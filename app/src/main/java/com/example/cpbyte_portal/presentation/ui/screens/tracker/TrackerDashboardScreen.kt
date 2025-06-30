package com.example.cpbyte_portal.presentation.ui.screens.tracker

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cpbyte_portal.domain.model.UserDashboardResponse
import com.example.cpbyte_portal.presentation.ui.navigation.BottomBar
import com.example.cpbyte_portal.presentation.ui.navigation.Routes
import com.example.cpbyte_portal.presentation.ui.screens.components.CommonHeader
import com.example.cpbyte_portal.presentation.ui.screens.components.CustomLoader
import com.example.cpbyte_portal.presentation.ui.screens.components.EnhancedPullToRefresh
import com.example.cpbyte_portal.presentation.ui.screens.tracker.components.EnhancedDrawerContent
import com.example.cpbyte_portal.presentation.ui.screens.tracker.components.TrackerCards
import com.example.cpbyte_portal.presentation.ui.screens.tracker.components.UserInfoCard
import com.example.cpbyte_portal.presentation.ui.screens.tracker.components.dashboard.ProjectDisplayingCard
import com.example.cpbyte_portal.presentation.ui.screens.tracker.components.dashboard.SelectorTabsForDashboard
import com.example.cpbyte_portal.presentation.ui.screens.tracker.components.dashboard.StatsCard
import com.example.cpbyte_portal.presentation.ui.screens.tracker.components.dashboard.TechnicalSkillsSection
import com.example.cpbyte_portal.presentation.viewmodel.AuthViewModel
import com.example.cpbyte_portal.presentation.viewmodel.CoordinatorViewModel
import com.example.cpbyte_portal.presentation.viewmodel.EventViewModel
import com.example.cpbyte_portal.presentation.viewmodel.SettingsViewModel
import com.example.cpbyte_portal.presentation.viewmodel.TrackerViewModel
import com.example.cpbyte_portal.presentation.viewmodel.UserViewModel
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
    userViewModel: UserViewModel,
    eventViewModel: EventViewModel,
    settingsViewModel: SettingsViewModel,
    coordinatorViewModel: CoordinatorViewModel,
) {
    val context = LocalContext.current
    var logoutTriggered by remember { mutableStateOf(false) }

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val libraryId = sharedPrefsManager.getProfile()?.data?.library_id
    Log.d("TrackerScreen", "Library ID: $libraryId")

    LaunchedEffect(Unit) {
        if (!logoutTriggered && libraryId == null) {
            Log.d("TrackerScreenBolteee", "Library ID is null, redirecting to Login")
            logoutTriggered = true
            navController.navigate(Routes.Login.route) {
                popUpTo(0) { inclusive = true }
            }
        } else if (!logoutTriggered && libraryId != null) {
            Log.d("TrackerScreen", "Calling loadDataIfNotLoadedForDashboard()")
            trackerViewModel.loadDataIfNotLoadedForDashboard(libraryId)
        }
    }

    if (logoutTriggered) return

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
                libraryId = libraryId ?: "",
                onLogoutClicked = onLogoutClicked,
                authViewModel = authViewModel,
                sharedPrefsManager = sharedPrefsManager,
                userViewModel = userViewModel,
                eventViewModel = eventViewModel,
                settingsViewModel = settingsViewModel,
                coordinatorViewModel = coordinatorViewModel
            )
        }

        else -> {
            DashboardContent(
                userDashboard = null,
                navController = navController,
                currentRoute = currentRoute,
                displayName = sharedPrefsManager.getProfile()?.data?.name ?: "User",
                trackerViewModel = trackerViewModel,
                libraryId = libraryId ?: "",
                onLogoutClicked = onLogoutClicked,
                authViewModel = authViewModel,
                sharedPrefsManager = sharedPrefsManager,
                userViewModel = userViewModel,
                eventViewModel = eventViewModel,
                settingsViewModel = settingsViewModel,
                coordinatorViewModel = coordinatorViewModel
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DashboardContent(
    authViewModel: AuthViewModel,
    userDashboard: UserDashboardResponse?, // Nullable now
    navController: NavHostController,
    currentRoute: String?,
    displayName: String,
    trackerViewModel: TrackerViewModel,
    libraryId: String,
    onLogoutClicked: () -> Unit,
    sharedPrefsManager: SharedPrefsManager,
    userViewModel: UserViewModel,
    eventViewModel: EventViewModel,
    settingsViewModel: SettingsViewModel,
    coordinatorViewModel: CoordinatorViewModel,
) {
    // Use safe calls and provide defaults if userDashboard is null
    val tracker = userDashboard?.tracker
    val leetcode = tracker?.leetcode
    val github = tracker?.github
    val skills = tracker?.skills ?: emptyList()
    val projects = tracker?.projects ?: emptyList()
    val heatMap = tracker?.past5?.toIntArray() ?: IntArray(0)

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
            if (userDashboard != null && tracker != null && leetcode != null && github != null) {
                EnhancedDrawerContent(
                    navController = navController,
                    displayName = displayName,
                    closeDrawer = { scope.launch { drawerState.close() } },
                    onLogoutClicked = onLogoutClicked,
                    skills = skills,
                    libraryId = userDashboard.library_id,
                    leetcode = leetcode.username,
                    github = github.username,
                    authViewModel = authViewModel,
                    sharedPrefsManager = sharedPrefsManager,
                    userViewModel = userViewModel,
                    eventViewModel = eventViewModel,
                    settingsViewModel = settingsViewModel,
                    trackerViewModel = trackerViewModel,
                    coordinatorViewModel = coordinatorViewModel
                )
            } else {
                CustomLoader("Loading profile...")
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                CommonHeader(
                    text = "Profile",
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "Menu",
                                tint = MaterialTheme.colorScheme.onBackground
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
                if (userDashboard == null || tracker == null || leetcode == null || github == null) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        CustomLoader(text = "Loading dashboard...")
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp)
                    ) {
                        UserInfoCard(userDashboard = userDashboard)

                        Spacer(modifier = Modifier.height(20.dp))

                        TrackerCards(
                            leetcode = leetcode,
                            tracker = tracker,
                            heatMap = heatMap
                        )

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
                                0 -> StatsCard(
                                    leetcode = leetcode,
                                    github = github
                                )

                                1 -> ProjectDisplayingCard(projects)
                                2 -> TechnicalSkillsSection(skills = skills)
                            }
                        }
                    }
                }
            }
        }
    }
}
