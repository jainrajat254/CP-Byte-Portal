package com.example.cpbyte_portal.presentation.ui.navigation

sealed class Routes(val route: String) {
    data object Splash : Routes("splash")
    data object Login : Routes("login")
    data object Home : Routes("home")
    data object AccountSettings : Routes("account_settings")
    data object Schedule : Routes("schedule")
    data object TrackerDashboard : Routes("tracker_dashboard")
    data object AddEvent : Routes("add_event/{selectedDate}") {
        fun createRoute(selectedDate: String): String {
            return "add_event/$selectedDate"
        }
    }

    data object Leaderboard : Routes("leaderboard")
    data object CheckAttendance : Routes("check_attendance")
    data object MarkAttendance : Routes("mark_attendance/{subject}/{date}") {
        fun createRoute(subject: String, date: String): String {
            return "mark_attendance/$subject/$date"
        }
    }

    data object AddProject : Routes("add_project")
    data object RemoveProject : Routes("remove_project")
    data object EditPassword : Routes("edit_password")
    data object EditSkills : Routes("edit_skills/{skills}") {
        fun createRoute(skills: List<String>): String {
            val encodedSkills = skills.joinToString(",")
            return "edit_skills/$encodedSkills"
        }
    }

    data object AddLeetcode : Routes("add_leetcode/{leetcode}/{libraryId}") {
        fun createRoute(leetcode: String, libraryId: String): String {
            return "add_leetcode/$leetcode/$libraryId"
        }
        const val ARG_LEETCODE = "leetcode"
        const val ARG_LIBRARY_ID = "libraryId"
    }

    data object AddGithub : Routes("add_github/{github}/{libraryId}") {
        fun createRoute(github: String, libraryId: String): String {
            return "add_github/$github/$libraryId"
        }

        const val ARG_GITHUB = "github"
        const val ARG_LIBRARY_ID = "libraryId"
    }
}