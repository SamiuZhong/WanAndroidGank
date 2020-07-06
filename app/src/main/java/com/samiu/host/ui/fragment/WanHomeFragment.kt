package com.samiu.host.ui.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import com.samiu.base.ui.BaseFragment
import com.samiu.base.ui.viewBinding
import com.samiu.host.R
import com.samiu.host.databinding.FragmentWanHomeBinding
import com.samiu.host.global.toBrowser
import com.samiu.host.model.bean.Banner
import com.samiu.host.ui.adapter.ArticleListenerImpl
import com.samiu.host.ui.adapter.ReboundingSwipeActionCallback
import com.samiu.host.ui.adapter.WanArticleAdapter
import com.samiu.host.ui.adapter.WanBannerAdapter
import com.samiu.host.viewmodel.WanHomeViewModel
import com.youth.banner.listener.OnBannerListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/3/2
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanHomeFragment : BaseFragment(R.layout.fragment_wan_home) {

    private val binding by viewBinding(FragmentWanHomeBinding::bind)
    private val viewModel: WanHomeViewModel by viewModel()

    private var mCurrentPage by Delegates.notNull<Int>()
    private lateinit var mAdapter: WanArticleAdapter

    override fun initView() {
        initAdapter()
        with(binding.refreshLayout) {
            setOnRefreshListener {
                mCurrentPage = 0
                mAdapter.clearAll()
                viewModel.getArticles(mCurrentPage)
                finishRefresh(1000)
            }
            setOnLoadMoreListener {
                mCurrentPage += 1
                viewModel.getArticles(mCurrentPage)
                finishLoadMore(1000)
            }
        }
    }

    override fun initData() {
        viewModel.run { getBanners() }
        binding.refreshLayout.autoRefresh()
    }

    override fun startObserve() = viewModel.run {
        mBanners.observe(this@WanHomeFragment, Observer { setBanner(it) })
        mArticles.observe(this@WanHomeFragment, Observer { mAdapter.addAll(it) })
    }

    private fun initAdapter() {
        mAdapter = WanArticleAdapter(ArticleListenerImpl(requireContext()))
        binding.recycler.apply {
            val itemTouchHelper = ItemTouchHelper(ReboundingSwipeActionCallback())
            itemTouchHelper.attachToRecyclerView(this)
            adapter = mAdapter
        }
    }

    private fun setBanner(bannerList: List<Banner>) {
        binding.banner.adapter = WanBannerAdapter(bannerList)
        binding.banner.setOnBannerListener(object : OnBannerListener<Banner> {
            override fun onBannerChanged(position: Int) = Unit
            override fun OnBannerClick(data: Banner?, position: Int) {
                data?.let {
                    requireContext().toBrowser(it.url, it.title)
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