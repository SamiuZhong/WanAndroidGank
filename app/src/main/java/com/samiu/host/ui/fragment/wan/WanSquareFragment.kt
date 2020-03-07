package com.samiu.host.ui.fragment.wan

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeremyliao.liveeventbus.LiveEventBus
import com.samiu.base.ui.BaseVMFragment
import com.samiu.host.R
import com.samiu.host.global.LOAD_MORE
import com.samiu.host.global.REFRESH
import com.samiu.host.global.SQUARE
import com.samiu.host.global.toBrowser
import com.samiu.host.ui.adapter.WanArticleAdapter
import com.samiu.host.viewmodel.wan.WanSquareViewModel
import kotlinx.android.synthetic.main.fragment_wan_square.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/3/2
 */
class WanSquareFragment : BaseVMFragment<WanSquareViewModel>() {
    override fun getLayoutResId() = R.layout.fragment_wan_square
    override fun initData() = refreshData(-1)

    private var currentPage by Delegates.notNull<Int>()
    private val mViewModel: WanSquareViewModel by viewModel()
    private lateinit var adapter: WanArticleAdapter

    override fun initView() {
        initRecyclerView()
        LiveEventBus    //refresh data
            .get(SQUARE, Int::class.java)
            .observe(this, Observer { refreshData(it) })
    }

    private fun refreshData(type: Int) {
        when (type) {
            REFRESH -> {
                currentPage = 0
                adapter.clearAll()
                mViewModel.getArticles(currentPage)
            }
            LOAD_MORE -> {
                currentPage += 1
                mViewModel.getArticles(currentPage)
            }
        }
    }

    override fun startObserve() = mViewModel.run {
        mArticles.observe(this@WanSquareFragment, Observer { adapter.addAll(it) })
    }

    private fun initRecyclerView() {
        adapter = WanArticleAdapter(context)
        square_recycler_view.layoutManager = LinearLayoutManager(context)
        square_recycler_view.adapter = adapter
        adapter.setOnItemClick { url -> toBrowser(this, url) }
    }
}