package com.example.cpbyte_portal.presentation.ui.screens.schedule.components

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
import androidx.compose.material3.MaterialTheme
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
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.ExtraExtraSmall
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.ExtraSmall
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.Medium
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.Small
import com.example.cpbyte_portal.presentation.ui.theme.CPByteTheme
import java.time.LocalDate
import java.time.Month

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarSection(
    selectedDate: Int,
    onDateSelected: (Int) -> Unit,
    events: Map<Triple<Int, Month, Int>, Pair<String, String>>,
    selectedMonth: Month,
    selectedYear: Int,
) {
    val firstDayOfMonth = LocalDate.of(selectedYear, selectedMonth, 1)
    val startDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7 // Sunday = 0
    val daysInMonth = selectedMonth.length(firstDayOfMonth.isLeapYear)
    val weekDays = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")

    var selectedDay by remember { mutableStateOf<Int?>(null) }

    Column(modifier = Modifier.padding(Medium)) {

        // Weekday labels
        Row(Modifier.fillMaxWidth()) {
            weekDays.forEach { day ->
                Text(
                    text = day,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(Small))

        val totalCells = startDayOfWeek + daysInMonth
        val totalRows = (totalCells + 6) / 7
        var dayCounter = 1

        repeat(totalRows) { rowIndex ->
            Row(Modifier.fillMaxWidth()) {
                repeat(7) { columnIndex ->
                    val cellIndex = rowIndex * 7 + columnIndex
                    if (cellIndex < startDayOfWeek || dayCounter > daysInMonth) {
                        Spacer(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .padding(ExtraExtraSmall)
                        )
                    } else {
                        val currentDay = dayCounter++
                        val isSelected = selectedDay == currentDay
                        val isBordered = selectedDate == currentDay
                        val key = Triple(currentDay, selectedMonth, selectedYear)
                        val hasEvent = events[key] != null

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .padding(ExtraSmall)
                                .clip(RoundedCornerShape(10.dp))
                                .background(
                                    if (isSelected) CPByteTheme.brandCyan
                                    else MaterialTheme.colorScheme.secondaryContainer
                                )
                                .border(
                                    width = if (isBordered) 2.dp else 0.dp,
                                    color = if (isBordered) CPByteTheme.brandCyan else Color.Transparent,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .clickable {
                                    selectedDay = currentDay
                                    onDateSelected(currentDay)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(vertical = Small),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = currentDay.toString(),
                                    color = MaterialTheme.colorScheme.onBackground,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                if (hasEvent) {
                                    Box(
                                        modifier = Modifier
                                            .size(8.dp)
                                            .background(CPByteTheme.brandCyan, CircleShape)
                                            .align(Alignment.CenterHorizontally)
                                    )
                                } else {
                                    Spacer(modifier = Modifier.height(6.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
