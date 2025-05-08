package com.example.cpbyte_portal.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.presentation.ui.screens.components.attendanceDate
import com.example.cpbyte_portal.presentation.ui.screens.components.tabSelection
import com.example.cpbyte_portal.presentation.ui.screens.components.topTitlebar
import com.example.cpbyte_portal.presentation.ui.screens.components.totalMembers
import com.example.cpbyte_portal.presentation.ui.screens.components.userAttendance



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

            // Row for arranging items
            topTitlebar(user_image, coord_name)


            //Tab selection buttons
            tabSelection(tabs = tabs, selectedTab = selectedTab, setSelectedTab = {selectedTab=it})


            //variable for size of members list
            var totalMembers = 0
            if (selectedTab == 0) {
                totalMembers = getDsaList().size
            } else
                totalMembers = getDevList().size


            //total members in particular domain
            totalMembers(totalMembers)


            //date for attendance
            attendanceDate(date)
            //if else for showing particular domain members list
            if (selectedTab == 0) {                       //for DSA members
                LazyColumn(modifier = Modifier.fillMaxWidth()
                    .padding(start =2.dp,top =10.dp),
                    content = {
                        items(getDsaList()) { user ->
                            userAttendance(
                                user.user_image,
                                user.username,
                                user.libid,
                                user.attendance,
                                user.total
                            )
                        }
                    })
            } else {                                      //for android members
                LazyColumn(modifier = Modifier.fillMaxWidth()
                    .padding(start =2.dp,top =10.dp),
                    content = {
                        items(getDevList()) { user ->
                            userAttendance(
                                user.user_image,
                                user.username,
                                user.libid,
                                user.attendance,
                                user.total
                            )
                        }
                    }
                )
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
    val attendance: Int,
    val total: Int
)

fun getDsaList(): MutableList<Dsa> {
    val list = mutableListOf<Dsa>()
    list.add(Dsa(1, R.drawable.user, "Sanchit", "2428CSE2559", 43,50))
    list.add(Dsa(2, R.drawable.user, "Pulkit", "2428CSE255", 10,50))
    list.add(Dsa(3, R.drawable.user, "Kartik", "2428EC2559", 11,50))
    list.add(Dsa(4, R.drawable.user, "Manish", "2428ME255", 21,50))
    list.add(Dsa(5, R.drawable.user, "Sanchit", "2428CSE2559", 43,50))
    list.add(Dsa(6, R.drawable.user, "Pulkit", "2428CSE255", 10,50))
    list.add(Dsa(7, R.drawable.user, "Kartik", "2428EC2559", 11,50))
    list.add(Dsa(8, R.drawable.user, "Manish", "2428ME255", 21,50))
    list.add(Dsa(9, R.drawable.user, "Sanchit", "2428CSE2559", 43,50))
    list.add(Dsa(10, R.drawable.user, "Pulkit", "2428CSE255", 10,50))
    list.add(Dsa(11, R.drawable.user, "Kartik", "2428EC2559", 11,50))
    list.add(Dsa(12, R.drawable.user, "Manish", "2428ME255", 21,50))
    return list;
}

data class Dev(
    val sno: Int,
    val user_image: Int,
    val username: String,
    val libid: String,
    val attendance: Int,
    val total: Int
)

fun getDevList(): MutableList<Dev> {
    val list = mutableListOf<Dev>()
    list.add(Dev(1, R.drawable.user, "Mradul", "2428CSE2559", 43,50))
    list.add(Dev(2, R.drawable.user, "Yavar", "2428CSE255", 10,50))
    list.add(Dev(3, R.drawable.user, "Pulkit", "2428EC2559", 11,50))
    list.add(Dev(4, R.drawable.user, "Sanchit", "2428ME255", 21,50))
    list.add(Dev(5, R.drawable.user, "Mradul", "2428CSE2559", 43,50))
    list.add(Dev(6, R.drawable.user, "Yavar", "2428CSE255", 10,50))
    list.add(Dev(7, R.drawable.user, "Pulkit", "2428EC2559", 11,50))
    list.add(Dev(8, R.drawable.user, "Sanchit", "2428ME255", 21,50))
    list.add(Dev(9, R.drawable.user, "Mradul", "2428CSE2559", 43,50))
    list.add(Dev(10, R.drawable.user, "Yavar", "2428CSE255", 10,50))
    return list;
}
