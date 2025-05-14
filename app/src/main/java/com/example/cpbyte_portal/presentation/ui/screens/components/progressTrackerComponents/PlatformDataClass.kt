package com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents

// Data class representing a platform with its name, logo, and URL
data class PlatformDataClass(
    // The name of the platform (e.g., GitHub, LeetCode, etc.)
    val name: String,

    // The resource ID of the logo image for the platform
    val logoResId: Int,

    // The URL of the platform, typically the homepage or profile URL
    val url: String
)