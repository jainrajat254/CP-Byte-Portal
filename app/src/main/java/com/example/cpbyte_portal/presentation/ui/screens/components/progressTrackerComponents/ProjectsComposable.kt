package com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cpbyte_portal.R

@Composable
fun ProjectsComposable(
    projects: List<ProjectDataClass> // List of project data to display
) {

    // Column layout to vertically stack all project cards
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Iterate over each project in the list and display a card for it
        projects.forEach {
            ProjectDisplayingCard(it)
        }
    }

}