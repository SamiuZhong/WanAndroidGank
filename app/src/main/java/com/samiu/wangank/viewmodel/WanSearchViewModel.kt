package com.samiu.wangank.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samiu.wangank.model.bean.Article
import com.samiu.wangank.model.bean.Hot
import com.samiu.wangank.model.http.WanResult
import com.samiu.wangank.model.repository.WanSearchRepository
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/3/7
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanSearchViewModel(
    private val searchRepository: WanSearchRepository
) : ViewModel() {

    val mArticles = MutableLiveData<List<Article>>()
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