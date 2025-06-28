package com.example.cpbyte_portal.presentation.ui.screens.leaderboard.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.cpbyte_portal.domain.model.Leaderboard
import com.example.cpbyte_portal.presentation.ui.screens.components.CustomLoader
import com.example.cpbyte_portal.presentation.ui.screens.components.EnhancedPullToRefresh

@Composable
fun LeaderboardContentDisplay(
    loading: Boolean,
    error: String?,
    leaderboardEntries: List<Leaderboard>,
    navController: NavController,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
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