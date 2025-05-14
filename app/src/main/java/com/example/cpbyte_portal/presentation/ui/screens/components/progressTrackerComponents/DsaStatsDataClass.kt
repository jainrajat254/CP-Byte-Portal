package com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents

// Data class representing the statistics of DSA questions
data class DsaStatsDataClass(
    // The number of easy questions solved in DSA
    val numberOfEasyQuestions: Int,

    // The number of medium difficulty questions solved in DSA
    val numberOfMediumQuestions: Int,

    // The number of hard questions solved in DSA
    val numberOfHardQuestions: Int,

    // The total number of DSA questions solved (easy + medium + hard)
    val totalQuestions: Int
)