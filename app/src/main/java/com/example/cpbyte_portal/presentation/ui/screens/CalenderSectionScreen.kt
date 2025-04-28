package com.example.cpbyte_portal.presentation.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.Month

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarSection(
    selectedDate: Int,
    onDateSelected: (Int) -> Unit,
    events: Map<Triple<Int, Month, Int>, String>,
    selectedMonth: Month,
    selectedYear: Int,
) {
    // Calender grid generation
    val daysInMonth = getDaysInMonth(selectedMonth, selectedYear)
    val firstDayOfMonth = getFirstDayOfMonth(selectedMonth, selectedYear)
    val today = LocalDate.now()

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Spacer(modifier = Modifier.height(8.dp))

        // weekdays title
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat").forEach { day ->
                Text(
                    text = day,
                    color = Color.Gray,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // days grid
        var day = 1
        Column {
            repeat(6) { // 6 rows
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    repeat(7) { weekDay -> // 7 columns
                        if (day <= daysInMonth && (weekDay >= firstDayOfMonth || day > 1)) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(
                                        if (selectedDate == day) Color(0xFF00CFFD) else Color(0xFF1E2A3A),
                                        shape = RoundedCornerShape(6.dp)
                                    )
                                    .clickable { onDateSelected(day) },
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(text = day.toString(), color = Color.White, fontSize = 14.sp)
                                    if (events.containsKey(Triple(day, selectedMonth, selectedYear))) {
                                        Text(text = ".", color = Color.Red, fontSize = 12.sp)
                                    }
                                }
                            }
                            day++
                        } else {
                            Spacer(modifier = Modifier.size(40.dp))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}