package com.samiu.wangank.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samiu.wangank.global.IS_LOGIN
import com.samiu.wangank.global.USER_NAME
import com.samiu.wangank.bean.base.WanResult
import com.samiu.wangank.util.Preference
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/4/17
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
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