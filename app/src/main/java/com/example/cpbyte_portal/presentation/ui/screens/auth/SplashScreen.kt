package com.example.cpbyte_portal.presentation.ui.screens.auth

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.cpbyte_portal.domain.usecase.IsTokenExpiredUseCase
import com.example.cpbyte_portal.presentation.ui.navigation.Routes
import com.example.cpbyte_portal.presentation.ui.screens.components.CustomLoader
import com.example.cpbyte_portal.util.SharedPrefsManager

@Composable
fun SplashScreen(navController: NavController, sharedPrefsManager: SharedPrefsManager) {
    LaunchedEffect(Unit) {
        val isTokenExpiredUseCase = IsTokenExpiredUseCase()
        val token = sharedPrefsManager.getToken()
        Log.d("SPLASH SCREEN", "${token}")
        if (token == null) {
            navController.navigate(Routes.Login.route) {
                popUpTo(0) { inclusive = true }
            }
        } else if (isTokenExpiredUseCase(token)) {
            navController.navigate(Routes.Login.route)
        } else {
            navController.navigate(Routes.Home.route) {
                popUpTo(0) { inclusive = true }
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CustomLoader()
    }
}