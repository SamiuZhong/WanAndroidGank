package com.samiu.host.ui.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import com.samiu.base.ui.BaseFragment
import com.samiu.base.ui.viewBinding
import com.samiu.host.R
import com.samiu.host.databinding.FragmentRecyclerBinding
import com.samiu.host.ui.adapter.ArticleListenerImpl
import com.samiu.host.ui.adapter.ReboundingSwipeActionCallback
import com.samiu.host.ui.adapter.WanArticleAdapter
import com.samiu.host.viewmodel.WanSquareViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/3/2
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanSquareFragment : BaseFragment(R.layout.fragment_recycler) {
    private val binding by viewBinding(FragmentRecyclerBinding::bind)
    private val viewModel: WanSquareViewModel by viewModel()

    private var mCurrentPage by Delegates.notNull<Int>()
    private lateinit var mAdapter: WanArticleAdapter

    override fun initView() {
        initAdapter()
        initRefresh()
    }

    override fun initData() {
        binding.refreshLayout.autoRefresh()
    }

    override fun startObserve() = viewModel.run {
        mArticles.observe(this@WanSquareFragment, Observer { mAdapter.addAll(it) })
    }

    private fun initAdapter() {
        mAdapter = WanArticleAdapter(ArticleListenerImpl(requireContext()))
        binding.recycler.apply {
            val itemTouchHelper = ItemTouchHelper(ReboundingSwipeActionCallback())
            itemTouchHelper.attachToRecyclerView(this)
            adapter = mAdapter
        }
    }

    private fun initRefresh() {
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
}