package com.samiu.wangank.ui.system

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samiu.wangank.bean.ArticleList
import com.samiu.wangank.bean.SystemParent
import com.samiu.wangank.bean.base.WanResult
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/3/6
 * @email samiuzhong@outlook.com
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