package com.samiu.wangank.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * @author samiu 2022/4/29
 * @email samiuzhong@outlook.com
 */
sealed class BottomBarScreen(
    val router: String,
    val title: String,
    val icon: ImageVector
) {
    object Front : BottomBarScreen(
        router = "front",
        title = "Front",
        icon = Icons.Default.Home
    )

    object Square : BottomBarScreen(
        router = "square",
        title = "Square",
        icon = Icons.Default.Person
    )

    object Project : BottomBarScreen(
        router = "project",
        title = "Project",
        icon = Icons.Default.AccountBox
    )

    object Wechat : BottomBarScreen(
        router = "wechat",
        title = "Wechat",
        icon = Icons.Default.ArrowForward
    )

    object Mine : BottomBarScreen(
        router = "mine",
        title = "Mine",
        icon = Icons.Default.Settings
    )
}
