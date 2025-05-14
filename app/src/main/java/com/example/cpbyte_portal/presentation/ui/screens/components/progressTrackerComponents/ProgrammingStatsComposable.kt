package com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents

import androidx.compose.runtime.Composable

@Composable
fun ProgrammingStatsComposable(
    dsaStats: DsaStatsDataClass,      // Data class containing DSA-related stats
    githubStats: GithubStatsDataClass // Data class containing GitHub-related stats
) {
    // Display a card with DSA-related statistics (e.g., problems solved, difficulty-wise stats)
    DsaStatsCard(
        dsaStats
    )
    // Display a card with GitHub-related statistics (e.g., contributions, PRs, repositories)
    GithubStatsCard(
        githubStats.totalContributions,
        githubStats.totalPRs,
        githubStats.totalRepos
    )
}