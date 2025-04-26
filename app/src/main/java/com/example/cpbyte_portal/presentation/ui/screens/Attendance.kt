package com.example.cpbyte_portal.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api


import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.R

@Preview
@Composable
fun attendance(
    user_image: Int = R.drawable.user,      //user icon
    coord_name: String = "Coord One",       //name
    date: String = "Mon, 26 April 2025"     // Date for attendence tracking
) {
    Scaffold() { innerpadding ->
        Column(
            modifier = Modifier
                .background(Color.Black)
                .padding(innerpadding)
                .padding(end = 5.dp,top =25.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            var selectedTab by remember { mutableStateOf(0) }     //variable for tab selection

            val tabs = listOf("JAVA Attendance", "Android Attendance")  //tabs

            var checked by remember { mutableStateOf(false) }     //for mark all checkbox


            // Row for arranging items
            Row {

                //Side blue line
                Card(
                    colors = CardDefaults.cardColors(
                        Color(0xff0EC1E7),
                        contentColor = Color(0xff0EC1E7)
                    ),
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .size(width = 4.dp, height = 24.dp)
                ) {
                    Text(text = "|")
                }


                //Title
                Text(
                    text = "Mark Attendence",
                    color = Color.White,
                    fontWeight = FontWeight.W600,
                    fontSize = 20.sp,
                    modifier = Modifier.size(124.dp, 60.dp)
                )


                //user logo
                Image(
                    painter = painterResource(id = user_image),
                    contentDescription = "User Image",
                    modifier = Modifier
                        .padding(start = 80.dp)
                        .size(40.dp)
                        .clip(CircleShape)
                )


                //Column for arranging name and role
                var scroll= rememberScrollState()
                Column(modifier=Modifier.padding(top =5.dp)) {
                    Text(
                        text = coord_name,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W500,
                        color = Color.White,
                        modifier = Modifier.size(80.dp, 18.dp)
                            .horizontalScroll(scroll)
                    )

                    Text(
                        text = "COORDINATOR",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.W500,
                        color = Color(0xffBABABA)
                    )
                }

            }


            //Tab selection buttons
            Row() {
                tabs.forEachIndexed { index, title ->
                    Button(
                        onClick = { selectedTab = index },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedTab == index)
                                Color(0xff0EC1E7)
                            else Color(0xff313131),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(),
                        modifier = Modifier
                            .padding(start = 24.dp)
                            .size(150.dp, 23.dp)

                    ) {
                        Text(
                            text = title,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }


            //variable for size of members list
            var s = 0
            if (selectedTab == 0) {
                s = getDsaList().size
            } else
                s = getDevList().size


            //total members in particular domain
            Text(
                "$s Members",
                color = Color.White,
                fontWeight = FontWeight.W600,
                modifier = Modifier.padding(
                    start =25.dp,
                    top = 34.dp,
                    bottom = 3.dp
                )
                    .fillMaxWidth(),
                textAlign = TextAlign.Start
            )


            //date for attendance
            Row(modifier = Modifier.padding(start = 24.dp, bottom = 5.dp)
                .fillMaxWidth()) {
                Text(
                    text = "Attendance for date:",
                    color = Color(0xffBABABA),
                    fontSize = 10.sp
                )


                Text(
                    text = "$date",
                    color = Color.White,
                    fontSize = 10.sp
                )
            }


            //list top titles
            Card(
                colors = CardDefaults.cardColors(          
                    Color(0xff313131),
                    Color(0xffBABABA)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(start=2.dp),

                //for changing top 2 corner round and bottom 2 pointed
                shape = RoundedCornerShape(
                    topStart = 10.dp,
                    topEnd = 10.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            ) {
                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "S.No.",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W600,
                        modifier = Modifier.padding(start =5.dp)
                            .size(30.dp,17.dp)
                    )


                    Text(
                        text = "Member",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W600,
                        modifier = Modifier.padding(start = 15.dp)
                            .size(50.dp,17.dp)
                    )


                    Text(
                        text = "Library ID",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W600,
                        modifier = Modifier.padding(start = 40.dp)
                            .size(55.dp,17.dp)
                    )

                    Text(
                        text = "Attended",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W600,
                        modifier = Modifier.padding(start = 25.dp)
                            .size(55.dp,17.dp)
                    )

                    Text(
                        text = "Status",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W600,
                        modifier = Modifier.padding(start = 30.dp)
                            .size(40.dp,17.dp)
                    )


                }
            }

            //if else for showing particular domain members list
            if (selectedTab == 0) {                       //for DSA members
                LazyColumn(modifier = Modifier.fillMaxWidth()
                    .padding(start =2.dp),
                    content = {
                        items(getDsaList()) { user ->
                            userAttendance(
                                user.sno,
                                user.user_image,
                                user.username,
                                user.libid,
                                user.attendance
                            )
                        }
                    })
            } else {                                      //for android members
                LazyColumn(modifier = Modifier.fillMaxWidth()
                    .padding(start =2.dp),
                    content = {
                        items(getDevList()) { user ->
                            userAttendance(
                                user.sno,
                                user.user_image,
                                user.username,
                                user.libid,
                                user.attendance
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable

//fun for creating each member
fun userAttendance(
    sno: Int = 1,
    user_image: Int = R.drawable.user,
    username: String = "User",
    libid: String = "LIB00001",
    attendance: Int = 0
) {

    val scrollState = rememberScrollState()       //scroll state for scrolling name
    Box(modifier = Modifier
        .height(95.dp)
        .fillMaxWidth()
        .background(Color(0xff0B1327))
        .drawBehind {                             //for adding lower border line in user box
            drawLine(
                color = Color(0xff1E2433),
                start = Offset(0f, size.height),
                end = Offset(size.width, size.height),
                strokeWidth = 5.dp.toPx()
            )
        }) {


        Row(modifier = Modifier.padding(top = 16.dp)) {
            Text("$sno.",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.W600,
                modifier = Modifier
                    .padding(start = 10.dp,top =10.dp)
                    .width(17.dp)
                    .fillMaxHeight()
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier =Modifier.padding(start =14.dp)) {
                Image(
                    painter = painterResource(id = user_image),
                    contentDescription = "user_image",
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                )


                Text(
                    "$username",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontWeight = FontWeight.W500,
                    fontSize = 14.sp,

                    modifier = Modifier
                        .size(60.dp, 17.dp)
                        .horizontalScroll(scrollState)
                )
            }


            Text(
                "2428CSE2559",
                color = Color.White,
                fontSize = 13.sp,
                modifier = Modifier
                    .padding(top = 10.dp, start =22.dp)
                    .size(90.dp, 17.dp)
            )


            Text(
                "$attendance",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.W600,
                modifier = Modifier
                    .padding(start = 20.dp,top =10.dp)
                    .size(17.dp, 17.dp)
            )


            //column for present absent and excused button
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(start = 47.dp)
            ) {
                    Button(
                        onClick = {/*TODO*/ },
                        modifier = Modifier.height(17.dp),
                        shape = RoundedCornerShape(2.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xff015225),
                            contentColor = Color.White),
                        contentPadding = PaddingValues()
                    ) {
                        Text(
                            text = "PRESENT",
                            fontSize = 10.sp
                        )
                    }


                Button(
                    onClick = {/*TODO*/ },
                    modifier = Modifier.padding(top =5.dp)
                        .height(17.dp),
                    shape = RoundedCornerShape(2.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xff920A00),
                        contentColor = Color.White),
                    contentPadding = PaddingValues()
                ) {
                    Text(
                        text = "ABSENT",
                        fontSize = 10.sp
                    )
                }

                Button(
                    onClick = {/*TODO*/ },
                    modifier = Modifier.padding(top = 5.dp)
                    .height(17.dp),
                    shape = RoundedCornerShape(2.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xff1F305A),
                        contentColor = Color.White),
                    contentPadding = PaddingValues()
                ) {
                    Text(
                        text = "EXCUSED",
                        fontSize = 10.sp
                    )
                }
            }

        }
    }
}


//temperory data class for dsa and development

data class Dsa(
    val sno: Int,
    val user_image: Int,
    val username: String,
    val libid: String,
    val attendance: Int
)

fun getDsaList(): MutableList<Dsa> {
    val list = mutableListOf<Dsa>()
    list.add(Dsa(1, R.drawable.user, "Sanchit", "2428CSE2559", 43))
    list.add(Dsa(2, R.drawable.user, "Pulkit", "2428CSE255", 10))
    list.add(Dsa(3, R.drawable.user, "Kartik", "2428EC2559", 11))
    list.add(Dsa(4, R.drawable.user, "Manish", "2428ME255", 21))
    list.add(Dsa(5, R.drawable.user, "Sanchit", "2428CSE2559", 43))
    list.add(Dsa(6, R.drawable.user, "Pulkit", "2428CSE255", 10))
    list.add(Dsa(7, R.drawable.user, "Kartik", "2428EC2559", 11))
    list.add(Dsa(8, R.drawable.user, "Manish", "2428ME255", 21))
    list.add(Dsa(9, R.drawable.user, "Sanchit", "2428CSE2559", 43))
    list.add(Dsa(10, R.drawable.user, "Pulkit", "2428CSE255", 10))
    list.add(Dsa(11, R.drawable.user, "Kartik", "2428EC2559", 11))
    list.add(Dsa(12, R.drawable.user, "Manish", "2428ME255", 21))
    return list;
}

data class Dev(
    val sno: Int,
    val user_image: Int,
    val username: String,
    val libid: String,
    val attendance: Int
)

fun getDevList(): MutableList<Dev> {
    val list = mutableListOf<Dev>()
    list.add(Dev(1, R.drawable.user, "Mradul", "2428CSE2559", 43))
    list.add(Dev(2, R.drawable.user, "Yavar", "2428CSE255", 10))
    list.add(Dev(3, R.drawable.user, "Pulkit", "2428EC2559", 11))
    list.add(Dev(4, R.drawable.user, "Sanchit", "2428ME255", 21))
    list.add(Dev(5, R.drawable.user, "Mradul", "2428CSE2559", 43))
    list.add(Dev(6, R.drawable.user, "Yavar", "2428CSE255", 10))
    list.add(Dev(7, R.drawable.user, "Pulkit", "2428EC2559", 11))
    list.add(Dev(8, R.drawable.user, "Sanchit", "2428ME255", 21))
    list.add(Dev(9, R.drawable.user, "Mradul", "2428CSE2559", 43))
    list.add(Dev(10, R.drawable.user, "Yavar", "2428CSE255", 10))
    return list;
}
