package com.samiu.host.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samiu.host.model.bean.ArticleList
import com.samiu.host.model.bean.SystemParent
import com.samiu.host.model.http.WanResult
import com.samiu.host.model.repository.WanSystemRepository
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/3/6
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanSystemViewModel(
    private val wanSystemRepository: WanSystemRepository
):ViewModel() {

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