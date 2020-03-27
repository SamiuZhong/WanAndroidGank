package com.samiu.host.ui.fragment.gank

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeremyliao.liveeventbus.LiveEventBus
import com.samiu.base.ui.BaseFragment
import com.samiu.base.ui.BaseVMFragment
import com.samiu.base.ui.viewBinding
import com.samiu.host.R
import com.samiu.host.databinding.FragmentGankExpandBinding
import com.samiu.host.global.*
import com.samiu.host.ui.adapter.GankAdapter
import com.samiu.host.viewmodel.gank.GankAndroidViewModel
import com.samiu.host.viewmodel.gank.GankExpandViewModel
import kotlinx.android.synthetic.main.fragment_gank_android.*
import kotlinx.android.synthetic.main.fragment_gank_expand.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/3/2
 */
class GankExpandFragment:BaseVMFragment<GankExpandViewModel>(R.layout.fragment_gank_expand) {
    private val binding by viewBinding(FragmentGankExpandBinding::bind)
    override fun initData() = refreshData(REFRESH)

    private var currentPage by Delegates.notNull<Int>()
    private val mViewModel: GankExpandViewModel by viewModel()
    private lateinit var adapter: GankAdapter

    override fun initView(){
        initRecyclerView()
        LiveEventBus
            .get(EXPAND,Int::class.java)
            .observe(this, Observer { refreshData(it) })
    }

    private fun refreshData(type: Int) {
        when (type) {
            REFRESH -> {
                currentPage = 0
                adapter.clearAll()
                mViewModel.getExpand(currentPage)
            }
            LOAD_MORE -> {
                currentPage += 1
                mViewModel.getExpand(currentPage)
            }
        }
    }

    override fun startObserve() = mViewModel.run {
        mExpandData.observe(this@GankExpandFragment, Observer { adapter.addAll(it) })
    }

    private fun initRecyclerView(){
        adapter = GankAdapter(context)
        gank_expand_rv.layoutManager = LinearLayoutManager(context)
        gank_expand_rv.adapter = adapter
        adapter.setOnItemClick { url -> toBrowser(this,url) }
    }
}