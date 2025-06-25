package com.example.cpbyte_portal.presentation.ui.screens.scheduleScreens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.Medium
import java.time.LocalDate
import java.time.Month

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarSection(
    selectedDate: Int, // The currently selected day
    onDateSelected: (Int) -> Unit, // Callback when a date is clicked
    events: Map<Triple<Int, Month, Int>, Pair<String, String>>,
    selectedMonth: Month, // Currently displayed month
    selectedYear: Int, // Currently displayed year
) {
    // Prepare Calendar data
    val firstDayOfMonth = LocalDate.of(selectedYear, selectedMonth, 1)
    val startDayOfWeek = (firstDayOfMonth.dayOfWeek.value % 7) // Sunday = 0
    val daysInMonth = selectedMonth.length(firstDayOfMonth.isLeapYear)

    // Weekdays header
    val weekDays = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")

    // Local state to handle internal UI interactions
    var showEventInput by remember { mutableStateOf(false) }
    var selectedDay by remember { mutableStateOf<Int?>(null) }

    var showEventDialog by remember { mutableStateOf(false) }
    var selectedEventText by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.padding(Medium)) {
        // Weekday header row
        Row(modifier = Modifier.fillMaxWidth()) {
            weekDays.forEach {
                Text(
                    text = it,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Total number of cells to fill rows
        val totalCells = startDayOfWeek + daysInMonth
        // Number of rows needed
        val totalRows = (totalCells + 6) / 7
        // Counter to track day numbers in month
        var dayCounter = 1

        repeat(totalRows) { rowIndex ->
            Row(modifier = Modifier.fillMaxWidth()) {
                repeat(7) { columnIndex ->
                    val cellIndex = rowIndex * 7 + columnIndex
                    if (cellIndex < startDayOfWeek || dayCounter > daysInMonth) {
                        // Empty cell (before 1st or after end of month)
                        Spacer(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .padding(2.dp)
                        )
                    } else {
                        val currentDay = dayCounter

                        // Check if this cell is selected
                        val isSelected = selectedDay == currentDay

                        // Get event text for this day
                        val key = Triple(currentDay, selectedMonth, selectedYear)
                        val eventPair = events[key]
                        val event = eventPair?.first

                        // Make clickable calendar cell
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .padding(4.dp)
                                .clip(RoundedCornerShape(10.dp)) // Rounded corners for the cells
                                .background(if (isSelected) Color(0xFF00CFFD) else Color(0xFF1F305A)) // Cyan if selected
                                .border(
                                    width = if (selectedDate == currentDay) 2.dp else 0.dp,
                                    color = if (selectedDate == currentDay) Color(0xFF00CFFD) else Color.Transparent,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .clickable {
                                    selectedDay = currentDay
                                    showEventInput = true
                                    onDateSelected(currentDay)
                                    // If the clicked day has an event, show it in dialog
                                    selectedEventText = event
                                    showEventDialog = event != null
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            // Display date and event inside the cell
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(vertical = 6.dp), // Increased padding for spacing
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = currentDay.toString(),
                                    color = Color.White,
                                    fontSize = 16.sp, // Slightly larger font for day numbers
                                    fontWeight = FontWeight.Bold
                                )

                                // Show dot for event if available
                                if (eventPair != null) {
                                    Box(
                                        modifier = Modifier
                                            .size(8.dp) // Increased dot size for better visibility
                                            .background(Color(0xFF00CFFD), CircleShape) // Cyan dot with circle shape
                                            .align(Alignment.CenterHorizontally)
                                    )
                                } else {
                                    Spacer(modifier = Modifier.height(6.dp)) // Keeps spacing consistent when no event
                                }
                            }
                        }
                        dayCounter++
                    }
                }
            }
        }
    }
}
