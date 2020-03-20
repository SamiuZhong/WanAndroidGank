package com.samiu.host.viewmodel.wan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.samiu.base.ui.BaseViewModel
import com.samiu.host.model.bean.wan.Article
import com.samiu.host.model.http.wan.WanResult
import com.samiu.host.model.repository.wan.WanSystemRepository
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/3/6
 */
class RecyclerViewModel(
    private val systemRepository: WanSystemRepository
) : BaseViewModel() {

    val mSystemArticles = MutableLiveData<List<Article>>()

    fun getSystemArticles(page: Int, cid: Int) = viewModelScope.launch {
        val data = systemRepository.getSystemDetail(page, cid)
        if (data is WanResult.Success) {
            mSystemArticles.value = data.data.datas
        }
    }
}