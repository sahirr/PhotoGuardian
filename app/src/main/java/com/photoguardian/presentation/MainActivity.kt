package com.photoguardian.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.photoguardian.presentation.theme.PhotoGuardianTheme
import dagger.hilt.android.AndroidEntryPoint
import com.photoguardian.ui.theme.PhotoGuardianTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotoGuardianTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PhotoGuardianApp()
                }
            }
        }
    }
}

@Composable
fun PhotoGuardianApp() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "dashboard") {
        composable("dashboard") {
            DashboardScreenPlaceholder()
        }
        composable("onboarding") {
            // TODO: Onboarding Screen
        }
    }
}

@Composable
fun DashboardScreenPlaceholder() {
    Text(text = "PhotoGuardian Dashboard - System Active")
}