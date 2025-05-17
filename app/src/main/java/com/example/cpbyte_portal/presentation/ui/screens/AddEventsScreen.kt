package com.example.cpbyte_portal.presentation.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddEventsScreen(
    selectedDate: LocalDate, // dates for which event is added
    onSave: (Triple<String, String, String>) -> Unit, // callback to return the list of entered events
) {
    // State to manage input for both events
    var eventTitle by remember { mutableStateOf("") }
    var eventDescription by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }
    val eventTypes = listOf("General", "Class")
    var selectedEventType by remember { mutableStateOf("Class") }


    Scaffold(
        containerColor = Color(0xFF111111)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFF111111))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Screen Title
            Text(
                text = "Add Event",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Main input container card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF17191d))
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Display selected date at the top
                    Text(
                        text = "${selectedDate.dayOfMonth} ${selectedDate.month} ${selectedDate.year}",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height((20.dp)))

                    // Row for Title input and delete icon
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = eventTitle,
                            onValueChange = { eventTitle = it },
                            placeholder = { Text("Event Title", color = Color.White) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp)),
                            colors = com.example.cpbyte_portal.presentation.ui.screens.components.textFieldColors(),
                            maxLines = 1,
                            shape = RoundedCornerShape(10.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    OutlinedTextField(
                        value = eventDescription,
                        onValueChange = { eventDescription = it },
                        placeholder = { Text("Description", color = Color.White) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp)),
                        colors = com.example.cpbyte_portal.presentation.ui.screens.components.textFieldColors(),
                        maxLines = 3,
                        shape = RoundedCornerShape(10.dp)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Box {
                        OutlinedTextField(
                            value = selectedEventType,
                            onValueChange = {},
                            label = { Text("Event Type", color = Color.White) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp)),
                            readOnly = true,
                            colors = com.example.cpbyte_portal.presentation.ui.screens.components.textFieldColors(),
                            shape = RoundedCornerShape(10.dp),
                            trailingIcon = {
                                IconButton(onClick = { expanded = !expanded }) {
                                    Icon(
                                        imageVector = if (expanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                }
                            }
                        )

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.background(Color(0xFF1E293B))
                        ) {
                            eventTypes.forEach { eventTypes ->
                                DropdownMenuItem(
                                    text = { Text(eventTypes, color = Color.White) },
                                    onClick = {
                                        selectedEventType = eventTypes
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }



                    Spacer(modifier = Modifier.height(30.dp))

                    // Save Button
                    Button(
                        onClick = {
                            if (eventTitle.isNotBlank() && eventDescription.isNotBlank()) {
                                val newEvent =
                                    Triple(eventTitle, eventDescription, selectedEventType)
                                onSave(newEvent)

                                // Clear input fields
                                eventTitle = ""
                                eventDescription = ""
                                selectedEventType = "Class"
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B82F6))
                    ) {
                        Text("Save", color = Color.White)
                    }

                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewAddEventsScreen(){
    AddEventsScreen(
        selectedDate = LocalDate.now(),
        onSave = {}
    )
}