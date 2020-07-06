package com.samiu.host.ui.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samiu.base.ui.BaseFragment
import com.samiu.base.ui.viewBinding
import com.samiu.host.R
import com.samiu.host.databinding.FragmentWanProjectBinding
import com.samiu.host.global.LOAD_MORE
import com.samiu.host.global.REFRESH
import com.samiu.host.ui.adapter.ArticleListenerImpl
import com.samiu.host.ui.adapter.ReboundingSwipeActionCallback
import com.samiu.host.ui.adapter.WanProjectAdapter
import com.samiu.host.ui.adapter.WanTypeAdapter
import com.samiu.host.viewmodel.WanProjectViewModel
import kotlinx.android.synthetic.main.fragment_wan_project.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/3/4
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanProjectFragment : BaseFragment(R.layout.fragment_wan_project) {

    private val binding by viewBinding(FragmentWanProjectBinding::bind)
    private val viewModel: WanProjectViewModel by viewModel()

    private var currentPage by Delegates.notNull<Int>()
    private var cid by Delegates.notNull<Int>()
    private lateinit var mTypeAdapter: WanTypeAdapter
    private lateinit var mArticleAdapter: WanProjectAdapter

    override fun initView() {
        initAdapter()
        initRefresh()
    }

    override fun initData() {
        viewModel.getProjectType()
    }

    private fun refreshData(type: Int) {
        when (type) {
            REFRESH -> {
                currentPage = 0
                mArticleAdapter.clearAll()
                viewModel.getAllProjects(currentPage, cid)
            }
            LOAD_MORE -> {
                currentPage += 1
                viewModel.getAllProjects(currentPage, cid)
            }
        }
    }

    override fun startObserve() = viewModel.run {
        mProjectType.observe(this@WanProjectFragment, Observer {
            it[0].isSelected = true
            cid = it[0].id
            mTypeAdapter.replaceAll(it)
            refreshData(REFRESH)
        })
        mAllProjects.observe(this@WanProjectFragment, Observer { mArticleAdapter.addAll(it) })
    }

    private fun initAdapter() {
        //title
        mTypeAdapter = WanTypeAdapter(context)
        binding.projectRecycler1.adapter = mTypeAdapter
        mTypeAdapter.setOnItemClick { cid ->
            run {
                this.cid = cid
                refreshData(REFRESH)
            }
        }
        //article
        mArticleAdapter = WanProjectAdapter(ArticleListenerImpl(requireContext()))
        binding.projectRecycler2.apply {
            val itemTouchHelper = ItemTouchHelper(ReboundingSwipeActionCallback())
            itemTouchHelper.attachToRecyclerView(this)
            adapter = mArticleAdapter
        }
    }

    private fun initRefresh() {
        with(binding.projectRefresh) {
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
}