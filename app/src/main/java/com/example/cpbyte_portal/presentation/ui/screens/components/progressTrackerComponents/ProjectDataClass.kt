package com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents

// Data class representing a project with its user, cover image, name, GitHub URL, and description
data class ProjectDataClass(
    // The user who owns or created the project (e.g., username or full name)
    val user: String,

    // The resource ID for the cover image of the project (used for displaying the image in the UI)
    val coverImage: Int,

    // The name of the project
    val projectName: String,

    // The URL to the GitHub repository of the project
    val gitHubUrl: String,

    // A short description of the project, providing context or details
    val description: String
)