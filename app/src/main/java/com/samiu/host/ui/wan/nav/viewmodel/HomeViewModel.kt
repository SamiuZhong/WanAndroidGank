package com.samiu.host.ui.wan.nav.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.samiu.base.ui.BaseViewModel
import com.samiu.host.model.api.WanResult
import com.samiu.host.model.bean.wan.Banner
import com.samiu.host.model.repository.HomeRepository

/**
 * @author Samiu 2020/3/3
 */
class HomeViewModel(
    private val homeRepository: HomeRepository
) : BaseViewModel() {

    val mBanners: LiveData<List<Banner>> = liveData {
        kotlin.runCatching {
            val data = homeRepository.getBanners()
            if (data is WanResult.Success) emit(data.data)
        }
    }
}