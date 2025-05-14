package com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.R

@Composable
//@Preview
fun ProjectDisplayingCard(
    projectDataClass: ProjectDataClass // Data class containing project details
) {
    val user = projectDataClass.user
    val coverImage = projectDataClass.coverImage
    val projectName = projectDataClass.projectName
    val githubUrl = projectDataClass.gitHubUrl
    val description = projectDataClass.description
    val context = LocalContext.current

    // Format GitHub repository name
    val repoName = "$user/${projectName.replace(" ", "_")}"

    // Outer card container
    Card(
        modifier = Modifier
            .fillMaxWidth()
//            .height(320.dp)
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF17191d)) // Dark background
    ) {
        // Placeholder for project cover image or illustration
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(Color(0xFFE4E4E4)), // Light gray placeholder background
            contentAlignment = Alignment.Center
        ) {
            // Placeholder icon inside the box
            Icon(
                painter = painterResource(coverImage),
                contentDescription = "Cover Image",
                tint = Color(0xFFBDBDBD),
                modifier = Modifier.size(48.dp)
            )
        }

        // Project information layout
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 16.dp, 20.dp, 16.dp)
        ) {
            // Project title
            Text(
                text = projectName,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Project description
            Text(
                text = description,
                color = Color.Gray,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Row for GitHub link and repo info
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // GitHub logo + repository name clickable to open GitHub URL
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl))
                        context.startActivity(intent) // Launch GitHub repo in browser
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.githublogo),
                        contentDescription = "GitHub Logo",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = repoName,
                        color = Color.Gray,
                        fontSize = 13.sp
                    )
                }

                Spacer(modifier = Modifier.weight(1f)) // Push content to the left
            }
        }
    }
}