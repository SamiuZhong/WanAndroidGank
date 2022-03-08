package com.samiu.wangank.ui.home

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ScrollView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ScrollingView
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
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
 * @email samiuzhong@outlook.com
 */
class WanHomeFragment : BaseFragment(R.layout.fragment_wan_home) {

    private val binding by viewBinding(FragmentWanHomeBinding::bind)
    private val viewModel: WanHomeViewModel by viewModel()

    private var mScrollState: Int = 0
    private lateinit var mAdapter: WanArticleAdapter
    private lateinit var scrollListener: HomeScrollListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            scrollListener = context as HomeScrollListener
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    override fun initView() {
        mAdapter = WanArticleAdapter(ArticleListenerImpl(requireContext()))
        binding.recycler.apply {
            val itemTouchHelper = ItemTouchHelper(ReboundingSwipeActionCallback())
            itemTouchHelper.attachToRecyclerView(this)
            adapter = mAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    mScrollState = newState
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (RecyclerView.SCROLL_STATE_DRAGGING == mScrollState) {
                        if (dy > 0)
                            scrollListener.onHomeScrolled(SCROLL_UP)
                        else if (dy < 0)
                            scrollListener.onHomeScrolled(SCROLL_DOWN)
                    }
                }
            })
        }
    }

    override fun initData() {
        viewModel.getBanners()
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.articlePagingList.collectLatest {
                mAdapter.submitData(it)
            }
        }
    }

    override fun startObserve() = viewModel.run {
        mBanners.observe(this@WanHomeFragment, Observer { setBanner(it) })
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