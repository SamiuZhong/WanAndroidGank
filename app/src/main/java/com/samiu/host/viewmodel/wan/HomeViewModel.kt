package com.samiu.host.viewmodel.wan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.samiu.base.ui.BaseViewModel
import com.samiu.host.model.bean.wan.Article
import com.samiu.host.model.bean.wan.Banner
import com.samiu.host.model.http.WanResult
import com.samiu.host.model.repository.wan.HomeRepository
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/3/3
 */
class HomeViewModel(
    private val homeRepository: HomeRepository
) : BaseViewModel() {

    val mArticles = MutableLiveData<List<Article>>()
    val mBanners = MutableLiveData<List<Banner>>()

    fun getBanners() = viewModelScope.launch {
        val data = homeRepository.getBanners()
        if (data is WanResult.Success)
            mBanners.value = data.data
    }

    fun getArticles(page: Int) = viewModelScope.launch {
        val articleList = homeRepository.getArticlesList(page)
        if (articleList is WanResult.Success)
            mArticles.value = articleList.data.datas
    }
}