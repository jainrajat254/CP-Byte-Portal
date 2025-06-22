package com.example.cpbyte_portal.presentation.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cpbyte_portal.R
import com.example.cpbyte_portal.domain.model.LoginResponse
import com.example.cpbyte_portal.presentation.ui.navigation.Routes
import com.example.cpbyte_portal.presentation.ui.screens.components.CPByteButton
import com.example.cpbyte_portal.presentation.ui.screens.components.CPByteTextField
import com.example.cpbyte_portal.presentation.ui.screens.components.CustomLoader
import com.example.cpbyte_portal.presentation.viewmodel.AuthViewModel
import com.example.cpbyte_portal.util.ResultState
import com.example.cpbyte_portal.util.SharedPrefsManager
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    sharedPrefsManager: SharedPrefsManager,
    authViewModel: AuthViewModel = koinViewModel(),
    navController: NavHostController,
) {

    // Stores user input
    var libraryId by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val scrollableState = rememberScrollState()
    val image = painterResource(R.drawable.cpbyte_logo)

    // Manages loading dialog
    var isDialog by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val loginState by authViewModel.loginState.collectAsState()

    LaunchedEffect(loginState) {
        when (loginState) {
            is ResultState.Success -> {
                isDialog = false
                sharedPrefsManager.saveToken((loginState as ResultState.Success<LoginResponse>).data.data)
                authViewModel.userViewModel.fetchUserProfile()
                Toast.makeText(context, "Logged in successfully!", Toast.LENGTH_SHORT).show()
                navController.navigate(Routes.Home.route) {
                    popUpTo(Routes.Login.route) { inclusive = true }
                }
            }

            is ResultState.Failure -> {
                isDialog = false
                Log.d("AUTH ERROR", (loginState as ResultState.Failure).error.message.toString())
                Toast.makeText(context, "Some error occurred, please try again", Toast.LENGTH_SHORT)
                    .show()
            }

            ResultState.Idle -> isDialog = false
            ResultState.Loading -> isDialog = true
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.login_bg),
                contentScale = ContentScale.Crop
            )
    ) {
        if (isDialog) {
            CustomLoader(text = stringResource(R.string.logging_in_text))
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .scrollable(
                        state = scrollableState,
                        orientation = Orientation.Vertical
                    ), // Scrollable
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // Spacer to create space at the top
                Spacer(modifier = Modifier.height(40.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.welcome_text),
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        lineHeight = 36.sp,
                        modifier = Modifier.weight(0.7f)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Image(
                        painter = image,
                        contentDescription = stringResource(R.string.logo_description),
                        modifier = Modifier.weight(0.3f)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = stringResource(R.string.login_text),
                    color = Color(0xFF83888E),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(40.dp))

                // Input field for Library Id
                CPByteTextField(
                    value = libraryId.uppercase().trim(),
                    label = stringResource(R.string.libraryId),
                    onValueChange = {
                        if (it.length <= 15) libraryId = it  // Limit Library ID length to 15
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                    ),
                    imeAction = ImeAction.Next
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Input field for Password
                CPByteTextField(
                    value = password,
                    label = stringResource(R.string.password),
                    onValueChange = { password = it },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    imeAction = ImeAction.Done
                )

                Spacer(modifier = Modifier.height(40.dp))

                CPByteButton(
                    value = stringResource(R.string.button_text),
                    onClick = {
                        authViewModel.loginUser(libraryId.uppercase().trim(), password.trim())
                    }
                )
            }
        }
    }
}