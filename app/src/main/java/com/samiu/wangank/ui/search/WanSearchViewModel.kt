package com.samiu.wangank.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samiu.wangank.bean.Article
import com.samiu.wangank.bean.Hot
import com.samiu.wangank.bean.base.WanResult
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/3/7
 * @email samiuzhong@outlook.com
 */
class WanSearchViewModel(
    private val searchRepository: WanSearchRepository
) : ViewModel() {

    val mArticles = MutableLiveData<List<Article>>()
    val mkeys = MutableLiveData<List<Hot>>()

    fun getArticles(page: Int, key: String) = viewModelScope.launch {
        val articleList = searchRepository.getSearchArticle(page, key)
        if (articleList is WanResult.Success)
            mArticles.value = articleList.data?.datas
    }

    fun getHotKeys() = viewModelScope.launch {
        val key = searchRepository.getHotKey()
        if (key is WanResult.Success) {
            key.data?.let {
                mkeys.value = it
            }
        }
    }
}