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
    content: String,           // Subtext content (e.g., "Number of questions solved")
    totalQuestions: String,    // Numerical value to display (or "days" if it's a heatmap)
    color: Color,              // Background color of the card
    arr: IntArray    , // Array for rendering a heatmap, if applicable
) {
    Card(
        modifier = Modifier
            .height(125.dp)
            .width(125.dp)
            .padding(16.dp, 0.dp, 0.dp, 16.dp)
            .border(
                width = 1.2.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                color= color
                ,
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(9.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Title text
            Text(
                text = title,
                color = Color.White,
                fontSize = 19.sp,
//                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

//            Spacer(modifier = Modifier.height(10.dp))

            // Conditional rendering: Show total or heatmap
            if (title != "Past days") {
                // Numeric stat (e.g., total questions, rank)
                Text(
                    text = totalQuestions,
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
            } else {
                // Render heatmap using values in arr
                Row(
                    modifier = Modifier.padding(0.dp,8.dp,0.dp,10.dp)
                ) {
                    for (num in arr) {
                        if (num == 1) {
                            BoxForHeatmap(Color.Green) // Active day
                        } else {
                            BoxForHeatmap(Color.Red) // Inactive day
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                    }
                }
            }
        }
    }
}

// Composable function to render a colored box for heatmap representation.
@Composable
fun BoxForHeatmap(color: Color) {
    Card(
        modifier = Modifier
            .width(13.dp)
            .height(13.dp),
        colors = CardDefaults.cardColors(containerColor = color),
        shape = RoundedCornerShape(4.dp)
    ) { /* Empty content, visual only */ }
}