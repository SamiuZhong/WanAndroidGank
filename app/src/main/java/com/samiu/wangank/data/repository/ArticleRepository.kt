package com.samiu.wangank.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.samiu.wangank.data.local.WanDatabase
import com.samiu.wangank.data.local.dao.ArticleDao
import com.samiu.wangank.data.local.dao.ArticleRemoteKeysDao
import com.samiu.wangank.data.paging.FrontArticleMediator
import com.samiu.wangank.data.remote.WanApiService
import com.samiu.wangank.model.ArticleDTO
import com.samiu.wangank.model.ArticleRemoteKeys
import com.samiu.wangank.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author samiu 2023/2/6
 * @email samiuzhong@outlook.com
 */
@OptIn(ExperimentalPagingApi::class)
class ArticleRepository @Inject constructor(
    private val service: WanApiService,
    private val database: WanDatabase,
    private val articleDao: ArticleDao,
    private val remoteKeysDao: ArticleRemoteKeysDao
) {

    /**
     * 获取首页文章列表
     */
    fun getFrontArticles(): Flow<PagingData<ArticleDTO>> {
        val sourceFactory = { articleDao.getAllArticles() }
        return Pager(
            config = PagingConfig(pageSize = Constants.Network.PAGER_SIZE),
            remoteMediator = FrontArticleMediator(
                service, database, articleDao, remoteKeysDao
            ),
            pagingSourceFactory = sourceFactory
        ).flow
    }
}