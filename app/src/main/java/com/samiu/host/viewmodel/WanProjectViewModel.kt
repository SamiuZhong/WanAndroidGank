package com.samiu.host.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samiu.host.model.bean.Article
import com.samiu.host.model.bean.SystemParent
import com.samiu.host.model.http.WanResult
import com.samiu.host.model.repository.WanProjectRepository
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/3/5
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanProjectViewModel(
    private val wanProjectRepository: WanProjectRepository
) : ViewModel() {

    val mProjectType = MutableLiveData<List<SystemParent>>()
    val mAllProjects = MutableLiveData<List<Article>>()

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