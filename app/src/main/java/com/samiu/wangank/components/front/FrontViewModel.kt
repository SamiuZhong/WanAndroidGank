package com.samiu.wangank.components.front

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.samiu.wangank.data.paging.ArticleType
import com.samiu.wangank.data.remote.BannerList
import com.samiu.wangank.data.repository.ArticleRepository
import com.samiu.wangank.model.BannerDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author samiu 2023/2/6
 * @email samiuzhong@outlook.com
 */
@HiltViewModel
class FrontViewModel @Inject constructor(
    private val repository: ArticleRepository
) : ViewModel() {

    private val _bannerFlow = MutableSharedFlow<BannerList>()
    val bannerFlow = _bannerFlow.asSharedFlow()

    val frontArticles = repository.getArticles(ArticleType.Front).cachedIn(viewModelScope)

    fun getBanners() = viewModelScope.launch {
        val data = repository.getBanners()
        _bannerFlow.emit(data)
    }
}