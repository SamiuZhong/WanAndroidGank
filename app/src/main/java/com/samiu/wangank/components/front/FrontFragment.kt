package com.samiu.wangank.components.front

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.samiu.wangank.R
import com.samiu.wangank.databinding.FragmentTimelineBinding
import com.samiu.wangank.utils.viewBinding

/**
 * 首页
 *
 * @author samiu 2023/2/2
 * @email samiuzhong@outlook.com
 */
class FrontFragment : Fragment(R.layout.fragment_timeline) {

    private val binding by viewBinding(FragmentTimelineBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.timelineRoot.setBackgroundColor(Color.BLUE)
    }
}