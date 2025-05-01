package com.example.cpbyte_portal.presentation.ui.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Attendance : Screen("attendance")
    object Member : Screen("member_detail")
    object Settings : Screen("setting")
    object Schedule : Screen("schedule")
}