package com.example.cpbyte_portal

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.cpbyte_portal.presentation.ui.navigation.NavigationGraph
import com.example.cpbyte_portal.presentation.ui.theme.CPBytePortalTheme
import com.example.cpbyte_portal.util.SharedPrefsManager
import org.koin.android.ext.android.get

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            CPBytePortalTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        val sharedPrefsManager = get<SharedPrefsManager>()
                        val navController = rememberNavController()
                        NavigationGraph(
                            navController = navController,
                            sharedPrefsManager = sharedPrefsManager
                        )
                    }
                }
            }
        }
    }
}
