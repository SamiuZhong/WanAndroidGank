package com.samiu.wangank.components.project

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.samiu.wangank.R
import com.samiu.wangank.databinding.FragmentTimelineBinding
import com.samiu.wangank.utils.viewBinding

/**
 * 开源项目
 *
 * @author samiu 2023/2/2
 * @email samiuzhong@outlook.com
 */
class ProjectFragment : Fragment(R.layout.fragment_timeline) {

    private val binding by viewBinding(FragmentTimelineBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.timelineRoot.setBackgroundColor(Color.GREEN)
    }
}