package com.samiu.wangank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samiu.wangank.navigation.nav_graph.SetupNavGraph
import com.samiu.wangank.ui.theme.WanAndroidGankTheme

/**
 * @author samiu 2020/3/2
 * @email samiuzhong@outlook.com
 */
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WanAndroidGankTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}