package com.samiu.host.ui.base

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.samiu.base.ui.BaseActivity
import com.samiu.base.ui.viewBinding
import com.samiu.host.databinding.ActivityRecyclerBinding
import com.samiu.host.global.CID
import com.samiu.host.global.TITLE
import com.samiu.host.global.toBrowser
import com.samiu.host.ui.adapter.WanArticleAdapter
import com.samiu.host.viewmodel.RecyclerViewModel
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/3/6
 */
class RecyclerActivity : BaseActivity() {
    private val binding by viewBinding (ActivityRecyclerBinding::inflate)
    override fun getBindingRoot() = binding.root
    private val recyclerViewModel: RecyclerViewModel by viewModel()

    private var currentPage by Delegates.notNull<Int>()
    private var cid by Delegates.notNull<Int>()
    private lateinit var adapter: WanArticleAdapter

    override fun initView() {
        cid = intent.getIntExtra(CID, 0)
        //toolbar
        toolbar_title.text = intent.getStringExtra(TITLE)!!
        back_icon.setOnClickListener { finish() }
        //recycler
        initRecyclerView()
        //refresh
        with(binding.recyclerRefresh) {
            setOnRefreshListener {
                currentPage = 0
                adapter.clearAll()
                recyclerViewModel.getSystemArticles(currentPage, cid)
                finishRefresh(1500)
            }
            setOnLoadMoreListener {
                currentPage += 1
                recyclerViewModel.getSystemArticles(currentPage, cid)
                finishLoadMore(2000)
            }
        }
    }

    override fun initData() {
        currentPage = 0
        recyclerViewModel.getSystemArticles(currentPage, cid)
    }

    private fun initRecyclerView() {
        adapter = WanArticleAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        adapter.setOnItemClick { url -> toBrowser(this, url) }
    }

    override fun startObserve() = recyclerViewModel.run {
        mSystemArticles.observe(this@RecyclerActivity, Observer { adapter.addAll(it) })
    }
}