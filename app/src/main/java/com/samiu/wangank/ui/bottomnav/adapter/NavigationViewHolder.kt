package com.samiu.wangank.ui.bottomnav.adapter

import androidx.recyclerview.widget.RecyclerView
import com.samiu.wangank.databinding.NavMenuItemLayoutBinding
import com.samiu.wangank.ui.bottomnav.model.NavigationModelItem

/**
 * @author Samiu 2020/3/31
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class NavigationViewHolder(
    private val binding: NavMenuItemLayoutBinding,
    private val listener: NavigationAdapter.NavigationAdapterListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(navItem: NavigationModelItem) {
        binding.run {
            navMenuItem = navItem
            navListener = listener
            executePendingBindings()
        }
    }
}