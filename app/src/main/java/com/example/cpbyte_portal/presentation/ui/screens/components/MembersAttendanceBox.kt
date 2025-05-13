package com.example.cpbyte_portal.presentation.ui.screens.components

import DevList
import DsaList
import Member
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun MembersAttendanceBox(selectedOption: String, date: String) {

    Card(
        colors = CardDefaults.cardColors(Color.Transparent, Color.Gray),

        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(modifier = Modifier.clip(RoundedCornerShape(10.dp))) {
            val members = remember {
                mutableStateListOf<Member>().apply {
                    if (selectedOption == "DSA")
                        addAll(DsaList())
                    else
                        addAll(DevList())
                }
            }
            val totalMembers = members.size

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Total Members = $totalMembers",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 1.dp),
                    fontWeight = FontWeight.W700
                )

                Spacer(modifier = Modifier.weight(1f))


                //checkBox for marking all members present
                MarkAllPresentCheckBox(members)

            }

            Spacer(Modifier.padding(5.dp))


            //Row for showing titles like name and attendance
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color(0xFF0E1425))
                    .padding(bottom = 2.dp)
                    .size(40.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Spacer(Modifier.weight(0.2f))

                Text(
                    text = "",
                    modifier = Modifier
                        .size(20.dp)
                )

                Spacer(Modifier.weight(0.2f))
                Text(
                    text = "Members",
                    modifier = Modifier
                        .width(100.dp)
                        .align(alignment = Alignment.CenterVertically), color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp

                )

                Spacer(modifier = Modifier.weight(0.8f))

                Text(
                    text = "LibID",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .width(100.dp), color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp

                )

                Spacer(Modifier.weight(1f))

                Text(
                    text = "Attendance",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .width(95.dp), color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )


                Spacer(modifier = Modifier.weight(1.5f))
            }

            Spacer(Modifier.padding(2.dp))

            //list of members for marking attendance
            MemberAttendanceMarkingList(members)

            Spacer(Modifier.weight(1f))

        }
    }
}
