package com.samiu.wangank.ui.mine.collect

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import com.samiu.base.ui.BaseFragment
import com.samiu.base.ui.viewBinding
import com.samiu.wangank.R
import com.samiu.wangank.databinding.FragmentRecyclerBinding
import com.samiu.wangank.ui.home.adapter.ArticleListenerImpl
import com.samiu.wangank.ui.home.adapter.ReboundingSwipeActionCallback
import com.samiu.wangank.ui.mine.collect.adapter.CollectArticleAdapter
import com.samiu.wangank.ui.mine.collect.adapter.CollectArticleListenerImpl
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 收藏页面
 *
 * @author Samiu 2022/3/12
 * @email samiuzhong@outlook.com
 */
class WanCollectFragment : BaseFragment(R.layout.fragment_recycler) {

    private val binding by viewBinding(FragmentRecyclerBinding::bind)
    private val viewModel: WanCollectViewModel by viewModel()

    private lateinit var mAdapter: CollectArticleAdapter

    override fun initView() {
        mAdapter = CollectArticleAdapter(CollectArticleListenerImpl(requireContext()))
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