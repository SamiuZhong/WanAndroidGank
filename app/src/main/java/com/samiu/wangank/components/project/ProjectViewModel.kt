package com.samiu.wangank.components.project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.samiu.wangank.data.remote.ProTypeList
import com.samiu.wangank.data.repository.ArticleFlow
import com.samiu.wangank.data.repository.ArticleRepository
import com.samiu.wangank.model.ArticleDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author samiu 2023/2/28
 * @email samiuzhong@outlook.com
 */
@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val repository: ArticleRepository
) : ViewModel() {

    private val _typesFlow = MutableSharedFlow<ProTypeList>()
    val typesFlow = _typesFlow.asSharedFlow()

    fun getProTypes() = viewModelScope.launch {
        val data = repository.getProTypes()
        _typesFlow.emit(data)
    }

    fun getProjects(cid: Int): ArticleFlow {
        return repository.getProjects(cid).cachedIn(viewModelScope)
    }
}