package com.samiu.wangank.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.samiu.wangank.ui.screens.*

/**
 * @author samiu 2022/4/29
 * @email samiuzhong@outlook.com
 */
@Composable
fun BottomNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = BottomBarScreen.Front.router
    ) {
        composable(route = BottomBarScreen.Front.router) {
            FrontScreen()
        }
        composable(route = BottomBarScreen.Square.router) {
            SquareScreen()
        }
        composable(route = BottomBarScreen.Project.router) {
            ProjectScreen()
        }
        composable(route = BottomBarScreen.Wechat.router) {
            WechatScreen()
        }
        composable(route = BottomBarScreen.Mine.router) {
            MineScreen()
        }
    }
}