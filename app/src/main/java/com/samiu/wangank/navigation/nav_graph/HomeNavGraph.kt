package com.samiu.wangank.navigation.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.samiu.wangank.navigation.HOME_ROUTE
import com.samiu.wangank.navigation.Screen
import com.samiu.wangank.screens.DetailScreen
import com.samiu.wangank.screens.HomeScreen

/**
 * @author samiu 2022/4/27
 * @email samiuzhong@outlook.com
 */
fun NavGraphBuilder.homeNavGraph(
    navController: NavController
) {
    navigation(
        route = HOME_ROUTE,
        startDestination = Screen.Home.router
    ) {
        composable(
            route = Screen.Home.router
        ) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Detail.router
        ) {
            DetailScreen(navController = navController)
        }
    }
}