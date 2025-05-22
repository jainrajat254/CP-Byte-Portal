package com.example.cpbyte_portal.presentation.ui.screens.trackerScreens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.cpbyte_portal.domain.model.SkillRequest
import com.example.cpbyte_portal.presentation.ui.screens.components.CommonHeader
import com.example.cpbyte_portal.presentation.ui.screens.components.CustomLoader
import com.example.cpbyte_portal.presentation.viewmodel.TrackerViewModel
import com.example.cpbyte_portal.util.ResultState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun SkillsScreen(
    skill: List<String>,
    trackerViewModel: TrackerViewModel,
) {
    var newSkill by remember { mutableStateOf("") }
    val context = LocalContext.current
    var isDialog by rememberSaveable { mutableStateOf(false) }

    // Local copy of skills
    val localSkills = remember { mutableStateListOf(*skill.toTypedArray()) }

    val addSkillState by trackerViewModel.addSkillState.collectAsState()
    val removeSkillState by trackerViewModel.removeSkillState.collectAsState()

    // Handle Add Skill Result
    LaunchedEffect(addSkillState) {
        when (addSkillState) {
            is ResultState.Success -> {
                Log.d("SkillsScreen", "Skill added successfully: ${newSkill.trim()}")
                isDialog = false
                localSkills.add(newSkill.trim())
                newSkill = ""
            }

            is ResultState.Failure -> {
                val error = (addSkillState as ResultState.Failure).error.message
                Log.e("SkillsScreen", "Failed to add skill: $error")
                isDialog = false
                Toast.makeText(context, "Failed to add skill", Toast.LENGTH_SHORT).show()
            }

            is ResultState.Loading -> {
                Log.d("SkillsScreen", "Adding skill...")
                isDialog = true
            }

            else -> {
                Log.d("SkillsScreen", "Add skill state: Idle or unknown")
            }
        }
    }

// Handle Remove Skill Result
    LaunchedEffect(removeSkillState) {
        when (removeSkillState) {
            is ResultState.Success -> {
                isDialog = false
                Toast.makeText(context, "Skill removed", Toast.LENGTH_SHORT).show()
            }

            is ResultState.Failure -> {
                val error = (removeSkillState as ResultState.Failure).error.message
                Log.e("SkillsScreen", "Failed to remove skill: $error")
                isDialog = false
                Toast.makeText(context, "Failed to remove skill", Toast.LENGTH_SHORT).show()
            }

            is ResultState.Loading -> {
                Log.d("SkillsScreen", "Removing skill...")
                isDialog = true
            }

            else -> {
                Log.d("SkillsScreen", "Remove skill state: Idle or unknown")
            }
        }
    }

    if (isDialog) {
        CustomLoader(text = "Processing...")
    }

    Scaffold(
        containerColor = Color(0xFF0F172A),
        topBar = { CommonHeader(text = "Edit Skills") }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .padding(paddingValues)
        ) {
            // Input for new skill
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextField(
                    value = newSkill,
                    onValueChange = { newSkill = it },
                    placeholder = { Text("Enter a skill...", color = Color.Gray) },
                    modifier = Modifier
                        .weight(1f)
                        .height(53.dp),
                    textStyle = TextStyle(color = Color.White),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFF334155),
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        val skillToAdd = newSkill.trim()
                        if (skillToAdd.isNotEmpty() && !localSkills.contains(skillToAdd)) {
                            trackerViewModel.addSkill(SkillRequest(skill = skillToAdd))
                        } else {
                            Toast.makeText(
                                context,
                                "Skill is empty or already added",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B82F6)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.height(50.dp)
                ) {
                    Text("Add Skill", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Skill chips
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                localSkills.forEach { skillName ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF334155)),
                        shape = RoundedCornerShape(50),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            IconButton(
                                onClick = {
                                    trackerViewModel.removeSkill(SkillRequest(skill = skillName))
                                    localSkills.remove(skillName)
                                },
                                modifier = Modifier.size(20.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.RemoveCircle,
                                    contentDescription = "Remove",
                                    tint = Color.Red,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(skillName, color = Color.White)
                        }
                    }
                }
            }
        }
    }
}

