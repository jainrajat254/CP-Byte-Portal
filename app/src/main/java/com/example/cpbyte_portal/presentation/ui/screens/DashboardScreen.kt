package com.example.cpbyte_portal.presentation.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.presentation.ui.screens.components.AttendanceBarCard
import com.example.cpbyte_portal.presentation.ui.screens.components.AttendanceCard
import com.example.cpbyte_portal.presentation.ui.screens.components.CoordinatorButton
import com.example.cpbyte_portal.presentation.ui.screens.components.CoordinatorCard
import com.example.cpbyte_portal.presentation.ui.screens.components.UpcomingEventsList

@Composable
@Preview(showBackground = true)
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF111111)) // Dark background
            .padding(16.dp)
            .verticalScroll(rememberScrollState()) //Enable scrolling
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        // Dashboard Title
        Text(
            text = "Dashboard",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Row displaying Attendance title and user role button
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Your Attendance",
                color = Color.White,
                fontSize = 21.sp
            )

            CoordinatorButton(
                onClick = { /*click action */ },
                label = "Member"
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // List of individual attendance cards
        attendanceDataList.forEach { data ->
            AttendanceCard(data) //calling attendance card
            Spacer(modifier = Modifier.height(18.dp))
        }

        // Section for Mentor cards
        Text(
            text = "Mentors",
            color = Color.White,
            fontSize = 26.sp
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Display each mentor's details in a card
        mentorList.forEach { mentor ->
            CoordinatorCard(mentor) // calling coordinator card
            Spacer(modifier = Modifier.height(16.dp))
        }

        Text(
            text = "Attendance Overview",
            color = Color.White,
            fontSize = 26.sp
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Bar chart displaying weekly attendance percentages
        AttendanceBarCard(attendanceList = sampleAttendanceData) //calling AttendanceBarCard

        Spacer(modifier = Modifier.height(24.dp))

        // List of upcoming events inside a styled Surface
            UpcomingEventsList(events = upcomingEvents) //calling upcomingEventsList

        Spacer(modifier = Modifier.height(24.dp))
    }
}




data class AttendanceData(
    val title: String,
    val percent: Int,
    val attended: Int,
    val total: Int,
)

data class Mentor(
    val role: String,
    val name: String,
    val email: String,
    @DrawableRes val logoRes: Int
)

data class Event(
    val title: String,
    val dateTime: String,
    val purpose: String
)

data class AttendanceDatagraph(
    val week: String,
    val dsaPercentage: Float,
    val devPercentage: Float
)

val sampleAttendanceData = listOf(
    AttendanceDatagraph("Week 1", 70f, 60f),
    AttendanceDatagraph("Week 2", 80f, 75f),
    AttendanceDatagraph("Week 3", 90f, 85f),
)

val upcomingEvents = listOf(
    Event("Next DSA Session", "Thursday, 5:00 PM", "Binary Trees"),
    Event("Next Development Session", "Saturday, 3:00 PM", "RESTful APIs"),
    Event("Competition Deadline", "May 15, 2025", "Submit your projects"),
)

val attendanceDataList = listOf(
    AttendanceData("DSA Attendance", 71, 15, 21),
    AttendanceData("Development Attendance", 76, 16, 21)
)

val mentorList = listOf(
    Mentor("DSA Coordinator", "Roushan Srivastav", "roushan@example.com",logoRes = R.drawable.user),
    Mentor("Development Coordinator", "Rajat Jain", "john@example.com",logoRes = R.drawable.user)
)
