package com.samiu.wangank.components.project

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.samiu.wangank.R
import com.samiu.wangank.data.remote.ProTypeList
import com.samiu.wangank.databinding.FragmentProHostBinding
import com.samiu.wangank.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

/**
 * 开源项目
 *
 * @author samiu 2023/2/28
 * @email samiuzhong@outlook.com
 */
@AndroidEntryPoint
class ProHostFragment : Fragment(R.layout.fragment_pro_host) {

    private val binding by viewBinding(FragmentProHostBinding::bind)
    private val viewModel: ProjectViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        TabLayoutMediator(binding.proTabLayout, binding.proViewPager) { tab, position ->
            tab.text = position.toString()
        }
        viewModel.getProTypes()
        observe()
    }

    private fun observe() = viewLifecycleOwner.lifecycleScope.launchWhenCreated {
        viewModel.typesFlow.collectLatest { types ->
            setupViewPager(types)
        }
    }

    private fun setupViewPager(types: ProTypeList) {
        binding.proViewPager.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return ProjectFragment.create(types[position].id)
            }

            override fun getItemCount() = types.size
        }
//        TabLayoutMediator(binding.proTabLayout, binding.proViewPager) { tab, position ->
//            tab.text = types[position].name
//        }
    }
}