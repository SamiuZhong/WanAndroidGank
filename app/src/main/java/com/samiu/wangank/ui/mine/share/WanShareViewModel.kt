package com.samiu.wangank.ui.mine.share

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samiu.wangank.bean.base.WanResult
import com.samiu.wangank.ui.mine.login.WanMineRepository
import kotlinx.coroutines.launch

class WanShareViewModel(
    private val shareRepository: WanMineRepository
) : ViewModel() {

    val shareSuccess = MutableLiveData<Boolean>()
    val shareMsg = MutableLiveData<String>()


    fun shareArticle(title: String, link: String) = viewModelScope.launch {
        val share = shareRepository.share(title, link)
        if (share is WanResult.Success) {
            shareSuccess.value = true
        } else if (share is WanResult.Error) {
            shareMsg.value = share.msg
        }
    }
}