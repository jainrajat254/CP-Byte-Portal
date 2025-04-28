sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Member : Screen("memberdetail")
    object Settings : Screen("setting")
    object Schedule : Screen("schedule")
    object Attendance : Screen("attendance")
}