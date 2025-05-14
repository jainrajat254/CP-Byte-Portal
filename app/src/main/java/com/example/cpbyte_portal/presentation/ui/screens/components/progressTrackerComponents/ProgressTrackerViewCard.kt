package com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.FlowRowScopeInstance.weight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Composable function to display a stylized card for progress metrics.
// Displays either a total value or a heatmap based on the `title` parameter.
@Composable
fun ProgressTrackerViewCard(
    title: String,             // Title of the card (e.g., "Total Questions")
    totalQuestions: String,    // Numerical value to display (or "days" if it's a heatmap)
    color: Color,              // Background color of the card
    arr: IntArray              // Array for rendering a heatmap, if applicable
) {
    // Main card container with specified size, padding, border, background, shape, and elevation
    Card(
        modifier = Modifier
            .height(125.dp) // Fixed card height
            .width(125.dp)  // Fixed card width
            .padding(16.dp, 0.dp, 0.dp, 16.dp) // Padding on left and bottom
            .border(
                width = 1.2.dp, // Border width
                color = Color.Gray, // Border color
                shape = RoundedCornerShape(16.dp) // Rounded corners for the border
            )
            .background(
                color = color, // Card background color
                shape = RoundedCornerShape(16.dp) // Shape matching border
            ),
        shape = RoundedCornerShape(16.dp), // Overall card shape
        colors = CardDefaults.cardColors(containerColor = Color.Transparent), // Transparent container to apply background separately
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Card shadow
    ) {
        // Column layout to stack the title and content vertically
        Column(
            modifier = Modifier
                .fillMaxWidth() // Fill available width
                .fillMaxHeight() // Fill available height
                .padding(9.dp), // Inner padding for spacing
            horizontalAlignment = Alignment.CenterHorizontally, // Center elements horizontally
            verticalArrangement = Arrangement.Center // Center elements vertically
        ) {
            // Title text (e.g., "Total Questions" or "Past days")
            Text(
                text = title,
                color = Color.White, // White colored text
                fontSize = 19.sp, // Font size for title
//                fontWeight = FontWeight.Bold // Optionally bold
            )

            Spacer(modifier = Modifier.height(10.dp)) // Space between title and content

//            Spacer(modifier = Modifier.height(10.dp)) // Optional extra spacing

            // Conditional logic: show total stat or a mini heatmap
            if (title != "Past days") {
                // If not "Past days", display the numeric stat
                Text(
                    text = totalQuestions, // Numeric value passed
                    color = Color.White, // Text color
                    fontSize = 26.sp, // Font size for number
                    fontWeight = FontWeight.Bold // Bold to emphasize
                )
            } else {
                // If title is "Past days", show a row of colored boxes (heatmap)
                Row(
                    modifier = Modifier.padding(4.dp, 8.dp, 0.dp, 10.dp) // Vertical padding around heatmap
                ) {
                    for (num in arr) {
                        if (num == 1) {
                            BoxForHeatmap(Color.Green) // Active day - green
                        } else {
                            BoxForHeatmap(Color.Red) // Inactive day - red
                        }
                        Spacer(modifier = Modifier.width(5.dp)) // Space between boxes
                    }
                }
            }
        }
    }
}

// Composable function to render a small colored square used in the heatmap
@Composable
fun BoxForHeatmap(color: Color) {
    // Simple colored card representing a day in the heatmap
    Card(
        modifier = Modifier
            .width(13.dp) // Box width
            .height(13.dp), // Box height
        colors = CardDefaults.cardColors(containerColor = color), // Dynamic color passed in
        shape = RoundedCornerShape(4.dp) // Slight rounding for heatmap boxes
    ) { /* Empty content, purely for visual appearance */ }
}