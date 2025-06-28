package com.example.cpbyte_portal.presentation.ui.screens.tracker

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cpbyte_portal.domain.model.AddProjectRequest
import com.example.cpbyte_portal.domain.model.Project
import com.example.cpbyte_portal.presentation.ui.screens.components.CPByteButton
import com.example.cpbyte_portal.presentation.ui.screens.components.CommonHeader
import com.example.cpbyte_portal.presentation.ui.screens.components.CustomLoader
import com.example.cpbyte_portal.presentation.ui.screens.tracker.components.AddProjectForm
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding
import com.example.cpbyte_portal.presentation.ui.theme.Spacing
import com.example.cpbyte_portal.presentation.ui.theme.WarningRed
import com.example.cpbyte_portal.presentation.viewmodel.TrackerViewModel
import com.example.cpbyte_portal.util.ResultState
import com.example.cpbyte_portal.util.Validator

@Composable
fun AddProjectScreen(
    trackerViewModel: TrackerViewModel,
    navController: NavController,
) {
    var projectName by remember { mutableStateOf("") }
    var githubUrl by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var base64ImageUri by remember { mutableStateOf<String?>(null) }

    var showError by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var isDialog by rememberSaveable { mutableStateOf(false) }
    val addProjectState by trackerViewModel.addProjectState.collectAsState()


    LaunchedEffect(addProjectState) {
        when (addProjectState) {
            is ResultState.Success -> {
                isDialog = false
                Toast.makeText(context, "Project added successfully!", Toast.LENGTH_SHORT).show()
                // Navigate or reset state if needed
            }

            is ResultState.Failure -> {
                isDialog = false
                Log.d(
                    "ADD PROJECT ERROR",
                    (addProjectState as ResultState.Failure).error.message.toString()
                )
                Toast.makeText(context, "Some error occurred, please try again", Toast.LENGTH_SHORT)
                    .show()
            }

            ResultState.Idle -> isDialog = false
            ResultState.Loading -> isDialog = true
        }
    }

    if (isDialog) {
        CustomLoader(text = "Adding your Project...")
    } else {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            contentWindowInsets = WindowInsets.statusBars,
            topBar = {
                CommonHeader(
                    text = "Add Project",
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
            },
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(AppPadding.Medium)
                    .verticalScroll(rememberScrollState())
                    .consumeWindowInsets(WindowInsets.navigationBars)
            ) {

                AddProjectForm(
                    projectName = projectName,
                    githubUrl = githubUrl,
                    description = description,
                    onProjectNameChange = { projectName = it },
                    onGithubUrlChange = { githubUrl = it },
                    onDescriptionChange = { description = it },
                    imageUri = imageUri,
                    onImageUriChange = { imageUri = it },
                    onBase64ImageUriChange = { base64ImageUri = it }
                )

                Spacer(modifier = Modifier.height(Spacing.Large))

                val isFormValid = Validator.isValidProjectForm(
                    projectName = projectName,
                    githubUrl = githubUrl,
                    description = description,
                    imageUri = imageUri
                ) && Validator.isValidGithubUrl(githubUrl)

                CPByteButton(
                    value = "Add Project",
                    onClick = {
                        if (isFormValid) {
                            val request = AddProjectRequest(
                                project = Project(
                                    projectName = projectName.trim(),
                                    githubUrl = githubUrl.trim(),
                                    description = description.trim(),
                                    coverImage = base64ImageUri ?: ""
                                )
                            )
                            trackerViewModel.addProject(request)
                            showError = false
                        } else {
                            showError = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = isFormValid,
                )
                if (showError) {
                    Text(
                        text = "Please fill all required information",
                        color = WarningRed,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}
