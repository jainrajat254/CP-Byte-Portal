package com.example.cpbyte_portal.presentation.ui.screens

import android.widget.Toast
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BuildCircle
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.CatchingPokemon
import androidx.compose.material.icons.twotone.MailOutline
import androidx.compose.material.icons.twotone.RocketLaunch
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.presentation.ui.screens.components.AccountInfoShowingCard
import com.example.cpbyte_portal.presentation.ui.screens.components.AccountScreenCard
import com.example.cpbyte_portal.presentation.ui.screens.components.AccountScreenCustomTextField
import com.example.cpbyte_portal.presentation.ui.screens.components.SaveChangesButton

// #F1F5F9	#9CA3AF	#1F2937	#F1F5F9	#A1A1AA	#101820	#6EE7B7	#6EE7B733
val textPrimaryColor = Color(0xffE0F2FE)
val textSecondaryColor = Color(0xff93C5FD)

val inputFieldBgColor = Color(0xff1E293B)

val fieldTextPrimaryColor = Color(0xffE2E8F0)
val fieldTextSecondaryColor = Color(0xff7DD3FC)

val cardBgColor = Color(0xff0F172A)
val cardBorderColor = Color(0xff3B82F6)

val imageTintColor = Color(0x403B82F6)

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
    val context = LocalContext.current
    val scrollableState = rememberScrollState()
    var oldPassword by rememberSaveable { mutableStateOf("") }
    var newPassword by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF111111))
    ) {
//        Image(
//            painter = painterResource(R.drawable.loginbgtwo),
//            contentDescription = "Background",
//            modifier = Modifier
//                .rotate(180f)
//                .fillMaxSize()
//                .scale(scaleX = 1.1f, scaleY = 1.2f),
//
//            )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollableState)
//                    .background(color = Color(0xFF030307)),
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
            /*      /*Old Parameters used for Changing Password Card*/
//                modifier = Modifier
//                    .width(400.dp)
//                    .wrapContentHeight()
//                    .padding(34.dp, 14.dp),
//                colors = CardDefaults.cardColors(
//                    containerColor = Color(0xFF0B1326)
//                ),
//                shape = RoundedCornerShape(12.dp),
*/

            /* Card for Changing Password */
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
                shape = RoundedCornerShape(18.dp), content = {
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
                                contentDescription = stringResource(R.string.change_password),
                                tint = Color.White,
                                modifier = Modifier.rotate(180f)
                            )
                            Text(
                                text = stringResource(R.string.change_password),
                                color = Color.White,
                                fontSize = 18.sp,
                                fontFamily = FontFamily.Serif,
                                modifier = Modifier.padding(3.dp, 0.dp, 0.dp, 0.dp)
                            )
                        }

                        Spacer(Modifier.padding(20.dp))

                        /* TextField for entering the old Password  */
                        AccountScreenCustomTextField(
                            value = oldPassword,
                            label = stringResource(R.string.old_password),
                            onValueChange = { oldPassword = it },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password
                            ),
                            imeAction = ImeAction.Done
                        )
                        val isOldPasswordTrue = oldPassword == userPassword
                        if (oldPassword.isNotEmpty() && oldPassword.length >= 8 && !isOldPasswordTrue) {
                            Spacer(Modifier.padding(5.dp))
                            Text(
                                text = "Password Mismatch",
                                fontSize = 15.sp,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xffEF4444),
                                modifier = Modifier
                                    .align(Alignment.End)
                                    .padding(end = 30.dp)
                                    .shadow(12.dp)
                            )
                            newPassword = ""
                            confirmPassword = ""
                            Spacer(Modifier.padding(bottom = 5.dp))
                        } else {
                            Spacer(Modifier.padding(10.dp))
                        }
                        /* TextField for entering the new Password  */
                        AccountScreenCustomTextField(
                            value = newPassword,
                            label = stringResource(R.string.new_password),
                            onValueChange = { newPassword = it },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password
                            ),
                            imeAction = ImeAction.Done,
                            enabled = isOldPasswordTrue
                        )
                        Spacer(Modifier.height(20.dp))
                        AccountScreenCustomTextField(
                            value = confirmPassword,
                            label = "Confirm Password",
                            onValueChange = { confirmPassword = it },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password
                            ),
                            imeAction = ImeAction.Done,
                            enabled = isOldPasswordTrue
                        )
                        Spacer(Modifier.height(20.dp))
                        Box(
                            Modifier
                                .width(165.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            //Save Changes Button
                            SaveChangesButton(
                                value = stringResource(R.string.save_changes),
                                onClick = {
                                    if(newPassword != oldPassword){
                                        if (confirmPassword == newPassword) {

                                            /*Logic for Changing Password Will be Written here
                                            *
                                            *
                                            * Changing Old Password will occur in this onClick()*/

                                //Prompt For the User That Password is Changed Successfully
                                            Toast.makeText(
                                                context,
                                                "Password Changed Successfully",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            // All the TextFields Become Empty again
                                            oldPassword = ""
                                            newPassword = ""
                                            confirmPassword = ""
                                        } else {
                            // if the New Password and Confirm Password
                            // Do not Match then this Block Executed
                                            Toast.makeText( //Prompt which says Passwords Don't match
                                                context,
                                                "Passwords Do Not Match.",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }else{
                            // This Else block is Executed when the Old
                            // password and the new Password are Same and the SaveChanges Button is Pressed
                                        confirmPassword = ""
                                        Toast.makeText(
                                            context,
                                            "Old and New Password Cannot Be Same!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            )
                        }
                        Spacer(Modifier.padding(10.dp))

                    }
                }
            )
        }

    }
}

@Preview
@Composable
private fun Account_DetailPreview() {
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
