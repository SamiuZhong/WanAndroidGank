package com.samiu.wangank.ui.project

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import com.samiu.base.ui.BaseFragment
import com.samiu.base.ui.viewBinding
import com.samiu.wangank.R
import com.samiu.wangank.databinding.FragmentWanProjectBinding
import com.samiu.wangank.global.LOAD_MORE
import com.samiu.wangank.global.REFRESH
import com.samiu.wangank.ui.home.adapter.ArticleListenerImpl
import com.samiu.wangank.ui.home.adapter.ReboundingSwipeActionCallback
import com.samiu.wangank.ui.project.adapter.WanProjectAdapter
import com.samiu.wangank.ui.project.adapter.WanTypeAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/3/4
 * @email samiuzhong@outlook.com
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