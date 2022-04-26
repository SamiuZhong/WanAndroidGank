package com.samiu.wangank

/**
 * @author samiu 2022/4/27
 * @email samiuzhong@outlook.com
 */
sealed class Screen(val router: String) {
    object Home : Screen(router = "home_screen")
    object Detail : Screen(router = "detail_screen")
}
