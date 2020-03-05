package com.samiu.host.viewmodel.wan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.samiu.base.ui.BaseViewModel
import com.samiu.host.model.bean.wan.Article
import com.samiu.host.model.bean.wan.ArticleList
import com.samiu.host.model.bean.wan.SystemParent
import com.samiu.host.model.http.WanResult
import com.samiu.host.model.repository.wan.ProjectRepository
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/3/5
 */
class ProjectViewModel(
    private val projectRepository: ProjectRepository
) : BaseViewModel() {

    val recentProjects = MutableLiveData<List<Article>>()
    val projectType = MutableLiveData<List<SystemParent>>()
    val allProjects = MutableLiveData<ArticleList>()

    fun getRecentProjects(page: Int) = viewModelScope.launch {
        val projects = projectRepository.getRecentProjects(page)
        if (projects is WanResult.Success)
            recentProjects.value = projects.data.datas
    }

    fun getProjectType() = viewModelScope.launch {
        val type = projectRepository.getProjectType()
        if (type is WanResult.Success)
            projectType.value = type.data
    }

    fun getAllProjects(page: Int, cid: Int) = viewModelScope.launch {
        val projects = projectRepository.getAllProjects(page, cid)
        if (projects is WanResult.Success)
            allProjects.value = projects.data
    }
}