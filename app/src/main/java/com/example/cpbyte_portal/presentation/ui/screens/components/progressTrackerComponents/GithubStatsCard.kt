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
    // Outer Card container with background color and padding
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF17191d)),
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .padding(16.dp,0.dp,16.dp,16.dp)
            .border(
                width = 1.2.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        // Column to layout content vertically with padding
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(20.dp)
        ) {
            // Row containing GitHub logo and heading
            Row {
                Image(
                    painter = painterResource(id = R.drawable.githublogo), // GitHub logo
                    contentDescription = "GitHub Logo",
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "GitHub Stats", // Title
                    color = Color.White,
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            // Reusable component showing total contributions
            Row {
                StatsItemCard(
                    totalContributions,
                    "Contribs",
                    Color(0xFF24d3ee) // Cyan color
                )

                Spacer(modifier = Modifier.width(15.dp))

                // Reusable component showing total PRs
                StatsItemCard(
                    totalPRs,
                    "Pull Reqs",
                    Color(0xFFfb923c) // Orange color
                )

                Spacer(modifier = Modifier.width(15.dp))

                // Reusable component showing total repositories
                StatsItemCard(
                    totalRepos,
                    "Repos",
                    Color(0xffef4444) // Red color
                )
            }
        }
    }
}