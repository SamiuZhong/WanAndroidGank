package com.samiu.wangank

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * @author samiu 2022/4/27
 * @email samiuzhong@outlook.com
 */
sealed class BottomBarScreen(
    val router: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        router = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Profile : BottomBarScreen(
        router = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )

    object Settings : BottomBarScreen(
        router = "settings",
        title = "Settings",
        icon = Icons.Default.Settings
    )
}
