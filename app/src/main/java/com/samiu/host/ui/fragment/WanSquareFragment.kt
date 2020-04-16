package com.samiu.host.ui.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeremyliao.liveeventbus.LiveEventBus
import com.samiu.base.ui.BaseFragment
import com.samiu.base.ui.viewBinding
import com.samiu.host.R
import com.samiu.host.databinding.FragmentWanSquareBinding
import com.samiu.host.global.LOAD_MORE
import com.samiu.host.global.REFRESH
import com.samiu.host.global.SQUARE
import com.samiu.host.global.toBrowser
import com.samiu.host.ui.adapter.WanArticleAdapter
import com.samiu.host.viewmodel.WanSquareViewModel
import kotlinx.android.synthetic.main.fragment_wan_square.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/3/2
 */
class WanSquareFragment : BaseFragment(R.layout.fragment_wan_square) {
    private val binding by viewBinding(FragmentWanSquareBinding::bind)
    private val squareViewModel: WanSquareViewModel by viewModel()

    private var currentPage by Delegates.notNull<Int>()
    private lateinit var adapter: WanArticleAdapter

    override fun initData() = refreshData(REFRESH)

    override fun initView() {
        initRecyclerView()
        LiveEventBus
            .get(SQUARE, Int::class.java)
            .observe(this, Observer { refreshData(it) })
    }

    private fun refreshData(type: Int) {
        when (type) {
            REFRESH -> {
                currentPage = 0
                adapter.clearAll()
                squareViewModel.getArticles(currentPage)
            }
            LOAD_MORE -> {
                currentPage += 1
                squareViewModel.getArticles(currentPage)
            }
        }
    }

    override fun startObserve() = squareViewModel.run {
        mArticles.observe(this@WanSquareFragment, Observer { adapter.addAll(it) })
    }

    private fun initRecyclerView() {
        adapter = WanArticleAdapter(context)
        square_recycler_view.layoutManager = LinearLayoutManager(context)
        square_recycler_view.adapter = adapter
        adapter.setOnItemClick { url -> toBrowser(this, url) }
    }
}