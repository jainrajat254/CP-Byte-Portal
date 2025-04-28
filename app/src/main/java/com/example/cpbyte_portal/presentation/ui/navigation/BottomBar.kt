import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun BottomBar() {
   NavigationBar(
       containerColor = Color.Black,
       contentColor = Color.White
   ) {
       var selectedItem by remember { mutableStateOf(0) }
       NavigationBarItem(
           icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
           onClick = { },
           colors = NavigationBarItemDefaults.colors(
               selectedIconColor = Color.White,
               unselectedIconColor = Color.Gray,
               indicatorColor = Color.Transparent // for background color while selecting icon
           ),
           selected = selectedItem == 0,
           label = null,
           alwaysShowLabel = false
       )
       NavigationBarItem(
           icon = { Icon(Icons.Default.DateRange, contentDescription = "Calender") },
           onClick = { },
           colors = NavigationBarItemDefaults.colors(
               selectedIconColor = Color.White,
               unselectedIconColor = Color.Gray,
               indicatorColor = Color.Transparent
           ),
           selected = selectedItem == 1,
           label = null,
           alwaysShowLabel = false
       )
       NavigationBarItem(
           icon = { Icon(Icons.Default.CheckCircle, contentDescription = "Checklist") },
           onClick = { },
           colors = NavigationBarItemDefaults.colors(
               selectedIconColor = Color.White,
               unselectedIconColor = Color.Gray,
               indicatorColor = Color.Transparent
           ),
           selected = selectedItem == 2,
           label = null,
           alwaysShowLabel = false
       )
       NavigationBarItem(
           icon = { Icon(Icons.Default.Settings, contentDescription = "Setting") },
           onClick = { },
           colors = NavigationBarItemDefaults.colors(
               selectedIconColor = Color.White,
               unselectedIconColor = Color.Gray,
               indicatorColor = Color.Transparent
           ),
           selected = selectedItem== 3,
           label = null,
           alwaysShowLabel = false
       )
   }
}