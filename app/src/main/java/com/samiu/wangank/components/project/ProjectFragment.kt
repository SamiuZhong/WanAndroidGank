package com.samiu.wangank.components.project

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
import com.samiu.wangank.utils.Constants
import com.samiu.wangank.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

/**
 * 开源项目
 *
 * @author samiu 2023/2/2
 * @email samiuzhong@outlook.com
 */
@AndroidEntryPoint
class ProjectFragment : Fragment(R.layout.fragment_timeline), ArticleAdapter.ArticleListener {

    private val binding by viewBinding(FragmentTimelineBinding::bind)
    private val viewModel: ProjectViewModel by viewModels()

    private lateinit var mAdapter: ArticleAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val cid = bundle?.getInt(Constants.Bundle.CID)
        cid?.run { initAdapter(cid) }
    }

    private fun initAdapter(cid: Int) {
        mAdapter = ArticleAdapter(this)
        binding.timelineRecycler.adapter = mAdapter
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.getProjects(cid).collectLatest {
                mAdapter.submitData(it)
            }
        }
    }

    override fun onItemClick(article: ArticleDTO) {
        BrowserActivity.toBrowser(requireContext(), article.link)
    }

    companion object {
        fun create(cid: Int): ProjectFragment {
            val fragment = ProjectFragment()
            val bundle = Bundle()
            bundle.putInt(Constants.Bundle.CID, cid)
            fragment.arguments = bundle
            return fragment
        }
    }
}