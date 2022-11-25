package com.samiu.wangank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import com.samiu.wangank.ui.screens.MainScreen
import com.samiu.wangank.ui.theme.WanAndroidTheme

/**
 * @author samiu 2020/3/2
 * @email samiuzhong@outlook.com
 */
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WanAndroidTheme() {
//                navController = rememberNavController()
//                SetupNavGraph(navController = navController)
                MainScreen()
            }
        }
    }
}