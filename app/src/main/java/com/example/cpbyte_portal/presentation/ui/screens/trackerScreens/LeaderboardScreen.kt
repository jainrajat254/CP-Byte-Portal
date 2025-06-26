package com.example.cpbyte_portal.presentation.ui.screens.trackerScreens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil3.compose.AsyncImage
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.domain.model.Leaderboard
import com.example.cpbyte_portal.presentation.ui.navigation.BottomBar
import com.example.cpbyte_portal.presentation.ui.screens.components.CommonHeader
import com.example.cpbyte_portal.presentation.ui.screens.components.CustomLoader
import com.example.cpbyte_portal.presentation.ui.screens.components.EnhancedPullToRefresh
import com.example.cpbyte_portal.presentation.ui.theme.CPByteTheme
import com.example.cpbyte_portal.presentation.ui.theme.SuccessGreen
import com.example.cpbyte_portal.presentation.ui.theme.WarningRed
import com.example.cpbyte_portal.presentation.viewmodel.TrackerViewModel
import com.example.cpbyte_portal.util.ResultState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LeetCodeLeaderboardScreen(
    vm: TrackerViewModel,
    navController: NavHostController,
) {
    var showFilterDialog by remember { mutableStateOf(false) }
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CommonHeader(text = "Leaderboard",
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomBar(navController, currentRoute)
        },
        content = {
            LeaderboardContent(
                vm = vm,
                navController = navController,
                showFilterDialog = showFilterDialog,
                setShowFilterDialog = { showFilterDialog = it }
            )
        },
        floatingActionButton = {
            FilterFAB(onClick = { showFilterDialog = true })
        },
    )
}

@Composable
fun LeaderboardContent(
    vm: TrackerViewModel,
    navController: NavController,
    showFilterDialog: Boolean,
    setShowFilterDialog: (Boolean) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.Top
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        ClubLeaderboard(
            vm = vm,
            navController = navController,
            showFilterDialog = showFilterDialog,
            setShowFilterDialog = setShowFilterDialog
        )
    }
}


@Composable
private fun ClubLeaderboard(
    vm: TrackerViewModel,
    navController: NavController,
    showFilterDialog: Boolean,
    setShowFilterDialog: (Boolean) -> Unit,
) {
    val getAllState by vm.getAllState.collectAsState()
    var selectedYear by remember { mutableStateOf("All") }
    var minQuestions by remember { mutableStateOf("") }

    var isRefreshing by remember {
        mutableStateOf(false)
    }

    val onRefresh = {
        isRefreshing = true
        vm.refreshLeaderBoard()
        isRefreshing = false
    }

    LaunchedEffect(Unit) {
        vm.loadDataIfNotLoadedForLeaderBoard()
    }


    when (val state = getAllState) {
        is ResultState.Loading -> {
            Log.d("ClubLeaderboard", "State: Loading")
            LeaderboardContentDisplay(
                loading = true,
                error = null,
                leaderboardEntries = emptyList(),
                navController = navController,
                isRefreshing = isRefreshing,
                onRefresh = onRefresh
            )
        }

        is ResultState.Failure -> {
            Log.e("ClubLeaderboard", "State: Failure - ${state.error.message}")
            LeaderboardContentDisplay(
                loading = false,
                error = state.error.message ?: "Something went wrong",
                leaderboardEntries = emptyList(),
                navController = navController,
                isRefreshing = isRefreshing,
                onRefresh = onRefresh
            )
        }

        is ResultState.Success -> {
            val allEntries = state.data
            Log.d("ClubLeaderboard", "State: Success - Entries loaded: ${allEntries.size}")

            val years =
                listOf("All") + allEntries.map { it.year.toString() }.distinct().sortedDescending()

            val filteredList = allEntries.filter { entry ->
                val matchesYear = selectedYear == "All" || entry.year.toString() == selectedYear
                val matchesQuestions =
                    minQuestions.toIntOrNull()?.let { entry.solvedProblems >= it } ?: true
                matchesYear && matchesQuestions
            }

            Log.d(
                "ClubLeaderboard",
                "Filtered entries: ${filteredList.size}, Year: $selectedYear, MinQ: $minQuestions"
            )

            if (showFilterDialog) {
                Log.d("ClubLeaderboard", "Showing FilterBottomSheet")
                FilterBottomSheet(
                    years = years,
                    selectedYear = selectedYear,
                    onYearSelected = {
                        Log.d("ClubLeaderboard", "Year selected: $it")
                        selectedYear = it
                    },
                    minQuestions = minQuestions,
                    onMinQuestionsChange = {
                        Log.d("ClubLeaderboard", "Min Questions Changed: $it")
                        minQuestions = it
                    },
                    onDismiss = {
                        Log.d("ClubLeaderboard", "Filter dialog dismissed")
                        setShowFilterDialog(false)
                    }
                )
            }

            LeaderboardContentDisplay(
                loading = false,
                error = null,
                leaderboardEntries = filteredList,
                navController = navController,
                isRefreshing = isRefreshing,
                onRefresh = onRefresh
            )
        }

        ResultState.Idle -> {
            Log.d("ClubLeaderboard", "State: Idle - No action")
        }
    }
}

@Composable
private fun LeaderboardContentDisplay(
    loading: Boolean,
    error: String?,
    leaderboardEntries: List<Leaderboard>,
    navController: NavController,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
) {
    when {
        loading && leaderboardEntries.isEmpty() -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CustomLoader()
        }

        error != null -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Error: $error", color = MaterialTheme.colorScheme.error)
        }

        else -> {
            // Use the enhanced PullToRefresh
            EnhancedPullToRefresh(
                isRefreshing = isRefreshing,
                onRefresh = onRefresh,
                modifier = Modifier.fillMaxSize()
            ) {
                LeaderboardList(
                    leaderboard = leaderboardEntries,
                    navController = navController,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun LeaderboardList(
    leaderboard: List<Leaderboard>,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    if (leaderboard.isEmpty()) {
        Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No entries found")
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = 84.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                TopThreeSection(users = leaderboard.take(3), navController = navController)
            }
            item {
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = CPByteTheme.brandCyan)
                )
            }
            itemsIndexed(leaderboard) { index, entry ->
                LeaderboardItem(rank = index + 1, entry = entry) {
                }
            }
        }
    }
}


@Composable
private fun TopThreeSection(
    users: List<Leaderboard>,
    navController: NavController,
) {
    val medalColors = listOf(Color(0xFFFFD700), Color(0xFFC0C0C0), Color(0xFFCD7F32))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        users.forEachIndexed { index, user ->
            val imageSize = if (index == 0) 100.dp else if (index == 1) 80.dp else 60.dp
            val columnHeight = if (index == 0) 160.dp else if (index == 1) 140.dp else 120.dp

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .width(100.dp)
                    .height(columnHeight)
            ) {
                Box(
                    modifier = Modifier
                        .size(imageSize)
                        .border(3.dp, medalColors[index], CircleShape)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    if (user.avatar != null) {
                        AsyncImage(
                            model = user.avatar,
                            contentDescription = "${user.id}'s avatar",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Image(
                            painter = painterResource(R.drawable.baseline_person_24),
                            contentDescription = "Profile",

                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                Spacer(Modifier.height(8.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = user.name,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = "@${user.leetcodeUsername}",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

        }
    }
}

@Composable
private fun LeaderboardItem(
    rank: Int,
    entry: Leaderboard,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar
            if (entry.avatar != null) {
                AsyncImage(
                    model = entry.avatar,
                    contentDescription = "${entry.id}'s avatar",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.baseline_person_24),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Name, username, previous activity
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = entry.name,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "@${entry.leetcodeUsername} → ${entry.solvedProblems} solved",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    entry.previous.forEach { activity ->
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .background(
                                    color = if (activity > 0) SuccessGreen else WarningRed,
                                    shape = CircleShape
                                )
                        )
                    }
                }
            }


            Text(
                text = "#$rank",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = CPByteTheme.brandCyan
                )
            )
        }
    }
}


@Composable
fun FilterFAB(onClick: () -> Unit) {
    FloatingActionButton(onClick = { onClick() },containerColor = CPByteTheme.brandCyan) {
        Image(
            painter = painterResource(id = R.drawable.baseline_filter_list_alt_24),
            contentDescription = "Filter",
            modifier = Modifier.padding(8.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    years: List<String>,
    selectedYear: String,
    onYearSelected: (String) -> Unit,
    showActive: Boolean = false,
    isActive: Boolean = false,
    onActiveChange: (Boolean) -> Unit = { },
    minQuestions: String,
    onMinQuestionsChange: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = "Filters", style = MaterialTheme.typography.titleMedium)

            DropdownMenuComponent(
                label = "Year",
                options = years,
                selectedOption = selectedYear,
                onOptionSelected = onYearSelected
            )
            if (showActive) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Status: ${if (isActive) "Active" else "Inactive"}")
                    Switch(checked = isActive, onCheckedChange = onActiveChange)
                }
            }

            OutlinedTextField(
                value = minQuestions,
                onValueChange = { if (it.all { char -> char.isDigit() }) onMinQuestionsChange(it) },
                label = { Text("Min Questions Solved") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { onDismiss() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Apply Filters")
            }
        }
    }
}

@Composable
fun DropdownMenuComponent(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "$label:",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true }
                    .padding(8.dp)
            ) {
                Text(text = selectedOption)
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth() // Ensures the dropdown width matches the parent
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}