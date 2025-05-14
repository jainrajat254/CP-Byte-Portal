package com.example.cpbyte_portal.presentation.ui.screens

import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType.Companion.Uri
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents.DsaStatsCard
import com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents.DsaStatsDataClass
import com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents.GithubStatsCard
import com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents.GithubStatsDataClass
import com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents.PlatformDataClass
import com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents.ProgrammingStatsComposable
import com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents.ProgressTrackerViewCard
import com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents.ProjectDataClass
import com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents.ProjectsComposable
import com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents.SelectorButtonForSkillsProjectAndStats
import com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents.TechnicalSkills
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = true)
fun ProgressTrackerScreen() {
    // ---------- Sample data (can be replaced with actual data source later) ---------- //
    val numberOfEasyQuestions = 169
    val numberOfMediumQuestions = 250
    val numberOfHardQuestions = 300
    val totalQuestions = 134
    val ranking = 1
    val totalContributions = 14
    val totalRepos = 14
    val totalPrs = 10

    // User and time information
    val user = "Roushan Srivastav"
    val current = LocalDateTime.now()
    val currDate = current.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"))
    val currTime = current.format(DateTimeFormatter.ofPattern("hh:mm a"))
    val currDay = current.format(DateTimeFormatter.ofPattern("EEEE"))

    // Social media links (placeholders for now)
    val instagramLink = ""
    val facebookLink = ""
    val linkedinLink = ""
    val twitterLink = ""
    val context = LocalContext.current

    // ---------- Composable UI State ---------- //
    var selector by remember { mutableStateOf("Programming Stats") }

    // ---------- Construct data classes for cards ---------- //
    val dsaStats = DsaStatsDataClass(
        numberOfEasyQuestions, numberOfMediumQuestions, numberOfHardQuestions, totalQuestions
    )

    val githubStats = GithubStatsDataClass(
        totalContributions, totalPrs, totalRepos
    )

    // ---------- Skills, languages & platforms ---------- //
    val skills = listOf("Reactjs", "Nodejs", "Postman", "Git/Github", "Postman", "Docker", "MongoDB", "Firebase", "GraphQL", "Prisma", "socket")

    val languages = listOf("Java")

    val platforms = listOf(
        PlatformDataClass("Leetcode", R.drawable.batman, ""),
        PlatformDataClass("Github", R.drawable.batman, ""),


    )

    // ---------- Sample heat map dummy data ---------- //
    val heatMapArray = intArrayOf(0, 1, 1, 1, 1)

    // ---------- Sample Projects ---------- //
    val projects = listOf(
        ProjectDataClass("Roshan_Sri", R.drawable.batman, "Progress Tracker", "", "A tool to track coding progress and daily streaks"),
        ProjectDataClass("Roshan_Sri", R.drawable.batman, "Uber Clone", "", "A ride-sharing application clone with real-time tracking"),
        ProjectDataClass("Roshan_Sri", R.drawable.batman, "Ochi Clone", "", "A presentation website with eye-catching animations")
    )

    // ---------- Main Scrollable Column ---------- //
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .verticalScroll(rememberScrollState())
    ) {
        // Heading
        Text(
            text = "Progress Tracker",
            fontSize = 32.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp, 10.dp, 0.dp, 16.dp)
        )

        // User greeting
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFF17191d))
            , modifier = Modifier.padding(16.dp,0.dp,16.dp,20.dp)
                .border(
                    width = 1.2.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(16.dp)
                )
        ){
            Column(
                modifier = Modifier.padding(0.dp,16.dp,0.dp,0.dp)
            ) {

                Text(
                    text = "Hello, $user",
                    fontSize = 29.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp)
                )
                Spacer(modifier = Modifier.height(6.dp))

                // Date & time
                Text(
                    text = "Today is $currDay, $currDate $currTime",
                    fontSize = 14.sp,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                // ---------- Social Icons Row ---------- //
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp, 0.dp, 20.dp, 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse(instagramLink))
                        context.startActivity(intent)
                    }) {
                        Icon(painter = painterResource(R.drawable.instagram), contentDescription = "Instagram", tint = Color.White, modifier = Modifier.size(30.dp))
                    }
                    IconButton(onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse(facebookLink))
                        context.startActivity(intent)
                    }) {
                        Icon(painter = painterResource(R.drawable.facebook), contentDescription = "Facebook", tint = Color.White, modifier = Modifier.size(30.dp))
                    }
                    IconButton(onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse(linkedinLink))
                        context.startActivity(intent)
                    }) {
                        Icon(painter = painterResource(R.drawable.linkedin), contentDescription = "LinkedIn", tint = Color.White, modifier = Modifier.size(30.dp))
                    }
                    IconButton(onClick = {
                        try {
                            val intent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse(instagramLink))
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            e.printStackTrace()
                            Toast.makeText(context, "Unable to open link", Toast.LENGTH_SHORT).show()
                        }
                    }) {
                        Icon(painter = painterResource(R.drawable.twitter), contentDescription = "Twitter", tint = Color.White, modifier = Modifier.size(30.dp))
                    }
                }
            }
        }

        // ---------- Summary Cards Section ---------- //
        Row {
            Spacer(modifier = Modifier.width(2.dp))
            ProgressTrackerViewCard(
                title = "Solved",
                content = "Number of questions solved",
                totalQuestions = totalQuestions.toString(),
                color = Color(0xFF17191d),
                arr = heatMapArray,
            )

            ProgressTrackerViewCard(
                title = "Ranking",
                content = "Overall Leaderboard Ranking 👑",
                totalQuestions = ranking.toString(),
                color = Color(0xFF17191d),
                arr = heatMapArray
            )

            ProgressTrackerViewCard(
                title = "Past days",
                content = "Consistency heat map 🔥",
                totalQuestions = "483",
                color = Color(0xFF17191d),
                arr = heatMapArray
            )
        }
        Spacer(modifier = Modifier.height(4.dp))

        // ---------- Section Toggle Buttons ---------- //
        SelectorButtonForSkillsProjectAndStats {
            selector = it
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ---------- Section Content Switch ---------- //
        when (selector) {
            "Programming Stats" -> ProgrammingStatsComposableCaller(dsaStats, githubStats)
            "Projects" -> ProjectsComposableCaller(projects)
            else -> TechnicalSkillsCaller(platforms, skills, languages)
        }
    }
}

/////////////////////////////////////////
// Sub-composables to keep code clean //
/////////////////////////////////////////

@Composable
fun ProgrammingStatsComposableCaller(
    dsaStats: DsaStatsDataClass,
    githubStats: GithubStatsDataClass
) {
    ProgrammingStatsComposable(dsaStats, githubStats)

}

@Composable
fun ProjectsComposableCaller(projects: List<ProjectDataClass>) {
    ProjectsComposable(projects = projects)
}

@Composable
fun TechnicalSkillsCaller(
    platforms: List<PlatformDataClass>,
    skills: List<String>,
    languages: List<String>
) {
    TechnicalSkills(platforms, skills, languages)
}