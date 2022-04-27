package com.samiu.wangank.navigation

/**
 * @author samiu 2022/4/27
 * @email samiuzhong@outlook.com
 */
const val AUTHENTICATION_ROUTER = "authentication"
const val ROOT_ROUTE = "root"
const val HOME_ROUTE = "home"

sealed class Screen(val router: String) {

    object Detail : Screen(router = "detail_screen")

    object Home : Screen(router = "home_screen")

    object Login : Screen(router = "login_screen")

    object SignUp : Screen(router = "sign_up_screen")
}
