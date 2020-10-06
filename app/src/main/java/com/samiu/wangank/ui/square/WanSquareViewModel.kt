package com.samiu.wangank.ui.square

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samiu.wangank.bean.ArticleItem
import com.samiu.wangank.bean.base.WanResult
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/3/5
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanSquareViewModel(
    private val wanSquareRepository: WanSquareRepository
) : ViewModel() {

    val mArticles = MutableLiveData<List<ArticleItem>>()

    fun getArticles(page: Int) = viewModelScope.launch {
        val articleList = wanSquareRepository.getSquareArticle(page)
        if (articleList is WanResult.Success)
            mArticles.value = articleList.data.datas
    }
}