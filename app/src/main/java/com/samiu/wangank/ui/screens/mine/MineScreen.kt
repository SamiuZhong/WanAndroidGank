package com.samiu.wangank.ui.screens.mine

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.samiu.wangank.ui.theme.wanTypography

/**
 * 我的
 *
 * @author samiu 2022/11/26
 * @email samiuzhong@outlook.com
 */
@Composable
fun MineScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize(), Alignment.Center) {
        Text(text = "Mine", style = wanTypography.headlineMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMine() {
    MineScreen(navController = rememberNavController())
}