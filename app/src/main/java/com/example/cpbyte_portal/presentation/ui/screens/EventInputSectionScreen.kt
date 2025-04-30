package com.example.cpbyte_portal.presentation.ui.screens


import android.os.Build
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.Month

@Composable
fun EventInputSection(selectedDate: Int,
                      selectedMonth: Month,
                      selectedYear: Int,
                      onEventAdded: (String, Int, Month, Int) -> Unit) {
    var eventText by remember { mutableStateOf("") }
    var dayInput by remember { mutableStateOf(selectedDate.toString()) }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(Color(0xFF1F305A), shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        // Show selected date at the top
        Text(
            text = "$selectedDate ${selectedMonth.name.lowercase().replaceFirstChar { it.uppercase() }} $selectedYear",
            color = Color(0xFF0EC1E7),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        //Text field for entering event details
        TextField(
            value = eventText,
            onValueChange = { eventText = it }, // Update state as user types
            placeholder = { Text(text = "Add event details", color = Color.LightGray) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF2555A7),
                unfocusedContainerColor = Color(0xFF2555A7),
                focusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
                .height(100.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                if (eventText.isNotEmpty()) { // Only add if input is not blank
                    onEventAdded(eventText, selectedDate, selectedMonth, selectedYear) // Call callback with event
                    eventText = "" // Clear input after adding
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00CFFD)),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add event", color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun getDaysInMonth(month: Month, year: Int): Int {
    // Get no. of days in a month accounting for leap years
    return LocalDate.of(year, month, 1).lengthOfMonth()
}

@RequiresApi(Build.VERSION_CODES.O)
fun getFirstDayOfMonth(month: Month, year: Int): Int {
    // Get the day of the week for the 1st day of the month
    val dayOfWeek = LocalDate.of(year, month, 1).dayOfWeek.value
    return (dayOfWeek%7) // Convert Monday=1, Sunday = 7 into Sunday=0, Saturday=6
}
