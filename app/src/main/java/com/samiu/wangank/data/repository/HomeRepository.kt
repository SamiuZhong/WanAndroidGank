package com.samiu.wangank.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.samiu.wangank.data.paging.ArticlePagingSource
import com.samiu.wangank.data.remote.WanApiService
import com.samiu.wangank.model.ArticleDTO
import com.samiu.wangank.model.BannerDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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

    /**
     * 获取首页文章列表
     */
    fun getHomeArticles(): Flow<PagingData<ArticleDTO>> {
        return Pager(config = PagingConfig(pageSize), pagingSourceFactory = {
            ArticlePagingSource(apiService, initialPage)
        }).flow
    }

    /**
     * 获取首页banner
     */
    fun getBanners(): Flow<List<BannerDTO>> = flow {
        val response = apiService.getBanners()
        if (response.errorCode == 0 && response.data.isNotEmpty()) {
            emit(response.data)
        }
    }.flowOn(Dispatchers.IO)
}