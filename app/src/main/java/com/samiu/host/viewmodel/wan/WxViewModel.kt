package com.samiu.host.viewmodel.wan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.samiu.base.ui.BaseViewModel
import com.samiu.host.model.bean.wan.Article
import com.samiu.host.model.bean.wan.SystemParent
import com.samiu.host.model.http.WanResult
import com.samiu.host.model.repository.wan.WxRepository
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/3/5
 */
class WxViewModel(
    private val wxRepository: WxRepository
) : BaseViewModel() {

    private val mAccounts = MutableLiveData<List<SystemParent>>()
    private val mArticles = MutableLiveData<List<Article>>()

    fun getAuthors() = viewModelScope.launch {
        val accounts = wxRepository.getWxAccount()
        if (accounts is WanResult.Success)
            mAccounts.value = accounts.data
    }

    fun getArticles(id: Int, page: Int) = viewModelScope.launch {
        val articles = wxRepository.getWxArticle(id, page)
        if (articles is WanResult.Success)
            mArticles.value = articles.data.datas
    }
}