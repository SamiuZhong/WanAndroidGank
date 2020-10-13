package com.samiu.wangank.ui.bottomnav.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.samiu.wangank.databinding.NavMenuItemLayoutBinding
import com.samiu.wangank.ui.bottomnav.model.NavModelItemDiff
import com.samiu.wangank.ui.bottomnav.model.NavigationModelItem


/**
 * @author Samiu 2020/3/31
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class NavigationAdapter(
    private val listener: NavigationAdapterListener
) : ListAdapter<NavigationModelItem, NavigationViewHolder>(
    NavModelItemDiff
) {

    interface NavigationAdapterListener {
        fun onNavMenuItemClicked(item: NavigationModelItem)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NavigationViewHolder {
        return NavigationViewHolder(
            NavMenuItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener
        )
    }

    override fun onBindViewHolder(
        holder: NavigationViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }
}