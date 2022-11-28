package com.samiu.wangank.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.samiu.wangank.data.local.WanDatabase
import com.samiu.wangank.data.paging.ArticleRemoteMediator
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
@OptIn(ExperimentalPagingApi::class)
class HomeRepository @Inject constructor(
    private val apiService: WanApiService,
    private val database: WanDatabase
) {

    private val pageSize = 20
    private val initialPage = 0

    /**
     * 获取首页文章列表
     */
    fun getHomeArticles(): Flow<PagingData<ArticleDTO>> {
        val pagingSourceFactory = { database.articleDao().getAllArticles() }
        return Pager(
            config = PagingConfig(pageSize),
            remoteMediator = ArticleRemoteMediator(
                apiService, database,initialPage
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow

//        return Pager(config = PagingConfig(pageSize), pagingSourceFactory = {
//            HomeArticlePagingSource(apiService, initialPage)
//        }).flow
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