package com.samiu.wangank.ui.nav.adapter

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.DiffUtil

/**
 * 这个密封类封装了[NavigationAdapter]能够展示的所有Item
 *
 * @author Samiu 2020/3/31
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
sealed class NavigationModelItem {

    /**
     * 菜单item
     */
    data class NavMenuItem(
        val id: Int,
        @DrawableRes val icon: Int,
        @StringRes val titleRes: Int,
        var checked: Boolean
    ) : NavigationModelItem()

    /**
     * item局部刷新
     */
    object NavModelItemDiff : DiffUtil.ItemCallback<NavigationModelItem>() {
        override fun areItemsTheSame(
            oldItem: NavigationModelItem,
            newItem: NavigationModelItem
        ): Boolean {
            return when {
                oldItem is NavMenuItem && newItem is NavMenuItem ->
                    oldItem.id == newItem.id
                else -> oldItem == newItem
            }
        }

        override fun areContentsTheSame(
            oldItem: NavigationModelItem,
            newItem: NavigationModelItem
        ): Boolean {
            return when {
                oldItem is NavMenuItem && newItem is NavMenuItem ->
                    oldItem.icon == newItem.icon &&
                            oldItem.titleRes == newItem.titleRes &&
                            oldItem.checked == newItem.checked
                else -> false
            }
        }
    }
}