package com.example.cpbyte_portal.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.BuildCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MovableContent
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.presentation.ui.screens.components.CpbyteText

@Composable
fun AccountSetting(User_ID: String ,User_Name: String) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF070B0F)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        Card(
            modifier = Modifier
                .size(339.dp, 193.dp)
                .padding(42.dp, 14.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF0B1327)
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .fillMaxWidth()
                        .padding(21.dp, 15.dp, 0.dp, 0.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pfp_logo),
                        contentDescription = "Profile Pic",
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier.size(16.dp, 16.dp)
                    )
                    Text(
                        text = "Profile Picture",
                        color = Color.White,
                        modifier = Modifier.padding(3.dp, 0.dp, 0.dp, 0.dp)
                    )
                }
                Spacer(modifier = Modifier.padding((1.dp)))
                Row(
                    modifier = Modifier.padding(21.dp, 15.dp, 0.dp, 0.dp)
                ) {
                    Box(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        contentAlignment = Alignment.BottomEnd,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.cpbyte_logo),
                            contentDescription = "Profile Pic",
                            modifier = Modifier.size(68.dp, 68.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.camer_pfp_selector),
                            contentDescription = "Profile Pic",
                            colorFilter = ColorFilter.tint(Color.White),
                            modifier = Modifier
                                .size(23.38.dp, 23.38.dp)
//                                .clip(CircleShape)
//                                .border(2.dp, Color.White, CircleShape)
//                                .clickable { }
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(8.dp, 0.dp, 0.dp, 0.dp)
                            .fillMaxHeight(.69f),
                        verticalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        Text(
                            text = User_Name,
                            color = Color(0xffBABABA),
                        )
//                        Box(
//                            modifier = Modifier
//                                .background(Color(0xff1F305A))
//                                .size(81.dp, 16.dp)
//                                .clickable { },
//                            contentAlignment = Alignment.Center
//                        ) {
//                            Text(
//                                text = "Choose Image",
//                                color = Color.White,
//                                fontSize = 8.sp,
//                            )
//                        }
                        Text(
                            text = stringResource(R.string.recommended_jpeg_png_webp),
                            color = Color(0xff727272),
                            fontSize = 6.sp
                        )
                    }
                }
            }
        }


        Card(
            modifier = Modifier
                .width(399.dp)
                .height(450.dp)
                .padding(34.dp, 14.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF0B1327)
            ),
            shape = RoundedCornerShape(20.dp)

        ) {
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
                        modifier = Modifier.padding(3.dp, 0.dp, 0.dp, 0.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(26.dp, 37.dp, 0.dp, 0.dp))
                CpbyteText("Email", "mkjmp77@gmail.com", Icons.Outlined.AccountBox, "Email")
                Spacer(modifier = Modifier.padding(26.dp, 18.dp, 0.dp, 0.dp))
                CpbyteText("Year", "First", Icons.Filled.CalendarMonth, "Year")
                Spacer(modifier = Modifier.padding(26.dp, 18.dp, 0.dp, 0.dp))
                CpbyteText("Role", "Member", Icons.Outlined.BuildCircle, "Role")
                Spacer(modifier = Modifier.padding(26.dp, 18.dp, 0.dp, 0.dp))
                CpbyteText(stringResource(R.string.library_id), User_ID, Icons.Outlined.AccountBox, stringResource(R.string.library_id))
            }
        }

    }
}

@Preview
@Composable
private fun Account_DetailPreview() {
    AccountSetting("2428CSIT1872" , "MKJ")
}

@Composable
fun AccountScreen_card(width: Dp, height : Dp, paddingValues: PaddingValues, content: Composable){
    Card(
        modifier = Modifier
            .width(width)
            .height(height)
            .padding(paddingValues),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF0B1327)
        ),
        shape = RoundedCornerShape(20.dp)

    ) {

    }
    
}