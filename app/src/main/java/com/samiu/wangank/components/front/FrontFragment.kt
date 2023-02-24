package com.samiu.wangank.components.front

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.samiu.wangank.R
import com.samiu.wangank.databinding.FragmentTimelineBinding
import com.samiu.wangank.model.ArticleDTO
import com.samiu.wangank.ui.activity.BrowserActivity
import com.samiu.wangank.ui.adapter.ArticleAdapter
import com.samiu.wangank.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

/**
 * 首页
 *
 * @author samiu 2023/2/2
 * @email samiuzhong@outlook.com
 */
@AndroidEntryPoint
class FrontFragment : Fragment(R.layout.fragment_timeline), ArticleAdapter.ArticleListener {

    private val binding by viewBinding(FragmentTimelineBinding::bind)
    private val viewModel: FrontViewModel by viewModels()

    private lateinit var mAdapter: ArticleAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        mAdapter = ArticleAdapter(this)
        binding.timelineRecycler.adapter = mAdapter
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.frontArticles.collectLatest {
                mAdapter.submitData(it)
            }
        }
    }

    override fun onItemClick(article: ArticleDTO) {
        BrowserActivity.toBrowser(requireContext(), article.link)
    }
}