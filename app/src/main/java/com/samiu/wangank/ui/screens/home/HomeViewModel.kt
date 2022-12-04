package com.samiu.wangank.ui.screens.home

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.samiu.wangank.data.repository.ArticleFlow
import com.samiu.wangank.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author samiu 2022/11/27
 * @email samiuzhong@outlook.com
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    // 获取首页文章列表
    private val articleFlow by lazy {
        repository.getHomeArticles().cachedIn(viewModelScope)
    }

    val uiState by mutableStateOf(HomeUIState(articleFlow))
}

data class HomeUIState(
    val articleFlow: ArticleFlow,
    val listState: LazyListState = LazyListState(),
    val loading: Boolean = false,
    val error: String? = null
)