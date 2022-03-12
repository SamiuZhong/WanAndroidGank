package com.samiu.wangank.ui.mine.personal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.samiu.wangank.global.IS_LOGIN
import com.samiu.wangank.ui.mine.WanMineRepository
import com.samiu.wangank.util.Preference
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/5/20
 * @email samiuzhong@outlook.com
 */
class WanPersonalViewModel(
    private val mineRepository: WanMineRepository
) : ViewModel() {

    private var isLogin by Preference(IS_LOGIN, false)

    val articlePagingList = mineRepository.getArticlePaging().cachedIn(viewModelScope)

    fun logout() = viewModelScope.launch {
        mineRepository.logout()
        isLogin = false
    }
}