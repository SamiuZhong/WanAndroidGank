package com.samiu.wangank.components.front

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.samiu.wangank.data.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author samiu 2023/2/6
 * @email samiuzhong@outlook.com
 */
@HiltViewModel
class FrontViewModel @Inject constructor (
    repository: ArticleRepository
) : ViewModel() {

    val frontArticles = repository.getFrontArticles().cachedIn(viewModelScope)
}