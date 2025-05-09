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
import androidx.compose.material.icons.twotone.MailOutline
import androidx.compose.material.icons.twotone.RocketLaunch
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.presentation.ui.screens.components.AccountInfoShowingCard
import com.example.cpbyte_portal.presentation.ui.screens.components.AccountScreenCard
import com.example.cpbyte_portal.presentation.ui.screens.components.ChangePasswordCard
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
    userEmail: String
) {
    val scrollableState = rememberScrollState() //Scroll State for vertical Scroll

    Column(
        /* Parent Column This Consists of -
        * 1st Card : for Displaying the Top Title of the Screen (User Details)
        * 2nd Card : for Displaying the User Avatar and Data (User Details)
        * 3rd Card : for Having Password Changing Function.
        * */
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollableState)
            .background(Color(0xFF111111)),
    ) {
        /*Displaying User Details Heading*/
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(start = 25.dp, top = 19.dp, bottom = 10.dp)
        ) {
            //card for line to the left of Member Details
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF0EC1E7)
                ),
                modifier = Modifier
                    .padding(top = 2.dp)
                    .width(6.dp)
            ) {
                Text(
                    text = "|",
                    fontSize = 35.sp,
                    color = Color(0xFF0EC1E7)
                )
            }
            Spacer(Modifier.padding(start = 7.dp))
            Text(
                text = stringResource(R.string.account_screen_heading),
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 25.sp,
            )
        }


        /*Card for Profile Overview */
        AccountScreenCard(
            colors = CardDefaults.cardColors(
                containerColor = cardBgColor
            ),
            modifier = Modifier
                .width(400.dp)
                .padding(35.dp, 14.dp)
                .border(
                    width = 1.5.dp,
                    color = cardBorderColor,
                    shape = RoundedCornerShape(18.dp)
                ),
            shape = RoundedCornerShape(18.dp),
            content = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.height((5.dp)))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(21.dp, 15.dp, 0.dp, 0.dp)
                            .fillMaxSize()
                    ) {
                        Box(
                            modifier = Modifier.align(Alignment.CenterVertically),
                            contentAlignment = Alignment.BottomEnd,
                        ) {
                            /* User Profile Picture or Avatar */
                            Image(
                                painter = painterResource(id = userPfp),
                                contentDescription = stringResource(R.string.profile_pic),
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(CircleShape)
                                    .border(
                                        color = Color(0xFF374151),
                                        shape = CircleShape,
                                        width = 2.5.dp
                                    )
                            )
                            /* Camera Image Button for Changing Pfp */
                            Image(
                                imageVector = Icons.Filled.Camera,
                                contentDescription = "Profile Pic",
                                colorFilter = ColorFilter.tint(Color(0xff000000)),
                                modifier = Modifier
                                    .clickable {         //Function will be called for changing Avatar of the User

                                    }
                                    .clip(CircleShape)
                                    .size(23.dp)
                                    .background(Color(0xFFBBBCBD))
                            )
                        }
                        Column(
                            // User Information Like Name Branch and Lib.Id Displayed in this Column
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier
                                .padding(start = 15.dp)
                                .fillMaxSize(),
                        ) {
                            Text(
                                text = userName,
                                fontWeight = FontWeight.W900,
                                fontSize = 23.sp,
                                color = textPrimaryColor,
                            )
                            Text(
                                text = userBranch,
                                fontWeight = FontWeight.SemiBold,
                                style = MaterialTheme.typography.labelLarge,
                                color = textSecondaryColor,
                            )
                            Text(
                                text = userID,
                                style = MaterialTheme.typography.labelLarge,
                                color = textSecondaryColor,
                            )
                        }
                    }
                    Spacer(Modifier.padding(7.dp))
                    AccountInfoShowingCard(
                        title = stringResource(R.string.role),
                        textFieldValue = userRole,
                        image = Icons.Filled.BuildCircle
                    )
                    AccountInfoShowingCard(
                        title = stringResource(R.string.year),
                        textFieldValue = userYear,
                        image = Icons.TwoTone.RocketLaunch
                    )
                    AccountInfoShowingCard(
                        title = stringResource(R.string.userEmail),
                        textFieldValue = userEmail,
                        image = Icons.TwoTone.MailOutline
                    )
                    Spacer(Modifier.padding(7.dp))
                }
            }
        )

        /* Function Called for Displaying Changing Password Screen */
        ChangePasswordCard(userPassword)
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
            userPassword = "12345678"
        )
    }
}
