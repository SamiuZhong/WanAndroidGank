package com.samiu.host.ui.fragment.wan

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeremyliao.liveeventbus.LiveEventBus
import com.samiu.base.ui.BaseVMFragment
import com.samiu.host.R
import com.samiu.host.global.HOME_PAGE
import com.samiu.host.global.toBrowser
import com.samiu.host.ui.adapter.WanBannerAdapter
import com.samiu.host.model.bean.wan.Banner
import com.samiu.host.ui.adapter.WanArticleAdapter
import com.samiu.host.viewmodel.wan.WanHomeViewModel
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.fragment_wan_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/3/2
 */
class WanHomeFragment : BaseVMFragment<WanHomeViewModel>() {
    override fun getLayoutResId() = R.layout.fragment_wan_home

    private var currentPage by Delegates.notNull<Int>()
    private val mViewModelWan: WanHomeViewModel by viewModel()
    private lateinit var adapter: WanArticleAdapter

    override fun initView() {
        initRecyclerView()
        LiveEventBus    //refresh data
            .get(HOME_PAGE, Int::class.java)
            .observe(this, Observer { refreshData(it) })
    }

    override fun initData() {
        mViewModelWan.run {
            getBanners()
        }
        refreshData(-1)
    }

    private fun refreshData(type: Int) {
        when (type) {
            -1 -> { //onRefresh
                currentPage = 0
                adapter.clearAll()
                mViewModelWan.getArticles(currentPage)
            }
            1 -> {  //onLoadMore
                currentPage += 1
                mViewModelWan.getArticles(currentPage)
            }
        }
    }

    override fun startObserve() = mViewModelWan.run {
        mBanners.observe(this@WanHomeFragment, Observer { setBanner(it) })
        mArticles.observe(this@WanHomeFragment, Observer { adapter.addAll(it) })
    }

    private fun initRecyclerView() {
        adapter = WanArticleAdapter(context)
        home_recycler_view.layoutManager = LinearLayoutManager(context)
        home_recycler_view.adapter = adapter
        adapter.setOnItemClick { url -> toBrowser(this, url) }
    }

    private fun setBanner(bannerList: List<Banner>) {
        banner.adapter = WanBannerAdapter(bannerList)
        banner.setOnBannerListener(object : OnBannerListener<Banner> {
            override fun onBannerChanged(position: Int) = Unit
            override fun OnBannerClick(data: Banner?, position: Int) {
                data?.url?.let {
                    toBrowser(this@WanHomeFragment, it)
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        banner.start()
    }

    override fun onStop() {
        super.onStop()
        banner.stop()
    }
}