package com.samiu.wangank.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.samiu.wangank.bean.Banner
import com.samiu.wangank.bean.base.WanResult
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/3/3
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanHomeViewModel(
    private val homeRepository: WanHomeRepository
) : ViewModel() {

    val mBanners = MutableLiveData<List<Banner>>()

    fun getBanners() = viewModelScope.launch {
        val data = homeRepository.getBanners()
        if (data is WanResult.Success)
            mBanners.value = data.data
    }

    val articles = homeRepository.getArticlesList().cachedIn(viewModelScope)
}