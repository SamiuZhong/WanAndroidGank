package com.samiu.host

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.samiu.base.ui.BaseActivity

/**
 * @author Samiu 2020/3/2
 */
class MainActivity : BaseActivity() {

    override fun getLayoutResId()=R.layout.activity_main

    override fun initView(){
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
    }

    override fun initData()=Unit
}
