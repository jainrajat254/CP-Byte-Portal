package com.example.cpbyte_portal.presentation.ui.screens.components

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.domain.model.Event
import com.example.cpbyte_portal.domain.model.EventsResponse
import com.example.cpbyte_portal.presentation.viewmodel.EventViewModel
import com.example.cpbyte_portal.util.ResultState
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@Composable
fun UpcomingEventCard(event: Event, eventDate: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .border(
                width = 1.dp,
                color = Color(0xFF2C3442),
                shape = RoundedCornerShape(12.dp)
            ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = event.title,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = eventDate,
                color = Color(0xFF90A4AE),
                fontSize = 16.sp
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Upcoming10DaysEvents(
    eventViewModel: EventViewModel,
    onEventsLoaded: (Boolean) -> Unit
) {
    val context = LocalContext.current
    val getEventState by eventViewModel.getEventState.collectAsState()

    val today = remember { LocalDate.now() }
    val endDate = remember { today.plusDays(10) }

    val addedEventIds = remember { mutableSetOf<String>() }
    val collectedEvents = remember { mutableStateListOf<Pair<LocalDate, Event>>() }

    var currentMonthFetched by remember { mutableStateOf(false) }
    var nextMonthFetched by remember { mutableStateOf(false) }

    val currentMonth = today.monthValue
    val currentYear = today.year

    val nextMonth = if (today.monthValue == 12) 1 else today.monthValue + 1
    val nextMonthYear = if (today.monthValue == 12) today.year + 1 else today.year

    LaunchedEffect(Unit) {
        eventViewModel.getEvents(formattedDate(currentYear, currentMonth))
    }

    LaunchedEffect(getEventState) {
        when (getEventState) {
            is ResultState.Success -> {
                val allEvents = (getEventState as ResultState.Success<List<EventsResponse>>).data

                for (response in allEvents) {
                    val date = try {
                        OffsetDateTime.parse(response.date).toLocalDate()
                    } catch (e: Exception) {
                        null
                    }

                    if (date != null && !date.isBefore(today) && !date.isAfter(endDate)) {
                        for (event in response.events) {
                            if (event.id !in addedEventIds) {
                                addedEventIds.add(event.id)
                                collectedEvents.add(date to event)
                            }
                        }
                    }
                }

                if (!currentMonthFetched) {
                    currentMonthFetched = true
                    eventViewModel.getEvents(formattedDate(nextMonthYear, nextMonth))
                } else if (!nextMonthFetched) {
                    nextMonthFetched = true
                    onEventsLoaded(collectedEvents.isNotEmpty())
                }
            }

            is ResultState.Failure -> {
                Toast.makeText(context, "Failed to fetch events", Toast.LENGTH_SHORT).show()
                onEventsLoaded(false)
            }

            else -> {}
        }
    }

    if (collectedEvents.isNotEmpty()) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            for ((date, event) in collectedEvents) {
                UpcomingEventCard(
                    event = event,
                    eventDate = date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
                )
            }
        }
    }
}


private fun formattedDate(year: Int, month: Int): String {
    return "%04d-%02d".format(year, month)
}

