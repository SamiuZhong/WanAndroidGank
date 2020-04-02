package com.samiu.host.ui.base.nav

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.samiu.host.R

/**
 * @author Samiu 2020/4/2
 */
object NavigationModel {

    private var navigationMenuItems = mutableListOf(
        NavigationModelItem.NavMenuItem(
            id = 0,
            icon = R.drawable.ic_twotone_inbox,
            titleRes = R.string.navigation_inbox,
            checked = false
        ),
        NavigationModelItem.NavMenuItem(
            id = 1,
            icon = R.drawable.ic_twotone_stars,
            titleRes = R.string.navigation_starred,
            checked = false
        ),
        NavigationModelItem.NavMenuItem(
            id = 2,
            icon = R.drawable.ic_twotone_send,
            titleRes = R.string.navigation_sent,
            checked = false
        ),
        NavigationModelItem.NavMenuItem(
            id = 3,
            icon = R.drawable.ic_twotone_delete,
            titleRes = R.string.navigation_trash,
            checked = false
        ),
        NavigationModelItem.NavMenuItem(
            id = 4,
            icon = R.drawable.ic_twotone_error,
            titleRes = R.string.navigation_spam,
            checked = false
        ),
        NavigationModelItem.NavMenuItem(
            id = 5,
            icon = R.drawable.ic_twotone_drafts,
            titleRes = R.string.navigation_drafts,
            checked = false
        )
    )

    private val _navigationList: MutableLiveData<List<NavigationModelItem>> = MutableLiveData()
    val navigationList: LiveData<List<NavigationModelItem>>
        get() = _navigationList

    init {
        postListUpdate()
    }

    fun setNavigationMenuItemChecked(id:Int):Boolean{
        var updated = false
        navigationMenuItems.forEachIndexed{index, item ->
            val shouldCheck = item.id==id
            if (item.checked!=shouldCheck){
                navigationMenuItems[index] = item.copy(checked = shouldCheck)
                updated = true
            }
        }
        if (updated)
            postListUpdate()
        return updated
    }

    private fun postListUpdate(){
        val newList = navigationMenuItems
        _navigationList.value = newList
    }
}