package com.example.cpbyte_portal.presentation.ui.screens.trackerScreens

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cpbyte_portal.domain.model.EditPasswordRequest
import com.example.cpbyte_portal.presentation.ui.screens.components.CPByteButton
import com.example.cpbyte_portal.presentation.ui.screens.components.CommonHeader
import com.example.cpbyte_portal.presentation.ui.screens.components.CustomLoader
import com.example.cpbyte_portal.presentation.viewmodel.SettingsViewModel
import com.example.cpbyte_portal.util.ResultState

@Composable
fun EditPasswordScreen(
    settingsViewModel: SettingsViewModel,
    navController: NavController
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

    // Observe state and handle UI side-effects
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
                val error = (editPasswordState as ResultState.Failure).error.message ?: "Unknown error"
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
        containerColor = Color(0xFF0F172A),
        topBar = { CommonHeader(text = "Edit Password",
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
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
                .padding(horizontal = 20.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color(0xFF374151), RoundedCornerShape(16.dp)),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    PasswordInputField(
                        label = "Old Password",
                        password = oldPassword,
                        onPasswordChange = { oldPassword = it }
                    )

                    PasswordInputField(
                        label = "New Password",
                        password = newPassword,
                        onPasswordChange = { newPassword = it }
                    )

                    PasswordInputField(
                        label = "Confirm New Password",
                        password = confirmPassword,
                        onPasswordChange = { confirmPassword = it }
                    )

                    CPByteButton(
                        value = "Save Password",
                        onClick = {
                            val request = EditPasswordRequest(
                                oldPass = oldPassword.trim(),
                                newPass = newPassword.trim(),
                                confPass = confirmPassword.trim()
                            )
                            settingsViewModel.editPassword(request)
                        },
                        enabled = isFormValid
                    )
                }
            }
        }
    }
}



@Composable
fun PasswordInputField(
    label: String,
    password: String,
    onPasswordChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text(label, color = Color(0xFFB0BEC5)) },
        singleLine = true,
        visualTransformation = PasswordVisualTransformation(),
        shape = RoundedCornerShape(10.dp),
        colors = textFieldColors(),
        modifier = Modifier.fillMaxWidth()
    )
}


