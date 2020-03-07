package com.samiu.host.viewmodel.wan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.samiu.base.ui.BaseViewModel
import com.samiu.host.model.bean.wan.Article
import com.samiu.host.model.bean.wan.Hot
import com.samiu.host.model.http.WanResult
import com.samiu.host.model.repository.wan.WanSearchRepository
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/3/7
 */
class WanSearchViewModel(
    private val searchRepository: WanSearchRepository
) : BaseViewModel() {

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