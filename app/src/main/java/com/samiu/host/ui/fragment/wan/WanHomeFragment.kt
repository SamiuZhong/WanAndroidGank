package com.samiu.host.ui.fragment.wan

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeremyliao.liveeventbus.LiveEventBus
import com.samiu.base.ui.BaseVMFragment
import com.samiu.base.ui.viewBinding
import com.samiu.host.R
import com.samiu.host.databinding.FragmentWanHomeBinding
import com.samiu.host.global.HOME_PAGE
import com.samiu.host.global.LOAD_MORE
import com.samiu.host.global.REFRESH
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
class WanHomeFragment : BaseVMFragment<WanHomeViewModel>(R.layout.fragment_wan_home) {
    private val binding by viewBinding(FragmentWanHomeBinding::bind)

    private var currentPage by Delegates.notNull<Int>()
    private val mViewModel: WanHomeViewModel by viewModel()
    private lateinit var mAdapter: WanArticleAdapter

    override fun initView() {
        initRecyclerView()
        with(binding.refreshLayout) {
            setOnRefreshListener {
                currentPage = 0
                mAdapter.clearAll()
                mViewModel.getArticles(currentPage)
                finishRefresh(1000)
            }
            setOnLoadMoreListener {
                currentPage += 1
                mViewModel.getArticles(currentPage)
                finishLoadMore(1000)
            }
        }
    }

    override fun initData() {
        mViewModel.run { getBanners() }
        binding.refreshLayout.autoRefresh()
    }

    override fun startObserve() = mViewModel.run {
        mBanners.observe(this@WanHomeFragment, Observer { setBanner(it) })
        mArticles.observe(this@WanHomeFragment, Observer { mAdapter.addAll(it) })
    }

    private fun initRecyclerView() {
        mAdapter = WanArticleAdapter(context)
        with(binding.homeRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
        mAdapter.setOnItemClick { url -> toBrowser(this, url) }
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