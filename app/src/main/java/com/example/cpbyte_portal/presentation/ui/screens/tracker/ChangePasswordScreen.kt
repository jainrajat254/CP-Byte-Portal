package com.example.cpbyte_portal.presentation.ui.screens.tracker

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cpbyte_portal.domain.model.EditPasswordRequest
import com.example.cpbyte_portal.presentation.ui.screens.components.CPByteButton
import com.example.cpbyte_portal.presentation.ui.screens.components.CommonHeader
import com.example.cpbyte_portal.presentation.ui.screens.components.CustomLoader
import com.example.cpbyte_portal.presentation.ui.screens.tracker.components.ChangePasswordForm
import com.example.cpbyte_portal.presentation.ui.theme.Spacing
import com.example.cpbyte_portal.presentation.viewmodel.SettingsViewModel
import com.example.cpbyte_portal.util.ResultState

@Composable
fun EditPasswordScreen(
    settingsViewModel: SettingsViewModel,
    navController: NavController,
) {
    var oldPassword by rememberSaveable { mutableStateOf("") }
    var newPassword by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current
    val scrollState = rememberScrollState()

    val editPasswordState by settingsViewModel.editPasswordState.collectAsState()
    var isDialogVisible by remember { mutableStateOf(false) }

    val isFormValid = oldPassword.isNotBlank() &&
            newPassword.length >= 6 &&
            confirmPassword == newPassword

    LaunchedEffect(editPasswordState) {
        when (editPasswordState) {
            is ResultState.Success -> {
                isDialogVisible = false
                Toast.makeText(context, "Password updated successfully!", Toast.LENGTH_SHORT).show()
                oldPassword = ""
                newPassword = ""
                confirmPassword = ""
            }

            is ResultState.Failure -> {
                isDialogVisible = false
                val error =
                    (editPasswordState as ResultState.Failure).error.message ?: "Unknown error"
                Toast.makeText(context, "Failed: $error", Toast.LENGTH_SHORT).show()
            }

            ResultState.Loading -> {
                isDialogVisible = true
            }

            ResultState.Idle -> {
                isDialogVisible = false
            }
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets.statusBars,
        topBar = {
            CommonHeader(
                text = "Edit Password",
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
    ) { innerPadding ->

        if (isDialogVisible) {
            CustomLoader(text = "Updating password...")
        }

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 32.dp)
                .consumeWindowInsets(WindowInsets.navigationBars),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ChangePasswordForm(
                oldPassword = oldPassword,
                onOldPasswordChange = { oldPassword = it },
                newPassword = newPassword,
                onNewPasswordChange = { newPassword = it },
                confirmPassword = confirmPassword,
                onConfirmPasswordChange = { confirmPassword = it }
            )

            Spacer(modifier = Modifier.height(Spacing.Large))

            CPByteButton(
                modifier = Modifier.fillMaxWidth(),
                value = "Update Password",
                onClick = {
                    val editPasswordRequest = EditPasswordRequest(
                        oldPass = oldPassword.trim(),
                        newPass = newPassword.trim(),
                        confPass = confirmPassword.trim()
                    )
                    settingsViewModel.editPassword(editPasswordRequest = editPasswordRequest)
                },
                enabled = isFormValid
            )
        }
    }
}


