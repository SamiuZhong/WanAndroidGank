package com.samiu.host.viewmodel.wan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.samiu.base.ui.BaseViewModel
import com.samiu.host.model.bean.wan.Article
import com.samiu.host.model.bean.wan.ArticleList
import com.samiu.host.model.bean.wan.SystemParent
import com.samiu.host.model.http.WanResult
import com.samiu.host.model.repository.wan.WanWxRepository
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/3/5
 */
class WanWxViewModel(
    private val wanWxRepository: WanWxRepository
) : BaseViewModel() {

    val mAccounts = MutableLiveData<List<SystemParent>>()
    val mArticles = MutableLiveData<List<Article>>()

    fun getAccounts() = viewModelScope.launch {
        val accounts = wanWxRepository.getWxAccount()
        if (accounts is WanResult.Success)
            mAccounts.value = accounts.data
    }

    fun getArticles(id: Int, page: Int) = viewModelScope.launch {
        val articles = wanWxRepository.getWxArticle(id, page)
        if (articles is WanResult.Success)
            mArticles.value = articles.data.datas
    }
}