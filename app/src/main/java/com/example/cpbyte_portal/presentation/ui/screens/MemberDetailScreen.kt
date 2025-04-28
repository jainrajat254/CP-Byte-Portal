package com.example.cpbyte_portal.presentation.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.RowScopeInstance.align
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cpbyte_portal.R

@Composable
//@Preview(showBackground = true)
fun MemberDetailScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(0.dp, 0.dp, 160.dp, 0.dp)
        ) {
            //card for line to the left of Member Details
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF0EC1E7)
                ),
                modifier = Modifier
                    .padding(10.dp)
                    .width(6.dp)
            ) {
                Text(
                    text = "|",
                    fontSize = 23.sp,
                    color = Color(0xFF0EC1E7)
                )
            }


            Text(
                text = "Member Details",
                fontSize = 23.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }


        Spacer(modifier = Modifier.padding(7.dp))


        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF000F2D)

            ),
            modifier = Modifier
                .width(330.dp)
                .height(480.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            border = BorderStroke(2.dp, Color.Gray)
        ) {
            Column(
//
            ) {

                // Row for the user details
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "User Icon",
                        modifier = Modifier

                            .background(Color(0xFF002147), CircleShape)
                            .padding(12.dp)
                            .clip(CircleShape)
                            .border(0.9.dp, color = Color.White, CircleShape),
                        tint = Color.White,
                    )
                    Text(text = "User one", color = Color.White)
                }


                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(R.drawable.cpbyte_logo),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(70.dp)
                            .clip(RoundedCornerShape(76.dp))
                    )
                }

                //Row for Role and DSA/DEV
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    BoxMaker("Role", "USER")
                    Spacer(modifier = Modifier.padding(10.dp))
                    BoxMaker("DSA/DEV", "CPP/ANDROID")

                }

                //Row for Library ID and Email
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    BoxMaker("Library ID", "LIB001010")
                    Spacer(modifier = Modifier.padding(10.dp))
                    BoxMaker("Email", "pulkit13345@gmail.com")

                }


                Spacer(modifier = Modifier.padding(12.dp))


                //Row for attendance of DSA and DEV
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Spacer(modifier = Modifier.padding(15.dp))
                    AttendanceBox("0%", "DSA Attendance")
                    Spacer(modifier = Modifier.padding(7.dp))
                    AttendanceBox("0%", "DEV Attendance")

                }


                Spacer(modifier = Modifier.padding(12.dp))


                //Row for Name of Mentor of DSA and DEV
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Spacer(modifier = Modifier.padding(15.dp))
                    AttendanceBox("--", "DSA Mentor")
                    Spacer(modifier = Modifier.padding(7.dp))
                    AttendanceBox("--", "DEV Mentor")

                }


            }
        }


        Spacer(modifier = Modifier.padding(12.dp))


        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF000F2D)

            ),
            modifier = Modifier
                .width(330.dp)
                .height(150.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            border = BorderStroke(2.dp, Color.Gray)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF0EC1E7)
                    ),
                    modifier = Modifier
                        .padding(10.dp)
                        .width(5.dp)
                ) {
                    Text(
                        text = "|",
                        fontSize = 18.sp,
                        color = Color(0xFF0EC1E7)
                    )
                }


                Text(
                    text = "Attendance History",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )


                Spacer(modifier = Modifier.padding(36.dp, 0.dp, 0.dp, 0.dp))


                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF000F2D),
                        contentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.LightGray
                    ),
                    modifier = Modifier
                        .border(2.dp, Color.Gray, RoundedCornerShape(8.dp))
                        .height(30.dp)
                        .width(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Dropdown Icon",
                            modifier = Modifier
                                .size(10.dp)
                                .background(Color(0xFF002147), shape = CircleShape),
                            tint = Color.White
                        )

                        Text(
                            text = "Filter",
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
//@Preview(showBackground = true)
fun BoxMaker(role: String, rolename: String) {
    Column(
        modifier = Modifier
            .height(70.dp)
            .width(135.dp)
            .background(Color(0xFF000F2D))
            .padding(40.dp, 0.dp, 0.dp, 0.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Text(
            text = role,
            color = Color.Gray,
            fontSize = 11.sp
        )
        Text(
            text = rolename,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 12.sp
        )
    }
}

@Composable
//@Preview(showBackground = true)
fun AttendanceBox(percentage: String, title: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF002147)),
        modifier = Modifier
            .height(63.dp)
            .width(130.dp),
        shape = RoundedCornerShape(9.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 24.dp),
        border = BorderStroke(2.dp, Color.Gray)

    ) {
        Column(


        ) {
            Text(
                text = percentage, color = Color.White,
                modifier = Modifier
                    .padding(24.dp, 0.dp, 0.dp, 0.dp)
                    .padding(0.dp, 7.dp, 0.dp, 0.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = title, color = Color.Gray,
                modifier = Modifier
                    .padding(24.dp, 0.dp, 0.dp, 0.dp)
                    .padding(0.dp, 7.dp, 0.dp, 0.dp),
                fontSize = 13.sp
            )
        }

    }
}
