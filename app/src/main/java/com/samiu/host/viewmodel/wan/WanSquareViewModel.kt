package com.samiu.host.viewmodel.wan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.samiu.base.ui.BaseViewModel
import com.samiu.host.model.bean.wan.Article
import com.samiu.host.model.http.wan.WanResult
import com.samiu.host.model.repository.wan.WanSquareRepository
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/3/5
 */
class WanSquareViewModel(
    private val wanSquareRepository: WanSquareRepository
) : BaseViewModel() {

    val mArticles = MutableLiveData<List<Article>>()

    fun getArticles(page: Int) = viewModelScope.launch {
        val articleList = wanSquareRepository.getSquareArticle(page)
        if (articleList is WanResult.Success)
            mArticles.value = articleList.data.datas
    }
}