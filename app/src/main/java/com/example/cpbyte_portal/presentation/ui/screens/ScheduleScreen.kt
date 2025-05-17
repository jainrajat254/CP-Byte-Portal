package com.example.cpbyte_portal.presentation.ui.screens

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.domain.model.EventsResponse
import com.example.cpbyte_portal.presentation.ui.screens.components.CustomLoader
import com.example.cpbyte_portal.presentation.viewmodel.EventViewModel
import com.example.cpbyte_portal.util.ResultState
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import java.time.Month
import java.time.OffsetDateTime
import java.time.ZoneOffset

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewScheduleScreen(
    eventViewModel: EventViewModel = koinViewModel(),
) {

    var selectedDate by remember { mutableIntStateOf(LocalDate.now().dayOfMonth) }
    var selectedMonth by remember { mutableStateOf(LocalDate.now().month) }
    var selectedYear by remember { mutableIntStateOf(LocalDate.now().year) }
    var events by remember {
        mutableStateOf(mutableMapOf<Triple<Int, Month, Int>, Pair<String, String>>())
    }

    val context = LocalContext.current
    val getEventState by eventViewModel.getEventState.collectAsState()
    val onDelete: (Triple<Int, Month, Int>) -> Unit = {Key ->
        events = events.toMutableMap().apply {
            remove(Key)
        }
    }
    var isDialog by rememberSaveable { mutableStateOf(false) }
    var fetchedEvents by remember { mutableStateOf<List<EventsResponse>>(emptyList()) }

    // FAB toggle state
    var showAddEventCard by remember { mutableStateOf(false) }

    LaunchedEffect(getEventState) {
        when (getEventState) {
            is ResultState.Success -> {
                isDialog = false
                fetchedEvents = (getEventState as ResultState.Success<List<EventsResponse>>).data

                val updatedEvents = mutableMapOf<Triple<Int, Month, Int>, Pair<String, String>>()

                for (eventResponse in fetchedEvents) {
                    try {
                        val offsetDateTime = OffsetDateTime.parse(eventResponse.date)
                        val localDate = offsetDateTime.toLocalDate()

                        // Example: combine all titles and all descriptions
                        val title = eventResponse.events.joinToString(", ") { it.title }
                        val description =
                            eventResponse.events.joinToString("\n\n") { it.discription }

                        updatedEvents[Triple(
                            localDate.dayOfMonth,
                            localDate.month,
                            localDate.year
                        )] =
                            title to description

                    } catch (e: Exception) {
                        Log.e("EVENT_PARSE", "Error parsing date: ${eventResponse.date}", e)
                    }
                }
                events = updatedEvents
            }

            is ResultState.Failure -> {
                isDialog = false
                Log.d(
                    "GET EVENTS ERROR",
                    (getEventState as ResultState.Failure).error.message.toString()
                )
                Toast.makeText(context, "some error occurred, please try again", Toast.LENGTH_SHORT)
                    .show()
            }

            ResultState.Idle -> isDialog = false
            ResultState.Loading -> isDialog = true
        }
    }

    LaunchedEffect(selectedMonth, selectedYear) {
        eventViewModel.getEvents(
            formattedDate(
                selectedYear,
                selectedMonth.value
            )
        )
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddEventCard = !showAddEventCard },
                containerColor = Color(0xFF00CFFD),
                contentColor = Color.Black
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Event"
                )
            }
        },
        containerColor = Color.Transparent
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.login_bg),
                    contentScale = ContentScale.Crop
                )
                .padding(paddingValues)
        ) {

            // Shows loader when in loading state
            if (isDialog) {
                CustomLoader(text = "Redirecting....")
            } else {

                // Main Screen Layout
                Column(
                    modifier = Modifier
                        //.background(Color(0xFF0A0A0A))
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize()
                ) {

                    // Month and year Navigation
                    ScheduleHeader(
                        selectedMonth,
                        selectedYear,
                        onPreviousClicked = {
                            val prevMonth = selectedMonth.minus(1)
                            val year =
                                if (selectedMonth == Month.JANUARY) selectedYear - 1 else selectedYear
                            selectedMonth = prevMonth
                            selectedYear = year
                        },
                        onNextClicked = {
                            val nextMonth = selectedMonth.plus(1)
                            val year =
                                if (selectedMonth == Month.DECEMBER) selectedYear + 1 else selectedYear
                            selectedMonth = nextMonth
                            selectedYear = year
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Calender grid showing dates
                    CalendarSection(
                        selectedDate, { day -> selectedDate = day },
                        events, selectedMonth, selectedYear
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // show selected day's event
                    ShowSelectedEvent(
                        selectedDate = selectedDate,
                        selectedMonth = selectedMonth,
                        selectedYear = selectedYear,
                        events = events,
                        showEvents = showAddEventCard,
                        onDelete = onDelete
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Add new event section
                    if (showAddEventCard) {
                            EventInputSection(
                                selectedDate = selectedDate,
                                selectedMonth = selectedMonth,
                                selectedYear = selectedYear
                            )
                        }

                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
}
}

//Function to format date as per the requirement of backend
private fun formattedDate(selectedYear: Int, selectedMonth: Int): String {
    return "%04d-%02d".format(selectedYear, selectedMonth)
}