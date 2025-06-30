package com.example.cpbyte_portal.presentation.ui.screens.home.components

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.cpbyte_portal.presentation.viewmodel.EventViewModel
import com.example.cpbyte_portal.util.ResultState
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Upcoming10DaysEvents(
    eventViewModel: EventViewModel,
    onEventsLoaded: (Boolean) -> Unit,
) {
    val context = LocalContext.current
    val getEventState by eventViewModel.getEventState.collectAsState()
    val upcomingEvents by eventViewModel.upcomingEvents.collectAsState()

    LaunchedEffect(Unit) {
        eventViewModel.getEventsForNext10Days()
    }

    LaunchedEffect(getEventState) {
        if (getEventState is ResultState.Failure) {
            Toast.makeText(context, "Failed to fetch events", Toast.LENGTH_SHORT).show()
        }
    }

    onEventsLoaded(upcomingEvents.isNotEmpty())

    if (upcomingEvents.isNotEmpty()) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            for ((date, event) in upcomingEvents) {
                UpcomingEventCard(
                    event = event,
                    eventDate = date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
                )
            }
        }
    }
}
