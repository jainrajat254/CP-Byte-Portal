package com.example.cpbyte_portal.presentation.ui.screens


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.Month

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarSection(
    selectedDate: Int, // The currently selected day
    onDateSelected: (Int) -> Unit, // Callback when a date is clicked
    events: Map<Triple<Int, Month, Int>, String>, // Events mapped by (day, month, year)
    selectedMonth: Month, // Currently displayed month
    selectedYear: Int, // Currently displayed year
) {



    // Prepare Calender data
    val firstDayOfMonth = LocalDate.of(selectedYear, selectedMonth, 1)
    val startDayOfWeek = (firstDayOfMonth.dayOfWeek.value % 7) // Sunday = 0
    val daysInMonth = selectedMonth.length(firstDayOfMonth.isLeapYear)

    // Days of week header
    val weekDays = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")

    // Local state to handle internal UI interactions
    var showEventInput by remember { mutableStateOf(false) }
    var eventText by remember { mutableStateOf("") }
    var selectedDay by remember { mutableStateOf<Int?>(null) }

    Column(modifier = Modifier.padding(16.dp)) {
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

        // Total no. of cells to fill rows
        val totalCells = startDayOfWeek + daysInMonth
        // no. of rows needed
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
                        var isClicked by remember { mutableStateOf(false) }
                        val scale by animateFloatAsState(
                            targetValue = if (isClicked) .7f else 1f,
                            animationSpec = tween(durationMillis = 200),
                            label = "ScaleAnimation"
                        )
                        LaunchedEffect(isClicked) {
                            if (isClicked) {
                                kotlinx.coroutines.delay(100)
                                isClicked = false
                            }
                        }
                        val currentDay = dayCounter

                        // Check if this cell is selected
                        val isSelected = selectedDay == currentDay

                        // Get event text for this day
                        val key = Triple(currentDay, selectedMonth, selectedYear)
                        val event = events[key]

                        // Make clickable calender
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .padding(2.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(
                                    if (selectedDate == dayCounter) Color(0xFF00CFFD) else Color(0xFF1F305A)
                                )
                                .scale(scale)
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                ) {
                                    isClicked = true
                                    selectedDay = currentDay
                                    showEventInput = true
                                    onDateSelected(currentDay)
                                }
                            ,
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(top = 6.dp, bottom = 4.dp)
                            ) {
                                Text(
                                    text = currentDay.toString(),
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                                event?.let {
                                    Text(
                                        text = it.take(10) + if (it.length > 10) "..." else "",
                                        color = Color.LightGray,
                                        fontSize = 10.sp,
                                        textAlign = TextAlign.Center,
                                        maxLines = 2
                                    )
                                }
                            }
                        }
                        // Move to next day after rendering
                        dayCounter++
                    }
                }
            }
        }
    }
}

