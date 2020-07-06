package com.samiu.host.ui.base

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import com.samiu.base.ui.BaseActivity
import com.samiu.base.ui.viewBinding
import com.samiu.host.databinding.ActivitySystemDisplayBinding
import com.samiu.host.global.CID
import com.samiu.host.global.TITLE
import com.samiu.host.ui.adapter.ArticleListenerImpl
import com.samiu.host.ui.adapter.ReboundingSwipeActionCallback
import com.samiu.host.ui.adapter.WanArticleAdapter
import com.samiu.host.viewmodel.SystemDisplayViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/3/6
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class SystemDisplayActivity : BaseActivity() {

    private val binding by viewBinding(ActivitySystemDisplayBinding::inflate)
    private val viewModel: SystemDisplayViewModel by viewModel()
    override fun getBindingRoot() = binding.root

    private var currentPage by Delegates.notNull<Int>()
    private var cid by Delegates.notNull<Int>()
    private lateinit var mAdapter: WanArticleAdapter

    override fun initView() {
        cid = intent.getIntExtra(CID, 0)
        binding.title.text = intent.getStringExtra(TITLE)
        binding.backIcon.setOnClickListener { finish() }
        initAdapter()
        initRefresh()
    }

    override fun initData() {
        currentPage = 0
        viewModel.getSystemArticles(currentPage, cid)
    }

    override fun startObserve() = viewModel.run {
        mSystemArticles.observe(this@SystemDisplayActivity, Observer { mAdapter.addAll(it) })
    }

    private fun initAdapter() {
        mAdapter = WanArticleAdapter(ArticleListenerImpl(this))
        binding.recyclerView.apply {
            val itemTouchHelper = ItemTouchHelper(ReboundingSwipeActionCallback())
            itemTouchHelper.attachToRecyclerView(this)
            adapter = mAdapter
        }
    }

    private fun initRefresh() {
        with(binding.recyclerRefresh) {
            setOnRefreshListener {
                currentPage = 0
                mAdapter.clearAll()
                viewModel.getSystemArticles(currentPage, cid)
                finishRefresh(1500)
            }
            setOnLoadMoreListener {
                currentPage += 1
                viewModel.getSystemArticles(currentPage, cid)
                finishLoadMore(2000)
            }
        }
    }
}