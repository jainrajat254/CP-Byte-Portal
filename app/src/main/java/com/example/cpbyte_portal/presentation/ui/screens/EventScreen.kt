package com.example.cpbyte_portal.presentation.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.presentation.ui.screens.components.CPByteTabRow
import com.example.cpbyte_portal.presentation.ui.screens.components.EventCard
import com.example.cpbyte_portal.presentation.ui.screens.components.EventData
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.ExtraExtraLarge
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.ExtraExtraSmall
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.ExtraSmall
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.Medium
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.Small
import java.time.LocalDate

// The code requires at least Android Oreo(API 26)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EventScreen(
    events: List<EventData>,
    onEdit: (EventData) -> Unit,
    onDelete: (EventData) -> Unit,
    onAddEvent: () -> Unit  // Navigation logic to navigate to ScheduleScreen
) {

    // Only show events with category "general"
    val filterEvents = rememberSaveable(events) {
        events.filter {
            it.category.lowercase() == "general"
        }
    }

    val todayDate = LocalDate.now()  // Today's date

    // Separate events into upcoming and past using todayDate
    val upcomingEvents = filterEvents.filter { it.date >= todayDate }.sortedBy { it.date }
    val pastEvents = filterEvents.filter { it.date < todayDate }.sortedByDescending { it.date }

    // Shows the list of tabs and their count
    val tabTitles = listOf(
        "Upcoming (${upcomingEvents.size})",
        "Past (${pastEvents.size})"
    )

    // Adding a slider using horizontal pager
    // Index 0 - upcoming, 1 - past
    var pagerIndex by rememberSaveable { mutableIntStateOf(0) }
    val pagerState = rememberPagerState {
        tabTitles.size
    }

    // When page index changes update the pagerState
    LaunchedEffect(pagerIndex) {
        pagerState.animateScrollToPage(pagerIndex)
    }

    // when pager index changes when user swipes, update the pagerIndex
    LaunchedEffect(pagerState.currentPage) {
        if (pagerIndex != pagerState.currentPage) {
            pagerIndex = pagerState.currentPage
        }
    }

    Scaffold(
        containerColor = Color(0xFF121212) // Background color for the screen
    ) { innerpadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
                .padding(horizontal = ExtraSmall, vertical = ExtraExtraSmall),
            verticalArrangement = Arrangement.Center,
        ) {
            // Top heading
            Text(
                text = stringResource(R.string.events_heading),  //Hard-coded string
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = Medium, bottom = Small, start = Small)
            )

            //Row for the "Add Event" button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = Small, end = Small),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = onAddEvent,   // This contains navigation logic
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1976D2)
                    ),
                    elevation = ButtonDefaults.elevatedButtonElevation(), //Gives an elevated look to the button
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = stringResource(R.string.add_event),
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
            }
            //TabRow for upcoming and past events
            CPByteTabRow(
                tabTitles = tabTitles,
                selectedTab = pagerIndex,
                onTabSelected = { pagerIndex = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Small)
                    .padding(start = Small, end = Small)
            )
            Spacer(Modifier.height(8.dp))

            //Horizontal pager for sliding between tabs
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // takes the rest of the available space
            ) { page ->
                // Choose which events to show on the current page
                val eventsToShow = if (page == 0) upcomingEvents
                else pastEvents

                if (eventsToShow.isEmpty()) {
                    // If no events found
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = ExtraExtraLarge),  // adds space at the top
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.no_events_message),
                            color = Color.LightGray,
                            fontSize = 18.sp,
                        )
                    }
                } else {
                    // List of event cards
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        items(eventsToShow.size) { index ->
                            val event = eventsToShow[index]
                            // Shows each event using EventCard
                            EventCard(
                                event = event,
                                onEdit = { onEdit(event) },
                                onDelete = { onDelete(event) }
                            )
                        }
                    }
                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = true)
fun EventScreenPreview() {

    // Creates a list of sample events for preview
    val sampleEvents = listOf(

        // upcoming event
        EventData(
            title = "DSA Workshop",
            description = "Deep dive into binary trees, traversals, and common problems.",
            category = "DSA",
            date = LocalDate.now().plusDays(1)
        ),

        // upcoming event
        EventData(
            title = "Club Meeting",
            description = "Club meeting for all members",
            category = "General",
            date = LocalDate.now().plusDays(2)
        ),
        EventData(
            title = "Development Workshop",
            description = "Hands-on session covering React hooks and custom hook patterns.",
            category = "General",
            date = LocalDate.now().plusDays(3)
        ),
        EventData(
            title = "Development Workshop",
            description = "Hands-on session covering React hooks and custom hook patterns.",
            category = "Contest",
            date = LocalDate.now().plusDays(3)
        )
    )
    // Shows EventScreen with the sample events
    EventScreen(
        events = sampleEvents,
        onEdit = {},   // no action for edit in preview
        onDelete = {},  // no action for delete in preview
        onAddEvent = {}  // no action for add event in preview
    )

}