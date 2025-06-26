package com.example.cpbyte_portal.presentation.ui.screens.trackerScreens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.DriveFileRenameOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cpbyte_portal.domain.model.ProjectResponse
import com.example.cpbyte_portal.presentation.ui.screens.components.CommonHeader
import com.example.cpbyte_portal.presentation.ui.screens.components.CustomLoader
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
        topBar = {
            CommonHeader(text = "Remove Project",
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
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
                .padding(innerPadding)
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
                        ProjectItem(
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
                        Button(
                            onClick = {
                                trackerViewModel.removeProject(projectToDelete!!.id)
                                showDialog = false
                                projectToDelete = null
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                        ) {
                            Text("Yes", color = MaterialTheme.colorScheme.onPrimary)
                        }
                    },
                    dismissButton = {
                        OutlinedButton(
                            onClick = {
                                showDialog = false
                                projectToDelete = null
                            },
                            border = ButtonDefaults.outlinedButtonBorder,
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.onSurface)
                        ) {
                            Text("No")
                        }
                    },
                    title = {
                        Text("Confirm Deletion", color =  MaterialTheme.colorScheme.onSurface)
                    },
                    text = {
                        Text(
                            "Do you really want to delete \"${projectToDelete?.projectName}\"?",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    containerColor = Color(0xFF1E293B),
                    shape = RoundedCornerShape(12.dp)
                )
            }
        }
    }
}

@Composable
fun ProjectItem(
    projectName: String,
    projectDescription: String,
    onDelete: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.DriveFileRenameOutline,
                    contentDescription = "Project Name",
                    tint = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = projectName,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Description,
                    contentDescription = "Project Description",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = projectDescription,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onDelete,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary), // Original: Color(0xFF3B82F6)
                    shape = RoundedCornerShape(15),
                    modifier = Modifier.wrapContentSize()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Remove Project",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Remove", color = MaterialTheme.colorScheme.onPrimary)
                    }
                }
            }
        }
    }
}


