package com.samiu.host.ui.base.nav

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter


/**
 * @author Samiu 2020/3/31
 */
private const val VIEW_TYPE_NAV_MENU_ITEM = 4
private const val VIEW_TYPE_NAV_DIVIDER = 6
private const val VIEW_TYPE_NAV_EMAIL_FOLDER_ITEM = 5

class NavigationAdapter(
    private val listener:NavigationAdapterListener
): ListAdapter<NavigationModeItem, NavigationViewHolder<NavigationModeItem>>(
    NavigationModeItem.NavModelItemDiff
) {

    interface NavigationAdapterListener{

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NavigationViewHolder<NavigationModeItem> {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: NavigationViewHolder<NavigationModeItem>, position: Int) {
        TODO("Not yet implemented")
    }
}