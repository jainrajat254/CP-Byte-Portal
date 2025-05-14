package com.example.cpbyte_portal.presentation.ui.screens

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.domain.model.AddEventRequest
import com.example.cpbyte_portal.presentation.ui.screens.components.textFieldColors
import com.example.cpbyte_portal.presentation.viewmodel.EventViewModel
import com.example.cpbyte_portal.util.ResultState
import org.koin.androidx.compose.koinViewModel
import java.time.Month
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EventInputSection(
    selectedDate: Int,
    selectedMonth: Month,
    selectedYear: Int,
    eventViewModel: EventViewModel = koinViewModel(),
) {

    var eventTitle by remember { mutableStateOf("") }
    var eventDescription by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val eventTypes = listOf("Class", "General")
    var selectedEventType by remember { mutableStateOf(eventTypes[0]) }

    val context = LocalContext.current
    val addEventState by eventViewModel.addEventState.collectAsState()

    LaunchedEffect(addEventState) {
        when (addEventState) {
            is ResultState.Success -> {
                Toast.makeText(context, "Event added successfully!", Toast.LENGTH_SHORT).show()
            }

            is ResultState.Failure -> {
                Log.d(
                    "Add EVENT ERROR",
                    (addEventState as ResultState.Failure).error.message.toString()
                )
                Toast.makeText(context, "Failed to add event, Please try again", Toast.LENGTH_SHORT)
                    .show()
            }

            else -> Unit
        }
    }


    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(Color(0xFF1F305A), shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        // Show selected date at the top
        Text(text = "$selectedDate ${
            selectedMonth.name.lowercase().replaceFirstChar { it.uppercase() }
        } $selectedYear",
            color = Color(0xFF0EC1E7),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        //Text field for entering event details
        TextField(
            value = eventTitle,
            onValueChange = { eventTitle = it },
            placeholder = { Text("Event Title", color = Color.LightGray) },
            colors = textFieldColors(),
            maxLines = 1,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = eventDescription,
            onValueChange = { eventDescription = it },
            placeholder = { Text("Description", color = Color.LightGray) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF2555A7),
                unfocusedContainerColor = Color(0xFF2555A7),
                focusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ), maxLines = 3,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
        ) {
            TextField(
                value = selectedEventType,
                onValueChange = {},
                readOnly = true,
                label = { Text("Event Type", color = Color.White) },
                trailingIcon = {
                    TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = textFieldColors(),
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(color = Color(0xFF0B2D50))
            ) {
                eventTypes.forEach { type ->
                    DropdownMenuItem(
                        text = { Text(type) },
                        onClick = {
                            selectedEventType = type
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                val date = buildIsoDate(selectedYear, selectedMonth.value, selectedDate)
                if (eventTitle.trim().isNotEmpty()) {
                    val addEvent = AddEventRequest(
                        date = date,
                        category = selectedEventType,
                        title = eventTitle.trim(),
                        discription = eventDescription.trim()
                    )
                    eventViewModel.addEvent(addEventRequest = addEvent)
                    eventTitle = ""
                    eventDescription = ""
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00CFFD)),
            shape = RoundedCornerShape(6.dp),
            enabled = eventTitle.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add event", color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun buildIsoDate(year: Int, month: Int, day: Int): String {
    val dateTime = OffsetDateTime.of(year, month, day, 0, 0, 0, 0, ZoneOffset.UTC)
    return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX"))
}
