package com.samiu.host.ui.base

import androidx.lifecycle.Observer
import com.samiu.base.ui.BaseActivity
import com.samiu.base.ui.viewBinding
import com.samiu.host.databinding.ActivitySystemDisplayBinding
import com.samiu.host.global.CID
import com.samiu.host.global.TITLE
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
    private val mBinding by viewBinding(ActivitySystemDisplayBinding::inflate)
    override fun getBindingRoot() = mBinding.root
    private val viewModel: SystemDisplayViewModel by viewModel()

    private var currentPage by Delegates.notNull<Int>()
    private var cid by Delegates.notNull<Int>()
    private lateinit var mAdapter: WanArticleAdapter

    override fun initView() {
        cid = intent.getIntExtra(CID, 0)
        mBinding.run {
            //toolbar
            title.text = intent.getStringExtra(TITLE)
            backIcon.setOnClickListener { finish() }
            //recycler
            initRecyclerView()
            //refresh
            with(mBinding.recyclerRefresh) {
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

    override fun initData() {
        currentPage = 0
        viewModel.getSystemArticles(currentPage, cid)
    }

    override fun startObserve() = viewModel.run {
        mSystemArticles.observe(this@SystemDisplayActivity, Observer { mAdapter.addAll(it) })
    }

    private fun initRecyclerView() {
        mAdapter = WanArticleAdapter(this@SystemDisplayActivity)
        mBinding.recyclerView.adapter = mAdapter
    }
}