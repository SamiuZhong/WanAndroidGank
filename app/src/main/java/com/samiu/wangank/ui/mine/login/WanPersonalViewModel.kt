package com.samiu.wangank.ui.mine.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samiu.wangank.global.IS_LOGIN
import com.samiu.wangank.bean.Article
import com.samiu.wangank.bean.base.WanResult
import com.samiu.wangank.util.Preference
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/5/20
 * @email samiuzhong@outlook.com
 */
class WanPersonalViewModel(
    private val collectionRepository: WanCollectionRepository,
    private val loginRepository: WanMineRepository
) : ViewModel() {

    val mCollections = MutableLiveData<List<Article>>()
    private var isLogin by Preference(IS_LOGIN, false)

    fun getCollections(page: Int) = viewModelScope.launch {
        val articleList = collectionRepository.getCollections(page)
        if (articleList is WanResult.Success)
            mCollections.value = articleList.data?.datas
    }

    fun logout() = viewModelScope.launch {
        loginRepository.logout()
        isLogin = false
    }
}