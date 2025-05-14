package com.example.cpbyte_portal.presentation.ui.screens

// Importing necessary Compose and Android components
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.cpbyte_portal.presentation.ui.screens.components.progressTrackerComponents.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = true)
fun ProgressTrackerScreen() {
    // ---------- Sample static data ---------- //
    val numberOfEasyQuestions = 169
    val numberOfMediumQuestions = 250
    val numberOfHardQuestions = 300
    val totalQuestions = 134
    val ranking = 1
    val totalContributions = 14
    val totalRepos = 14
    val totalPrs = 10

    // ---------- User & time details ---------- //
    val user = "Roushan Srivastav"
    val current = LocalDateTime.now()
    val currDate = current.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"))
    val currTime = current.format(DateTimeFormatter.ofPattern("hh:mm a"))
    val currDay = current.format(DateTimeFormatter.ofPattern("EEEE"))

    // ---------- Social links (currently blank) ---------- //
    val instagramLink = ""
    val facebookLink = ""
    val linkedinLink = ""
    val twitterLink = ""
    val context = LocalContext.current

    // ---------- UI section selector state ---------- //
    var selector by remember { mutableStateOf("Programming Stats") }

    // ---------- Data objects ---------- //
    val dsaStats = DsaStatsDataClass(numberOfEasyQuestions, numberOfMediumQuestions, numberOfHardQuestions, totalQuestions)
    val githubStats = GithubStatsDataClass(totalContributions, totalPrs, totalRepos)

    // ---------- Sample technical skill set ---------- //
    val skills = listOf("Reactjs", "Nodejs", "Postman", "Git/Github", "Postman", "Docker", "MongoDB", "Firebase", "GraphQL", "Prisma", "socket")
    val languages = listOf("Java")

    // ---------- Platform icons (dummy resources) ---------- //
    val platforms = listOf(
        PlatformDataClass("Leetcode", R.drawable.batman, ""),
        PlatformDataClass("Github", R.drawable.batman, "")
    )

    // ---------- Heat map data (dummy values) ---------- //
    val heatMapArray = intArrayOf(0, 1, 1, 1, 1)

    // ---------- Sample project data ---------- //
    val projects = listOf(
        ProjectDataClass("Roshan_Sri", R.drawable.batman, "Progress Tracker", "", "A tool to track coding progress and daily streaks"),
        ProjectDataClass("Roshan_Sri", R.drawable.batman, "Uber Clone", "", "A ride-sharing application clone with real-time tracking"),
        ProjectDataClass("Roshan_Sri", R.drawable.batman, "Ochi Clone", "", "A presentation website with eye-catching animations")
    )

    // ---------- Main layout starts ---------- //
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .verticalScroll(rememberScrollState())
    ) {
        // ---------- Main heading ---------- //
        Text(
            text = "Progress Tracker",
            fontSize = 32.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp, 10.dp, 0.dp, 16.dp)
        )

        // ---------- User welcome card ---------- //
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFF17191d)),
            modifier = Modifier
                .padding(16.dp, 0.dp, 16.dp, 20.dp)
                .border(width = 1.2.dp, color = Color.Gray, shape = RoundedCornerShape(16.dp))
        ) {
            Column(modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 0.dp)) {
                Text(
                    text = "Hello, $user",
                    fontSize = 29.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp)
                )
                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Today is $currDay, $currDate $currTime",
                    fontSize = 14.sp,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                // ---------- Social media icon row ---------- //
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
                            val intent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse(twitterLink))
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

        // ---------- Summary card row ---------- //
        Row {
            Spacer(modifier = Modifier.width(2.dp))
            ProgressTrackerViewCard(
                title = "Solved",
                totalQuestions = totalQuestions.toString(),
                color = Color(0xFF17191d),
                arr = heatMapArray,
            )
            ProgressTrackerViewCard(
                title = "Ranking",
                totalQuestions = ranking.toString(),
                color = Color(0xFF17191d),
                arr = heatMapArray
            )
            ProgressTrackerViewCard(
                title = "Past days",
                totalQuestions = "483",
                color = Color(0xFF17191d),
                arr = heatMapArray
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        // ---------- Section switch toggle ---------- //
        SelectorButtonForSkillsProjectAndStats {
            selector = it
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ---------- Display selected section ---------- //
        when (selector) {
            "Programming Stats" -> ProgrammingStatsComposableCaller(dsaStats, githubStats)
            "Projects" -> ProjectsComposableCaller(projects)
            else -> TechnicalSkillsCaller(platforms, skills, languages)
        }
    }
}

////////////////////////////////////////////
// Composable wrappers to organize calls //
////////////////////////////////////////////

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