package com.samiu.wangank.data.repository

import androidx.paging.*
import com.samiu.wangank.data.local.WanDatabase
import com.samiu.wangank.data.paging.ArticleType
import com.samiu.wangank.data.paging.FrontArticleMediator
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
@Singleton
@OptIn(ExperimentalPagingApi::class)
class ArticleRepository @Inject constructor(
    private val service: WanApiService,
    private val database: WanDatabase,
) {

    /**
     * 获取文章列表
     *
     * @param type 区分文章数据的来源
     */
    fun getArticles(type: ArticleType): Flow<PagingData<ArticleDTO>> {
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
            remoteMediator = FrontArticleMediator(service, database, type),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}