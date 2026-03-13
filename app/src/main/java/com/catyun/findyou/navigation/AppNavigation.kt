package com.catyun.findyou.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

// 定義所有的 Route 與對應的參數定義，enum的建立固定物件方法
enum class Screen(val route: String) {
    Home("home"),
    Room("room/{roomCode}"), // 可以帶入參數 例如 room/888666
    Game("game/{roomCode}"),
}

// 擴展函數建立強型別的 Route string
sealed class NavigationRoute(val route: String) {
    object Home : NavigationRoute(Screen.Home.route)
    class Room(roomCode: String) : NavigationRoute("room/$roomCode")
    class Game(roomCode: String) : NavigationRoute("game/$roomCode")
}
