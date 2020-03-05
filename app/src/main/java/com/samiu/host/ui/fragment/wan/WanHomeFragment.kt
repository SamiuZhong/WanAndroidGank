package com.samiu.host.ui.fragment.wan

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeremyliao.liveeventbus.LiveEventBus
import com.samiu.base.ui.BaseVMFragment
import com.samiu.base.view.SpaceItemDecoration
import com.samiu.host.MainActivity
import com.samiu.host.R
import com.samiu.host.global.HOME_PAGE
import com.samiu.host.global.TAB_HEIGHT
import com.samiu.host.global.toBrowser
import com.samiu.host.ui.adapter.ImageBannerAdapter
import com.samiu.host.model.bean.wan.Banner
import com.samiu.host.ui.adapter.WanHomeAdapter
import com.samiu.host.ui.viewmodel.wan.HomeViewModel
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.fragment_wan_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/3/2
 */
class WanHomeFragment : BaseVMFragment<HomeViewModel>() {
    override fun getLayoutResId() = R.layout.fragment_wan_home

    private var currentPage by Delegates.notNull<Int>()
    private val mViewModel: HomeViewModel by viewModel()
    private lateinit var adapter: WanHomeAdapter

    override fun initView() {
        initRecyclerView()
        LiveEventBus    //refresh data
            .get(HOME_PAGE, Int::class.java)
            .observe(this, Observer { refreshData(it) })
    }

    override fun initData() = refreshData(-1)

    private fun refreshData(type: Int) {
        when (type) {
            -1 -> { //onRefresh
                currentPage = 0
                adapter.clearAll()
                mViewModel.getArticles(currentPage)
            }
            1 -> {  //onLoadMore
                currentPage += 1
                mViewModel.getArticles(currentPage)
            }
        }
    }

    override fun startObserve() {
        mViewModel.run {
            mBanners.observe(this@WanHomeFragment, Observer { setBanner(it) })
            mArticles.observe(this@WanHomeFragment, Observer {
                adapter.addAll(it)
            })
        }
    }

    private fun initRecyclerView() {
        adapter = WanHomeAdapter(context)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    private fun setBanner(bannerList: List<Banner>) {
        banner.adapter = ImageBannerAdapter(bannerList)
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