package com.samiu.host.ui.fragment.wan

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.samiu.base.ui.BaseVMFragment
import com.samiu.host.R
import com.samiu.host.ui.adapter.WanSystemAdapter
import com.samiu.host.viewmodel.wan.WanSystemViewModel
import kotlinx.android.synthetic.main.fragment_wan_system.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/3/2
 */
class WanSystemFragment : BaseVMFragment<WanSystemViewModel>() {
    override fun getLayoutResId() = R.layout.fragment_wan_system
    override fun initView() = initRecyclerView()
    override fun initData() = mViewModel.getSystem()

    private val mViewModel: WanSystemViewModel by viewModel()
    private lateinit var adapter: WanSystemAdapter

    override fun startObserve() = mViewModel.run {
        mSystems.observe(this@WanSystemFragment, Observer {
            adapter.addAll(it)
        })
    }

    private fun initRecyclerView() {
        adapter = WanSystemAdapter(context)
        system_rv.layoutManager = LinearLayoutManager(context)
        system_rv.adapter = adapter
    }
}