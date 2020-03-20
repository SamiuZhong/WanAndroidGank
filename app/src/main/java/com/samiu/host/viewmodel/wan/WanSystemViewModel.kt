package com.samiu.host.viewmodel.wan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.samiu.base.ui.BaseViewModel
import com.samiu.host.model.bean.wan.ArticleList
import com.samiu.host.model.bean.wan.SystemParent
import com.samiu.host.model.http.wan.WanResult
import com.samiu.host.model.repository.wan.WanSystemRepository
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/3/6
 */
class WanSystemViewModel(
    private val wanSystemRepository: WanSystemRepository
):BaseViewModel() {

    val mSystems = MutableLiveData<List<SystemParent>>()
    val mSystemDetails = MutableLiveData<ArticleList>()

    fun getSystem(){
        viewModelScope.launch {
            val system = wanSystemRepository.getSystem()
            if (system is WanResult.Success)
                mSystems.value = system.data
        }
    }

    fun getSystemDetail(page: Int, cid: Int){
        viewModelScope.launch {
            val articles = wanSystemRepository.getSystemDetail(page, cid)
            if (articles is WanResult.Success)
                mSystemDetails.value = articles.data
        }
    }
}