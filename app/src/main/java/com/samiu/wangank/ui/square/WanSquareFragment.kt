package com.samiu.wangank.ui.square

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import com.samiu.base.ui.BaseFragment
import com.samiu.base.ui.viewBinding
import com.samiu.wangank.R
import com.samiu.wangank.databinding.FragmentRecyclerBinding
import com.samiu.wangank.ui.home.adapter.ArticleListenerImpl
import com.samiu.wangank.ui.home.adapter.ReboundingSwipeActionCallback
import com.samiu.wangank.ui.home.adapter.WanArticleAdapter
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 广场页
 *
 * @author Samiu 2020/3/2
 * @email samiuzhong@outlook.com
 */
class WanSquareFragment : BaseFragment(R.layout.fragment_recycler) {
    
    private val binding by viewBinding(FragmentRecyclerBinding::bind)
    private val viewModel: WanSquareViewModel by viewModel()

    private lateinit var mAdapter: WanArticleAdapter

    override fun initView() {
        mAdapter = WanArticleAdapter(ArticleListenerImpl(requireContext()))
        binding.recycler.apply {
            val itemTouchHelper = ItemTouchHelper(ReboundingSwipeActionCallback())
            itemTouchHelper.attachToRecyclerView(this)
            adapter = mAdapter
        }
    }

    override fun initData() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.articlePagingList.collectLatest {
                mAdapter.submitData(it)
            }
        }
    }
}