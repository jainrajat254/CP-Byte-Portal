package com.example.cpbyte_portal.presentation.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.cpbyte_portal.presentation.ui.screens.components.CPByteTextDisplayBox


@Composable
fun AccountSetting(
    userID: String,
    userName: String,
    userBranch: String,
    userPfp: Int
) {
    var oldPassword by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var newPassword by rememberSaveable { mutableStateOf("") }
    Column(
        modifier
        = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF0A0915)),
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
/*        AccountScreen_card(
//            width = 400.dp,
//            height = 300.dp,
//            modifier = Modifier.padding(35.dp, 14.dp),
//            {
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    modifier = Modifier.fillMaxSize()
//                ) {
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.Center,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Icon(
//                            imageVector = Icons.Rounded.AccountCircle,
//                            contentDescription = "",
//                            tint = Color.White,
//                            modifier = Modifier
//                                .size(28.dp, 28.dp)
//                        )
//                        Text(
//                            text = "Profile Setting",
//                            color = Color.White,
//                            fontSize = 15.sp,
//                            fontFamily = FontFamily.Serif,
//                            fontWeight = FontWeight.Thin
//                        )
//                    }
//                    Spacer(modifier = Modifier.padding(5.dp))
//                    Box(
//                        modifier = Modifier.align(Alignment.CenterHorizontally),
//                        contentAlignment = Alignment.BottomEnd,
//                    ) {
//                        Image(
//                            painter = painterResource(id = R.drawable.club_logo),
//                            contentDescription = "Profile Pic",
//                            modifier = Modifier
//                                .size(80.dp, 80.dp)
//                                .clip(CircleShape)
//                                .border(
//                                    color = Color.White,
//                                    shape = CircleShape,
//                                    width = 1.dp
//                                )
//                        )
//                        Image(
//                            painter = painterResource(id = R.drawable.camer_pfp_selector),
//                            contentDescription = "Profile Pic",
//                            colorFilter = ColorFilter.tint(Color.White),
//                            modifier = Modifier
//                                .size(23.38.dp, 23.38.dp)
//                                .clickable {  }
//                        )
//                    }
//                    Spacer(modifier = Modifier.padding(5.dp))
//                    Text(
//                        text = userName ,
//                        color = Color.White,
//                        fontSize = 20.sp,
//                        fontFamily = FontFamily.Cursive,
//                        fontWeight = FontWeight.W200
//
//                    )
////                    ExpandableSideBySideTextBox(
////                        title = "Library ID",
////                        hiddenText = "2428CSIT1872"
////                    )
////                    ExpandableSideBySideTextBox(
////                        title = "Branch",
////                        hiddenText = "Computer Science & Information Technology"
////                    )
//                    CPByteTextDisplayBox(
//                        title = stringResource(R.string.libraryId),
//                        text = userID,
//                        img = Icons.Filled.AcUnit,
//                        contentDescription = stringResource(R.string.libraryId),
//                        height = 30.dp,
//                        width = 0.dp,
//                    )
//                    Spacer(modifier = Modifier.padding(5.dp))
//                    CPByteTextDisplayBox(
//                        title = "Branch",
//                        text = userBranch,
//                        img = Icons.Filled.AcUnit,
//                        contentDescription = stringResource(R.string.libraryId),
//                        height = 37.dp,
//                        width = 0.dp,
//                    )
//                }
//            }
//        )*/

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
                            Image(
                                painter = painterResource(id = userPfp),
                                contentDescription = "Profile Pic",
                                modifier = Modifier
                                    .size(100.dp, 100.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .border(
                                        color = Color(0xFF0A0915),
                                        shape = RoundedCornerShape(14.dp),
                                        width = 3.dp
                                    )
                            )
                            Image(
                                painter = painterResource(id = R.drawable.camer_pfp_selector),
                                contentDescription = "Profile Pic",
                                colorFilter = ColorFilter.tint(Color.White),
                                modifier = Modifier
                                    .size(23.38.dp, 23.38.dp)
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
                        fontSize = 6.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(30.dp, 3.dp, 0.dp, 0.dp)

                    )
                    Spacer(modifier = Modifier.padding(26.dp, 20.dp, 0.dp, 0.dp))
                    val displayModifier = Modifier
                        .height(28.dp)
                        .fillMaxWidth()
                    CPByteTextDisplayBox(
                        "Email",
                        "mkjmp77@gmail.com",
                        Icons.Outlined.AccountBox,
                        "Email",
                        modifier = displayModifier
                    )
                    Spacer(modifier = Modifier.padding(26.dp, 18.dp, 0.dp, 0.dp))
                    CPByteTextDisplayBox(
                        "Year",
                        "First",
                        Icons.Filled.CalendarMonth,
                        "Year",
                        modifier = displayModifier
                    )
                    Spacer(modifier = Modifier.padding(26.dp, 18.dp, 0.dp, 0.dp))
                    CPByteTextDisplayBox(
                        "Role",
                        "Member",
                        Icons.Outlined.BuildCircle,
                        "Role",
                        modifier = displayModifier
                    )
                    Spacer(modifier = Modifier.padding(26.dp, 18.dp, 0.dp, 0.dp))
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
        /*        Card(
        //            modifier = Modifier
        //                .width(400.dp)
        //                .height(335.dp)
        //                .padding(34.dp, 14.dp),
        //            colors = CardDefaults.cardColors(
        //                containerColor = Color(0xFF0B1327)
        //            ),
        //            shape = RoundedCornerShape(20.dp)
        //
        //        ) {
        //            Column(
        //                modifier = Modifier
        //                    .fillMaxSize()
        //                    .align(Alignment.CenterHorizontally)
        //            ) {
        //                Row(
        //                    verticalAlignment = Alignment.CenterVertically,
        //                    modifier = Modifier
        //                        .wrapContentSize()
        //                        .fillMaxWidth()
        //                        .padding(21.dp, 15.dp, 0.dp, 0.dp)
        //                ) {
        //                    Icon(
        //                        imageVector = Icons.Filled.AdminPanelSettings,
        //                        contentDescription = "Account Information",
        //                        tint = Color.White
        //                    )
        //                    Text(
        //                        text = "Account Information",
        //                        color = Color.White,
        //                        modifier = Modifier.padding(3.dp, 0.dp, 0.dp, 0.dp)
        //                    )
        //                }
        //                Spacer(modifier = Modifier.padding(26.dp, 37.dp, 0.dp, 0.dp))
        //                CPByteTextDisplayBox(
        //                    "Email",
        //                    "mkjmp77@gmail.com",
        //                    Icons.Outlined.AccountBox,
        //                    "Email",
        //                    24.dp,
        //                    3000.dp
        //                )
        //                Spacer(modifier = Modifier.padding(26.dp, 18.dp, 0.dp, 0.dp))
        //                CPByteTextDisplayBox(
        //                    "Year",
        //                    "First",
        //                    Icons.Filled.CalendarMonth,
        //                    "Year",
        //                    24.dp,
        //                    0.dp
        //                )
        //                Spacer(modifier = Modifier.padding(26.dp, 18.dp, 0.dp, 0.dp))
        //                CPByteTextDisplayBox(
        //                    "Role",
        //                    "Member",
        //                    Icons.Outlined.BuildCircle,
        //                    "Role",
        //                    24.dp,
        //                    0.dp
        //                )
        //                Spacer(modifier = Modifier.padding(26.dp, 18.dp, 0.dp, 0.dp))
        //                CPByteTextDisplayBox(
        //                    stringResource(R.string.libraryId),
        //                    userID,
        //                    Icons.Outlined.AccountBox,
        //                    stringResource(R.string.libraryId),
        //                    24.dp,
        //                    0.dp
        //                )
        //            }
                }*/
        AccountScreenCard(
            modifier = Modifier
                .width(400.dp)
                .height(235.dp)
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
                    AccountScreenTextField(
                        value = oldPassword,
                        label = "Old Password",
                        onValueChange = { oldPassword = it },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        imeAction = ImeAction.Done
                    )
//                    if()
                    Spacer(Modifier.padding(5.dp))
                    /* TextField for entering the new Password  */
                    AccountScreenTextField(
                        value = newPassword,
                        label = "New Password",
                        onValueChange = { newPassword = it },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        imeAction = ImeAction.Done
                    )
                    /* TextField for confirming Password  */
                    AccountScreenTextField(
                        value = confirmPassword,
                        label = "Confirm Password",
                        onValueChange = { confirmPassword = it },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        imeAction = ImeAction.Done
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
//        ExpandableSideBySideTextBox(
//            title = "Library ID",
//            hiddenText = "2428CSIT1872"
//        )
        AccountSetting("2428CSIT1872", "Mradul Gupta", branch, R.drawable.club_logo)

    }
}

@Composable
fun AccountScreenCustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean = false,
) {
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }

    // Define visual transformation based on password state
    val visualTransformation = if (isPassword && !passwordVisibility)
        PasswordVisualTransformation()
    else
        VisualTransformation.None

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        // Label
        Text(
            text = label,
            color = Color.White,
            fontSize = 13.sp,
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        )

        // Container for the text field
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(Color(0xFF1F305A), shape = RoundedCornerShape(8.dp))
                .border(
                    width = 0.8.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 8.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(
                        color = Color.White,
                        fontSize = 12.sp
                    ),
                    visualTransformation = visualTransformation,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = if (isPassword) 8.dp else 0.dp)
                )

                // Password visibility toggle if needed
                if (isPassword) {
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(
                            imageVector = if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = if (passwordVisibility) "Hide" else "Show",
                            tint = Color.LightGray,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun AccountScreenTextField(

    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit, // takes a String input and doesn't return anything
    label: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    imeAction: ImeAction = ImeAction.Done,  // What happens when the user presses the action button

) {
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }
    val isPassword = keyboardOptions.keyboardType == KeyboardType.Password
    val visualTransformation = if (isPassword && !passwordVisibility)
        PasswordVisualTransformation() //hides password
    else
        VisualTransformation.None //shows the text as it is
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {

        // label above the text field
        Text(
            text = label,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(bottom = 8.dp)
        )

        // OutlineTextField forms a border around the selected field
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions(imeAction = imeAction),
            visualTransformation = visualTransformation,
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(fontSize = 18.sp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = Color(0xFF1F3059),
                unfocusedContainerColor = Color(0xFF1F3059),
                cursorColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            trailingIcon = {
                if (isPassword) {
                    val icon =
                        if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val description = if (passwordVisibility) "Hide password" else "Show password"
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(
                            imageVector = icon,
                            contentDescription = description,
                            tint = Color.LightGray
                        )
                    }
                }
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(horizontal = 25.dp)
                .border(
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White,
                    width = 1.2.dp
                ),
        )

    }
}

@Composable
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
}
