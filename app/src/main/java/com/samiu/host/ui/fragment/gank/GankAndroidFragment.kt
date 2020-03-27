package com.samiu.host.ui.fragment.gank

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeremyliao.liveeventbus.LiveEventBus
import com.samiu.base.ui.BaseVMFragment
import com.samiu.base.ui.viewBinding
import com.samiu.host.R
import com.samiu.host.databinding.FragmentGankAndroidBinding
import com.samiu.host.global.ANDROID
import com.samiu.host.global.LOAD_MORE
import com.samiu.host.global.REFRESH
import com.samiu.host.global.toBrowser
import com.samiu.host.ui.adapter.GankAdapter
import com.samiu.host.viewmodel.gank.GankAndroidViewModel
import kotlinx.android.synthetic.main.fragment_gank_android.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/3/2
 */
class GankAndroidFragment:BaseVMFragment<GankAndroidViewModel>(R.layout.fragment_gank_android) {
    private val binding by viewBinding (FragmentGankAndroidBinding::bind)
    override fun initData() = refreshData(REFRESH)

    private var currentPage by Delegates.notNull<Int>()
    private val mViewModel:GankAndroidViewModel by viewModel()
    private lateinit var adapter:GankAdapter

    override fun initView(){
        initRecyclerView()
        LiveEventBus
            .get(ANDROID,Int::class.java)
            .observe(this, Observer { refreshData(it) })
    }

    private fun refreshData(type: Int) {
        when (type) {
            REFRESH -> {
                currentPage = 0
                adapter.clearAll()
                mViewModel.getAndroid(currentPage)
            }
            LOAD_MORE -> {
                currentPage += 1
                mViewModel.getAndroid(currentPage)
            }
        }
    }

    override fun startObserve() = mViewModel.run {
        mAndroidData.observe(this@GankAndroidFragment, Observer { adapter.addAll(it) })
    }

    private fun initRecyclerView(){
        adapter = GankAdapter(context)
        gank_android_rv.layoutManager = LinearLayoutManager(context)
        gank_android_rv.adapter = adapter
        adapter.setOnItemClick { url -> toBrowser(this,url) }
    }
}