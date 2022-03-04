package com.samiu.wangank.ui.system

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samiu.wangank.bean.Article
import com.samiu.wangank.bean.base.WanResult
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/3/6
 * @email samiuzhong@outlook.com
 */
class SystemDisplayViewModel(
    private val systemRepository: WanSystemRepository
) : ViewModel() {

    val mSystemArticles = MutableLiveData<List<Article>>()

    fun getSystemArticles(page: Int, cid: Int) = viewModelScope.launch {
        val data = systemRepository.getSystemDetail(page, cid)
        if (data is WanResult.Success) {
            mSystemArticles.value = data.data.datas
        }
    }
}