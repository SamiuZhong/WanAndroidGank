package com.samiu.host.viewmodel.gank

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.samiu.base.ui.BaseViewModel
import com.samiu.host.model.bean.gank.GankBean
import com.samiu.host.model.http.gank.GankResult
import com.samiu.host.model.repository.gank.GankRepository
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/3/9
 */
class GankFrontViewModel(
    private val gankRepository: GankRepository
) : BaseViewModel() {

    val mFrontData = MutableLiveData<List<GankBean>>()

    fun getFront(page: Int) = viewModelScope.launch {
        val android = gankRepository.getFrontData(page)
        if (android is GankResult.Success)
            mFrontData.value = android.data
    }
}