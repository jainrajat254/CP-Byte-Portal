package com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
//@Preview // You can uncomment this for previewing the UI in Android Studio
fun SkillsCardForStats(
    title: String // The skill name or category to be displayed
) {
    // Card is used to create an elevated container with background color
    Card(
        modifier = Modifier, // No additional layout modifiers for now
        colors = CardDefaults.cardColors(
            containerColor = Color(0xff2b303b) // Dark background for the card
        )
    ) {
        // Text inside the card showing the skill title
        Text(
            text = title, // Text to display, passed as a parameter
            modifier = Modifier.padding(8.dp), // Padding around the text
            color = Color.White, // White text for contrast against dark card
            fontWeight = FontWeight.Bold // Bold font style for emphasis
        )
    }
}