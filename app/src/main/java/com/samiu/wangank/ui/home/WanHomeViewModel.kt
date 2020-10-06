package com.samiu.wangank.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samiu.wangank.bean.Article
import com.samiu.wangank.bean.Banner
import com.samiu.wangank.bean.base.WanResult
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/3/3
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanHomeViewModel(
    private val wanHomeRepository: WanHomeRepository
) : ViewModel() {

    val mArticles = MutableLiveData<List<Article>>()
    val mBanners = MutableLiveData<List<Banner>>()

    fun getBanners() = viewModelScope.launch {
        val data = wanHomeRepository.getBanners()
        if (data is WanResult.Success)
            mBanners.value = data.data
    }

    fun getArticles(page: Int) = viewModelScope.launch {
        val articleList = wanHomeRepository.getArticlesList(page)
        if (articleList is WanResult.Success)
            mArticles.value = articleList.data.datas
    }
}