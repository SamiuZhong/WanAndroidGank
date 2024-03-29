package com.samiu.wangank.ui.system

import androidx.recyclerview.widget.ItemTouchHelper
import com.samiu.base.ui.BaseActivity
import com.samiu.base.ui.viewBinding
import com.samiu.wangank.databinding.ActivitySystemDisplayBinding
import com.samiu.wangank.global.CID
import com.samiu.wangank.global.TITLE
import com.samiu.wangank.ui.home.adapter.ReboundingSwipeActionCallback
import com.samiu.wangank.ui.wxpub.adapter.WxArticleAdapter
import com.samiu.wangank.ui.wxpub.adapter.WxArticleListenerImpl
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * 体系列表页
 *
 * @author Samiu 2020/3/6
 * @email samiuzhong@outlook.com
 */
class SystemDisplayActivity : BaseActivity() {

    private val binding by viewBinding(ActivitySystemDisplayBinding::inflate)
    private val viewModel: SystemDisplayViewModel by viewModel()
    override fun getBindingRoot() = binding.root

    private var currentPage by Delegates.notNull<Int>()
    private var cid by Delegates.notNull<Int>()
    private lateinit var mAdapter: WxArticleAdapter

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
        mSystemArticles.observe(this@SystemDisplayActivity) { mAdapter.addAll(it) }
    }

    private fun initAdapter() {
        mAdapter = WxArticleAdapter(WxArticleListenerImpl(this))
        binding.recyclerView.apply {
            val itemTouchHelper = ItemTouchHelper(ReboundingSwipeActionCallback())
            itemTouchHelper.attachToRecyclerView(this)
            adapter = mAdapter
        }
    }

    private fun initRefresh() {
        with(binding.recyclerRefresh) {
            setEnableRefresh(false)
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