package com.samiu.host.ui.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeremyliao.liveeventbus.LiveEventBus
import com.samiu.base.ui.BaseFragment
import com.samiu.base.ui.viewBinding
import com.samiu.host.R
import com.samiu.host.databinding.FragmentRecyclerBinding
import com.samiu.host.global.LOAD_MORE
import com.samiu.host.global.REFRESH
import com.samiu.host.global.SQUARE
import com.samiu.host.global.toBrowser
import com.samiu.host.ui.adapter.WanArticleAdapter
import com.samiu.host.viewmodel.WanSquareViewModel
import kotlinx.android.synthetic.main.fragment_wan_square.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/3/2
 */
class WanSquareFragment : BaseFragment(R.layout.fragment_recycler) {
    private val binding by viewBinding(FragmentRecyclerBinding::bind)
    private val squareViewModel: WanSquareViewModel by viewModel()

    private var mCurrentPage by Delegates.notNull<Int>()
    private lateinit var mAdapter: WanArticleAdapter

    override fun initView() {
        initRecyclerView()
        with(binding.refreshLayout) {
            setOnRefreshListener {
                mCurrentPage = 0
                mAdapter.clearAll()
                squareViewModel.getArticles(mCurrentPage)
                finishRefresh(1000)
            }
            setOnLoadMoreListener {
                mCurrentPage += 1
                squareViewModel.getArticles(mCurrentPage)
                finishLoadMore(1000)
            }
        }
    }

    override fun initData() {
        binding.refreshLayout.autoRefresh()
    }

    override fun startObserve() = squareViewModel.run {
        mArticles.observe(this@WanSquareFragment, Observer { mAdapter.addAll(it) })
    }

    private fun initRecyclerView() {
        mAdapter = WanArticleAdapter(context)
        binding.recycler.adapter = mAdapter
        mAdapter.setOnItemClick { url -> toBrowser(this, url) }
    }
}