package com.samiu.host.ui.fragment.gank

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeremyliao.liveeventbus.LiveEventBus
import com.samiu.base.ui.BaseVMFragment
import com.samiu.host.R
import com.samiu.host.global.IOS
import com.samiu.host.global.LOAD_MORE
import com.samiu.host.global.REFRESH
import com.samiu.host.global.toBrowser
import com.samiu.host.ui.adapter.GankAdapter
import com.samiu.host.viewmodel.gank.GankIosViewModel
import kotlinx.android.synthetic.main.fragment_gank_ios.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/3/2
 */
class GankIosFragment : BaseVMFragment<GankIosViewModel>() {
    override fun getLayoutResId() = R.layout.fragment_gank_ios
    override fun initData() = refreshData(REFRESH)

    private var currentPage by Delegates.notNull<Int>()
    private val mViewModel: GankIosViewModel by viewModel()
    private lateinit var adapter: GankAdapter

    override fun initView() {
        initRecyclerView()
        LiveEventBus
            .get(IOS, Int::class.java)
            .observe(this, Observer { refreshData(it) })
    }


    private fun refreshData(type: Int) {
        when (type) {
            REFRESH -> {
                currentPage = 0
                adapter.clearAll()
                mViewModel.getIos(currentPage)
            }
            LOAD_MORE -> {
                currentPage += 1
                mViewModel.getIos(currentPage)
            }
        }
    }

    override fun startObserve() = mViewModel.run {
        mIosData.observe(this@GankIosFragment, Observer { adapter.addAll(it) })
    }

    private fun initRecyclerView() {
        adapter = GankAdapter(context)
        gank_ios_rv.layoutManager = LinearLayoutManager(context)
        gank_ios_rv.adapter = adapter
        adapter.setOnItemClick { url -> toBrowser(this, url) }
    }
}