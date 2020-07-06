package com.samiu.host.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samiu.host.model.bean.Article
import com.samiu.host.model.bean.SystemParent
import com.samiu.host.model.http.WanResult
import com.samiu.host.model.repository.WanWxRepository
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/3/5
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanWxViewModel(
    private val wanWxRepository: WanWxRepository
) : ViewModel() {

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