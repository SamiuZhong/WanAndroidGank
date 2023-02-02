package com.samiu.wangank.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.samiu.wangank.bean.Banner
import com.samiu.wangank.bean.base.WanResult
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/3/3
 * @email samiuzhong@outlook.com
 */
class WanHomeViewModel(
    private val wanHomeRepository: WanHomeRepository
) : ViewModel() {

    val mBanners = MutableLiveData<List<Banner>>()
    val articlePagingList = wanHomeRepository.getArticlePaging().cachedIn(viewModelScope)

    fun getBanners() = viewModelScope.launch {
        val data = wanHomeRepository.getBanners()
        if (data is WanResult.Success)
            mBanners.value = data.data
    }
}