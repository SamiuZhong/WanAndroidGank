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

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, ArticleDTO>
    ): MediatorResult {
        val initialPage = Constants.Network.PAGER_INITIAL_PAGE
        val currentPage: Int =
            when (val pageKeyData = getKeyPageData(initialPage, loadType, state)) {
                is MediatorResult.Success -> {
                    return pageKeyData
                }
                else -> {
                    pageKeyData as Int
                }
            }
        try {
            val response = service.getFrontArticles(currentPage)
            val articleList: List<ArticleDTO> = response.data.datas
            val endOfList = articleList.isEmpty()

            val prevPage = if (currentPage == initialPage) null else currentPage.minus(1)
            val nextPage = if (endOfList) null else currentPage.plus(1)

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    articleDao.clearAll()
                    remoteKeysDao.clearAll()
                }
                val keys = articleList.map { article ->
                    ArticleRemoteKeys(article.id, prevPage, nextPage)
                }
                remoteKeysDao.insertRemote(keys)
                articleDao.insert(articleList)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfList)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getKeyPageData(
        initialPage: Int, loadType: LoadType, state: PagingState<Int, ArticleDTO>
    ): Any {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRefreshRemoteKey(state)
                remoteKeys?.nextPage?.minus(1) ?: initialPage
            }
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(
                    endOfPaginationReached = false
                )
                prevPage
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                nextPage
            }
        }
    }

    private suspend fun getFirstRemoteKey(
        state: PagingState<Int, ArticleDTO>
    ): ArticleRemoteKeys? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { article ->
            remoteKeysDao.getRemoteKeys(article.id)
        }
    }

    private suspend fun getLastRemoteKey(
        state: PagingState<Int, ArticleDTO>
    ): ArticleRemoteKeys? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { article ->
            remoteKeysDao.getRemoteKeys(article.id)
        }
    }

    private suspend fun getRefreshRemoteKey(
        state: PagingState<Int, ArticleDTO>
    ): ArticleRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteKeysDao.getRemoteKeys(id)
            }
        }
    }
}