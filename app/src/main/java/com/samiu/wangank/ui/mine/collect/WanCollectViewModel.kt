package com.samiu.wangank.ui.mine.collect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.samiu.wangank.ui.mine.WanMineRepository

/**
 * @author Samiu 2022/3/12
 * @email samiuzhong@outlook.com
 */
class WanCollectViewModel(
    mineRepository: WanMineRepository
) : ViewModel() {

    val articlePagingList = mineRepository.getCollectPaging().cachedIn(viewModelScope)
}