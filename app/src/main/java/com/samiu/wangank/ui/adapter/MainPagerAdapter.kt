package com.samiu.wangank.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @author samiu 2023/2/2
 * @email samiuzhong@outlook.com
 */
class MainPagerAdapter(
    private val fragments: List<Fragment>,
    activity: FragmentActivity
) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int) = fragments[position]

    override fun getItemCount() = fragments.size
}