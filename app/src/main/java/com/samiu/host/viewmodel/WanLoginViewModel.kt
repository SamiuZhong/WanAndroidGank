package com.samiu.host.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samiu.host.global.IS_LOGIN
import com.samiu.host.global.USER_NAME
import com.samiu.host.model.http.WanResult
import com.samiu.host.model.repository.WanLoginRepository
import com.samiu.host.util.Preference
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/4/17
 */
class WanLoginViewModel(
    private val loginRepository: WanLoginRepository
) : ViewModel() {

    private var isLogin by Preference(IS_LOGIN, false)
    private var nickname by Preference(USER_NAME, "")
    val loginSuccess = MutableLiveData<Boolean>()

    fun login(userName: String, passWord: String) = viewModelScope.launch {
        val user = loginRepository.login(userName, passWord)
        if (user is WanResult.Success) {
            loginSuccess.value = true
            isLogin = true
            nickname = user.data.nickname
        } else loginSuccess.value = false
    }
}