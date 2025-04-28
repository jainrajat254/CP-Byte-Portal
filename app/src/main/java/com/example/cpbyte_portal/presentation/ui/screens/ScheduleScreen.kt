package com.example.cpbyte_portal.presentation.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.time.Month


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleScreen() {
    // State for selected day, month, year and events
    var selectedDate by remember { mutableStateOf(1) }
    var selectedMonth by remember { mutableStateOf(Month.APRIL) }
    var selectedYear by remember { mutableStateOf(2025) }
    var events by remember { mutableStateOf(mutableMapOf<Triple<Int, Month, Int>, String>()) }

    // Main Screen Layout
    Column(
        modifier = Modifier
            .background(Color(0xFF0A0A0A))
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        TopBar()

        // Month and year Navigation
        ScheduleHeader (
            selectedMonth,
            selectedYear,
            onPreviousClicked = {
                val prevMonth = selectedMonth.minus(1)
                val year = if(selectedMonth == Month.JANUARY) selectedYear - 1 else selectedYear
                selectedMonth = prevMonth
                selectedYear = year
            },
            onNextClicked = {
                val nextMonth = selectedMonth.plus(1)
                val year = if(selectedMonth == Month.DECEMBER) selectedYear + 1 else selectedYear
                selectedMonth = nextMonth
                selectedYear = year
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Calender grid showing dates
        CalendarSection(
            selectedDate, { day -> selectedDate = day },
            events, selectedMonth, selectedYear)
        Spacer(modifier = Modifier.height(16.dp))

        // Add new event section
        EventInputSection(selectedDate,
            selectedMonth,
            selectedYear){
                eventText, day, month, year ->
            events[Triple(day, month, year)] = eventText
        }
        Spacer(modifier = Modifier.height(16.dp))

        // show selected day's event
        ShowSelectedEvent(selectedDate,
            selectedMonth,
            selectedYear,
            events
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}

