package com.samiu.host.ui.fragment.wan

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeremyliao.liveeventbus.LiveEventBus
import com.samiu.base.ui.BaseVMFragment
import com.samiu.host.R
import com.samiu.host.global.RECENT_PROJECT
import com.samiu.host.global.toBrowser
import com.samiu.host.ui.adapter.ProjectAdapter
import com.samiu.host.viewmodel.wan.ProjectViewModel
import kotlinx.android.synthetic.main.fragment_wan_recent_project.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/3/5
 */
class RecentProjectFragment : BaseVMFragment<ProjectViewModel>() {
    override fun getLayoutResId() = R.layout.fragment_wan_recent_project

    private val mViewModel: ProjectViewModel by viewModel()
    private lateinit var adapter: ProjectAdapter
    private var currentPage by Delegates.notNull<Int>()

    override fun initView() {
        initRecyclerView()
        LiveEventBus    //refresh data
            .get(RECENT_PROJECT, Int::class.java)
            .observe(this, Observer { refreshData(it) })
    }

    override fun initData() {
        refreshData(-1)
    }

    private fun refreshData(type: Int) {
        when (type) {
            -1 -> { //onRefresh
                currentPage = 0
                adapter.clearAll()
                mViewModel.getRecentProjects(currentPage)
            }
            1 -> {  //onLoadMore
                currentPage += 1
                mViewModel.getRecentProjects(currentPage)
            }
        }
    }

    override fun startObserve() {
        mViewModel.run {
            recentProjects.observe(this@RecentProjectFragment, Observer { adapter.addAll(it) })
        }
    }

    private fun initRecyclerView() {
        adapter = ProjectAdapter(context)
        recent_project_rv.layoutManager = LinearLayoutManager(context)
        recent_project_rv.adapter = adapter
        adapter.setOnItemClick { url -> toBrowser(this, url) }
    }
}

