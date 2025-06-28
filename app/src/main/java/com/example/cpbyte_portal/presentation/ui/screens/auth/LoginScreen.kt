package com.example.cpbyte_portal.presentation.ui.screens.auth

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.di.TokenProvider
import com.example.cpbyte_portal.domain.model.LoginResponse
import com.example.cpbyte_portal.presentation.ui.navigation.Routes
import com.example.cpbyte_portal.presentation.ui.screens.auth.components.LoginForm
import com.example.cpbyte_portal.presentation.ui.screens.auth.components.LoginHeader
import com.example.cpbyte_portal.presentation.ui.screens.components.CPByteButton
import com.example.cpbyte_portal.presentation.ui.screens.components.CustomLoader
import com.example.cpbyte_portal.presentation.ui.theme.AppPadding.Large
import com.example.cpbyte_portal.presentation.viewmodel.AuthViewModel
import com.example.cpbyte_portal.util.ResultState
import com.example.cpbyte_portal.util.SharedPrefsManager
import com.example.cpbyte_portal.util.Validator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    sharedPrefsManager: SharedPrefsManager,
    authViewModel: AuthViewModel = koinViewModel(),
    navController: NavHostController,
) {

    var libraryId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isDialog by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val loginState by authViewModel.loginState.collectAsState()

    LaunchedEffect(loginState) {
        when (loginState) {
            is ResultState.Success -> {
                isDialog = false
                val newToken = (loginState as ResultState.Success<LoginResponse>).data.data
                TokenProvider.token = newToken
                sharedPrefsManager.saveToken(newToken)
                authViewModel.userViewModel.fetchUserProfile()

                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Logged in successfully!", Toast.LENGTH_SHORT).show()
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                    }
                }
                authViewModel.resetLoginState()
            }

            is ResultState.Failure -> {
                isDialog = false
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "Some error occurred, please try again",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            ResultState.Idle -> isDialog = false
            ResultState.Loading -> isDialog = true
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->

        if (isDialog) {
            CustomLoader(text = stringResource(R.string.logging_in_text))
        } else {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(Large)
                    .verticalScroll(rememberScrollState())
                    .consumeWindowInsets(WindowInsets.navigationBars),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                LoginHeader(modifier = Modifier.fillMaxWidth())

                Spacer(modifier = Modifier.height(40.dp))

                LoginForm(
                    libraryId = libraryId,
                    password = password,
                    onLibraryIdChange = { libraryId = it },
                    onPasswordChange = { password = it }
                )

                Spacer(modifier = Modifier.height(40.dp))

                CPByteButton(
                    value = stringResource(R.string.button_text),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        when {
                            !Validator.isValidLibraryId(libraryId) -> {
                                Toast.makeText(context, "Invalid Library ID", Toast.LENGTH_SHORT)
                                    .show()
                            }

                            !Validator.isValidPassword(password) -> {
                                Toast.makeText(
                                    context,
                                    "Invalid Password (min 6 characters)",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            else -> {
                                authViewModel.loginUser(
                                    libraryId = libraryId.uppercase().trim(),
                                    password = password.trim()
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}