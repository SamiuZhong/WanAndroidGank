package com.samiu.host.viewmodel.wan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.samiu.base.ui.BaseViewModel
import com.samiu.host.model.bean.wan.Article
import com.samiu.host.model.bean.wan.SystemParent
import com.samiu.host.model.http.wan.WanResult
import com.samiu.host.model.repository.wan.WanProjectRepository
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/3/5
 */
class WanProjectViewModel(
    private val wanProjectRepository: WanProjectRepository
) : BaseViewModel() {

    val mRecentProjects = MutableLiveData<List<Article>>()
    val mProjectType = MutableLiveData<List<SystemParent>>()
    val mAllProjects = MutableLiveData<List<Article>>()

    fun getRecentProjects(page: Int) = viewModelScope.launch {
        val projects = wanProjectRepository.getRecentProjects(page)
        if (projects is WanResult.Success)
            mRecentProjects.value = projects.data.datas
    }

    fun getProjectType() = viewModelScope.launch {
        val type = wanProjectRepository.getProjectType()
        if (type is WanResult.Success)
            mProjectType.value = type.data
    }

    fun getAllProjects(page: Int, cid: Int) = viewModelScope.launch {
        val projects = wanProjectRepository.getAllProjects(page, cid)
        if (projects is WanResult.Success)
            mAllProjects.value = projects.data.datas
    }
}