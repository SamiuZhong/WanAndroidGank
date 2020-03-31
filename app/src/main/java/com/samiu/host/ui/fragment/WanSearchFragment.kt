package com.samiu.host.ui.fragment

import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.samiu.base.ui.BaseVMFragment
import com.samiu.base.ui.viewBinding
import com.samiu.host.R
import com.samiu.host.databinding.FragmentWanSearchBinding
import com.samiu.host.global.LOAD_MORE
import com.samiu.host.global.REFRESH
import com.samiu.host.global.toBrowser
import com.samiu.host.model.bean.Hot
import com.samiu.host.ui.adapter.WanArticleAdapter
import com.samiu.host.viewmodel.WanSearchViewModel
import kotlinx.android.synthetic.main.fragment_wan_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/3/4
 */
class WanSearchFragment : BaseVMFragment<WanSearchViewModel>(R.layout.fragment_wan_search) {
    private val binding by viewBinding(FragmentWanSearchBinding::bind)

    private var currentPage by Delegates.notNull<Int>()
    private val mViewModel: WanSearchViewModel by viewModel()
    private lateinit var adapter: WanArticleAdapter
    private var key = ""

    override fun initView() {
        initRecyclerView()
        //search
        search_icon.setOnClickListener {
            key = search_edt.text.toString()
            refreshData(REFRESH)
        }
        //refresh layout
        with(search_refresh) {
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
        search_back_icon.setOnClickListener { adapter.clearAll() }
        //listen keyBoard
//        if (activity is MainActivity)
//            KeyboardStateObserver.getKeyboardStateObserver(activity)
//                .setKeyboardVisibilityListener(object :
//                    KeyboardStateObserver.OnKeyboardVisibilityListener {
//                    override fun onKeyboardShow() {
//                        (activity as MainActivity).showBottomNav(show = false, atOnce = true)
//                    }
//
//                    override fun onKeyboardHide() {
//                        (activity as MainActivity).showBottomNav(show = true, atOnce = false)
//                    }
//                })
    }

    override fun initData() {
        mViewModel.getHotKeys()
    }

    private fun refreshData(type: Int) {
        when (type) {
            REFRESH -> {
                currentPage = 0
                adapter.clearAll()
                mViewModel.getArticles(currentPage, key)
            }
            LOAD_MORE -> {
                currentPage += 1
                mViewModel.getArticles(currentPage, key)
            }
        }
    }

    override fun startObserve() = mViewModel.run {
        mArticles.observe(this@WanSearchFragment, Observer {
            if (it.isEmpty())
                Toast.makeText(context, R.string.no_search_resut, Toast.LENGTH_SHORT).show()
            else
                adapter.addAll(it)
        })
        mkeys.observe(this@WanSearchFragment, Observer { setHotKeys(it) })
    }

    private fun setHotKeys(list: List<Hot>) {
        for (item in list) {
            val textView = TextView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                setPadding(10, 4, 10, 4)
                background =
                    context?.resources?.getDrawable(R.drawable.shape_100_line_3066be, null)
                text = item.name
                setOnClickListener {
                    key = item.name
                    refreshData(REFRESH)
                }
            }
            search_flow.addView(textView)
        }
    }

    private fun initRecyclerView() {
        adapter = WanArticleAdapter(context)
        search_recycler.layoutManager = LinearLayoutManager(context)
        search_recycler.adapter = adapter
        adapter.setOnItemClick { url -> toBrowser(this, url) }
    }
}