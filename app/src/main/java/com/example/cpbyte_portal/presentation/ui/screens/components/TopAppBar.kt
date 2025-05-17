package com.example.cpbyte_portal.presentation.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.LibraryAdd
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.presentation.ui.screens.CodingPlatformsScreen
import com.example.cpbyte_portal.presentation.ui.screens.SkillsScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun CPNavigationDrawer() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val configuration = LocalConfiguration.current
    var currentScreen by remember { mutableStateOf("") }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color.Black,
                modifier = Modifier.width(180.dp)
                // Adjust width as per your design
            ) {
                // 🟦 Top Logo + Title
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                ) {
                    UserLogoCircular(R.drawable.cp_byte_logo)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "CPBYTE",
                        color = Color(0xFF0EC1E7),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                // 🏠 MAIN MENU Section

                DrawerItem(Icons.Default.Help, "Help Center") {}

                DrawerItemForImage(R.drawable.leetcode_logoo, "Leetcode") {
                    currentScreen = "leetcode"
                }

                DrawerItemForImage(R.drawable.github, "Github") {
                    currentScreen = "github"
                }

                when(currentScreen){
                    "leetcode" -> CallLeetcodeScreen()
                    "github" -> CallGithubScreen()
//                    "skill" -> CallSkillsScreen()
                }
                DrawerItem(Icons.Default.LibraryAdd, "Skill") {
                    currentScreen = "skiils"
                }



                Spacer(modifier = Modifier.height(410.dp))

                DrawerItem(Icons.Default.Logout, "Log Out", tint = Color.Red) {}
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                CenterAlignedTopAppBar(
                    scrollBehavior = scrollBehavior,
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Black,
                        titleContentColor = Color.White,
                    ),
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menu",
                                tint = Color.White
                            )
                        }
                    },
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(R.drawable.cpbyte_logo),
                                contentDescription = "cp_byte_logo",
                                modifier = Modifier
                                    .height(54.dp)
                                    .width(74.dp)
                            )
                            Spacer(modifier = Modifier.width(0.dp))
                            Text(
                                "CP",
                                color = Color.White,
                                style = MaterialTheme.typography.labelSmall,
                                fontSize = 22.sp
                            )
                            Text(
                                "BYTE",
                                color = Color(0xFF0EC1E7),
                                style = MaterialTheme.typography.labelSmall,
                                fontSize = 22.sp
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            ScrollContent(innerPadding)
        }
    }
}

@Composable
fun ScrollContent(innerPadding: PaddingValues) {

}

@Composable
fun DrawerItem(icon: ImageVector, label: String, tint: Color = Color.White, onclick: () -> Unit) {
    Button(
        onclick, colors = ButtonColors(
            containerColor = Color.Black,
            contentColor = Color.White,
            disabledContainerColor = Color.Black,
            disabledContentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 18.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = tint,
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = label, color = tint, fontSize = 16.sp)
        }
    }
}

@Composable
fun DrawerItemForImage(image: Int, label: String, tint: Color = Color.White, onclick: () -> Unit) {
    Button(
        onclick,
        colors = ButtonColors(
            containerColor = Color.Black,
            contentColor = Color.White,
            disabledContainerColor = Color.Black,
            disabledContentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 18.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = image),
                contentDescription = label,
                tint = tint,
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = label, color = tint, fontSize = 16.sp)
        }
    }
}

@Composable
fun CallLeetcodeScreen(modifier: Modifier = Modifier) {
    val initialUsernames = mapOf(
        "Leetcode" to "",
    )
    val platformIcons = mapOf(
        "Leetcode" to painterResource(id = R.drawable.leetcode_logoo)
    )
    CodingPlatformsScreen(initialUsernames,platformIcons)
}
@Composable
fun CallGithubScreen(modifier: Modifier = Modifier) {
    val initialUsernames = mapOf(
        "Github" to "",
    )
    val platformIcons = mapOf(
        "Github" to painterResource(id = R.drawable.github)
    )
    CodingPlatformsScreen(initialUsernames,platformIcons)
}
@Composable
fun CallSkillsScreen(modifier: Modifier = Modifier) {
    SkillsScreen()
}