package com.samiu.host

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.samiu.base.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Samiu 2020/3/2
 */
class MainActivity : BaseActivity() {
    override fun getLayoutResId() = R.layout.activity_main
    override fun initData() = Unit

    override fun initView() {
        val navController = findNavController(R.id.nav_host_fragment)
        nav_view.setupWithNavController(navController)
    }
}
