package com.samiu.wangank

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.samiu.wangank.ui.navigation.WanTopLevelDestination
import com.samiu.wangank.ui.navigation.WanBottomNavigationBar
import com.samiu.wangank.ui.navigation.WanNavigationActions
import com.samiu.wangank.ui.navigation.WanRoute
import com.samiu.wangank.ui.screens.home.HomeScreen
import com.samiu.wangank.ui.screens.mine.MineScreen
import com.samiu.wangank.ui.screens.project.ProjectScreen
import com.samiu.wangank.ui.screens.publich.PAccountScreen
import com.samiu.wangank.ui.screens.square.SquareScreen

/**
 * @author samiu 2022/11/25
 * @email samiuzhong@outlook.com
 */
@Composable
fun WanAndroidApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val selectedDestination = navBackStackEntry?.destination?.route ?: WanRoute.HOME
    val navigationActions = remember(navController) {
        WanNavigationActions(navController)
    }

    WanAppContent(
        navController = navController,
        selectedDestination = selectedDestination,
        navigateToTopLevelDestination = navigationActions::navigateTo
    )
}

@Composable
private fun WanAppContent(
    navController: NavHostController,
    selectedDestination: String,
    navigateToTopLevelDestination: (WanTopLevelDestination) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.inverseOnSurface)
    ) {
        WanNavHost(
            navController = navController, modifier = Modifier.weight(1F)
        )
        WanBottomNavigationBar(
            selectedDestination = selectedDestination,
            navigateToTopLevelDestination = navigateToTopLevelDestination
        )
    }
}

@Composable
private fun WanNavHost(
    navController: NavHostController, modifier: Modifier
) {
    NavHost(
        modifier = modifier, navController = navController, startDestination = WanRoute.HOME
    ) {
        composable(WanRoute.HOME) {
            HomeScreen(navController = navController)
        }
        composable(WanRoute.SQUARE) {
            SquareScreen(navController = navController)
        }
        composable(WanRoute.PROJECTS) {
            ProjectScreen(navController = navController)
        }
        composable(WanRoute.PUBLISH_ACCOUNT) {
            PAccountScreen(navController = navController)
        }
        composable(WanRoute.MINE) {
            MineScreen(navController = navController)
        }
    }
}