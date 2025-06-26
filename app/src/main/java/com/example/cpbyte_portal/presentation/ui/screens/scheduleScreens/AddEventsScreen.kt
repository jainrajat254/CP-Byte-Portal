package com.example.cpbyte_portal.presentation.ui.screens.scheduleScreens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cpbyte_portal.domain.model.AddEventRequest
import com.example.cpbyte_portal.presentation.ui.navigation.Routes
import com.example.cpbyte_portal.presentation.ui.screens.components.CommonHeader
import com.example.cpbyte_portal.presentation.ui.screens.components.CustomLoader
import com.example.cpbyte_portal.presentation.ui.screens.trackerScreens.textFieldColors
import com.example.cpbyte_portal.presentation.ui.theme.CPByteTheme
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
                Toast.makeText(context, "Event added successfully!", Toast.LENGTH_SHORT).show()
                navController.navigate(Routes.Schedule.route)
            }

            is ResultState.Failure -> {
                isDialog = false
                Toast.makeText(
                    context,
                    "Failed to add event. Please try again.",
                    Toast.LENGTH_SHORT
                ).show()
            }

            ResultState.Idle -> isDialog = false
            ResultState.Loading -> isDialog = true
        }
    }

    Scaffold(topBar = {
        CommonHeader(text = "Add Event", navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        })
    }, containerColor = MaterialTheme.colorScheme.background) { innerPadding ->
        if (isDialog) {
            CustomLoader(text = "Adding event")
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .padding(20.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = selectedDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                        OutlinedTextField(
                            value = eventTitle,
                            onValueChange = { eventTitle = it },
                            placeholder = { Text("Event Title", color = Color(0xFFB0BEC5)) },
                            colors = textFieldColors(),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.fillMaxWidth(),
                            maxLines = 1
                        )

                        OutlinedTextField(
                            value = eventDescription,
                            onValueChange = { eventDescription = it },
                            placeholder = { Text("Description", color = Color(0xFFB0BEC5)) },
                            colors = textFieldColors(),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.fillMaxWidth(),
                            maxLines = 3
                        )

                        ExposedDropdownMenuBox(
                            expanded = expanded,
                            onExpandedChange = { expanded = !expanded },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OutlinedTextField(
                                value = selectedEventType,
                                onValueChange = { selectedEventType = it },
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                                },
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth(),
                                colors = textFieldColors()
                            )

                            ExposedDropdownMenu(expanded = expanded,
                                onDismissRequest = { expanded = false }) {
                                eventTypes.forEach { subject ->
                                    DropdownMenuItem(text = { Text(subject) }, onClick = {
                                        selectedEventType = subject
                                        expanded = false
                                    })
                                }
                            }
                        }

                        Button(
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
                            enabled = eventTitle.isNotEmpty(),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = CPByteTheme.brandCyan),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Add Event", color = Color.Black, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun buildIsoDate(year: Int, month: Int, day: Int): String {
    val dateTime = OffsetDateTime.of(year, month, day, 0, 0, 0, 0, ZoneOffset.UTC)
    return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX"))
}