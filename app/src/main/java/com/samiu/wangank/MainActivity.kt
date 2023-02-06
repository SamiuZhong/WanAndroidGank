package com.samiu.wangank

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViewPager()
        binding.bottomNav.setOnItemSelectedListener(onBottomItemSelect)
    }

    private fun initViewPager() {
        val fragmentList: List<Fragment> = listOf(
            FrontFragment(), SquareFragment(), ProjectFragment()
        )
        binding.viewPager.run {
            isUserInputEnabled = false
            offscreenPageLimit = 1
            adapter = MainPagerAdapter(fragmentList, this@MainActivity)
        }
    }

    private val onBottomItemSelect = NavigationBarView.OnItemSelectedListener {
        when (it.itemId) {
            R.id.main_page_0 -> {
                switchFragment(0)
            }
            R.id.main_page_1 -> {
                switchFragment(1)
            }
            R.id.main_page_2 -> {
                switchFragment(2)
            }
        }
        true
    }

    private fun switchFragment(position: Int): Boolean {
        binding.viewPager.setCurrentItem(position, false)
        return true
    }
}