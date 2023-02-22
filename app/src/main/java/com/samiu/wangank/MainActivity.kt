package com.samiu.wangank

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationBarView
import com.samiu.wangank.components.front.FrontFragment
import com.samiu.wangank.components.project.ProjectFragment
import com.samiu.wangank.components.square.SquareFragment
import com.samiu.wangank.databinding.ActivityMainBinding
import com.samiu.wangank.ui.adapter.MainPagerAdapter
import com.samiu.wangank.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author samiu 2023/2/2
 * @email samiuzhong@outlook.com
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)
    }
}