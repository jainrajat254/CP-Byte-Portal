package com.example.cpbyte_portal.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.twotone.DateRange
import androidx.compose.material.icons.twotone.MailOutline
import androidx.compose.material.icons.twotone.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.presentation.ui.navigation.BottomBar
import com.example.cpbyte_portal.presentation.ui.screens.components.AccountInfoShowingCard
import com.example.cpbyte_portal.presentation.ui.screens.components.AccountInfoShowingCardImage
import com.example.cpbyte_portal.presentation.ui.screens.components.CommonHeader
import com.example.cpbyte_portal.presentation.ui.screens.components.poppinsFamily
import com.example.cpbyte_portal.util.SharedPrefsManager

@Composable
fun AccountSetting(sharedPrefsManager: SharedPrefsManager, navController: NavHostController) {
    val scrollableState = rememberScrollState()
    val profile = sharedPrefsManager.getProfile()?.data

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            BottomBar(navController, currentRoute)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollableState)
                .background(Color(0xFF111111)) // Dark background
                .padding(20.dp)
        ) {
            CommonHeader(text = stringResource(id = R.string.account_screen_heading))

            if (profile != null) {
                // Main Profile Card
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                        .border(1.dp, Color(0xFF374151), RoundedCornerShape(16.dp))
                )
                {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Avatar
                        Box(contentAlignment = Alignment.BottomEnd) {
                            Image(
                                painter = painterResource(id = R.drawable.baseline_person_24),
                                contentDescription = stringResource(R.string.profile_pic),
                                modifier = Modifier
                                    .size(90.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, Color(0xFF374151), CircleShape)
                            )

                            Icon(
                                imageVector = Icons.Filled.CameraAlt,
                                contentDescription = "Edit",
                                modifier = Modifier
                                    .size(26.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFF007BFF)) // Lighter and more vibrant blue background
                                    .padding(4.dp)
                                    .clickable { /* TODO: Avatar change handler */ },
                                tint = Color.White // White icon tint for better contrast
                            )

                        }

                        Spacer(Modifier.height(12.dp))

                        // Name
                        Text(
                            text = profile.name,
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = poppinsFamily
                        )

                        // Library ID
                        profile.library_id?.let {
                            Text(
                                text = it,
                                color = Color(0xFF90A4AE), // light steel blue
                                fontSize = 12.sp,
                                fontFamily = poppinsFamily
                            )
                        }

                        Spacer(Modifier.height(16.dp))

                        // Info Cards
                        AccountInfoShowingCard(
                            title = stringResource(R.string.role),
                            textFieldValue = profile.role ?: "N/A",
                            image = Icons.TwoTone.Person
                        )
                        AccountInfoShowingCard(
                            title = stringResource(R.string.year),
                            textFieldValue = profile.year?.toString() ?: "N/A",
                            image = Icons.TwoTone.DateRange
                        )
                        AccountInfoShowingCard(
                            title = stringResource(R.string.userEmail),
                            textFieldValue = profile.email ?: "N/A",
                            image = Icons.TwoTone.MailOutline
                        )
                        AccountInfoShowingCardImage(
                            title = "GitHub",
                            textFieldValue = profile.id ?: "N/A",
                            image = R.drawable.github
                        )
                        AccountInfoShowingCardImage(
                            title = "Leetcode",
                            textFieldValue = profile.id ?: "N/A",
                            image = R.drawable.leetcode_logoo
                        )
                    }
                }
            } else {
                Text(
                    text = "Failed to load profile.",
                    color = Color.Red,
                    modifier = Modifier.padding(top = 32.dp)
                )
            }
        }
    }
}
