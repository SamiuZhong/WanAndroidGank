package com.samiu.wangank.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samiu.wangank.bean.ArticleItem
import com.samiu.wangank.bean.Hot
import com.samiu.wangank.bean.base.WanResult
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/3/7
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanSearchViewModel(
    private val searchRepository: WanSearchRepository
) : ViewModel() {

    val mArticles = MutableLiveData<List<ArticleItem>>()
    val mkeys = MutableLiveData<List<Hot>>()

    fun getArticles(page: Int, key: String) = viewModelScope.launch {
        val articleList = searchRepository.getSearchArticle(page, key)
        if (articleList is WanResult.Success)
            mArticles.value = articleList.data.datas
    }

    fun getHotKeys() = viewModelScope.launch {
        val key = searchRepository.getHotKey()
        if (key is WanResult.Success)
            mkeys.value = key.data
    }
}