package com.example.cpbyte_portal.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BuildCircle
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.CameraEnhance
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.twotone.AccountBox
import androidx.compose.material.icons.twotone.DateRange
import androidx.compose.material.icons.twotone.Face
import androidx.compose.material.icons.twotone.Grade
import androidx.compose.material.icons.twotone.MailOutline
import androidx.compose.material.icons.twotone.Person
import androidx.compose.material.icons.twotone.RocketLaunch
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.domain.model.LeetCode
import com.example.cpbyte_portal.presentation.ui.screens.components.AccountInfoShowingCard
import com.example.cpbyte_portal.presentation.ui.screens.components.AccountInfoShowingCardImage
import com.example.cpbyte_portal.presentation.ui.screens.components.AccountScreenCard
import com.example.cpbyte_portal.presentation.ui.screens.components.ChangePasswordCard
import com.example.cpbyte_portal.presentation.ui.screens.components.poppinsFamily
import com.example.cpbyte_portal.presentation.ui.theme.cardBgColor
import com.example.cpbyte_portal.presentation.ui.theme.cardBorderColor
import com.example.cpbyte_portal.presentation.ui.theme.textPrimaryColor
import com.example.cpbyte_portal.presentation.ui.theme.textSecondaryColor

@Composable
fun AccountSetting(
    userID: String,
    userName: String,
    userBranch: String,
    userPfp: Int,
    userPassword: String,
    userRole: String,
    userYear: String,
    userEmail: String,
    userGithub: String,
    userLeetCode: String
) {
    val scrollableState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollableState)
            .background(Color(0xFF111111))
            .padding(20.dp)
    ) {
        // Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(5.dp)
                    .height(28.dp)
                    .background(Color(0xFF0EC1E7), shape = RoundedCornerShape(5.dp))
            )
            Spacer(Modifier.width(10.dp))
            Text(
                text = stringResource(R.string.account_screen_heading),
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
//                fontFamily = poppinsFamily
            )
        }

        // Profile Overview Card
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFF17191d)),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(16.dp)

                    )
//                .shadow(
//                    elevation = 8.dp,
//                    shape = RoundedCornerShape(16.dp),
//                    ambientColor = Color.White, // light gray shadow
//                    spotColor = Color.White
//                )
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Profile Picture
                Box(
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Image(
                        painter = painterResource(id = userPfp),
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
                            .background(Color(0xFFBBBBBB))
                            .padding(4.dp)
                            .clickable {
                                // Avatar change logic
                            },
                        tint = Color.Black
                    )
                }

                Spacer(Modifier.height(12.dp))

                // User Info
                Text(
                    text = userName,
                    color = textPrimaryColor,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppinsFamily
                )
                Text(
                    text = userID,
                    color = textSecondaryColor,
                    style = MaterialTheme.typography.labelMedium,
                    fontFamily = poppinsFamily
                )

                Spacer(Modifier.height(16.dp))

                // Info Cards
                AccountInfoShowingCard(
                    title = stringResource(R.string.role),
                    textFieldValue = userRole,
                    image = Icons.TwoTone.Person
                )
                AccountInfoShowingCard(
                    title = stringResource(R.string.year),
                    textFieldValue = userYear,
                    image = Icons.TwoTone.DateRange
                )
                AccountInfoShowingCard(
                    title = stringResource(R.string.userEmail),
                    textFieldValue = userEmail,
                    image = Icons.TwoTone.MailOutline
                )
                AccountInfoShowingCardImage(
                    title = "GitHub",
                    textFieldValue = userGithub,
                    image = R.drawable.github
                )
                AccountInfoShowingCardImage(
                    title = "Leetcode",
                    textFieldValue = userLeetCode,
                    image = R.drawable.leetcode_logoo
                )

            }
        }

        // Future Enhancement: Password Card
//        ChangePasswordCard(userPassword)
    }
}


@Preview
@Composable
private fun AccountDetailScreenPreview() {
    val branch = "CSIT"
    Column {
        AccountSetting(
            "2428CSIT1872",
            "Mradul Gupta",
            userBranch = branch,
            userPfp = R.drawable.club_logo,
            userEmail = "mkjmp77@gmail.com",
            userYear = "First",
            userRole = "Member",
            userPassword = "12345678",
            userGithub = "mradul1245",
            userLeetCode = "mradul1245"
        )
    }
}
