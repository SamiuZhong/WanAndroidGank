package com.samiu.host.ui.viewmodel.wan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.samiu.base.ui.BaseViewModel
import com.samiu.host.model.bean.wan.Article
import com.samiu.host.model.http.WanResult
import com.samiu.host.model.bean.wan.Banner
import com.samiu.host.model.repository.wan.HomeRepository
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/3/3
 */
class HomeViewModel(
    private val homeRepository: HomeRepository
) : BaseViewModel() {

    val mArticles = MutableLiveData<List<Article>>()

    val mBanners: LiveData<List<Banner>> = liveData {
        kotlin.runCatching {
            val data = homeRepository.getBanners()
            if (data is WanResult.Success) emit(data.data)
        }
    }

    fun getArticles(page: Int) {
        viewModelScope.launch {
            val articleList = homeRepository.getArticlesList(page)
            if (articleList is WanResult.Success) {
                mArticles.value = articleList.data.datas
            }
        }
    }
}