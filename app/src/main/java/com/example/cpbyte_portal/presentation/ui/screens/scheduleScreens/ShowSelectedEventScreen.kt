package com.example.cpbyte_portal.presentation.ui.screens.scheduleScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.Month

@Composable
fun ShowSelectedEvent(
    selectedDate: Int,
    selectedMonth: Month,
    selectedYear: Int,
    events: Map<Triple<Int, Month, Int>, List<Pair<String, String>>>,
    showEvents: Boolean,
    onDelete: (String) -> Unit // Pass event ID or unique identifier
) {
    val eventKey = Triple(selectedDate, selectedMonth, selectedYear)
    val eventList = events[eventKey]

    var showDeleteDialog by remember { mutableStateOf(false) }
    var selectedEventIndex by remember { mutableStateOf(-1) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showEvents && !eventList.isNullOrEmpty()) {
            eventList.forEachIndexed { index, event ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = event.first,
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f)
                            )
                            IconButton(
                                onClick = {
                                    selectedEventIndex = index
                                    showDeleteDialog = true
                                },
                                modifier = Modifier.size(40.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete Event",
                                    tint = Color(0xFFF87171), // Red for delete
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = event.second,
                            color = Color(0xFF90A4AE),
                            fontSize = 16.sp
                        )
                    }
                }
            }

            // Show delete confirmation dialog
            if (showDeleteDialog && selectedEventIndex >= 0) {
                AlertDialog(
                    onDismissRequest = { showDeleteDialog = false },
                    title = {
                        Text(
                            text = "Delete Event",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    },
                    text = {
                        Text(
                            text = "Are you sure you want to delete this event?",
                            color = Color(0xFFB0BEC5),
                            fontSize = 16.sp
                        )
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            val eventToDelete = eventList[selectedEventIndex]
                            onDelete(eventToDelete.first) // Ideally pass ID, not title
                            showDeleteDialog = false
                        }) {
                            Text("Yes", color = Color(0xFF00CFFD))
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDeleteDialog = false }) {
                            Text("No", color = Color(0xFFB0BEC5))
                        }
                    },
                    containerColor = Color(0xFF1E293B),
                )
            }
        } else {
            Text(
                text = "No events to show",
                color = Color(0xFF90A4AE),
                fontSize = 16.sp
            )
        }
    }
}


