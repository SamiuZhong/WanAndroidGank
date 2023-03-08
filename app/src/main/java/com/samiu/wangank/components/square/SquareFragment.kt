package com.samiu.wangank.components.square

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.samiu.wangank.R
import com.samiu.wangank.databinding.FragmentSquareBinding
import com.samiu.wangank.model.ArticleDTO
import com.samiu.wangank.ui.activity.BrowserActivity
import com.samiu.wangank.ui.adapter.ArticleAdapter
import com.samiu.wangank.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

/**
 * 广场
 *
 * @author samiu 2023/2/2
 * @email samiuzhong@outlook.com
 */
@AndroidEntryPoint
class SquareFragment : Fragment(R.layout.fragment_square), ArticleAdapter.ArticleListener {

    private val binding by viewBinding(FragmentSquareBinding::bind)
    private val viewModel: SquareViewModel by viewModels()

    private lateinit var mSquareAdapter: ArticleAdapter
    private lateinit var mSearchAdapter: ArticleAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        mSquareAdapter = ArticleAdapter(this)
        mSearchAdapter = ArticleAdapter(this)
        binding.squareRecycler.adapter = mSquareAdapter
        binding.searchRecycler.adapter = mSearchAdapter
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.squareArticles.collectLatest {
                mSquareAdapter.submitData(it)
            }
        }
        binding.searchRecycler.setOnKeyListener { v, keyCode, event ->
            Log.d("SquareFragment", "initAdapter: ")
            false
        }
    }

    override fun onItemClick(article: ArticleDTO) {
        BrowserActivity.toBrowser(requireContext(), article.link)
    }
}