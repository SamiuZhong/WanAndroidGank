package com.samiu.wangank.ui.home

import androidx.recyclerview.widget.ItemTouchHelper
import com.samiu.base.ui.BaseFragment
import com.samiu.base.ui.viewBinding
import com.samiu.wangank.R
import com.samiu.wangank.bean.Banner
import com.samiu.wangank.databinding.FragmentWanHomeBinding
import com.samiu.wangank.ui.home.adapter.ArticleListenerImpl
import com.samiu.wangank.ui.home.adapter.ReboundingSwipeActionCallback
import com.samiu.wangank.ui.home.adapter.WanArticleAdapter
import com.samiu.wangank.ui.home.adapter.WanBannerAdapter
import com.samiu.wangank.util.toBrowser
import com.youth.banner.listener.OnBannerListener
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Samiu 2020/3/2
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanHomeFragment : BaseFragment(R.layout.fragment_wan_home) {

    private val binding by viewBinding(FragmentWanHomeBinding::bind)
    private val viewModel: WanHomeViewModel by viewModel()

    private lateinit var mAdapter: WanArticleAdapter

    override fun initView() {
        initAdapter()
    }

    override fun initData() {
        viewModel.getBanners()
    }

    override suspend fun startFlow() {
        viewModel.articles.collectLatest {
            mAdapter.submitData(it)
        }
    }

    override fun startObserve() = viewModel.run {
        mBanners.observe(this@WanHomeFragment, { setBanner(it) })
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