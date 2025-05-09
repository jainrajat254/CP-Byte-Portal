package com.example.cpbyte_portal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.cpbyte_portal.presentation.ui.navigation.NavigationGraph
import com.example.cpbyte_portal.presentation.ui.screens.LoginScreen
import com.example.cpbyte_portal.presentation.ui.screens.NotificationScreen
import com.example.cpbyte_portal.presentation.ui.theme.CPBytePortalTheme
import com.example.cpbyte_portal.util.SharedPrefsManager
import org.koin.android.ext.android.get

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            CPBytePortalTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {innerPadding->
                    Box(modifier = Modifier.padding(innerPadding)){
                        val sharedPrefsManager = get<SharedPrefsManager>()
                        val navController= rememberNavController()
                        NavigationGraph(navController = navController, sharedPrefsManager = sharedPrefsManager)
                    }
                }
            }
        }
    }
}
