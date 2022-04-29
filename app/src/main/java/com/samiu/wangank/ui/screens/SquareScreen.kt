package com.samiu.wangank.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.samiu.wangank.ui.theme.Canghuang
import com.samiu.wangank.ui.theme.Qianshibai

/**
 * 广场
 *
 * @author samiu 2022/4/29
 * @email samiuzhong@outlook.com
 */
@Composable
fun SquareScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Canghuang),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "广场",
            color = Qianshibai,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Preview
@Composable
fun PreviewSquare() {
    SquareScreen()
}