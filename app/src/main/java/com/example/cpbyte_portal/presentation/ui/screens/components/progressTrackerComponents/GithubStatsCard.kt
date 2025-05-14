package com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.R

@Composable
//@Preview
fun GithubStatsCard(
    totalContributions: Int, // Number of GitHub contributions
    totalPRs: Int,           // Total pull requests
    totalRepos: Int          // Total public/private repositories
) {
    // Card container to encapsulate the entire GitHub stats section
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF17191d)), // Background color for dark theme
        modifier = Modifier
            .fillMaxWidth() // Occupy full horizontal space
            .height(210.dp) // Fixed height of 210dp
            .padding(16.dp,0.dp,16.dp,16.dp) // Padding around the card
            .border(
                width = 1.2.dp, // Border thickness
                color = Color.Gray, // Border color
                shape = RoundedCornerShape(16.dp) // Rounded corners for the card
            )
    ) {
        // Vertical column layout to stack elements inside the card
        Column(
            modifier = Modifier
                .fillMaxWidth() // Full width of parent
                .fillMaxHeight() // Full height of card
                .padding(20.dp) // Inner padding for content spacing
        ) {
            // Top row containing GitHub logo and the heading "GitHub Stats"
            Row {
                Image(
                    painter = painterResource(id = R.drawable.githublogo), // GitHub logo image from drawable
                    contentDescription = "GitHub Logo", // Accessibility description
                    modifier = Modifier.size(30.dp) // Image size
                )
                Spacer(modifier = Modifier.width(10.dp)) // Space between image and text
                Text(
                    text = "GitHub Stats", // Heading text
                    color = Color.White, // Text color
                    fontSize = 27.sp, // Font size
                    fontWeight = FontWeight.Bold // Bold style for emphasis
                )
            }

            Spacer(modifier = Modifier.height(15.dp)) // Space between title and stats

            // Row showing contribution, pull request, and repository stats
            Row {
                StatsItemCard(
                    totalContributions, // Value to display
                    "Contribs", // Label for contributions
                    Color(0xFF24d3ee) // Cyan color for visual distinction
                )

                Spacer(modifier = Modifier.width(15.dp)) // Spacing between stat cards

                StatsItemCard(
                    totalPRs, // Value to display
                    "Pull Reqs", // Label for pull requests
                    Color(0xFFfb923c) // Orange color for visual distinction
                )

                Spacer(modifier = Modifier.width(15.dp)) // Spacing between stat cards

                StatsItemCard(
                    totalRepos, // Value to display
                    "Repos", // Label for repositories
                    Color(0xffef4444) // Red color for visual distinction
                )
            }
        }
    }
}