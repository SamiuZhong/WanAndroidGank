package com.samiu.host.ui.base.nav

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.DiffUtil

/**
 * @author Samiu 2020/3/31
 */
sealed class NavigationModeItem {

    data class NavMenuItem(
        val id:Int,
        @DrawableRes val icon:Int,
        @StringRes val titleRes:Int,
        var checked:Boolean
    ):NavigationModeItem()

    data class NavDivider(val title:String):NavigationModeItem()


    object NavModelItemDiff:DiffUtil.ItemCallback<NavigationModeItem>(){
        override fun areItemsTheSame(
            oldItem: NavigationModeItem,
            newItem: NavigationModeItem
        ): Boolean {
            TODO("Not yet implemented")
        }

        override fun areContentsTheSame(
            oldItem: NavigationModeItem,
            newItem: NavigationModeItem
        ): Boolean {
            TODO("Not yet implemented")
        }

    }
}