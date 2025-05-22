package com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cpbyte_portal.domain.model.Github
import com.example.cpbyte_portal.domain.model.LeetCode

@Composable
fun ProgrammingStatsComposable(
    leetcode: LeetCode,
    github: Github,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        DsaStatsCard(leetcode)
        Spacer(modifier = Modifier.height(20.dp))
        GithubStatsCard(
            github.contributions,
            github.prs,
            github.repos
        )
    }
}
