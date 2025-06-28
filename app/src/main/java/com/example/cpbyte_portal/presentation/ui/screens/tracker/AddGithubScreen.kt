package com.example.cpbyte_portal.presentation.ui.screens.tracker

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.domain.model.AddGithubRequest
import com.example.cpbyte_portal.presentation.ui.screens.components.CPByteButton
import com.example.cpbyte_portal.presentation.ui.screens.components.CommonHeader
import com.example.cpbyte_portal.presentation.ui.screens.tracker.components.PlatformInputCard
import com.example.cpbyte_portal.presentation.viewmodel.TrackerViewModel
import com.example.cpbyte_portal.util.ResultState
import com.example.cpbyte_portal.util.Validator.isValidGitHubUsername

@Composable
fun EditGithubScreen(
    initialUsername: String,
    trackerViewModel: TrackerViewModel,
    navController: NavController,
    libraryId: String,
) {
    var githubUsername by remember { mutableStateOf(initialUsername) }
    val hasChanged = githubUsername.trim() != initialUsername.trim()

    val addGithubState by trackerViewModel.addGithubState.collectAsState()
    val context = LocalContext.current
    var isProcessing by remember { mutableStateOf(false) }

    val isUsernameValid = isValidGitHubUsername(githubUsername)
    val isButtonEnabled = hasChanged && isUsernameValid && !isProcessing

    LaunchedEffect(addGithubState) {
        when (addGithubState) {
            is ResultState.Loading -> {
                isProcessing = true
            }

            is ResultState.Success -> {
                trackerViewModel.getUserDashboard(libraryId = libraryId)
                isProcessing = false
                Log.d("GitHubState", "GitHub Username Saved Successfully")
                Toast.makeText(context, "GitHub Username Saved", Toast.LENGTH_SHORT).show()
                navController.navigateUp()
            }

            is ResultState.Failure -> {
                isProcessing = false
                Log.e(
                    "GitHubState",
                    "Failed to Save GitHub Username: ${(addGithubState as ResultState.Failure).error.message}"
                )
                Toast.makeText(context, "Failed to Save GitHub Username", Toast.LENGTH_SHORT).show()
            }

            else -> {
                isProcessing = false
                Log.d("GitHubState", "Unknown state or idle state")
            }
        }
    }


    Scaffold(
        contentWindowInsets = WindowInsets.statusBars,
        topBar = {
            CommonHeader(
                text = "Edit GitHub",
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
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp)
                .consumeWindowInsets(WindowInsets.navigationBars)
        ) {

            PlatformInputCard(
                platformName = "GitHub",
                icon = painterResource(id = R.drawable.github),
                username = githubUsername,
                onUsernameChange = { githubUsername = it },
            )

            Spacer(modifier = Modifier.height(20.dp))

            CPByteButton(
                value = if (isProcessing) "Saving..." else "Save",
                onClick = {
                    val addGithubRequest = AddGithubRequest(githubUsername = githubUsername)
                    trackerViewModel.addGithub(addGithubRequest)
                },
                enabled = isButtonEnabled,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )
        }
    }
}
