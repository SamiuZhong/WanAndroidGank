package com.samiu.host.ui.base.nav

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.samiu.host.databinding.NavMenuItemLayoutBinding

/**
 * 这个密封类可以定义NavigationViewHolder的多个子类，方便拓展
 *
 * @author Samiu 2020/3/31
 */
sealed class NavigationViewHolder<T : NavigationModelItem>(
    view: View
) : RecyclerView.ViewHolder(view) {

    abstract fun bind(navItem:T)

    class NavMenuItemViewHolder(
        private val binding:NavMenuItemLayoutBinding,
        private val listener:NavigationAdapter.NavigationAdapterListener
    ):NavigationViewHolder<NavigationModelItem.NavMenuItem>(binding.root){

        override fun bind(navItem: NavigationModelItem.NavMenuItem) {
            binding.run {
                navMenuItem = navItem
                navListener = listener
                executePendingBindings()
            }
        }
    }
}