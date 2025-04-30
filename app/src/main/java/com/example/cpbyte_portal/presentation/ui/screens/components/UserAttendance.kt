package com.example.cpbyte_portal.presentation.ui.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.R



//@Preview
//fun for creating each member

@Composable
fun attendanceDate(date: String) {
    Row(
        modifier = Modifier
            .padding(start = 24.dp, bottom = 5.dp)
            .fillMaxWidth()
    ) {
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
}


@Composable
fun totalMembers(totalMembers: Int) {
    Text(
        "$totalMembers Members",
        color = Color.White,
        fontWeight = FontWeight.W600,
        modifier = Modifier
            .padding(
                start = 25.dp,
                top = 34.dp,
                bottom = 3.dp
            )
            .fillMaxWidth(),
        textAlign = TextAlign.Start
    )
}


@Composable
fun userAttendance(
    user_image: Int = R.drawable.user,
    username: String = "User",
    libid: String = "LIB00001",
    attendance: Int = 0,
    total:Int=0
){
    Card(elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier.fillMaxWidth()
            .height(150.dp)
            .padding(top = 8.dp),
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(1.dp, Color(0xff1F305A))
    ) {

        Column(modifier=Modifier.fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF0B1327), // Deep dark blue shade
                        Color(0xFF1C2541), // Slightly lighter dark blue
                        Color(0xFF00172D),
                    )
                )
            )
            .padding(start =10.dp,end =10.dp,top =5.dp)
        ){
            user(user_image, username)

            Divider(modifier = Modifier.padding(vertical = 9.dp),
                thickness = 1.dp,
                color = Color(0xFF707070))

            userAttendance(attendance, total)

            attandanceMarkingButtons()
        }
    }
}


@Composable
private fun attandanceMarkingButtons() {
    Row(
        modifier = Modifier.padding(start = 10.dp, top = 15.dp)
    ) {
        Button(
            onClick = {/*TODO*/ },
            modifier = Modifier.size(85.dp, 25.dp),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff015225),
                contentColor = Color.White
            ),
            contentPadding = PaddingValues()
        ) {
            Text(
                text = "PRESENT",
                fontSize = 16.sp,
                fontWeight = FontWeight.W600
            )
        }


        Button(
            onClick = {/*TODO*/ },
            modifier = Modifier
                .size(85.dp, 25.dp)
                .padding(start = 8.dp),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff920A00),
                contentColor = Color.White
            ),
            contentPadding = PaddingValues()
        ) {
            Text(
                text = "ABSENT",
                fontSize = 16.sp,
                fontWeight = FontWeight.W600
            )
        }

        Button(
            onClick = {/*TODO*/ },
            modifier = Modifier
                .size(90.dp, 25.dp)
                .padding(start = 8.dp),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff1F30B9),
                contentColor = Color.White
            ),
            contentPadding = PaddingValues()
        ) {
            Text(
                text = "EXCUSED",
                fontSize = 16.sp,
                fontWeight = FontWeight.W600
            )
        }
    }
}


@Composable
private fun userAttendance(attendance: Int, total: Int) {
    Row() {
        Text(
            "Attended: ",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.W600,
            modifier = Modifier.height(17.dp)
        )

        Text(
            "$attendance / $total",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.W600,
            modifier = Modifier.size(60.dp, 17.dp)
        )

        var percentage = {
            if (total == 0)
                0f
            else
                (attendance.toFloat() / total.toFloat())
        }
        LinearProgressIndicator(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .width(150.dp)
                .padding(start =5.dp)
                .clip(RoundedCornerShape(5.dp)),
            progress = percentage,
            color = Color(0xFA05DBF6),
            trackColor = Color.White
        )
    }
}


@Composable
private fun user(user_image: Int, username: String) {
    Row() {
        Image(
            painter = painterResource(id = user_image),
            contentDescription = "User image",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.dp, Color.White, CircleShape)
        )

        Text(
            text = "$username",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.W700,
            modifier = Modifier
                .padding(start = 7.dp, top = 8.dp)
                .fillMaxWidth()
                .height(20.dp)
        )
    }
}