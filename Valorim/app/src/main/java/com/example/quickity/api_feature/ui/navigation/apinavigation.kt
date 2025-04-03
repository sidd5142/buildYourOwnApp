package com.example.quickity.api_feature.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quickity.api_feature.ui.screen.detail.DetailAgents
import com.example.quickity.api_feature.ui.screen.detail.DetailWeapon
import com.example.quickity.api_feature.viewmodel.HomeViewModel
import com.example.quickity.ui.screens.BillScreen
import com.example.quickity.ui.screens.HomeScreen
import com.example.quickity.ui.screens.ScannerScreen
import com.example.quickity.urlText

@Composable
fun Navigation(navController: NavHostController) {
    //val navController = rememberNavController()
    val viewModel: HomeViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = "home",

    ) {
        composable("Home") {
                HomeScreen(
                    navHostController = navController,
                    viewModel = viewModel
                    )
        }
        composable("Scan") {
                ScannerScreen(
                    navController = navController,
                    urlText = urlText,
                    onUrlTextUpdate = {
                        urlText = it
                    },
                )
        }
        composable("Bills") {
                BillScreen(navController = navController)
        }
        composable("detail_agent") {
            DetailAgents(viewModel = viewModel, navHostController = navController)
        }
        composable("detail_weapon"){
            DetailWeapon(viewModel = viewModel, navHostController = navController )
        }
    }
}

