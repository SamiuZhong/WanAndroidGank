package com.samiu.host.ui.fragment.wan

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.jeremyliao.liveeventbus.LiveEventBus
import com.samiu.base.interactive.ZoomOutPageTransformer
import com.samiu.base.ui.BaseFragment
import com.samiu.host.R
import com.samiu.host.global.*
import kotlinx.android.synthetic.main.fragment_wan_nav.*

/**
 * @author Samiu 2020/3/2
 */
class WanNavFragment : BaseFragment() {
    override fun getLayoutResId() = R.layout.fragment_wan_nav
    override fun initData() = Unit

    private val homeFragment by lazy { WanHomeFragment() }
    private val squareFragment by lazy { WanSquareFragment() }
    private val recentProjectFragment by lazy { RecentProjectFragment() }
    private val systemFragment by lazy { WanSystemFragment() }
    private val navigationFragment by lazy { WanNavigationFragment() }
    private val wxArticleFragment by lazy { WanWxArticleFragment() }
    private val fragmentList = ArrayList<Fragment>()
    private val titleList = arrayOf(
        HOME_PAGE, SQUARE, RECENT_PROJECT, SYSTEM, NAVIGATION, WX_ARTICLE
    )
    private var currentTitle = titleList[0]

    init {
        fragmentList.add(homeFragment)
        fragmentList.add(squareFragment)
        fragmentList.add(recentProjectFragment)
        fragmentList.add(systemFragment)
        fragmentList.add(navigationFragment)
        fragmentList.add(wxArticleFragment)
    }

    override fun initView() {
        //viewPager2
        pager.adapter = ScreenPagerAdapter(this)
        pager.setPageTransformer(ZoomOutPageTransformer())
        pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                currentTitle = titleList[position]
            }
        })
        //tabLayout
        TabLayoutMediator(tab, pager) { tab, position -> tab.text = titleList[position] }.attach()
        //smartRefreshLayout
        home_refresh_layout.setOnRefreshListener {
            LiveEventBus
                .get(currentTitle, Int::class.java)
                .post(-1)
            it.finishRefresh(1500)
        }
        home_refresh_layout.setOnLoadMoreListener {
            LiveEventBus
                .get(currentTitle, Int::class.java)
                .post(1)
            it.finishLoadMore(2000)
        }
    }

    private inner class ScreenPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount() = fragmentList.size
        override fun createFragment(position: Int) = fragmentList[position]
    }
}