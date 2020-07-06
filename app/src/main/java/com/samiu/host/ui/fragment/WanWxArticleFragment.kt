package com.samiu.host.ui.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.samiu.base.ui.BaseFragment
import com.samiu.base.ui.viewBinding
import com.samiu.host.R
import com.samiu.host.databinding.FragmentWanWxArticleBinding
import com.samiu.host.global.LOAD_MORE
import com.samiu.host.global.REFRESH
import com.samiu.host.ui.adapter.ArticleListenerImpl
import com.samiu.host.ui.adapter.ReboundingSwipeActionCallback
import com.samiu.host.ui.adapter.WanArticleAdapter
import com.samiu.host.ui.adapter.WanTitleAdapter
import com.samiu.host.viewmodel.WanWxViewModel
import kotlinx.android.synthetic.main.fragment_wan_wx_article.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/3/2
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanWxArticleFragment : BaseFragment(R.layout.fragment_wan_wx_article) {
    private val binding by viewBinding(FragmentWanWxArticleBinding::bind)
    private val viewModel: WanWxViewModel by viewModel()

    private var currentPage by Delegates.notNull<Int>()
    private var mId by Delegates.notNull<Int>()
    private lateinit var mTitleAdapter: WanTitleAdapter
    private lateinit var mArticleAdapter: WanArticleAdapter

    override fun initView() {
        initAdapter()
        initRefresh()
    }

    override fun initData() {
        viewModel.getAccounts()
    }

    override fun startObserve() = viewModel.run {
        mAccounts.observe(this@WanWxArticleFragment, Observer {
            mTitleAdapter.replaceAll(it.also {
                mId = it[0].id
                refreshData(REFRESH)
                it[0].isSelected = true
            })
        })
        mArticles.observe(this@WanWxArticleFragment, Observer { mArticleAdapter.addAll(it) })
    }

    private fun initAdapter() {
        //left recycler
        mTitleAdapter = WanTitleAdapter(context)
        binding.wxRecycler1.adapter = mTitleAdapter
        mTitleAdapter.setOnItemClick { id ->
            run {
                mId = id
                refreshData(REFRESH)
            }
        }
        //right recycler
        mArticleAdapter = WanArticleAdapter(ArticleListenerImpl(requireContext()))
        binding.wxRecycler2.apply {
            val itemTouchHelper = ItemTouchHelper(ReboundingSwipeActionCallback())
            itemTouchHelper.attachToRecyclerView(this)
            adapter = mArticleAdapter
        }
    }

    private fun initRefresh() {
        with(binding.wxRefresh) {
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
                currentPage = 0
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