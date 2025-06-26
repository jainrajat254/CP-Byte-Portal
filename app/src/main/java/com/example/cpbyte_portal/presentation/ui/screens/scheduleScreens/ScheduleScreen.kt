package com.example.cpbyte_portal.presentation.ui.screens.scheduleScreens

import android.annotation.SuppressLint
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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cpbyte_portal.domain.model.EventsResponse
import com.example.cpbyte_portal.presentation.ui.navigation.BottomBar
import com.example.cpbyte_portal.presentation.ui.navigation.Routes
import com.example.cpbyte_portal.presentation.ui.screens.components.CustomLoader
import com.example.cpbyte_portal.presentation.ui.theme.CPByteTheme
import com.example.cpbyte_portal.presentation.viewmodel.EventViewModel
import com.example.cpbyte_portal.util.ResultState
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import java.time.Month
import java.time.OffsetDateTime

@SuppressLint("MutableCollectionMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PreviewScheduleScreen(
    eventViewModel: EventViewModel = koinViewModel(),
    navController: NavHostController,
) {

    var selectedDate by remember { mutableIntStateOf(LocalDate.now().dayOfMonth) }
    var selectedMonth by remember { mutableStateOf(LocalDate.now().month) }
    var selectedYear by remember { mutableIntStateOf(LocalDate.now().year) }
    var events by remember {
        mutableStateOf(mutableMapOf<Triple<Int, Month, Int>, Pair<String, String>>())
    }
    var multipleEvents by remember {
        mutableStateOf(mutableMapOf<Triple<Int, Month, Int>, List<Pair<String, String>>>())
    }

    val context = LocalContext.current
    val getEventState by eventViewModel.getEventState.collectAsState()
    val removeEventState by eventViewModel.removeEventState.collectAsState()
    var isDialog by rememberSaveable { mutableStateOf(false) }
    var fetchedEvents by remember { mutableStateOf<List<EventsResponse>>(emptyList()) }

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    LaunchedEffect(removeEventState) {
        when (removeEventState) {
            is ResultState.Success -> {
                Toast.makeText(context, "Event deleted successfully", Toast.LENGTH_SHORT).show()
                eventViewModel.resetRemoveEventState()
                eventViewModel.getEvents(formattedDate(selectedYear, selectedMonth.value))
            }

            is ResultState.Failure -> {
                Toast.makeText(context, "Failed to delete event", Toast.LENGTH_SHORT).show()
                eventViewModel.resetRemoveEventState()
            }

            else -> Unit
        }
    }


    LaunchedEffect(getEventState) {
        when (getEventState) {
            is ResultState.Success -> {
                isDialog = false
                fetchedEvents = (getEventState as ResultState.Success<List<EventsResponse>>).data

                val updatedEvents = mutableMapOf<Triple<Int, Month, Int>, Pair<String, String>>()
                val updatedMultipleEvents =
                    mutableMapOf<Triple<Int, Month, Int>, List<Pair<String, String>>>()

                for (eventResponse in fetchedEvents) {
                    try {
                        val offsetDateTime = OffsetDateTime.parse(eventResponse.date)
                        val localDate = offsetDateTime.toLocalDate()
                        val key = Triple(localDate.dayOfMonth, localDate.month, localDate.year)

                        val eventList = eventResponse.events.map { it.title to it.discription }

                        // Store a single combined summary (e.g. for calendar dots)
                        val titleSummary = eventList.joinToString(", ") { it.first }
                        val descriptionSummary = eventList.joinToString("\n\n") { it.second }

                        updatedEvents[key] = titleSummary to descriptionSummary
                        updatedMultipleEvents[key] = eventList // list of individual events

                    } catch (e: Exception) {
                        Log.e("EVENT_PARSE", "Error parsing date: ${eventResponse.date}", e)
                    }
                }

                events = updatedEvents
                multipleEvents = updatedMultipleEvents
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

    LaunchedEffect("${selectedMonth.name}-${selectedYear}") {
        eventViewModel.getEvents(formattedDate(selectedYear, selectedMonth.value))
    }


    Scaffold(
        bottomBar = {
            BottomBar(navController, currentRoute)
        },
        topBar = {
            ScheduleHeader(
                selectedMonth,
                selectedYear,
                onPreviousClicked = {
                    if (selectedMonth == Month.JANUARY) {
                        selectedMonth = Month.DECEMBER
                        selectedYear -= 1
                    } else {
                        selectedMonth = selectedMonth.minus(1)
                    }
                },
                onNextClicked = {
                    if (selectedMonth == Month.DECEMBER) {
                        selectedMonth = Month.JANUARY
                        selectedYear += 1
                    } else {
                        selectedMonth = selectedMonth.plus(1)
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val dateToPass =
                        LocalDate.of(selectedYear, selectedMonth, selectedDate).toString()
                    navController.navigate(Routes.AddEvent.createRoute(dateToPass))
                },
                containerColor = CPByteTheme.brandCyan,
                contentColor = Color.Black
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Event"
                )
            }
        },
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {

            // Shows loader when in loading state
            if (isDialog) {
                CustomLoader(text = "Fetching events...")
            } else {

                // Main Screen Layout
                Column(
                    modifier = Modifier
                        //.background(Color(0xFF0A0A0A))
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize()
                ) {

                    Spacer(modifier = Modifier.height(8.dp))

                    CalendarSection(
                        selectedDate, { day -> selectedDate = day },
                        events, selectedMonth, selectedYear
                    )

                    ShowSelectedEvent(
                        selectedDate = selectedDate,
                        selectedMonth = selectedMonth,
                        selectedYear = selectedYear,
                        events = multipleEvents,
                        showEvents = true,
                        onDelete = {
                            if (getEventState is ResultState.Success) {
                                val eventsResponse =
                                    (getEventState as ResultState.Success<List<EventsResponse>>).data

                                val formattedDate = "%04d-%02d-%02d".format(
                                    selectedYear,
                                    selectedMonth.value,
                                    selectedDate
                                )
                                val matchingResponse = eventsResponse.find {
                                    it.date.substring(
                                        0,
                                        10
                                    ) == formattedDate
                                }

                                if (matchingResponse != null && matchingResponse.events.isNotEmpty()) {
                                    val eventToDelete = matchingResponse.events.first()
                                    eventViewModel.removeEvent(eventId = eventToDelete.id)
                                } else {
                                    Toast.makeText(context, "Event not found", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            } else {
                                Toast.makeText(context, "Error fetching events", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}

private fun formattedDate(selectedYear: Int, selectedMonth: Int): String {
    return "%04d-%02d".format(selectedYear, selectedMonth)
}