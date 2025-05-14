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
    dsaStats: DsaStatsDataClass // Contains the DSA statistics (easy, medium, hard, total)
) {
    // Extract values from the DSA stats data class
    val numberOfMediumQuestions = dsaStats.numberOfMediumQuestions
    val numberOfEasyQuestions = dsaStats.numberOfEasyQuestions
    val numberOfHardQuestions = dsaStats.numberOfHardQuestions
    val totalQuestions = dsaStats.totalQuestions

    // Card displaying the entire DSA stats section
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF17191d)), // Dark background
        modifier = Modifier.fillMaxWidth() // Full width
            .height(290.dp) // Fixed height
            .padding(16.dp,0.dp,16.dp,20.dp) // Outer padding
            .border(
                width = 1.2.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(16.dp)
            ) // Rounded border
    ) {
        // Column to stack all UI elements vertically
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight() // Full size column
                .padding(20.dp) // Inner padding
        ) {
            // Row for icon and title
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                // Stats icon
                Icon(imageVector = Icons.Outlined.QueryStats,
                    contentDescription = "DSA Stats Icon", // Accessibility
                    tint = Color.White, modifier = Modifier.size(27.dp)) // Icon styling
                Spacer(modifier = Modifier.width(10.dp)) // Spacing
                // Card title
                Text(
                    text = "DSA Stats",
                    color = Color.White,
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Spacer(modifier = Modifier.height(15.dp)) // Space after title

            // Row for easy, medium, hard question stats
            Row {
                StatsItemCard(numberOfEasyQuestions,
                    "Easy", // Easy label
                    Color(0xFF24d3ee) // Easy color (light blue)
                )
                Spacer(modifier = Modifier.width(18.dp)) // Spacing
                StatsItemCard(numberOfMediumQuestions,
                    "Medium", // Medium label
                    Color(0xFFfb923c) // Medium color (orange)
                )
                Spacer(modifier = Modifier.width(18.dp)) // Spacing
                StatsItemCard(numberOfHardQuestions,
                    "Hard", // Hard label
                    Color(0xffef4444) // Hard color (red)
                )
            }
            Spacer(modifier = Modifier.height(18.dp)) // Space after stats row

            // Row showing DSA language info
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Language for DSA",
                    color = Color.White,
                    fontSize = 18.sp,
                )
                Spacer(modifier = Modifier.width(5.dp)) // Space between text and icon
                Icon(imageVector = Icons.Outlined.Edit,
                    tint = Color.White, contentDescription = "Edit Language",
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.height(12.dp)) // Space before skills section

            // Displays the programming language used for DSA
            SkillsCardForStats("JAVA")

        }
    }
}