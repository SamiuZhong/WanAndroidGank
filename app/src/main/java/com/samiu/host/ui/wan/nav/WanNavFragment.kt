package com.samiu.host.ui.wan.nav

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
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
    private val list = ArrayList<Fragment>()

    init {
        list.add(homeFragment)
        list.add(squareFragment)
        list.add(articleFragment)
        list.add(systemFragment)
        list.add(navigationFragment)
    }

    override fun initView(){
        pager.adapter = ScreenPagerAdapter(this)
        pager.setPageTransformer(ZoomOutPageTransformer())
    }

    private inner class ScreenPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount() = list.size
        override fun createFragment(position: Int) = list[position]
    }
}