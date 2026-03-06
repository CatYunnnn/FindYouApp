package com.catyun.findyou.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.catyun.findyou.ui.screens.GameScreen
import com.catyun.findyou.ui.screens.HomeScreen
import com.catyun.findyou.ui.screens.RoomScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home",
    ) {
        composable("home") {
        }
    }
}
