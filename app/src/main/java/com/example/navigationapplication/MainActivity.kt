package com.example.navigationapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationapplication.ui.screens.Screen1
import com.example.navigationapplication.ui.screens.Screen2
import com.example.navigationapplication.ui.theme.NavigationApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "screen1"
    ){
        composable("screen1"){ Screen1{ navController.navigate("screen2") } }
        composable("screen2"){ Screen2{ navController.navigate("screen1") } }
    }
}