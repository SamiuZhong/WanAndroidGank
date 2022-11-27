package com.samiu.wangank.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.samiu.wangank.data.paging.HomeArticlePagingSource
import com.samiu.wangank.data.remote.WanApiService
import com.samiu.wangank.model.ArticleDTO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author samiu 2022/11/27
 * @email samiuzhong@outlook.com
 */
class HomeRepository @Inject constructor(
    private val apiService: WanApiService
) {

    private val pageSize = 20
    private val initialPage = 0

    fun getHomeArticles(): Flow<PagingData<ArticleDTO>> {
        return Pager(config = PagingConfig(pageSize), pagingSourceFactory = {
            HomeArticlePagingSource(apiService, initialPage)
        }).flow
    }
}