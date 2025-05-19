package com.example.cpbyte_portal.presentation.ui.navigation

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object AccountSettings : Routes("account_settings")
    object Schedule : Routes("schedule")
    data object AddEvent : Routes("add_event/{selectedDate}") {
        fun createRoute(selectedDate: String): String {
            return "add_event/$selectedDate"
        }
    }

    data object MarkAttendance : Routes("mark_attendance")
    object AddProject : Routes("add_project")
}