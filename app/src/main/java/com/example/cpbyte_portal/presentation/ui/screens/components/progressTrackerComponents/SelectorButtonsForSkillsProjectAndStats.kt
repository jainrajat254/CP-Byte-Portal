package com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

// Composable that displays a horizontal row of selector buttons:
// "Programming Stats", "Projects", and "Skills".
// Clicking a button triggers the `onclick` lambda with the corresponding label.
@Composable
fun SelectorButtonForSkillsProjectAndStats(onclick: (String) -> Unit) {
    // Wrapper card with padding
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 0.dp)
            .border(
                width = 1.2.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        // Row layout to horizontally align the buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    color = Color(0xFF17191d) // Dark background color
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // "Programming Stats" Button
            Button(
                onClick = {
                    onclick("Programming Stats")
                }, // wider button
                modifier = Modifier.padding(16.dp,0.dp,0.dp,0.dp),
                shape = RectangleShape, // flat edges
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF17191d) // same dark color to blend with background
                )
            ) {
                Text("Prog Stats")
            }

            // "Projects" Button
            Button(
                onClick = {
                    onclick("Projects")
                },
                shape = RectangleShape,
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF17191d)
                )
            ) {
                Text("Projects")
            }

            // "Skills" Button
            Button(
                onClick = {
                    onclick("Skills")
                },
                modifier = Modifier.padding(0.dp,0.dp,20.dp,0.dp),
                shape = RectangleShape,
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF17191d)
                )
            ) {
                Text("Skills")
            }
        }
    }
}