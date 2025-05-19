
package com.example.cpbyte_portal.presentation.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.presentation.ui.screens.AttendanceData
import com.example.cpbyte_portal.presentation.ui.screens.Mentor

@Composable
fun AttendanceCard(data: AttendanceData) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .height(180.dp) //fixed height
        .border(
            width = 1.dp,
    color = Color(0xFF343B43),
    shape = RoundedCornerShape(12.dp) // Match card shape
    ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF17191d)),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp), //  (shadow)


    ) {
        Column(modifier = Modifier.padding(20.dp)) {

            Spacer(modifier = Modifier.height(10.dp))

            //Title
            Text(
                text = data.title,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            //Show percentage
            Text(
                text = "${data.percent}%",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))

            // representing attendance progress
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(50)) // Fully rounded corners
                    .background(Color.LightGray) // Track color
            ) {
                LinearProgressIndicator(
                    progress = { data.attended.toFloat() / data.total }, //for progress
                    modifier = Modifier
                        .fillMaxSize(),
                    color = Color(0xFF40C4FF),
                    trackColor = Color.Transparent // Transparent to show outer background
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            //Text for detailed attendance
            Text(
                text = "${data.attended} of ${data.total} sessions attended",
                color = Color.Gray,
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}


@Composable
fun CoordinatorCard(mentor: Mentor) {
    Card(modifier = Modifier.fillMaxWidth()
        .height(160.dp)
        .border(
            width = 2.dp,
            color = Color(0xFF343B43),
            shape = RoundedCornerShape(12.dp) // Match card shape
        ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF17191d)),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp))
    {
        Column(modifier = Modifier.padding(16.dp)) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = mentor.role,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Horizontal layout for image and mentor details
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {


                // Mentor's profile or logo image
                Image(
                    painter = painterResource(id = mentor.logoRes),
                    contentDescription = "${mentor.name} Logo",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop //crop to fit
                )
                // Column to hold mentor's name and email
                Column(modifier = Modifier.padding(2.dp)) {
                    Text(
                        text = mentor.name,
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = mentor.email,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}




