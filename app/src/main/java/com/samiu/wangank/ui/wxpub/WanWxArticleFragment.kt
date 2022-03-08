package com.samiu.wangank.ui.wxpub

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import com.samiu.base.ui.BaseFragment
import com.samiu.base.ui.viewBinding
import com.samiu.wangank.R
import com.samiu.wangank.databinding.FragmentWanWxArticleBinding
import com.samiu.wangank.global.LOAD_MORE
import com.samiu.wangank.global.REFRESH
import com.samiu.wangank.ui.home.adapter.ReboundingSwipeActionCallback
import com.samiu.wangank.ui.wxpub.adapter.WanTitleAdapter
import com.samiu.wangank.ui.wxpub.adapter.WxArticleAdapter
import com.samiu.wangank.ui.wxpub.adapter.WxArticleListenerImpl
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/3/2
 * @email samiuzhong@outlook.com
 */
class WanWxArticleFragment : BaseFragment(R.layout.fragment_wan_wx_article) {
    private val binding by viewBinding(FragmentWanWxArticleBinding::bind)
    private val viewModel: WanWxViewModel by viewModel()

    private var currentPage by Delegates.notNull<Int>()
    private var mId by Delegates.notNull<Int>()
    private lateinit var mTitleAdapter: WanTitleAdapter
    private lateinit var mArticleAdapter: WxArticleAdapter

    override fun initView() {
        initAdapter()
        initRefresh()
    }

    override fun initData() {
        viewModel.getAccounts()
    }

    override fun startObserve() = viewModel.run {
        mAccounts.observe(this@WanWxArticleFragment) {
            mTitleAdapter.replaceAll(it.also {
                mId = it[0].id
                refreshData(REFRESH)
                it[0].isSelected = true
            })
        }
        mArticles.observe(this@WanWxArticleFragment) { mArticleAdapter.addAll(it) }
    }

    private fun initAdapter() {
        //left recycler
        mTitleAdapter = WanTitleAdapter(requireContext())
        binding.wxRecycler1.adapter = mTitleAdapter
        mTitleAdapter.setOnItemClick { id ->
            run {
                mId = id
                refreshData(REFRESH)
            }
        }
        //right recycler
        mArticleAdapter = WxArticleAdapter(WxArticleListenerImpl(requireContext()))
        binding.wxRecycler2.apply {
            val itemTouchHelper = ItemTouchHelper(ReboundingSwipeActionCallback())
            itemTouchHelper.attachToRecyclerView(this)
            adapter = mArticleAdapter
        }
    }

    private fun initRefresh() {
        with(binding.wxRefresh) {
            setEnableRefresh(false)
            setOnRefreshListener {
                refreshData(REFRESH)
                finishRefresh(1500)
            }
            setOnLoadMoreListener {
                refreshData(LOAD_MORE)
                finishLoadMore(2000)
            }
        }
    }

    private fun refreshData(type: Int) {
        when (type) {
            REFRESH -> {
                currentPage = 1
                mArticleAdapter.clearAll()
                viewModel.getArticles(mId, currentPage)
            }
            LOAD_MORE -> {
                currentPage += 1
                viewModel.getArticles(mId, currentPage)
            }
        }
    }
}