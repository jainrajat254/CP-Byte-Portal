package com.example.cpbyte_portal.presentation.ui.navigation

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Attendance : Routes("attendance")
    object Member : Routes("member_detail")
    object AccountSettings : Routes("account_settings")
    object Schedule : Routes("schedule")
    object AddProject : Routes("add_project")
}