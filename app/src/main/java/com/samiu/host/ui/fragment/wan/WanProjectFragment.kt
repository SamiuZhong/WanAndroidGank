package com.samiu.host.ui.fragment.wan

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.samiu.base.ui.BaseVMFragment
import com.samiu.host.R
import com.samiu.host.databinding.FragmentWanProjectBinding
import com.samiu.host.global.LOAD_MORE
import com.samiu.host.global.REFRESH
import com.samiu.host.global.toBrowser
import com.samiu.host.model.bean.wan.SystemParent
import com.samiu.host.ui.adapter.WanArticleAdapter
import com.samiu.host.ui.adapter.WanProjectAdapter
import com.samiu.host.ui.adapter.WanTypeAdapter
import com.samiu.host.viewmodel.wan.WanProjectViewModel
import kotlinx.android.synthetic.main.fragment_wan_project.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/3/4
 */
class WanProjectFragment : BaseVMFragment<FragmentWanProjectBinding,WanProjectViewModel>() {
    override fun getLayoutResId() = R.layout.fragment_wan_project

    private val mViewModel: WanProjectViewModel by viewModel()
    private var currentPage by Delegates.notNull<Int>()
    private var cid by Delegates.notNull<Int>()
    private lateinit var mTypeAdapter: WanTypeAdapter
    private lateinit var mArticleAdapter: WanProjectAdapter

    override fun initView() {
        initRecyclerView()
    }

    override fun initData() {
        mViewModel.getProjectType()
        with(project_refresh) {
            setOnRefreshListener {
                refreshData(REFRESH)
                finishRefresh(1500)
            }
            setOnLoadMoreListener {
                refreshData(LOAD_MORE)
                finishLoadMore(2000)
            }
        }
    }

    private fun refreshData(type: Int) {
        when (type) {
            REFRESH -> {
                currentPage = 0
                mArticleAdapter.clearAll()
                mViewModel.getAllProjects(currentPage, cid)
            }
            LOAD_MORE -> {
                currentPage += 1
                mViewModel.getAllProjects(currentPage, cid)
            }
        }
    }

    override fun startObserve() = mViewModel.run {
        mProjectType.observe(this@WanProjectFragment, Observer {
            it[0].isSelected = true
            cid = it[0].id
            mTypeAdapter.replaceAll(it)
            refreshData(REFRESH)
        })
        mAllProjects.observe(this@WanProjectFragment, Observer { mArticleAdapter.addAll(it) })
    }

    private fun initRecyclerView() {
        //title
        mTypeAdapter = WanTypeAdapter(context)
        project_recycler_1.layoutManager = LinearLayoutManager(context).apply {
            orientation = RecyclerView.HORIZONTAL
        }
        project_recycler_1.adapter = mTypeAdapter
        mTypeAdapter.setOnItemClick { cid ->
            run {
                this.cid = cid
                refreshData(REFRESH)
            }
        }
        //article
        mArticleAdapter = WanProjectAdapter(context)
        project_recycler_2.layoutManager = LinearLayoutManager(context)
        project_recycler_2.adapter = mArticleAdapter
        mArticleAdapter.setOnItemClick { url -> toBrowser(this, url) }
    }
}