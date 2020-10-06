package com.samiu.wangank.ui.search

import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import com.samiu.base.ui.BaseActivity
import com.samiu.base.ui.viewBinding
import com.samiu.wangank.R
import com.samiu.wangank.databinding.ActivityWanSearchBinding
import com.samiu.wangank.global.LOAD_MORE
import com.samiu.wangank.global.REFRESH
import com.samiu.wangank.bean.Hot
import com.samiu.wangank.ui.home.adapter.ArticleListenerImpl
import com.samiu.wangank.ui.home.adapter.ReboundingSwipeActionCallback
import com.samiu.wangank.ui.home.adapter.WanArticleAdapter
import com.samiu.wangank.util.drawShape
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/5/21
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class SearchActivity : BaseActivity() {

    private val binding by viewBinding(ActivityWanSearchBinding::inflate)
    private val viewModel: WanSearchViewModel by viewModel()
    override fun getBindingRoot() = binding.root

    private var currentPage by Delegates.notNull<Int>()
    private lateinit var mAdapter: WanArticleAdapter
    private var key = ""

    override fun initView() {
        initAdapter()
        initRefresh()
        //search
        binding.searchLayout.background =
            drawShape(this, R.dimen.corner_20, R.color.white)
        binding.searchIcon.setOnClickListener {
            key = binding.searchEdt.text.toString()
            refreshData(REFRESH)
        }
        //clear
//        binding.searchBackIcon.setOnClickListener { mAdapter.clearAll() }
        //back
        binding.searchBackIcon.setOnClickListener { finish() }
    }

    override fun initData() {
        viewModel.getHotKeys()
    }

    private fun initAdapter() {
        mAdapter = WanArticleAdapter(ArticleListenerImpl(this))
        binding.searchRecycler.apply {
            val itemTouchHelper = ItemTouchHelper(ReboundingSwipeActionCallback())
            itemTouchHelper.attachToRecyclerView(this)
            adapter = mAdapter
        }
    }

    private fun initRefresh() {
        with(binding.searchRefresh) {
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
//        when (type) {
//            REFRESH -> {
//                currentPage = 0
//                mAdapter.clearAll()
//                viewModel.getArticles(currentPage, key)
//            }
//            LOAD_MORE -> {
//                currentPage += 1
//                viewModel.getArticles(currentPage, key)
//            }
//        }
    }

    override fun startObserve() = viewModel.run {
//        mArticles.observe(this@SearchActivity, Observer {
//            if (it.isEmpty())
//                Toast.makeText(this@SearchActivity, R.string.no_search_resut, Toast.LENGTH_SHORT)
//                    .show()
//            else
////                mAdapter.addAll(it)
//        })
        mkeys.observe(this@SearchActivity, Observer { setHotKeys(it) })
    }

    private fun setHotKeys(list: List<Hot>) {
        for (item in list) {
            val textView = TextView(this).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                setPadding(20, 6, 20, 6)

                background = drawShape(
                    this@SearchActivity,
                    R.dimen.corner_20,
                    R.color.white,
                    R.color.reply_blue_700
                )

                text = item.name
                setOnClickListener {
                    key = item.name
                    binding.searchEdt.setText(key)
                    refreshData(REFRESH)
                }
            }
            binding.searchFlow.addView(textView)
        }
    }
}