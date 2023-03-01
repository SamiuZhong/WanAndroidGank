package com.samiu.wangank.data.repository

import androidx.paging.*
import com.samiu.wangank.data.local.WanDatabase
import com.samiu.wangank.data.paging.ArticleType
import com.samiu.wangank.data.paging.ArticleMediator
import com.samiu.wangank.data.paging.ProjectMediator
import com.samiu.wangank.data.remote.BannerList
import com.samiu.wangank.data.remote.ProTypeList
import com.samiu.wangank.data.remote.WanApiService
import com.samiu.wangank.model.ArticleDTO
import com.samiu.wangank.utils.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author samiu 2023/2/6
 * @email samiuzhong@outlook.com
 */
typealias ArticleFlow = Flow<PagingData<ArticleDTO>>

@Singleton
@OptIn(ExperimentalPagingApi::class)
class ArticleRepository @Inject constructor(
    private val service: WanApiService,
    private val database: WanDatabase,
) : BaseRepository() {

    /**
     * 获取banner数据
     */
    suspend fun getBanners(): BannerList {
        val response = safeRequest { service.getBanners() }
        return response.data
    }

    /**
     * 获取开源项目的分类
     */
    suspend fun getProTypes(): ProTypeList {
        val response = safeRequest { service.getProjectTypes() }
        return response.data
    }

    /**
     * 获取文章列表
     *
     * @param type 区分文章数据的来源
     */
    fun getArticles(type: ArticleType): ArticleFlow {
        val pagingSourceFactory = {
            when (type) {
                ArticleType.Front -> {
                    database.articleDao().getFrontArticles()
                }
                ArticleType.Square -> {
                    database.articleDao().getSquareArticles()
                }
            }
        }
        return Pager(
            config = PagingConfig(pageSize = Constants.Network.DEFAULT_PAGE_SIZE),
            remoteMediator = ArticleMediator(service, database, type),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    /**
     * 获取项目列表
     *
     * @param cid 项目分类
     */
    fun getProjects(cid: Int): ArticleFlow {
        val pagingSourceFactory = { database.articleDao().getArticlesWithCid(cid) }
        return Pager(
            config = PagingConfig(pageSize = Constants.Network.DEFAULT_PAGE_SIZE),
            remoteMediator = ProjectMediator(service, database, cid),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}