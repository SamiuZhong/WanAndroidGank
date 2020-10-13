package com.samiu.wangank.ui.bottomnav.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.DiffUtil

/**
 * @author Samiu 2020/3/31
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
data class NavigationModelItem(
    val id: Int,
    @DrawableRes val icon: Int,
    @StringRes val titleRes: Int,
    var checked: Boolean
)

object NavModelItemDiff : DiffUtil.ItemCallback<NavigationModelItem>() {
    override fun areItemsTheSame(
        oldItem: NavigationModelItem,
        newItem: NavigationModelItem
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: NavigationModelItem,
        newItem: NavigationModelItem
    ) = oldItem.icon == newItem.icon &&
            oldItem.titleRes == newItem.titleRes &&
            oldItem.checked == newItem.checked
}