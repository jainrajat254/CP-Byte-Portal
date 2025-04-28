import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cpbyte_portal.presentation.ui.screens.LoginScreen
import com.example.cpbyte_portal.presentation.ui.screens.MemberDetailScreen

@Composable
fun  NavigationGraph(navController: NavHostController,modifier: Modifier = Modifier) {
       NavHost(
           navController = navController,
           startDestination = Screen.Splash.route
       ) {
          composable(Screen.Splash.route) {

          }
          composable(Screen.Login.route) {
            LoginScreen(navController)
          }
          composable(Screen.Member.route) {
             MemberDetailScreen(navController)
          }
          composable(Screen.Settings.route) {

          }
           composable(Screen.Schedule.route) {

           }
           composable(Screen.Attendance.route) {

           }
       }
}
