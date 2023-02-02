package com.samiu.wangank.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.samiu.wangank.R

/**
 * @author samiu 2022/11/26
 * @email samiuzhong@outlook.com
 */
object WanRoute {
    const val HOME = "Home" // 首页
    const val SQUARE = "Square" // 广场
    const val PROJECTS = "Projects" // 项目
    const val PUBLISH_ACCOUNT = "PublishAccount"// 公众号
    const val MINE = "Mine" // 我的
    const val BROWDER = "Browser" // WenView Page
}

data class WanTopLevelDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int
)

class WanNavigationActions(
    private val navController: NavHostController
) {
    fun navigateTo(destination: WanTopLevelDestination) {
        navController.navigate(destination.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

val TOP_LEVEL_DESTINATIONS = listOf(
    WanTopLevelDestination(
        route = WanRoute.HOME,
        selectedIcon = Icons.Default.Inbox,
        unselectedIcon = Icons.Default.Inbox,
        iconTextId = R.string.home
    ),
    WanTopLevelDestination(
        route = WanRoute.SQUARE,
        selectedIcon = Icons.Default.Inbox,
        unselectedIcon = Icons.Default.Inbox,
        iconTextId = R.string.square
    ),
    WanTopLevelDestination(
        route = WanRoute.PROJECTS,
        selectedIcon = Icons.Default.Inbox,
        unselectedIcon = Icons.Default.Inbox,
        iconTextId = R.string.project
    ),
    WanTopLevelDestination(
        route = WanRoute.PUBLISH_ACCOUNT,
        selectedIcon = Icons.Default.Inbox,
        unselectedIcon = Icons.Default.Inbox,
        iconTextId = R.string.publish_account
    ),
    WanTopLevelDestination(
        route = WanRoute.MINE,
        selectedIcon = Icons.Default.Inbox,
        unselectedIcon = Icons.Default.Inbox,
        iconTextId = R.string.mine
    )
)