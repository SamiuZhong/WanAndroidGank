package com.samiu.wangank.ui.screens

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.samiu.wangank.ui.navigation.BottomBarScreen
import com.samiu.wangank.ui.navigation.BottomNavGraph

/**
 * App主页
 *
 * @author samiu 2022/4/29
 * @email samiuzhong@outlook.com
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(bottomBar = {
        WanBottom(navHostController = navController)
    }) {
        BottomNavGraph(navHostController = navController)
    }
}

@Composable
fun WanBottom(
    navHostController: NavHostController
) {
    val screens = listOf(
        BottomBarScreen.Front,
        BottomBarScreen.Square,
        BottomBarScreen.Project,
        BottomBarScreen.Wechat,
        BottomBarScreen.Mine
    )
    val navBaskStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBaskStackEntry?.destination

    BottomNavigation {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navHostController = navHostController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navHostController: NavHostController
) {
    BottomNavigationItem(
        label = { Text(text = screen.title) },
        icon = {
            Icon(imageVector = screen.icon, contentDescription = "Navigation Icon")
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.router
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navHostController.navigate(screen.router) {
                popUpTo(navHostController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}