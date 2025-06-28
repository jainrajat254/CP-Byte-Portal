package com.example.cpbyte_portal.presentation.ui.screens.leaderboard.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.cpbyte_portal.presentation.viewmodel.TrackerViewModel
import com.example.cpbyte_portal.util.ResultState

@Composable
fun ClubLeaderboard(
    modifier: Modifier = Modifier,
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
                modifier = modifier,
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
                modifier = modifier,
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
                    modifier = modifier,
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
                modifier = modifier,
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