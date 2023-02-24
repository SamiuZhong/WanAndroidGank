package com.samiu.wangank.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.samiu.wangank.data.local.WanDatabase
import com.samiu.wangank.data.remote.ArticleRes
import com.samiu.wangank.data.remote.WanApiService
import com.samiu.wangank.model.ArticleDTO
import com.samiu.wangank.model.ArticleRemoteKeys
import com.samiu.wangank.model.PageWrapper
import com.samiu.wangank.model.WanResponse
import com.samiu.wangank.utils.Constants
import okio.IOException
import retrofit2.HttpException

/**
 * 文章列表，多接口通用
 * Paging3网络数据，Room本地数据
 *
 * @author samiu 2023/2/6
 * @email samiuzhong@outlook.com
 */
@OptIn(ExperimentalPagingApi::class)
class FrontArticleMediator(
    private val service: WanApiService,
    private val database: WanDatabase,
    private val type: ArticleType
) : RemoteMediator<Int, ArticleDTO>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, ArticleDTO>
    ): MediatorResult {
        val startIndex = Constants.Network.STARTING_PAGE_INDEX
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextPage?.minus(1) ?: startIndex
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevPage
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextPage
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }
        try {
            val response: ArticleRes = when (type) {
                ArticleType.Front -> {
                    service.getFrontArticles(page, Constants.Network.DEFAULT_PAGE_SIZE)
                }
                ArticleType.Square -> {
                    service.getSquareArticles(page, Constants.Network.DEFAULT_PAGE_SIZE)
                }
            }
            val articles = response.data.datas
            val endOfPaginationReached = articles.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    when (type) {
                        ArticleType.Front -> {
                            database.articleDao().clearFrontArticles()
                            database.articleRemoteKeysDao().clearFrontKeys()
                        }
                        ArticleType.Square -> {
                            database.articleDao().clearSquareArticles()
                            database.articleRemoteKeysDao().clearSquareKeys()
                        }
                    }
                }
                val prevKey = if (page == startIndex) null else page.minus(1)
                val nextKey = if (endOfPaginationReached) null else page.plus(1)
                val keys = articles.map { article ->
                    ArticleRemoteKeys(
                        articleId = article.articleId,
                        prevPage = prevKey,
                        nextPage = nextKey,
                        chapterName = article.chapterName
                    )
                }
                database.articleDao().insertAll(articles)
                database.articleRemoteKeysDao().insertAll(keys)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, ArticleDTO>): ArticleRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { article ->
                database.articleRemoteKeysDao().findRemoteKeyByArticleId(article.articleId)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, ArticleDTO>): ArticleRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { article ->
                database.articleRemoteKeysDao().findRemoteKeyByArticleId(article.articleId)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, ArticleDTO>
    ): ArticleRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.articleId?.let { articleId ->
                database.articleRemoteKeysDao().findRemoteKeyByArticleId(articleId)
            }
        }
    }
}