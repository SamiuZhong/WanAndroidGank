package com.samiu.wangank.ui.wxpub

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samiu.wangank.bean.Article
import com.samiu.wangank.bean.SystemParent
import com.samiu.wangank.bean.base.WanResult
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