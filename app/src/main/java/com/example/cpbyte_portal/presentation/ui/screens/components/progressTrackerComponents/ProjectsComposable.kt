package com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cpbyte_portal.domain.model.ProjectResponse

@Composable
fun ProjectsComposable(
    projects: List<ProjectResponse>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        projects.forEach {
            ProjectDisplayingCard(it)
        }
    }
}