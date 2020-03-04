package com.samiu.host

import android.content.Intent
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.samiu.base.ui.BaseActivity
import com.samiu.host.ui.activity.BrowserActivity

/**
 * @author Samiu 2020/3/2
 */
class MainActivity : BaseActivity() {

    companion object {
        const val URL = "url"
    }

    override fun getLayoutResId() = R.layout.activity_main
    override fun initData() = Unit

    override fun initView() {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
    }

    fun toBrowser(url: String) {
        startActivity(Intent(this, BrowserActivity::class.java).apply {
            putExtra(URL, url)
        })
    }
}
