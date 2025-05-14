package com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
//@Preview // Uncomment for previewing the component in Android Studio
fun StatsItemCard(
    numberOfQuestions : Int,   // Number or statistic to display
    levelOfQuestions : String, // Label or category associated with the number
    color: Color               // Color for the number text to differentiate stats
) {
    // Card serves as the container with dark background styling
    Card(
        modifier = Modifier
            .width(95.dp)    // Card stretches full width of parent
            .height(95.dp),     // Fixed height
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF202633) // Dark gray background
        )
    ) {
        // Column arranges number and label vertically, centered
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(10.dp), // Padding inside the card
            verticalArrangement = Arrangement.Center, // Center content vertically
            horizontalAlignment = Alignment.CenterHorizontally // Center content horizontally
        ) {
            // Main statistic number, styled with input color
            Text(
                text = numberOfQuestions.toString(),
                color = color,          // Dynamic color for visual emphasis
                fontSize = 28.sp,       // Large font size
                fontWeight = FontWeight.Bold
            )
            // Description or category label under the number
            Text(
                text = levelOfQuestions,
                color = Color.White,    // White color for readability
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}