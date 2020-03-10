package com.samiu.host.ui.fragment.gank

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.jeremyliao.liveeventbus.LiveEventBus
import com.samiu.base.interactive.ZoomOutPageTransformer
import com.samiu.base.ui.BaseFragment
import com.samiu.host.R
import com.samiu.host.global.*
import kotlinx.android.synthetic.main.fragment_gank_nav.*

/**
 * @author Samiu 2020/3/2
 */
class GankNavFragment:BaseFragment() {
    override fun getLayoutResId() = R.layout.fragment_gank_nav
    override fun initData()=Unit

    private val androidFragment by lazy { GankAndroidFragment() }
    private val iosFragment by lazy { GankIosFragment() }
    private val frontFragment by lazy { GankFrontFragment() }
    private val expandFragment by lazy { GankExpandFragment() }
    private val fragmentList = ArrayList<Fragment>()
    private val titleList = arrayOf(ANDROID, IOS, FRONT, EXPAND)
    private var currentTitle = titleList[0]

    init {
        fragmentList.add(androidFragment)
        fragmentList.add(iosFragment)
        fragmentList.add(frontFragment)
        fragmentList.add(expandFragment)
    }

    override fun initView(){
        //viewPager2
        gank_nav_pager.adapter = ScreenPagerAdapter(this)
        gank_nav_pager.setPageTransformer(ZoomOutPageTransformer())
        gank_nav_pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                currentTitle = titleList[position]
            }
        })
        //tabLayout
        TabLayoutMediator(gank_nav_tab, gank_nav_pager) { tab, position -> tab.text = titleList[position] }.attach()
        //smartRefreshLayout
        with(gank_nav_refresh_layout) {
            setOnRefreshListener {
                LiveEventBus
                    .get(currentTitle, Int::class.java)
                    .post(REFRESH)
                finishRefresh(1500)
            }
            setOnLoadMoreListener {
                LiveEventBus
                    .get(currentTitle, Int::class.java)
                    .post(LOAD_MORE)
                finishLoadMore(2000)
            }
        }
    }


    private inner class ScreenPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount() = fragmentList.size
        override fun createFragment(position: Int) = fragmentList[position]
    }
}