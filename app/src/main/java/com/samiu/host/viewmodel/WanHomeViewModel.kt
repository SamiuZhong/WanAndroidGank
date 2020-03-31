package com.samiu.host.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.samiu.base.ui.BaseViewModel
import com.samiu.host.model.bean.Article
import com.samiu.host.model.bean.Banner
import com.samiu.host.model.http.WanResult
import com.samiu.host.model.repository.WanHomeRepository
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/3/3
 */
class WanHomeViewModel(
    private val wanHomeRepository: WanHomeRepository
) : BaseViewModel() {

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