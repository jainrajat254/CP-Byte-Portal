package com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowRowScopeInstance.weight
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.QueryStats
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
//@Preview
fun DsaStatsCard(
    dsaStats: DsaStatsDataClass // Data class containing the DSA stats like number of questions in different levels
) {
    // Extracting values from the dsaStats object
    val numberOfMediumQuestions = dsaStats.numberOfMediumQuestions
    val numberOfEasyQuestions = dsaStats.numberOfEasyQuestions
    val numberOfHardQuestions = dsaStats.numberOfHardQuestions
    val totalQuestions = dsaStats.totalQuestions

    // Card that holds the entire content of the DSA Stats
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF17191d)), // Dark background color
        modifier = Modifier.fillMaxWidth() // Make card take up full width
            .height(290.dp) // Fixed height of 560dp for the card
            .padding(16.dp,0.dp,16.dp,20.dp)
            .border(
                width = 1.2.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(16.dp)
            )// Padding around the card
    ) {
        // Column layout to stack items vertically
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight() // Make the column take full width and height
                .padding(20.dp) // Padding inside the card
        ) {
            // Row to display the icon and title
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                // Icon representing stats
                Icon(imageVector = Icons.Outlined.QueryStats,
                    contentDescription = "DSA Stats Icon", // Description for accessibility
                    tint = Color.White, modifier = Modifier.size(27.dp)) // Icon size and color
                Spacer(modifier = Modifier.width(10.dp)) // Space between icon and text
                // Text for the title of the card
                Text(
                    text = "DSA Stats",
                    color = Color.White, // White text color
                    fontSize = 27.sp, // Font size of 27sp
                    fontWeight = FontWeight.Bold, // Bold font weight
                )
            }
            Spacer(modifier = Modifier.height(15.dp)) // Space between title and stats
            // Displaying the stats for easy questions
            Row {
                StatsItemCard(numberOfEasyQuestions,
                    "Easy", // Label for Easy questions
                    Color(0xFF24d3ee) // Color for easy questions (light blue)
                )
                Spacer(modifier = Modifier.width(18.dp)) // Space between different stats
                // Displaying the stats for medium questions
                StatsItemCard(numberOfMediumQuestions,
                    "Medium", // Label for Medium questions
                    Color(0xFFfb923c) // Color for medium questions (orange)
                )
                Spacer(modifier = Modifier.width(18.dp)) // Space between different stats
                // Displaying the stats for hard questions
                StatsItemCard(numberOfHardQuestions,
                    "Hard", // Label for Hard questions
                    Color(0xffef4444) // Color for hard questions (red)
                )
            }
            Spacer(modifier = Modifier.height(18.dp)) // Space between the stats and language section
            // Row to display language info with an icon
            Row(
                modifier = Modifier.fillMaxWidth() // Make the row take full width
            ) {
                // Text showing the language for DSA
                Text(
                    text = "Language for DSA",
                    color = Color.White, // White text color
                    fontSize = 18.sp, // Font size of 18sp
                )
                Spacer(modifier = Modifier.width(5.dp)) // Space between text and icon
                // Edit icon for editing language settings
                Icon(imageVector = Icons.Outlined.Edit,
                    tint = Color.White, contentDescription = "Edit Language", // Description for accessibility
                    modifier = Modifier.size(20.dp) // Icon size
                )
            }
            Spacer(modifier = Modifier.height(12.dp)) // Space between language section and skills card
            // Displaying the skills card with the language used (Java in this case)
            SkillsCardForStats("JAVA")

        }
    }
}