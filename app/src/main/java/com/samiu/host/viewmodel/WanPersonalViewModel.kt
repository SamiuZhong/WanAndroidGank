package com.samiu.host.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samiu.host.model.bean.Article
import com.samiu.host.model.http.WanResult
import com.samiu.host.model.repository.WanCollectionRepository
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/5/20
 */
class WanPersonalViewModel(
    private val collectionRepository: WanCollectionRepository
) : ViewModel() {

    val mCollections = MutableLiveData<List<Article>>()

    fun getCollections(page: Int) = viewModelScope.launch {
        val articleList = collectionRepository.getCollections(page)
        if (articleList is WanResult.Success)
            mCollections.value = articleList.data.datas
    }
}