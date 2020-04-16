package com.samiu.host.ui.fragment

import androidx.lifecycle.Observer
import com.samiu.base.ui.BaseFragment
import com.samiu.base.ui.viewBinding
import com.samiu.host.R
import com.samiu.host.databinding.FragmentWanHomeBinding
import com.samiu.host.global.toBrowser
import com.samiu.host.model.bean.Banner
import com.samiu.host.ui.adapter.WanArticleAdapter
import com.samiu.host.ui.adapter.WanBannerAdapter
import com.samiu.host.viewmodel.WanHomeViewModel
import com.youth.banner.listener.OnBannerListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/3/2
 */
class WanHomeFragment : BaseFragment(R.layout.fragment_wan_home) {
    private val binding by viewBinding(FragmentWanHomeBinding::bind)
    private val homeViewModel: WanHomeViewModel by viewModel()

    private var mCurrentPage by Delegates.notNull<Int>()
    private lateinit var mAdapter: WanArticleAdapter

    override fun initView() {
        initRecyclerView()
        with(binding.refreshLayout) {
            setOnRefreshListener {
                mCurrentPage = 0
                mAdapter.clearAll()
                homeViewModel.getArticles(mCurrentPage)
                finishRefresh(1000)
            }
            setOnLoadMoreListener {
                mCurrentPage += 1
                homeViewModel.getArticles(mCurrentPage)
                finishLoadMore(1000)
            }
        }
    }

    override fun initData() {
        homeViewModel.run { getBanners() }
        binding.refreshLayout.autoRefresh()
    }

    override fun startObserve() = homeViewModel.run {
        mBanners.observe(this@WanHomeFragment, Observer { setBanner(it) })
        mArticles.observe(this@WanHomeFragment, Observer { mAdapter.addAll(it) })
    }

    private fun initRecyclerView() {
        mAdapter = WanArticleAdapter(context)
        binding.recycler.adapter = mAdapter
        mAdapter.setOnItemClick { url -> toBrowser(this, url) }
    }

    private fun setBanner(bannerList: List<Banner>) {
        binding.banner.adapter = WanBannerAdapter(bannerList)
        binding.banner.setOnBannerListener(object : OnBannerListener<Banner> {
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
        binding.banner.start()
    }

    override fun onStop() {
        super.onStop()
        binding.banner.stop()
    }
}