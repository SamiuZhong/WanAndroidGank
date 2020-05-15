package com.samiu.host.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samiu.host.base.AppApplication
import com.samiu.host.global.IS_LOGIN
import com.samiu.host.global.WAN_ANDROID
import com.samiu.host.model.http.WanResult
import com.samiu.host.model.repository.WanLoginRepository
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/4/17
 */
class WanLoginViewModel(
    private val loginRepository: WanLoginRepository
) : ViewModel() {

    private val sharedPref = AppApplication.context.getSharedPreferences(
        WAN_ANDROID, Context.MODE_PRIVATE
    )

    private var isLogin = sharedPref.getBoolean(IS_LOGIN, false)

    fun login(userName: String, passWord: String) = viewModelScope.launch {
        val user = loginRepository.login(userName, passWord)
        if (user is WanResult.Success)
            with(sharedPref.edit()) {
                putBoolean(IS_LOGIN, true)
                commit()
            }
    }
}