package com.example.cpbyte_portal.presentation.ui.screens.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.presentation.ui.screens.Event


// event card
@Composable
fun UpcomingEventCard(event: Event) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp) //Vertical spacing between cards
            .border(
                width = 2.dp,
                color = Color(0xFF343B43),
                shape = RoundedCornerShape(12.dp) // Match card shape
            ), RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),  // Inner padding inside the card
            verticalAlignment = Alignment.CenterVertically // Vertically center icon and text
        ) { //Icon for event
            Icon(
                imageVector = Icons.Default.Event,
                contentDescription = "Event Icon",
                tint = Color(0xFF60A5FA), // Light blue
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))

            // Column to display title and date/purpose
            Column {
                Text(text = event.title, fontWeight = FontWeight.Bold, color = Color.White , fontSize = 20.sp)
                Spacer(modifier = Modifier.width(24.dp))
                Text(
                    text = "${event.dateTime} - ${event.purpose}",
                    color = Color(0xFF9CA3AF), // Gray text
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun UpcomingEventsList(events: List<Event>) {
    Card(
        Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color(0xFF343B43),
                shape = RoundedCornerShape(12.dp) // Match card shape
            ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF17191d)),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    )
    {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Upcoming Activities",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Loop through each event and render the individual event cards
            events.forEach { event ->
                UpcomingEventCard(event = event)
            }
        }
    }
}
