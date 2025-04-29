package com.example.cpbyte_portal.presentation.ui.screens

import CPByteButton
import android.provider.CalendarContract.Attendees
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.presentation.ui.screens.components.AttendanceBox
import com.example.cpbyte_portal.presentation.ui.screens.components.LineAndTextBox
import com.example.cpbyte_portal.presentation.ui.screens.components.UserLogoCircular
import com.example.cpbyte_portal.presentation.ui.screens.components.UserRoleBox
import com.example.cpbyte_portal.presentation.ui.screens.components.UserRoleBoxEmail

@Composable
@Preview(showBackground = true)
fun MemberDetailScreen() {

    val username by remember { mutableStateOf("User One") }
    val role by remember { mutableStateOf("User") }
    val libraryId by remember { mutableStateOf("LIB01202") }
    val email by remember { mutableStateOf("pulkit1234@gmail.com") }
    val dsa by remember { mutableStateOf("JAVA") }
    val dev by remember { mutableStateOf("ANDROID") }
    val dsaAttendance by remember { mutableStateOf("80%") }
    val devAttendance by remember { mutableStateOf("75%") }
    val dsaMentor by remember { mutableStateOf("Mentor A") }
    val devMentor by remember { mutableStateOf("Mentor B") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {


        LineAndTextBox(30,23,"Member Details")


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
                    //Icon of person
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
                    Text(text = username, color = Color.White)
                }


                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ){
                    UserLogoCircular(R.drawable.cp_byte_logo)
                }
                //Composable for person logo just click control+UserLogo to understand the modifiers used

                //Row for Role and DSA/DEV
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    UserRoleBox("Role", role)
                    Spacer(modifier = Modifier.padding(8.dp))
                    UserRoleBox("DSA/DEV", dsa+"/"+dev)

                }

                //Row for Library ID and Email
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    //
                    UserRoleBox(libraryId, "LIB001010")

                    //space between box
                    Spacer(modifier = Modifier.padding(8.dp))

                    UserRoleBoxEmail("Email", email)

                }


                Spacer(modifier = Modifier.padding(12.dp))


                //Row for attendance of DSA and DEV
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Spacer(modifier = Modifier.padding(15.dp))
                    AttendanceBox(dsaAttendance, "DSA Attendance")
                    Spacer(modifier = Modifier.padding(7.dp))
                    AttendanceBox(devAttendance, "DEV Attendance")

                }


                Spacer(modifier = Modifier.padding(12.dp))


                //Row for Name of Mentor of DSA and DEV
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Spacer(modifier = Modifier.padding(15.dp))
                    AttendanceBox(dsaMentor, "DSA Mentor")
                    Spacer(modifier = Modifier.padding(7.dp))
                    AttendanceBox(devMentor, "DEV Mentor")

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
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                //For the blue line we used a card and providing it height and width accordingly
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF0EC1E7)
                    ),
                    modifier = Modifier
                        .padding(12.dp)
                        .width(5.dp)
                        .height(25.dp)
                ){}
                Text(
                    text = "Attendance History",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )


                Spacer(modifier = Modifier.padding(54.dp, 0.dp, 0.dp, 0.dp))


                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF000F2D)) ,
                    modifier = Modifier
                            .width(80.dp)
                        .height(20.dp)
                        .clickable {  }
                        ,
                    shape = RoundedCornerShape(5.dp) ,
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                      ,
                    border = BorderStroke(2.dp, Color.Gray)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(3.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Dropdown Icon",
                            modifier = Modifier
                                .size(15.dp)
                                .background(Color(0xFF002147), shape = CircleShape),
                            tint = Color.White
                        )

                        Text(
                            text = "Filter",
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

