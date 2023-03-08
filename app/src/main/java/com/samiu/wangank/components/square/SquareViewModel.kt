package com.samiu.wangank.components.square

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.samiu.wangank.data.paging.ArticleType
import com.samiu.wangank.data.repository.ArticleFlow
import com.samiu.wangank.data.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * 广场页
 *
 * @author samiu 2023/2/23
 * @email samiuzhong@outlook.com
 */
@HiltViewModel
class SquareViewModel @Inject constructor(
    private val repository: ArticleRepository
) : ViewModel() {

    val squareArticles = repository.getArticles(ArticleType.Square).cachedIn(viewModelScope)

    fun search(keyword: String): ArticleFlow {
        return repository.search(keyword).cachedIn(viewModelScope)
    }
}