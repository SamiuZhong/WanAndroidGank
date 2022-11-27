package com.samiu.wangank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.samiu.wangank.ui.theme.WanAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author samiu 2020/3/2
 * @email samiuzhong@outlook.com
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WanAndroidTheme {
                WanAndroidApp()
            }
        }
    }
}