package com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents

// Data class representing the statistics of a user's GitHub activity
data class GithubStatsDataClass(
    // The total number of contributions (commits, pull requests, etc.) made by the user
    val totalContributions: Int,

    // The total number of pull requests (PRs) made by the user
    val totalPRs: Int,

    // The total number of repositories created or forked by the user
    val totalRepos: Int
)