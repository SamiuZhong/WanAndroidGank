package com.samiu.wangank.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.samiu.wangank.data.local.WanDatabase
import com.samiu.wangank.data.local.dao.ArticleDao
import com.samiu.wangank.data.local.dao.ArticleRemoteKeysDao
import com.samiu.wangank.data.remote.WanApiService
import com.samiu.wangank.model.ArticleDTO
import com.samiu.wangank.model.ArticleRemoteKeys
import com.samiu.wangank.utils.Constants
import javax.inject.Inject

/**
 * 首页文章列表
 * Paging3网络数据，Room本地数据
 *
 * @author samiu 2023/2/6
 * @email samiuzhong@outlook.com
 */
@OptIn(ExperimentalPagingApi::class)
class FrontArticleMediator(
    private val service: WanApiService,
    private val database: WanDatabase,
    private val articleDao: ArticleDao,
    private val remoteKeysDao: ArticleRemoteKeysDao
) : RemoteMediator<Int, ArticleDTO>() {

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, ArticleDTO>
    ): MediatorResult {
        try {
            val initialPage = Constants.Network.PAGER_INITIAL_PAGE
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: initialPage
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeysForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }
            }
            val response = service.getFrontArticles(page = currentPage)
            val endOfPaginationReached = response.data.datas.isEmpty()
            val articleList: List<ArticleDTO> = response.data.datas

            val prevPage = if (currentPage == initialPage) null else currentPage.minus(1)
            val nextPage = if (endOfPaginationReached) null else currentPage.plus(1)

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    articleDao.deleteAllImages()
                    remoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = articleList.map { article ->
                    ArticleRemoteKeys(
                        id = article.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                remoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                articleDao.addAllArticles(articles = articleList)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeysForFirstItem(
        state: PagingState<Int, ArticleDTO>
    ): ArticleRemoteKeys? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { article ->
            remoteKeysDao.getRemoteKeys(id = article.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, ArticleDTO>
    ): ArticleRemoteKeys? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { article ->
            remoteKeysDao.getRemoteKeys(id = article.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, ArticleDTO>
    ): ArticleRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }
}