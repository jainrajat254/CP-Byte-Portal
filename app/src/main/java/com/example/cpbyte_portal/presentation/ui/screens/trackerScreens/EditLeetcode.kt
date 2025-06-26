package com.example.cpbyte_portal.presentation.ui.screens.trackerScreens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.domain.model.AddLeetCodeRequest
import com.example.cpbyte_portal.presentation.ui.screens.components.CommonHeader
import com.example.cpbyte_portal.presentation.viewmodel.TrackerViewModel
import com.example.cpbyte_portal.util.ResultState

@Composable
fun EditLeetcodeScreen(
    initialUsername: String,
    trackerViewModel: TrackerViewModel,
    navController: NavController,
    libraryId: String,
) {
    var leetcodeUsername by remember { mutableStateOf(initialUsername) }
    val hasChanged = leetcodeUsername.trim() != initialUsername.trim()

    val addLeetCodeState by trackerViewModel.addLeetCodeState.collectAsState()
    val context = LocalContext.current

    var isProcessing by remember { mutableStateOf(false) }

    // Observe the API result
    LaunchedEffect(addLeetCodeState) {
        when (addLeetCodeState) {
            is ResultState.Loading -> {
                isProcessing = true
            }

            is ResultState.Success -> {
                trackerViewModel.getUserDashboard(libraryId = libraryId)
                isProcessing = false
                Toast.makeText(context, "LeetCode username updated", Toast.LENGTH_SHORT).show()
            }

            is ResultState.Failure -> {
                isProcessing = false
                val error = (addLeetCodeState as ResultState.Failure).error.message
                Toast.makeText(context, "Failed to update: $error", Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }

    Scaffold(
        topBar = {
            CommonHeader(text = "Edit Leetcode",
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
                .padding(paddingValues)
                .padding(24.dp)
                .fillMaxSize()
        ) {
            PlatformInputCard(
                platformName = "LeetCode",
                icon = painterResource(id = R.drawable.leetcode_logoo),
                username = leetcodeUsername,
                onUsernameChange = { leetcodeUsername = it }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    trackerViewModel.addLeetCode(
                        AddLeetCodeRequest(leetcodeUsername = leetcodeUsername.trim())
                    )
                },
                enabled = hasChanged && !isProcessing,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (hasChanged) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = if (hasChanged) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant // Original: Color.White
                )
            ) {
                Text(text = if (isProcessing) "Saving..." else "Save", fontSize = 16.sp)
            }
        }
    }
}

