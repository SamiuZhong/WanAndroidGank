package com.samiu.wangank.components.front

import androidx.lifecycle.ViewModel
import com.samiu.wangank.data.repository.ArticleRepository
import javax.inject.Inject

/**
 * @author samiu 2023/2/6
 * @email samiuzhong@outlook.com
 */
class FrontViewModel @Inject constructor(
    repository: ArticleRepository
) : ViewModel() {

    val frontArticles = repository.getFrontArticles()
}