package com.samiu.wangank

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.samiu.wangank.ui.theme.WanAndroidTheme

/**
 * @author samiu 2022/11/25
 * @email samiuzhong@outlook.com
 */
@Composable
fun WanAndroidApp() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Hello World!")
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    WanAndroidTheme {
        WanAndroidApp()
    }
}