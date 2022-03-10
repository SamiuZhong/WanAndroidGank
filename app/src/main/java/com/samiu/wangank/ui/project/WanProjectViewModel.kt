package com.samiu.wangank.ui.project

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samiu.wangank.bean.Article
import com.samiu.wangank.bean.SystemParent
import com.samiu.wangank.bean.base.WanResult
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/3/5
 * @email samiuzhong@outlook.com
 */
class WanProjectViewModel(
    private val wanProjectRepository: WanProjectRepository
) : ViewModel() {

    val mProjectType = MutableLiveData<List<SystemParent>>()
    val mAllProjects = MutableLiveData<List<Article>>()

    fun getProjectType() = viewModelScope.launch {
        val type = wanProjectRepository.getProjectType()
        if (type is WanResult.Success) {
            type.data?.let {
                mProjectType.value = it
            }
        }
    }

    fun getAllProjects(page: Int, cid: Int) = viewModelScope.launch {
        val projects = wanProjectRepository.getAllProjects(page, cid)
        if (projects is WanResult.Success)
            mAllProjects.value = projects.data?.datas
    }
}