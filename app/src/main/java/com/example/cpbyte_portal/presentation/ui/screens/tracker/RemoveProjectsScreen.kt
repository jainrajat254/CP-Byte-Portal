package com.example.cpbyte_portal.presentation.ui.screens.tracker

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cpbyte_portal.domain.model.ProjectResponse
import com.example.cpbyte_portal.presentation.ui.screens.components.CPByteButton
import com.example.cpbyte_portal.presentation.ui.screens.components.CommonHeader
import com.example.cpbyte_portal.presentation.ui.screens.components.CustomLoader
import com.example.cpbyte_portal.presentation.ui.screens.tracker.components.RemoveProjectCard
import com.example.cpbyte_portal.presentation.viewmodel.TrackerViewModel
import com.example.cpbyte_portal.presentation.viewmodel.UserViewModel
import com.example.cpbyte_portal.util.ResultState

@Composable
fun RemoveProjectScreen(
    userViewModel: UserViewModel,
    trackerViewModel: TrackerViewModel,
    navController: NavHostController,
) {
    val context = LocalContext.current

    val projectsState by userViewModel.projectState.collectAsState()
    val removeState by trackerViewModel.removeProjectState.collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    var projectToDelete by remember { mutableStateOf<ProjectResponse?>(null) }

    // Initial load
    LaunchedEffect(Unit) {
        userViewModel.getProjects()
    }

    // Handle deletion result
    LaunchedEffect(removeState) {
        when (removeState) {
            is ResultState.Success -> {
                Toast.makeText(context, "Project removed", Toast.LENGTH_SHORT).show()
                userViewModel.getProjects()
            }

            is ResultState.Failure -> {
                val error = (removeState as ResultState.Failure).error.message ?: "Deletion failed"
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }

    Scaffold(
        contentWindowInsets = WindowInsets.statusBars,
        topBar = {
            CommonHeader(
                text = "Remove Project",
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
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(innerPadding)
                .consumeWindowInsets(WindowInsets.navigationBars)
        ) {
            when (val state = projectsState) {
                is ResultState.Loading -> CustomLoader()

                is ResultState.Failure -> {
                    val errorMessage = state.error.message ?: "Unknown error"
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                }

                is ResultState.Success -> {
                    val projectList = state.data.data.projects // Update this if needed
                    projectList.forEach { project ->
                        RemoveProjectCard(
                            projectName = project.projectName,
                            projectDescription = project.description,
                            onDelete = {
                                projectToDelete = project
                                showDialog = true
                            }
                        )
                    }
                }

                ResultState.Idle -> {}
            }

            if (showDialog && projectToDelete != null) {
                AlertDialog(
                    onDismissRequest = {
                        showDialog = false
                        projectToDelete = null
                    },
                    confirmButton = {
                        CPByteButton(
                            value = "Yes",
                            onClick = {
                                trackerViewModel.removeProject(projectToDelete!!.id)
                                showDialog = false
                                projectToDelete = null
                            },
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    },
                    dismissButton = {
                        OutlinedButton(
                            onClick = {
                                showDialog = false
                                projectToDelete = null
                            },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = MaterialTheme.colorScheme.surface,
                                contentColor = MaterialTheme.colorScheme.onSurface
                            ),
                            border = ButtonDefaults.outlinedButtonBorder
                        ) {
                            Text("No", style = MaterialTheme.typography.labelLarge)
                        }
                    },
                    title = {
                        Text(
                            text = "Confirm Deletion",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    },
                    text = {
                        Text(
                            text = "Do you really want to delete\n \"${projectToDelete!!.projectName}\"?",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    containerColor = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(12.dp)
                )
            }
        }
    }
}