package com.example.smartday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.smartday.navigation.SmartDayNavigation
import com.example.smartday.ui.components.appbars.SmartDayBottomAppNavigation
import com.example.smartday.ui.components.appbars.SmartDayTopAppBar
import com.example.smartday.ui.theme.SmartDayTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartDayTheme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { SmartDayTopAppBar(navController = navController) },
                    bottomBar = { SmartDayBottomAppNavigation(navController = navController) },
                    containerColor = MaterialTheme.colorScheme.surface
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        SmartDayNavigation(navController = navController)
                    }
                }
            }
        }
    }
}

