package com.samiu.wangank.ui.square

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn

/**
 * @author Samiu 2020/3/5
 * @email samiuzhong@outlook.com
 */
class WanSquareViewModel(
    wanSquareRepository: WanSquareRepository
) : ViewModel() {

    val articlePagingList = wanSquareRepository.getArticlePaging().cachedIn(viewModelScope)
}