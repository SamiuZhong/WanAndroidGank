package com.samiu.wangank

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

/**
 * @author samiu 2022/4/27
 * @email samiuzhong@outlook.com
 */
@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.router
    ) {
        composable(
            route = Screen.Home.router
        ) {
            HomeScreen(navController)
        }
        composable(
            route = Screen.Detail.router
        ) {
            DetailScreen(navController)
        }
    }
}