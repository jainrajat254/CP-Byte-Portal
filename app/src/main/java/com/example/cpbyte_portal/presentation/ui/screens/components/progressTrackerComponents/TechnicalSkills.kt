package com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Style
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.cpbyte_portal.R

// Main composable that displays categorized technical skills: Skills, Languages, and Platforms
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TechnicalSkills(
    platforms: List<PlatformDataClass>, // List of platform data (name, logo, URL)
    skills: List<String>,              // List of technical skills
    languages: List<String>           // List of programming languages
) {
    // Outer card container with padding and background
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF17191d)),
        modifier = Modifier
            .fillMaxWidth()
            .height(530.dp)
            .padding(20.dp)
            .border(
                width = 1.2.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(20.dp)
        ) {
            // Header: Technical Skills title
            Row(modifier = Modifier.fillMaxWidth()) {
                Icon(
                    imageVector = Icons.Outlined.Style,
                    tint = Color.White,
                    contentDescription = "Skills Icon",
                    modifier = Modifier.size(26.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Technical Skills",
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            // Section: Skills
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Skills",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    tint = Color.White,
                    contentDescription = "Edit Icon",
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Flow layout for displaying skill tags
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                skills.forEach {
                    SkillsCardForStats(it) // Custom composable for displaying individual skill
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            // Section: Languages
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Languages",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    tint = Color.White,
                    contentDescription = "Edit Icon",
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Flow layout for programming languages
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                languages.forEach {
                    SkillsCardForStats(it) // Reuse same skill card for languages
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            // Section: Platforms
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Platforms",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    tint = Color.White,
                    contentDescription = "Edit Icon",
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Flow layout for platform cards
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                platforms.forEach {
                    PlatformCard(
                        name = it.name,
                        logoResId = it.logoResId,
                        url = it.url
                    )
                }
            }
        }
    }
}

// Composable to display a clickable card with platform logo and name
// Opens platform URL on click
@Composable
fun PlatformCard(
    name: String,       // Platform name (e.g., GitHub)
    logoResId: Int,     // Resource ID for platform logo
    url: String         // URL to open on click
) {
    val context = LocalContext.current

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xff2b303b) // Dark card color
        )
    ) {
        // Row layout inside the card
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp, 0.dp, 0.dp, 0.dp)
                .clickable {
                    // Open platform URL using intent
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                }
        ) {
            // Logo
            Image(
                painter = painterResource(id = logoResId),
                contentDescription = "Platform Logo",
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(1.dp))
            // Platform Name
            Text(
                text = name,
                modifier = Modifier.padding(8.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}