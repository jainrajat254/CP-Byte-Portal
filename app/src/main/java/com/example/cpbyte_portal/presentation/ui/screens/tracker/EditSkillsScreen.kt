package com.example.cpbyte_portal.presentation.ui.screens.tracker

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cpbyte_portal.domain.model.SkillRequest
import com.example.cpbyte_portal.presentation.ui.screens.components.CommonHeader
import com.example.cpbyte_portal.presentation.ui.screens.components.CustomLoader
import com.example.cpbyte_portal.presentation.ui.screens.tracker.components.SkillChip
import com.example.cpbyte_portal.presentation.ui.screens.tracker.components.SkillInputRow
import com.example.cpbyte_portal.presentation.viewmodel.TrackerViewModel
import com.example.cpbyte_portal.util.ResultState

@Composable
fun SkillsScreen(
    skill: List<String>,
    trackerViewModel: TrackerViewModel,
    navController: NavController,
) {
    var newSkill by remember { mutableStateOf("") }
    val context = LocalContext.current
    var isDialog by rememberSaveable { mutableStateOf(false) }
    val localSkills = remember { mutableStateListOf(*skill.toTypedArray()) }

    val trimmedSkill = newSkill.trim()
    val isSkillValid = trimmedSkill.isNotEmpty() && !localSkills.contains(trimmedSkill)

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
        contentWindowInsets = WindowInsets.statusBars,
        topBar = {
            CommonHeader(
                text = "Edit Skills",
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .padding(paddingValues)
                .consumeWindowInsets(WindowInsets.navigationBars)
        ) {
            // Input for new skill
            SkillInputRow(
                skillText = newSkill,
                onSkillChange = { newSkill = it },
                onAddClick = {
                    trackerViewModel.addSkill(SkillRequest(skill = trimmedSkill))
                },
                isEnabled = isSkillValid && !isDialog
            )

            Spacer(modifier = Modifier.height(30.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                localSkills
                    .filter { it.isNotBlank() }
                    .forEach { skillName ->
                        SkillChip(
                            skillName = skillName,
                            onRemove = {
                                trackerViewModel.removeSkill(SkillRequest(skill = skillName))
                                localSkills.remove(skillName)
                            }
                        )
                    }

            }
        }
    }
}

