package com.samiu.host.ui.wan.nav

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.samiu.base.interactive.ZoomOutPageTransformer
import com.samiu.base.ui.BaseFragment
import com.samiu.host.R
import com.samiu.host.ui.wan.nav.fragment.*
import kotlinx.android.synthetic.main.fragment_wan_nav.*

/**
 * 玩安卓 底部导航栏的首页
 * @author Samiu 2020/3/2
 */
class WanNavFragment : BaseFragment() {
    override fun getLayoutResId() = R.layout.fragment_wan_nav

    override fun initData() = Unit

    private val homeFragment by lazy { WanHomeFragment() }
    private val squareFragment by lazy { WanSquareFragment() }
    private val articleFragment by lazy { WanArticleFragment() }
    private val systemFragment by lazy { WanSystemFragment() }
    private val navigationFragment by lazy { WanNavigationFragment() }
    private val fragmentList = ArrayList<Fragment>()
    private val titleList = arrayOf("首页", "广场", "公众号", "体系", "导航")

    init {
        fragmentList.add(homeFragment)
        fragmentList.add(squareFragment)
        fragmentList.add(articleFragment)
        fragmentList.add(systemFragment)
        fragmentList.add(navigationFragment)
    }

    override fun initView(){
        pager.adapter = ScreenPagerAdapter(this)
        pager.setPageTransformer(ZoomOutPageTransformer())
        TabLayoutMediator(tab,pager){tab, position ->  tab.text = titleList[position]}.attach()
    }

    private inner class ScreenPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount() = fragmentList.size
        override fun createFragment(position: Int) = fragmentList[position]
    }
}