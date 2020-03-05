package com.samiu.host.ui.fragment.wan

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeremyliao.liveeventbus.LiveEventBus
import com.samiu.base.ui.BaseVMFragment
import com.samiu.host.R
import com.samiu.host.global.SQUARE
import com.samiu.host.global.toBrowser
import com.samiu.host.ui.adapter.WanHomeAdapter
import com.samiu.host.viewmodel.wan.SquareViewModel
import kotlinx.android.synthetic.main.fragment_wan_square.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/3/2
 */
class WanSquareFragment:BaseVMFragment<SquareViewModel>() {
    override fun getLayoutResId() = R.layout.fragment_wan_square

    private var currentPage by Delegates.notNull<Int>()
    private val mViewModel: SquareViewModel by viewModel()
    private lateinit var adapter:WanHomeAdapter

    override fun initView(){
        initRecyclerView()
        LiveEventBus    //refresh data
            .get(SQUARE, Int::class.java)
            .observe(this, Observer { refreshData(it) })
    }

    override fun initData() = refreshData(-1)

    private fun refreshData(type: Int) {
        when (type) {
            -1 -> { //onRefresh
                currentPage = 0
                adapter.clearAll()
                mViewModel.getArticles(currentPage)
            }
            1 -> {  //onLoadMore
                currentPage += 1
                mViewModel.getArticles(currentPage)
            }
        }
    }

    override fun startObserve() {
        mViewModel.run {
            mArticles.observe(this@WanSquareFragment, Observer { adapter.addAll(it) })
        }
    }

    private fun initRecyclerView(){
        adapter = WanHomeAdapter(context)
        square_recycler_view.layoutManager = LinearLayoutManager(context)
        square_recycler_view.adapter = adapter
        adapter.setOnItemClick { url -> toBrowser(this, url) }
    }
}