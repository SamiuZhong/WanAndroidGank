package com.samiu.host.ui.fragment.gank

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeremyliao.liveeventbus.LiveEventBus
import com.samiu.base.ui.BaseVMFragment
import com.samiu.host.R
import com.samiu.host.global.FRONT
import com.samiu.host.global.LOAD_MORE
import com.samiu.host.global.REFRESH
import com.samiu.host.global.toBrowser
import com.samiu.host.ui.adapter.GankAdapter
import com.samiu.host.viewmodel.gank.GankFrontViewModel
import kotlinx.android.synthetic.main.fragment_gank_front.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/3/2
 */
class GankFrontFragment : BaseVMFragment<GankFrontViewModel>() {
    override fun getLayoutResId() = R.layout.fragment_gank_front

    override fun initData() = refreshData(REFRESH)

    private var currentPage by Delegates.notNull<Int>()
    private val mViewModel: GankFrontViewModel by viewModel()
    private lateinit var adapter: GankAdapter

    override fun initView() {
        initRecyclerView()
        LiveEventBus
            .get(FRONT, Int::class.java)
            .observe(this, Observer { refreshData(it) })
    }

    private fun refreshData(type: Int) {
        when (type) {
            REFRESH -> {
                currentPage = 0
                adapter.clearAll()
                mViewModel.getFront(currentPage)
            }
            LOAD_MORE -> {
                currentPage += 1
                mViewModel.getFront(currentPage)
            }
        }
    }

    override fun startObserve() = mViewModel.run {
        mFrontData.observe(this@GankFrontFragment, Observer { adapter.addAll(it) })
    }

    private fun initRecyclerView() {
        adapter = GankAdapter(context)
        gank_front_rv.layoutManager = LinearLayoutManager(context)
        gank_front_rv.adapter = adapter
        adapter.setOnItemClick { url -> toBrowser(this, url) }
    }
}