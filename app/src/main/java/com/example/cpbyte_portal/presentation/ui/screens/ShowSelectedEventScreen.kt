package com.example.cpbyte_portal.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.Month

@Composable
fun ShowSelectedEvent(
    selectedDate: Int,
    selectedMonth: Month,
    selectedYear: Int,
    events: Map<Triple<Int, Month, Int>, String>
) {
    // show the event text for the selected day
    val event = events[Triple(selectedDate, selectedMonth, selectedYear)]
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Events for $selectedDate ${
                selectedMonth.name.lowercase().replaceFirstChar { it.uppercase() }
            }",
            color = Color(0xFF00CFFD),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))
        if (event != null) {
            Text(
                text = event,
                color = Color.White,
                fontSize = 16.sp
            )
        } else {
            Text(
                text = "No events scheduled for this day.",
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
    }
}