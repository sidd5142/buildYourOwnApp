package com.example.quickity.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.quickity.api_feature.ui.navigation.Navigation
import com.example.quickity.ui.BottomNavigation
import com.example.quickity.ui.theme.blueV

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HolderScreen(
    modifier: Modifier = Modifier.background(blueV)
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) {
        Navigation(navController)
    }
}
