package com.samiu.host.ui.fragment

import androidx.lifecycle.Observer
import com.samiu.base.ui.BaseFragment
import com.samiu.base.ui.viewBinding
import com.samiu.host.R
import com.samiu.host.databinding.FragmentRecyclerBinding
import com.samiu.host.global.toBrowserFragment
import com.samiu.host.ui.adapter.WanArticleAdapter
import com.samiu.host.viewmodel.WanSquareViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/3/2
 */
class WanSquareFragment : BaseFragment(R.layout.fragment_recycler) {
    private val mBinding by viewBinding(FragmentRecyclerBinding::bind)
    private val viewModel: WanSquareViewModel by viewModel()

    private var mCurrentPage by Delegates.notNull<Int>()
    private lateinit var mAdapter: WanArticleAdapter

    override fun initView() {
        initRecyclerView()
        with(mBinding.refreshLayout) {
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
        mBinding.refreshLayout.autoRefresh()
    }

    override fun startObserve() = viewModel.run {
        mArticles.observe(this@WanSquareFragment, Observer { mAdapter.addAll(it) })
    }

    private fun initRecyclerView() {
        mAdapter = WanArticleAdapter(requireContext())
        mBinding.recycler.adapter = mAdapter
        mAdapter.setOnItemClick { url -> toBrowserFragment(this, url) }
    }
}