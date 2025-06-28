package com.example.cpbyte_portal.presentation.ui.screens.schedule.components

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.domain.model.AddEventRequest
import com.example.cpbyte_portal.presentation.ui.navigation.Routes
import com.example.cpbyte_portal.presentation.ui.screens.components.CPByteButton
import com.example.cpbyte_portal.presentation.ui.screens.components.CommonHeader
import com.example.cpbyte_portal.presentation.ui.screens.components.CustomLoader
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.Between
import com.example.cpbyte_portal.presentation.viewmodel.EventViewModel
import com.example.cpbyte_portal.util.ResultState
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddEventsScreen(
    selectedDate: LocalDate,
    eventViewModel: EventViewModel,
    navController: NavHostController,
) {
    var eventTitle by remember { mutableStateOf("") }
    var eventDescription by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val eventTypes = listOf("Class", "General")
    var selectedEventType by remember { mutableStateOf(eventTypes[0]) }

    val context = LocalContext.current
    val addEventState by eventViewModel.addEventState.collectAsState()
    var isDialog by remember { mutableStateOf(false) }

    LaunchedEffect(addEventState) {
        when (addEventState) {
            is ResultState.Success -> {
                isDialog = false
                Toast.makeText(
                    context,
                    context.getString(R.string.event_added_successfully), Toast.LENGTH_SHORT
                ).show()
                eventViewModel.resetAddEventState()
                navController.navigate(Routes.Schedule.route)
            }

            is ResultState.Failure -> {
                isDialog = false
                Toast.makeText(
                    context,
                    "Failed to add event. Please try again.",
                    Toast.LENGTH_SHORT
                ).show()
                eventViewModel.resetAddEventState()
            }

            ResultState.Idle -> isDialog = false
            ResultState.Loading -> isDialog = true
        }
    }

    Scaffold(
        topBar = {
            CommonHeader(text = stringResource(R.string.add_event_main), navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            })
        }
    ) { innerPadding ->
        if (isDialog) {
            CustomLoader(text = stringResource(R.string.adding_event))
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(Between),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                AddEventForm(
                    selectedDate = selectedDate,
                    eventTitle = eventTitle,
                    onEventTitleChange = { eventTitle = it },
                    eventDescription = eventDescription,
                    onEventDescriptionChange = { eventDescription = it },
                    selectedEventType = selectedEventType,
                    onEventTypeChange = { selectedEventType = it },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    eventTypes = eventTypes,
                )

                CPByteButton(
                    onClick = {
                        val date = buildIsoDate(
                            selectedDate.year,
                            selectedDate.monthValue,
                            selectedDate.dayOfMonth
                        )
                        if (eventTitle.trim().isNotEmpty()) {
                            val addEvent = AddEventRequest(
                                date = date,
                                category = selectedEventType,
                                title = eventTitle.trim(),
                                discription = eventDescription.trim()
                            )
                            eventViewModel.addEvent(addEvent)
                            eventTitle = ""
                            eventDescription = ""
                        }
                    },
                    value = stringResource(R.string.add_event_main),
                    enabled = eventTitle.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun buildIsoDate(year: Int, month: Int, day: Int): String {
    val dateTime = OffsetDateTime.of(year, month, day, 0, 0, 0, 0, ZoneOffset.UTC)
    return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX"))
}