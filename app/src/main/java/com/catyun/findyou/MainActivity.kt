package com.catyun.findyou

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.catyun.findyou.navigation.NavigationRoute
import com.catyun.findyou.navigation.Screen
import com.catyun.findyou.ui.screens.GameScreen
import com.catyun.findyou.ui.screens.HomeScreen
import com.catyun.findyou.ui.screens.RoomScreen
import com.catyun.findyou.ui.theme.FindYouAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FindYouAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FindYouAppNavigation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun FindYouAppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier,
    ) {
        // Home Screen Route
        composable(Screen.Home.route) {
            HomeScreen(
                onJoinRoom = { roomCode ->
                    // 當按下 Join Room 時，跳轉到 RoomScreen 並帶入 Room Code
                    navController.navigate(NavigationRoute.Room(roomCode).route)
                },
            )
        }

        // Room Screen Route
        composable(
            route = Screen.Room.route,
            arguments = listOf(navArgument("roomCode") { type = NavType.StringType }),
        ) { backStackEntry ->
            val roomCode = backStackEntry.arguments?.getString("roomCode") ?: ""
            RoomScreen(
                roomCode = roomCode,
                onStartGame = {
                    // 當按下開始追蹤時，跳轉到 GameScreen
                    navController.navigate(NavigationRoute.Game(roomCode).route) {
                        // 可以選擇在這裡清空 BackStack，避免按返回鍵又回到房間 (依據遊戲需求調整)
                        // popUpTo(Screen.Home.route)
                    }
                },
                onLeaveRoom = {
                    navController.popBackStack()
                },
            )
        }

        // Game Screen Route
        composable(
            route = Screen.Game.route,
            arguments = listOf(navArgument("roomCode") { type = NavType.StringType }),
        ) { _ ->
            GameScreen(
                onLeaveGame = {
                    // 離開遊戲回到首頁
                    navController.popBackStack(Screen.Home.route, inclusive = false)
                },
            )
        }
    }
}
