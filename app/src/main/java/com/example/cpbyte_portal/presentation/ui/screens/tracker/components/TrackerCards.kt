package com.example.cpbyte_portal.presentation.ui.screens.tracker.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cpbyte_portal.domain.model.LeetCode
import com.example.cpbyte_portal.domain.model.Tracker
import com.example.cpbyte_portal.presentation.ui.screens.tracker.components.dashboard.ProgressTrackerViewCard

@Composable
fun TrackerCards(
    leetcode: LeetCode,
    tracker: Tracker,
    heatMap: IntArray,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ProgressTrackerViewCard(
            title = "Solved",
            totalQuestions = leetcode.solvedProblems.toString(),
            color = MaterialTheme.colorScheme.surfaceVariant,
            arr = heatMap
        )
        ProgressTrackerViewCard(
            title = "Ranking",
            totalQuestions = tracker.rank.toString(),
            color = MaterialTheme.colorScheme.surfaceVariant,
            arr = heatMap
        )
        ProgressTrackerViewCard(
            title = "Heatmap",
            totalQuestions = heatMap.sum().toString(),
            color = MaterialTheme.colorScheme.surfaceVariant,
            arr = heatMap
        )
    }
}