package com.example.cpbyte_portal.presentation.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.*

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun BottomBar() {
    var selectedItem by remember { mutableStateOf(0) }

    NavigationBar(
        containerColor = Color.Black,
        contentColor = Color.White
    ) {
        val items = listOf(
            Icons.Default.Home to "Home",
            Icons.Default.DateRange to "Calendar",
            Icons.Default.CheckCircle to "Checklist",
            Icons.Default.Settings to "Settings"
        )

        items.forEachIndexed { index, (icon, description) ->
            NavigationBarItem(
                icon = { Icon(icon, contentDescription = description) },
                selected = selectedItem == index,
                onClick = { selectedItem = index },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.Gray,
                    indicatorColor = Color.Transparent
                ),
                label = null,
                alwaysShowLabel = false
            )
        }
    }
}
