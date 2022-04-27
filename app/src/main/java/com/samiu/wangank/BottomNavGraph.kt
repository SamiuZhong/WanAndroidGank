package com.samiu.wangank

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.samiu.wangank.screens.LeftScreen
import com.samiu.wangank.screens.ProfileScreen
import com.samiu.wangank.screens.SettingsScreen

/**
 * @author samiu 2022/4/27
 * @email samiuzhong@outlook.com
 */
@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = BottomBarScreen.Home.router) {
        composable(route = BottomBarScreen.Home.router) {
            LeftScreen()
        }
        composable(route = BottomBarScreen.Profile.router) {
            ProfileScreen()
        }
        composable(route = BottomBarScreen.Settings.router) {
            SettingsScreen()
        }
    }
}