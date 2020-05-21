package com.samiu.host.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samiu.host.global.IS_LOGIN
import com.samiu.host.model.bean.Article
import com.samiu.host.model.http.WanResult
import com.samiu.host.model.repository.WanCollectionRepository
import com.samiu.host.model.repository.WanLoginRepository
import com.samiu.host.util.Preference
import kotlinx.coroutines.launch
import kotlin.math.log

/**
 * @author Samiu 2020/5/20
 */
class WanPersonalViewModel(
    private val collectionRepository: WanCollectionRepository,
    private val loginRepository: WanLoginRepository
) : ViewModel() {

    val mCollections = MutableLiveData<List<Article>>()
    private var isLogin by Preference(IS_LOGIN, false)

    fun getCollections(page: Int) = viewModelScope.launch {
        val articleList = collectionRepository.getCollections(page)
        if (articleList is WanResult.Success)
            mCollections.value = articleList.data.datas
    }

    fun logout() = viewModelScope.launch {
        loginRepository.logout()
        isLogin = false
    }
}