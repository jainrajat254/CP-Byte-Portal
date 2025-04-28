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
            .background(Color(0xFF1E2A3A), shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text(
            text = "$selectedDate ${selectedMonth.name.lowercase().replaceFirstChar { it.uppercase() }} $selectedYear",
            color = Color(0xFF00CFFD),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = eventText,
            onValueChange = { eventText = it },
            placeholder = { Text(text = "Add event details", color = Color.LightGray) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF1E2A3A),
                unfocusedContainerColor = Color(0xFF1E2A3A),
                focusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
                .height(120.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (eventText.isNotEmpty()) {
                    onEventAdded(eventText, selectedDate, selectedMonth, selectedYear)
                    eventText = ""
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00CFFD)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add event", color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun getDaysInMonth(month: Month, year: Int): Int {
    return LocalDate.of(year, month, 1).lengthOfMonth()
}

@RequiresApi(Build.VERSION_CODES.O)
fun getFirstDayOfMonth(month: Month, year: Int): Int {
    val dayOfWeek = LocalDate.of(year, month, 1).dayOfWeek.value
    return (dayOfWeek % 7)
}
