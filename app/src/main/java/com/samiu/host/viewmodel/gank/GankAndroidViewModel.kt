package com.samiu.host.viewmodel.gank

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.samiu.base.ui.BaseViewModel
import com.samiu.host.model.bean.gank.GankBean
import com.samiu.host.model.http.GankResult
import com.samiu.host.model.repository.gank.GankRepository
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/3/9
 */
class GankAndroidViewModel(
    private val gankRepository: GankRepository
) : BaseViewModel() {

    val mAndroidData = MutableLiveData<List<GankBean>>()

    fun getAndroid(page: Int) = viewModelScope.launch {
        val android = gankRepository.getAndroidData(page)
        if (android is GankResult.Success)
            mAndroidData.value = android.data
    }
}