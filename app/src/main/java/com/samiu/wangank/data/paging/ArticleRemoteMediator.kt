package com.samiu.wangank.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.samiu.wangank.data.local.WanDatabase
import com.samiu.wangank.data.remote.WanApiService
import com.samiu.wangank.model.ArticleDTO
import com.samiu.wangank.model.ArticleRemoteKeys

/**
 * @author samiu 2022/11/28
 * @email samiuzhong@outlook.com
 */
@OptIn(ExperimentalPagingApi::class)
class ArticleRemoteMediator(
    private val apiService: WanApiService,
    private val database: WanDatabase,
    private val initialPage: Int
) : RemoteMediator<Int, ArticleDTO>() {

    companion object{
        private const val TAG = "ArticleRemoteMediator##";
    }

    private val articleDao = database.articleDao()
    private val remoteKeysDao = database.articleRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, ArticleDTO>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: initialPage
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            Log.d(TAG, "=================================================================================")
            Log.d(TAG, "load: current page is $currentPage")
            val response = apiService.getHomeArticles(currentPage)
            val endOfPaginationReached = response.data.over

            val prevPage = if (currentPage == initialPage) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    articleDao.deleteAllArticles()
                    remoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = response.data.datas.map { article ->
                    ArticleRemoteKeys(
                        id = article.sortId,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                Log.d(TAG, "load: add remote keys = $keys")
                Log.d(TAG, "load: add article = ${response.data}")
                remoteKeysDao.addAllRemoteKeys(keys)
                articleDao.addArticles(response.data.datas)
            }
            MediatorResult.Success(endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, ArticleDTO>
    ): ArticleRemoteKeys? {
        Log.d(TAG, "getRemoteKeyClosestToCurrentPosition: ")
        return state.anchorPosition?.let { position ->
            Log.d(TAG, "getRemoteKeyClosestToCurrentPosition: position = $position")
            state.closestItemToPosition(position)?.sortId?.let { id ->
                Log.d(TAG, "getRemoteKeyClosestToCurrentPosition: id = $id")
                remoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, ArticleDTO>
    ): ArticleRemoteKeys? {
        Log.d(TAG, "getRemoteKeyForFirstItem: ")
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { article ->
                Log.d(TAG, "getRemoteKeyForFirstItem: id = ${article.sortId}")
                remoteKeysDao.getRemoteKeys(id = article.sortId)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, ArticleDTO>
    ): ArticleRemoteKeys? {
        Log.d(TAG, "getRemoteKeyForLastItem: ")
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { article ->
            Log.d(TAG, "getRemoteKeyForLastItem: id = ${article.sortId}")
            remoteKeysDao.getRemoteKeys(id = article.sortId)
        }
    }
}