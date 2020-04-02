package com.samiu.host.ui.base.nav

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.samiu.host.databinding.ItemNavMenuBinding


/**
 * @author Samiu 2020/3/31
 */
private const val VIEW_TYPE_NAV_MENU_ITEM = 4
//private const val VIEW_TYPE_NAV_DIVIDER = 6
//private const val VIEW_TYPE_NAV_EMAIL_FOLDER_ITEM = 5

@Suppress("UNCHECKED_CAST")
class NavigationAdapter(
    private val listener: NavigationAdapterListener
) : ListAdapter<NavigationModelItem, NavigationViewHolder<NavigationModelItem>>(
    NavigationModelItem.NavModelItemDiff
) {

    interface NavigationAdapterListener {
        fun onNavMenuItemClicked(item: NavigationModelItem.NavMenuItem)
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is NavigationModelItem.NavMenuItem -> VIEW_TYPE_NAV_MENU_ITEM
            else -> throw RuntimeException("Unsupported ItemViewType for obj ${getItem(position)}")
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NavigationViewHolder<NavigationModelItem> {
        return when (viewType) {
            VIEW_TYPE_NAV_MENU_ITEM -> NavigationViewHolder.NavMenuItemViewHolder(
                ItemNavMenuBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                listener
            )
            else -> throw RuntimeException("Unsupported view holder type")
        } as NavigationViewHolder<NavigationModelItem>
    }

    override fun onBindViewHolder(
        holder: NavigationViewHolder<NavigationModelItem>,
        position: Int
    ) {
        holder.bind(getItem(position))
    }
}