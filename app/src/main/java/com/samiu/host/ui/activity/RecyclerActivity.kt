package com.samiu.host.ui.activity

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.samiu.base.ui.BaseVMActivity
import com.samiu.host.R
import com.samiu.host.global.CID
import com.samiu.host.global.TITLE
import com.samiu.host.global.toBrowser
import com.samiu.host.ui.adapter.WanArticleAdapter
import com.samiu.host.viewmodel.wan.RecyclerViewModel
import kotlinx.android.synthetic.main.activity_recycler.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/3/6
 */
class RecyclerActivity : BaseVMActivity<RecyclerViewModel>() {
    override fun getLayoutResId() = R.layout.activity_recycler

    private var currentPage by Delegates.notNull<Int>()
    private var cid by Delegates.notNull<Int>()
    private val mViewModel: RecyclerViewModel by viewModel()
    private lateinit var adapter: WanArticleAdapter

    override fun initView() {
        cid = intent.getIntExtra(CID, 0)
        //toolbar
        toolbar_title.text = intent.getStringExtra(TITLE)!!
        back_icon.setOnClickListener { finish() }
        //recycler
        initRecyclerView()
        //refresh
        with(recycler_refresh) {
            setOnRefreshListener {
                currentPage = 0
                adapter.clearAll()
                mViewModel.getSystemArticles(currentPage, cid)
                finishRefresh(1500)
            }
            setOnLoadMoreListener {
                currentPage += 1
                mViewModel.getSystemArticles(currentPage, cid)
                finishLoadMore(2000)
            }
        }
    }

    override fun initData() {
        currentPage = 0
        mViewModel.getSystemArticles(currentPage, cid)
    }

    private fun initRecyclerView() {
        adapter = WanArticleAdapter(this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
        adapter.setOnItemClick { url -> toBrowser(this, url) }
    }

    override fun startObserve() = mViewModel.run {
        mSystemArticles.observe(this@RecyclerActivity, Observer { adapter.addAll(it) })
    }
}