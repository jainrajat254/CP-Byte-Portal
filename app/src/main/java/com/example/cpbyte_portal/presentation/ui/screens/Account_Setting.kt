package com.example.cpbyte_portal.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CatchingPokemon
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.BuildCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.presentation.ui.screens.components.AccountScreenCard
import com.example.cpbyte_portal.presentation.ui.screens.components.AccountScreenCustomTextField
import com.example.cpbyte_portal.presentation.ui.screens.components.CPByteTextDisplayBox


@Composable
fun AccountSetting(
    userID: String,
    userName: String,
    userBranch: String,
    userPfp: Int,
    userPassword: String
) {
    var oldPassword by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var newPassword by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF0A0915)),
    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(start = 25.dp, top = 10.dp)
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
            Spacer(Modifier.padding(start = 5.dp))
            Text(
                text = "User Details",
                fontSize = 23.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }


        /*Card for Profile Overview */
        AccountScreenCard(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF0B1327)
            ),
            modifier = Modifier
                .width(400.dp)
                .height(170.dp)
                .padding(35.dp, 14.dp),
            shape = RoundedCornerShape(18.dp),
            content = {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.padding((1.dp)))
                    Row(
                        modifier = Modifier
                            .padding(21.dp, 15.dp, 0.dp, 0.dp)
                            .wrapContentSize()
                    ) {
                        Box(
                            modifier = Modifier.align(Alignment.CenterVertically),
                            contentAlignment = Alignment.BottomEnd,
                        ) {
                            /* User Profile Picture or Avatar */
                            Image(
                                painter = painterResource(id = userPfp),
                                contentDescription = "Profile Pic",
                                modifier = Modifier
                                    .size(100.dp, 100.dp)
                                    .clip(CircleShape)
                                    .border(
                                        color = Color(0xFF0EC2E0),
                                        shape = CircleShape,
                                        width = 2.5.dp
                                    )
                            )
                            /* Camera Image Button for Changing Pfp */
                            Image(
                                painter = painterResource(id = R.drawable.camer_pfp_selector),
                                contentDescription = "Profile Pic",
                                colorFilter = ColorFilter.tint(Color.White),
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(26.dp, 26.dp)
                                    .background(Color(0xFF0B1327))
                            )
                        }
                        Column(
                            modifier = Modifier
                                .padding(8.dp, 0.dp, 0.dp, 0.dp)
                                .fillMaxHeight(.7f),
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = userName,
                                fontWeight = FontWeight.W900,
                                fontSize = 20.sp,
                                color = Color(0xffBABABA),
                            )
                            Text(
                                text = userID,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 11.sp,
                                color = Color(0xFF756E6E),
                            )
                            Text(
                                text = userBranch,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 11.sp,
                                color = Color(0xFF756E6E),
                            )
                        }
                    }
                }
            }
        )

        /* Card for Credential Account Information of User */
        AccountScreenCard(
            modifier = Modifier
                .width(400.dp)
                .height(350.dp)
                .padding(34.dp, 14.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF0B1327)
            ),
            shape = RoundedCornerShape(12.dp),

            content = {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.CenterHorizontally)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .wrapContentSize()
                            .fillMaxWidth()
                            .padding(21.dp, 15.dp, 0.dp, 0.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.AdminPanelSettings,
                            contentDescription = "Account Information",
                            tint = Color.White
                        )
                        Text(
                            text = "Account Information",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontFamily = FontFamily.Serif,
                            modifier = Modifier.padding(3.dp, 0.dp, 0.dp, 0.dp)
                        )
                    }
                    Text(
                        text = "* Only Admin has right to change account information",
                        color = Color(0xFF8A3F3F),
                        fontSize = 7.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(30.dp, 3.dp, 0.dp, 0.dp)

                    )

                    Spacer(modifier = Modifier.padding(26.dp, 16.dp, 0.dp, 0.dp))

                    // Setting Height and width for each information so they have same dimensions
                    val displayModifier = Modifier
                        .height(28.dp)
                        .fillMaxWidth()

                    // Displaying Email of the User
                    CPByteTextDisplayBox(
                        "Email",
                        "mkjmp77@gmail.com",
                        Icons.Outlined.AccountBox,
                        "Email",
                        modifier = displayModifier
                    )
                    Spacer(modifier = Modifier.padding(26.dp, 18.dp, 0.dp, 0.dp))

                    // Displaying Year of the User
                    CPByteTextDisplayBox(
                        "Year",
                        "First",
                        Icons.Filled.CalendarMonth,
                        "Year",
                        modifier = displayModifier
                    )
                    Spacer(modifier = Modifier.padding(26.dp, 18.dp, 0.dp, 0.dp))

                    // Displaying Role of the User in the community
                    CPByteTextDisplayBox(
                        "Role",
                        "Member",
                        Icons.Outlined.BuildCircle,
                        "Role",
                        modifier = displayModifier
                    )
                    Spacer(modifier = Modifier.padding(26.dp, 18.dp, 0.dp, 0.dp))

                    //Displaying Library Id of the User
                    CPByteTextDisplayBox(
                        stringResource(R.string.libraryId),
                        userID,
                        Icons.Outlined.AccountBox,
                        stringResource(R.string.libraryId),
                        modifier = displayModifier
                    )
                }

            }
        )

        /* Card for Changing Password */
        AccountScreenCard(
            modifier = Modifier
                .width(400.dp)
                .height(260.dp)
                .padding(34.dp, 14.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF0B1326)
            ),
            shape = RoundedCornerShape(12.dp),
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.CenterHorizontally)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .wrapContentSize()
                            .fillMaxWidth()
                            .padding(21.dp, 15.dp, 0.dp, 0.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CatchingPokemon,
                            contentDescription = "Account Information",
                            tint = Color.White,
                            modifier = Modifier.rotate(180f)
                        )
                        Text(
                            text = "Change Password",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontFamily = FontFamily.Serif,
                            modifier = Modifier.padding(3.dp, 0.dp, 0.dp, 0.dp)
                        )
                    }

                    Spacer(Modifier.padding(10.dp))

                    /* TextField for entering the old Password  */
                    AccountScreenCustomTextField(
                        value = oldPassword,
                        label = "Old Password",
                        onValueChange = { oldPassword = it },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        imeAction = ImeAction.Done
                    )
                    val isOldPasswordTrue = oldPassword.equals(userPassword)
                    Spacer(Modifier.padding(5.dp))
                    /* TextField for entering the new Password  */
                    AccountScreenCustomTextField(
                        value = newPassword,
                        label = "New Password",
                        onValueChange = { newPassword = it },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        imeAction = ImeAction.Done,
                        enabled = isOldPasswordTrue
                    )
                }
            }
        )
    }
}

@Preview
@Composable
private fun Account_DetailPreview() {
    val branch = "Computer Science and Information Technology"
    Column {
        AccountSetting("2428CSIT1872", "Mradul Gupta", branch, R.drawable.club_logo, "12345678")
    }
}


//Thought of using but didn't used
/*@Composable
fun ExpandableSideBySideTextBox(
    title: String,
    hiddenText: String
) {
    var expanded by remember { mutableStateOf(false) }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .clickable { expanded = !expanded },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1F305A)
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )

                AnimatedVisibility(visible = expanded) {
                    Text(
                        text = hiddenText,
                        color = Color(0xFFBBBBBB),
                        fontSize = 13.sp
                    )
                }
            }

        }
    }
}*/
