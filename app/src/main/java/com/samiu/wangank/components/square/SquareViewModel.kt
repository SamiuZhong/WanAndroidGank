package com.samiu.wangank.components.square

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.samiu.wangank.data.paging.ArticleType
import com.samiu.wangank.data.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author samiu 2023/2/23
 * @email samiuzhong@outlook.com
 */
@HiltViewModel
class SquareViewModel @Inject constructor(
    repository: ArticleRepository
) : ViewModel() {

    val squareArticles = repository.getArticles(ArticleType.Square).cachedIn(viewModelScope)
}