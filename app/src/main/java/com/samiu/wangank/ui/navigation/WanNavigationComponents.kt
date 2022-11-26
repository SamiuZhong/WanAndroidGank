package com.samiu.wangank.ui.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

/**
 * @author samiu 2022/11/26
 * @email samiuzhong@outlook.com
 */
@Composable
fun WanBottomNavigationBar(
    selectedDestination: String, navigateToTopLevelDestination: (WanTopLevelDestination) -> Unit
) {
    NavigationBar(modifier = Modifier.fillMaxWidth()) {
        TOP_LEVEL_DESTINATIONS.forEach { wanDestination ->
            NavigationBarItem(selected = selectedDestination == wanDestination.route,
                onClick = { navigateToTopLevelDestination(wanDestination) },
                icon = {
                    Icon(
                        imageVector = wanDestination.selectedIcon,
                        contentDescription = stringResource(id = wanDestination.iconTextId)
                    )
                })
        }
    }
}