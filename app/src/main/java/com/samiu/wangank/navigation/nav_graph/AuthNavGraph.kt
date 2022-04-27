package com.samiu.wangank.navigation.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.samiu.wangank.navigation.AUTHENTICATION_ROUTER
import com.samiu.wangank.navigation.Screen
import com.samiu.wangank.screens.LoginScreen
import com.samiu.wangank.screens.SignUpScreen

/**
 * @author samiu 2022/4/27
 * @email samiuzhong@outlook.com
 */
fun NavGraphBuilder.authNavGraph(
    navController: NavController
){
    navigation(
        route = AUTHENTICATION_ROUTER,
        startDestination = Screen.Login.router
    ) {
        composable(
            route = Screen.Login.router
        ) {
            LoginScreen(navController = navController)
        }
        composable(
            route = Screen.SignUp.router
        ) {
            SignUpScreen(navController = navController)
        }
    }
}