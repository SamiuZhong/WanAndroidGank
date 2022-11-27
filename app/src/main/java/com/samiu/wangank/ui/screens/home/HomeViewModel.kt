package com.samiu.wangank.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.samiu.wangank.data.repository.HomeRepository
import com.samiu.wangank.model.ArticleDTO
import com.samiu.wangank.model.BannerDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author samiu 2022/11/27
 * @email samiuzhong@outlook.com
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private val _articles = MutableStateFlow<PagingData<ArticleDTO>>(PagingData.empty())
    private val _banners = MutableStateFlow(emptyList<BannerDTO>())

    val articles = _articles
    val banners = _banners

    /**
     * 获取首页文章列表
     */
    fun getArticles() = viewModelScope.launch {
        repository.getHomeArticles().cachedIn(viewModelScope).collect {
            _articles.value = it
        }
    }

    /**
     * 获取首页banner
     */
    fun getBanners() = viewModelScope.launch {
        repository.getBanners().collectLatest {
            _banners.value = it
        }
    }
}