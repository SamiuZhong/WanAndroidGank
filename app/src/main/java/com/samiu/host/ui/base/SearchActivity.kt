package com.samiu.host.ui.base

import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.samiu.base.ui.BaseActivity
import com.samiu.base.ui.viewBinding
import com.samiu.host.R
import com.samiu.host.databinding.ActivityWanSearchBinding
import com.samiu.host.global.LOAD_MORE
import com.samiu.host.global.REFRESH
import com.samiu.host.global.drawShape
import com.samiu.host.model.bean.Hot
import com.samiu.host.ui.adapter.WanArticleAdapter
import com.samiu.host.viewmodel.WanSearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/5/21
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class SearchActivity : BaseActivity() {

    private val mBinding by viewBinding(ActivityWanSearchBinding::inflate)
    private val viewModel: WanSearchViewModel by viewModel()
    override fun getBindingRoot() = mBinding.root

    private var currentPage by Delegates.notNull<Int>()
    private lateinit var mAdapter: WanArticleAdapter
    private var key = ""

    override fun initView() {
        //recycler view
        mAdapter = WanArticleAdapter(this)
        mBinding.searchRecycler.adapter = mAdapter
        //search
        mBinding.searchLayout.background =
            drawShape(this, 100F, getColor(R.color.white))
        mBinding.searchIcon.setOnClickListener {
            key = mBinding.searchEdt.text.toString()
            refreshData(REFRESH)
        }
        //refresh layout
        with(mBinding.searchRefresh) {
            setOnRefreshListener {
                refreshData(REFRESH)
                finishRefresh(1500)
            }
            setOnLoadMoreListener {
                refreshData(LOAD_MORE)
                finishLoadMore(2000)
            }
        }
        //clear
        mBinding.searchBackIcon.setOnClickListener { mAdapter.clearAll() }
        //back
        mBinding.searchBackIcon.setOnClickListener { finish() }
    }

    override fun initData() {
        viewModel.getHotKeys()
    }

    private fun refreshData(type: Int) {
        when (type) {
            REFRESH -> {
                currentPage = 0
                mAdapter.clearAll()
                viewModel.getArticles(currentPage, key)
            }
            LOAD_MORE -> {
                currentPage += 1
                viewModel.getArticles(currentPage, key)
            }
        }
    }

    override fun startObserve() = viewModel.run {
        mArticles.observe(this@SearchActivity, Observer {
            if (it.isEmpty())
                Toast.makeText(this@SearchActivity, R.string.no_search_resut, Toast.LENGTH_SHORT)
                    .show()
            else
                mAdapter.addAll(it)
        })
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
                    100F,
                    getColor(R.color.white),
                    getColor(R.color.reply_blue_700)
                )

                text = item.name
                setOnClickListener {
                    key = item.name
                    mBinding.searchEdt.setText(key)
                    refreshData(REFRESH)
                }
            }
            mBinding.searchFlow.addView(textView)
        }
    }
}